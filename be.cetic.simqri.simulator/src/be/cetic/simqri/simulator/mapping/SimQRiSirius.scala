/**
 * SimQRi Project.
	 This module implements the transformation between the metamodel sent from the interface and the
   SimQRi model in OscaR-DES
 * @author FK & Gustavo Ospina
 */

package be.cetic.simqri.simulator.mapping

import oscar.des.engine.Model

import oscar.des.flow.core.{DiscreteChoice, Putable, Fetchable}
import oscar.des.flow.lib._
import oscar.des.flow.modeling._
import oscar.des.logger.Logger
import oscar.des.montecarlo.DataSampling

import scala.collection.{mutable, SortedSet}
import scala.collection.mutable
import scala.collection.JavaConversions._
import be.cetic.simqri.metamodel.OrderType
import be.cetic.simqri.metamodel.Output
import be.cetic.simqri.metamodel.OutputType
import be.cetic.simqri.metamodel.Component
import be.cetic.simqri.metamodel.Flow
import be.cetic.simqri.metamodel.Scalar
import be.cetic.simqri.metamodel.impl.ScalarImpl
import be.cetic.simqri.metamodel.StorageOutputFlow
import be.cetic.simqri.metamodel.ProcessOutputFlow

class SimQRiSirius(duration : Float, verbose : Boolean, sqlogger : Logger[String], mcSim : Boolean) extends FactorySimulationHelper {
  
  val m = new Model
  private val verboseFunc = if (verbose) sqlogger.loggerFunc else null
  var runTime = 0L
  val factoryModel = if (mcSim) new FactoryModel(null) else new FactoryModel(verboseFunc)
  
  // Main function for create the model (with OscaR-DES-Flow)
  def fillModelWithSiriusData(model : be.cetic.simqri.metamodel.Model) {
    
    // Auxiliary function to obtain the list of inputs or failures
    def getStorageFlowInfo(storInfo : mutable.HashMap[Int, Storage],
                           connInfo : List[(() => Int, Int)]) : List[(() => Int, Fetchable)] = connInfo match {
      case Nil => Nil
      case (dur, storId)::ls =>
        val storeInf = storInfo.get(storId)
        storeInf match {
          case Some(stor) => (dur, stor)::getStorageFlowInfo(storInfo, ls)
          case None => getStorageFlowInfo(storInfo, ls)
        }
    }
    
    // Auxiliary function to obtain the list of outputs
    // it will create Delay objects if the delay data is not zero
    def getStorageFlowOutputInfo(storInfo : mutable.HashMap[Int, Storage],
                                 connInfo : List[(() => Int, Option[()=>Double], Int)]) : List[(() => Int, Putable)] = connInfo match {
      case Nil => Nil
      case (dur, delayOpt, storId)::ls =>
        val storeInf = storInfo.get(storId)
        storeInf match {
          case Some(stor) =>
            delayOpt match {
              case None => (dur, stor)::getStorageFlowOutputInfo(storInfo, ls)
              case Some(delay) =>
                val newDelay = new Delay(stor, m, delay)
                (dur, newDelay)::getStorageFlowOutputInfo(storInfo, ls)
            }
          case None => getStorageFlowOutputInfo(storInfo, ls)
        }
    }
    
    // NEW : Auxiliary function to obtain the place of a Storage object in a list of components from the 'sirius' model
    def getIdStorage(components : List[Component], storage : be.cetic.simqri.metamodel.Storage) : Int = {
      for(c <- components) {
        if(c.isInstanceOf[be.cetic.simqri.metamodel.Storage]) {
          val s = c.asInstanceOf[be.cetic.simqri.metamodel.Storage]
          if(s.equals(storage))
            components.indexOf(c)
        }
      }
      -1
    }
    
    // NEW : Auxiliary function to obtain the output port(s) of a process
    def getOutputPorts(process : be.cetic.simqri.metamodel.Process) : List[Output] = {
      if(process.isInstanceOf[be.cetic.simqri.metamodel.BatchProcess]) {
        val batchProcess = process.asInstanceOf[be.cetic.simqri.metamodel.BatchProcess]
        val ports = batchProcess.getOutputs.toList
        ports
        
      }
      else if (process.isInstanceOf[be.cetic.simqri.metamodel.ConveyorBelt]) {
        val conveyorBelt = process.asInstanceOf[be.cetic.simqri.metamodel.ConveyorBelt]
        val port = List(conveyorBelt.getOutput)
        port
      }
      List()
    }
    
    // Attributes
    // val standardItemClass = zeroItemClass
    val attributes = attributeDefinitions("rawQuantity")
    val rawBatch = attributes.getN(0)
    val ph = new ProbabilityHandler()
    
    // Retrieve Processes (BP & CB), Storages & Suppliers from the 'sirius' model
    val components = model.getComponent.toList
    // Retrieve Flows (SOF & POF) from the 'sirius' model
    val flows = model.getFlow.toList
    
    // We proceed as follows : the first step is a loop on the components
    // for identifying the storages and the part suppliers of the model.
    // those elements will be put in a map so they can be accessed by these ids
    
    // Notice that the loops are implemented in a functional style, using foreach
    var mapStorages = new mutable.HashMap[Int, Storage]()
    var mapPartSuppliers = new mutable.HashMap[Int, (String, () => Double, Int)]()
    var activableProcesses = new mutable.MutableList[ActivableProcess]()
    
    var mapLinkInfos = new mutable.HashMap[Int, (mutable.MutableList[(() => Int, Int)], // ID_PROC => (<INPUTS, ID_STORAGE>, <OUTPUTS, ID_STORAGE>, <FAILS, ID_STORAGE>)
                                                 mutable.MutableList[(() => Int, Option[() => Double], Int)],
                                                 mutable.MutableList[(() => Int, Option[() => Double], Int)])]()
    // The handeling of a new kind of outputs will require a new mutable list in the tuple above
                                                 
    for(c <- components) {
      if(c.isInstanceOf[be.cetic.simqri.metamodel.Storage]) {
        val storage = c.asInstanceOf[be.cetic.simqri.metamodel.Storage]
        val newStorage = factoryModel.fIFOStorage(storage.getSize, 
                                                  List((storage.getInitialContent.asInstanceOf[Int], attributeSet(SortedSet(rawBatch), attributes).itemClass)), 
                                                  storage.getName, 
                                                  storage.isOverflow())
        mapStorages += (components.indexOf(c) -> newStorage)
      }
      else if(c.isInstanceOf[be.cetic.simqri.metamodel.Supplier]) {
        val supplier = c.asInstanceOf[be.cetic.simqri.metamodel.Supplier]
        val triplet = (supplier.getName,
                       ph.getNonNegativeDoubleFunc(supplier.getSupplierDelay), 
                       supplier.getDeliveredPercentage.intValue())
        mapPartSuppliers += (components.indexOf(c) -> triplet)
      }
      // NEW : we fill a map which will contain informations about processes inputs and
      // all kinds of outputs that will be used to create process objects in the oscar-des simulator
      else if(c.isInstanceOf[be.cetic.simqri.metamodel.Process]) {
        val process = c.asInstanceOf[be.cetic.simqri.metamodel.Process]
        val idProc = components.indexOf(c)
        var inputs = mutable.MutableList[(() => Int, Int)]()
        var outputs = mutable.MutableList[(() => Int, Option[() => Double], Int)]()
        var fails = mutable.MutableList[(() => Int, Option[() => Double], Int)]()
        for(flow <- flows) {
          if(flow.isInstanceOf[StorageOutputFlow]) {
            val sof = flow.asInstanceOf[StorageOutputFlow]
            if(sof.getDestination.equals(process))
              inputs.+=((ph.getNonNegativeIntFunc(sof.getQuantity), getIdStorage(components, sof.getSource)))
          }
          else if(flow.isInstanceOf[ProcessOutputFlow]) {
            val pof = flow.asInstanceOf[ProcessOutputFlow]
            val listOfOutputPorts = getOutputPorts(process)
            var pofLinkedToProcess = false
            for(port <- listOfOutputPorts) {
              if(port.equals(pof.getSource))
                  pofLinkedToProcess = true
            }
            if(pofLinkedToProcess) {
              val delay = ph.getDoubleFunc(pof.getProcessOutputFlowDelay)
              val out = (ph.getNonNegativeIntFunc(pof.getQuantity), 
                         if(delay().doubleValue() == 0F) None else Some(delay), 
                         getIdStorage(components, pof.getDestination))
              
              val outputPort = pof.getSource
              outputPort.getType match {
                case OutputType.SUCCESS => outputs.+=(out)
                case OutputType.FAILURE => fails.+=(out)
                case _ => outputs.+=(out)
                // TODO : Other output types behaviours 
              }
            }
          }
        }
        mapLinkInfos.+=(idProc -> (inputs, outputs, fails))
      }
    }
    
    // The second loop is done on the OrderOnStockThreshold links and will allow to create 
    // some objects of the model which have linking information associated to them
    
    val oosts = model.getOrderOnStockThreshold.toList;
    
    for (oost <- oosts) {
      var storageDest = oost.getStorage
      // Declaration of the oscar-des storage (will definitely be updated in the following loop)
       var optionStorage = mapStorages.get(0) 
      // Retrieve the storage from oscar-des correspinding to the storage dest of the oost from metamodel
      for(c <- components) {
        if(c.isInstanceOf[be.cetic.simqri.metamodel.Storage] && c.equals(storageDest)) {
          optionStorage = mapStorages.get(components.indexOf(c))
          
        }
      }
      // Transform the Option[Storage] to Storage (need for the upcoming singleBatchProcess creation)
      var oStorage = optionStorage.get
      
      var supplierSrc = oost.getSupplier;
      // Declaration of the mapped supplier (will definitely be updated in the following loop)
      var partSuppInfo = mapPartSuppliers.get(0) 
      // Retrieve the right mapped supplier from oscar-des correspinding to the supplier src of the oost from metamodel
      for(c <- components) {
        if(c.isInstanceOf[be.cetic.simqri.metamodel.Supplier] && c.equals(supplierSrc)) {
          partSuppInfo = mapPartSuppliers.get(components.indexOf(c))
        }
      }
      
      
      val orderQtyFunc = if(oost.getOrderType == OrderType.FIXED) 
                            (x:Int) => oost.getOrderQuantity.toInt
                         else
                            (x:Int) => oStorage.maxCapacity - oStorage.contentSize
      val activateFunc = (x : Int) => 1
      
      // We create now the single batch process corresponding to the part supplier
      val partSupp = factoryModel.singleBatchProcess(partSuppInfo.get._2,
                                                     Array(), 
                                                     Array(( () => partSuppInfo.get._3*orderQtyFunc(oost.getThreshold())/100, oStorage)), 
                                                     identity, 
                                                     oost.getName,
                                                     partSuppInfo.get._1)
      val period = oost.getPeriod.toFloat                                               
      val newOost = factoryModel.onLowerThreshold(oStorage, partSupp, oost.getThreshold, activateFunc, period, oost.getName)
      activableProcesses +:= partSupp
    }
    
    // The third loop is done on the elements to create and add all the process elements in the model.
    for(c <- components) {
      if(c.isInstanceOf[be.cetic.simqri.metamodel.BatchProcess]) {
        val batchProcess = c.asInstanceOf[be.cetic.simqri.metamodel.BatchProcess]
        val numLines = batchProcess.getNumberOfLines
        val perSuc = batchProcess.getPercentageOfSuccess
        val duration = ph.getNonNegativeDoubleFunc(batchProcess.getDuration)
        val linkInfos = mapLinkInfos.get(components.indexOf(c))
        val storageFlowInfo = getStorageFlowInfo(mapStorages, linkInfos.get._1.toList).toArray
        val storageFlowOutputInfo = getStorageFlowOutputInfo(mapStorages, linkInfos.get._2.toList).toArray
        val getStorageFlowOutputFailsInfo = getStorageFlowOutputInfo(mapStorages, linkInfos.get._3.toList).toArray
        if(numLines==1 && perSuc==100) {
          val newSBP = factoryModel.singleBatchProcess(duration, 
                                                       storageFlowInfo, 
                                                       storageFlowOutputInfo, 
                                                       identity, 
                                                       batchProcess.getName)
          activableProcesses +:= newSBP
        }
        else if(numLines!=1 && perSuc==100) {
          val newBP = factoryModel.batchProcess(batchProcess.getNumberOfLines,
                                                duration, 
                                                storageFlowInfo, 
                                                storageFlowOutputInfo, 
                                                batchProcess.getName,
                                                identity)
          activableProcesses +:= newBP
        }
        else if(numLines==1 && perSuc!=100) {
          val portChoices = List((0, perSuc.toDouble), (1, 1D-perSuc))
          val newFSBP = factoryModel.splittingSingleBatchProcess(duration, 
                                                                 storageFlowInfo, 
                                                                 Array(storageFlowOutputInfo, getStorageFlowOutputFailsInfo),
                                                                 outputValue(DiscreteChoice(portChoices)), 
                                                                 batchProcess.getName)
          activableProcesses +:= newFSBP                                                     
        }
        else if(numLines!=1 && perSuc!=100) {
          val portChoices = List((0, perSuc.toDouble), (1, 1D-perSuc))
          val newFBP = factoryModel.splittingBatchProcess(numLines, 
                                                          duration,
                                                          storageFlowInfo, 
                                                          Array(storageFlowOutputInfo, getStorageFlowOutputFailsInfo),
                                                          batchProcess.getName,
                                                          outputValue(DiscreteChoice(portChoices)))
          activableProcesses +:= newFBP                                                
        }
      }
      else if(c.isInstanceOf[be.cetic.simqri.metamodel.ConveyorBelt]) {
        val conveyorBelt = c.asInstanceOf[be.cetic.simqri.metamodel.ConveyorBelt]
        val duration = ph.getNonNegativeDoubleFunc(conveyorBelt.getDuration)
        val linkInfos = mapLinkInfos.get(components.indexOf(c))
        val storageFlowInfo = getStorageFlowInfo(mapStorages, linkInfos.get._1.toList).toArray
        val storageFlowOutputInfo = getStorageFlowOutputInfo(mapStorages, linkInfos.get._2.toList).toArray
        val newCBP = factoryModel.conveyorBeltProcess(duration, 
                                                      conveyorBelt.getMinimalSeparationBetweenBatches, 
                                                      storageFlowInfo, 
                                                      storageFlowOutputInfo, 
                                                      identity, 
                                                      conveyorBelt.getName)
        activableProcesses +:= newCBP
      }
    }
    
     // The final loop is on the probes. We will parse and add them to the probes list
    var probesList : List[(String,Expression)] = Nil
    val probeParser = ListenerParser.apply(mapStorages.values, activableProcesses)
    // The model is now complete! We can now simulate it.
    for(query <- model.getQuery) {
      probeParser.apply(query.getValue) match { // probesList :+= (s"${query.getName} $query.getType", boolExpr) add a type to probes ?
        case BooleanExpressionResult(boolExpr) =>
          probesList :+= (s"${query.getName}", boolExpr)
        case DoubleExpressionResult(dblExpr) =>
          probesList :+= (s"${query.getName}", dblExpr)
        case BoolHistoryExpressionResult(boolHistExpr) =>
          probesList :+= (s"${query.getName}", boolHistExpr)
        case DoubleHistoryExpressionResult(dblHistExpr) =>
          probesList :+= (s"${query.getName}", dblHistExpr)
        case ParsingError(errStr) =>
          sqlogger.log("rawinfo", s"The probe '${query.getName}' cannot be parsed. This is the error : $errStr")
          println("The probe '${query.getName}' cannot be parsed. This is the error : $errStr")
        case _ =>
      }
    }
    factoryModel.setQueries(probesList)
    
    // This epilogue loop is just here to check the good working of this service...
    println("-----------------------------------------------------------------------")
    println("PROCESSES")
    for(a <- factoryModel.getProcesses)
      println(a.name)
    println("-----------------------------------------------------------------------")
    println("STORAGES")
    for(b <- factoryModel.getStorages)
      println(b.name)
    println("-----------------------------------------------------------------------")
    println("QUERIES")
    for(c <- factoryModel.queries)
      println(c._1.toString()+' '+c._2.toString())
    println("-----------------------------------------------------------------------") 
  }
  
}