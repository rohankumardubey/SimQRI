/**
 */
package be.cetic.simqri.metamodel.provider;

import be.cetic.simqri.metamodel.util.MetamodelAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MetamodelItemProviderAdapterFactory extends MetamodelAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetamodelItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Model} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelItemProvider modelItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Model}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createModelAdapter() {
		if (modelItemProvider == null) {
			modelItemProvider = new ModelItemProvider(this);
		}

		return modelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Query} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QueryItemProvider queryItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Query}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createQueryAdapter() {
		if (queryItemProvider == null) {
			queryItemProvider = new QueryItemProvider(this);
		}

		return queryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Storage} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StorageItemProvider storageItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Storage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStorageAdapter() {
		if (storageItemProvider == null) {
			storageItemProvider = new StorageItemProvider(this);
		}

		return storageItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Supplier} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SupplierItemProvider supplierItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Supplier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSupplierAdapter() {
		if (supplierItemProvider == null) {
			supplierItemProvider = new SupplierItemProvider(this);
		}

		return supplierItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.ConveyorBelt} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConveyorBeltItemProvider conveyorBeltItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.ConveyorBelt}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConveyorBeltAdapter() {
		if (conveyorBeltItemProvider == null) {
			conveyorBeltItemProvider = new ConveyorBeltItemProvider(this);
		}

		return conveyorBeltItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.BatchProcess} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BatchProcessItemProvider batchProcessItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.BatchProcess}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBatchProcessAdapter() {
		if (batchProcessItemProvider == null) {
			batchProcessItemProvider = new BatchProcessItemProvider(this);
		}

		return batchProcessItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Output} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutputItemProvider outputItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Output}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOutputAdapter() {
		if (outputItemProvider == null) {
			outputItemProvider = new OutputItemProvider(this);
		}

		return outputItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.StorageOutputFlow} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StorageOutputFlowItemProvider storageOutputFlowItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.StorageOutputFlow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStorageOutputFlowAdapter() {
		if (storageOutputFlowItemProvider == null) {
			storageOutputFlowItemProvider = new StorageOutputFlowItemProvider(this);
		}

		return storageOutputFlowItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.ProcessOutputFlow} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessOutputFlowItemProvider processOutputFlowItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.ProcessOutputFlow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProcessOutputFlowAdapter() {
		if (processOutputFlowItemProvider == null) {
			processOutputFlowItemProvider = new ProcessOutputFlowItemProvider(this);
		}

		return processOutputFlowItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.OrderOnStockThreshold} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrderOnStockThresholdItemProvider orderOnStockThresholdItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.OrderOnStockThreshold}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOrderOnStockThresholdAdapter() {
		if (orderOnStockThresholdItemProvider == null) {
			orderOnStockThresholdItemProvider = new OrderOnStockThresholdItemProvider(this);
		}

		return orderOnStockThresholdItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Cauchy} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CauchyItemProvider cauchyItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Cauchy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCauchyAdapter() {
		if (cauchyItemProvider == null) {
			cauchyItemProvider = new CauchyItemProvider(this);
		}

		return cauchyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Binomial} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinomialItemProvider binomialItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Binomial}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBinomialAdapter() {
		if (binomialItemProvider == null) {
			binomialItemProvider = new BinomialItemProvider(this);
		}

		return binomialItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Beta} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BetaItemProvider betaItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Beta}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBetaAdapter() {
		if (betaItemProvider == null) {
			betaItemProvider = new BetaItemProvider(this);
		}

		return betaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Scalar} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScalarItemProvider scalarItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Scalar}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createScalarAdapter() {
		if (scalarItemProvider == null) {
			scalarItemProvider = new ScalarItemProvider(this);
		}

		return scalarItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.ChiSquare} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChiSquareItemProvider chiSquareItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.ChiSquare}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createChiSquareAdapter() {
		if (chiSquareItemProvider == null) {
			chiSquareItemProvider = new ChiSquareItemProvider(this);
		}

		return chiSquareItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.DiracDelta} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiracDeltaItemProvider diracDeltaItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.DiracDelta}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDiracDeltaAdapter() {
		if (diracDeltaItemProvider == null) {
			diracDeltaItemProvider = new DiracDeltaItemProvider(this);
		}

		return diracDeltaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Exponential} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExponentialItemProvider exponentialItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Exponential}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExponentialAdapter() {
		if (exponentialItemProvider == null) {
			exponentialItemProvider = new ExponentialItemProvider(this);
		}

		return exponentialItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.FDistribution} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FDistributionItemProvider fDistributionItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.FDistribution}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFDistributionAdapter() {
		if (fDistributionItemProvider == null) {
			fDistributionItemProvider = new FDistributionItemProvider(this);
		}

		return fDistributionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Gamma} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GammaItemProvider gammaItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Gamma}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGammaAdapter() {
		if (gammaItemProvider == null) {
			gammaItemProvider = new GammaItemProvider(this);
		}

		return gammaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Geometric} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeometricItemProvider geometricItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Geometric}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGeometricAdapter() {
		if (geometricItemProvider == null) {
			geometricItemProvider = new GeometricItemProvider(this);
		}

		return geometricItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.LogNormal} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogNormalItemProvider logNormalItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.LogNormal}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createLogNormalAdapter() {
		if (logNormalItemProvider == null) {
			logNormalItemProvider = new LogNormalItemProvider(this);
		}

		return logNormalItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Gaussian} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GaussianItemProvider gaussianItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Gaussian}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGaussianAdapter() {
		if (gaussianItemProvider == null) {
			gaussianItemProvider = new GaussianItemProvider(this);
		}

		return gaussianItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Pareto} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParetoItemProvider paretoItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Pareto}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createParetoAdapter() {
		if (paretoItemProvider == null) {
			paretoItemProvider = new ParetoItemProvider(this);
		}

		return paretoItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Poisson} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PoissonItemProvider poissonItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Poisson}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPoissonAdapter() {
		if (poissonItemProvider == null) {
			poissonItemProvider = new PoissonItemProvider(this);
		}

		return poissonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.StudentsT} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentsTItemProvider studentsTItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.StudentsT}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStudentsTAdapter() {
		if (studentsTItemProvider == null) {
			studentsTItemProvider = new StudentsTItemProvider(this);
		}

		return studentsTItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Uniform} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniformItemProvider uniformItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Uniform}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUniformAdapter() {
		if (uniformItemProvider == null) {
			uniformItemProvider = new UniformItemProvider(this);
		}

		return uniformItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link be.cetic.simqri.metamodel.Weibull} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WeibullItemProvider weibullItemProvider;

	/**
	 * This creates an adapter for a {@link be.cetic.simqri.metamodel.Weibull}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWeibullAdapter() {
		if (weibullItemProvider == null) {
			weibullItemProvider = new WeibullItemProvider(this);
		}

		return weibullItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (modelItemProvider != null) modelItemProvider.dispose();
		if (queryItemProvider != null) queryItemProvider.dispose();
		if (storageItemProvider != null) storageItemProvider.dispose();
		if (supplierItemProvider != null) supplierItemProvider.dispose();
		if (batchProcessItemProvider != null) batchProcessItemProvider.dispose();
		if (outputItemProvider != null) outputItemProvider.dispose();
		if (storageOutputFlowItemProvider != null) storageOutputFlowItemProvider.dispose();
		if (processOutputFlowItemProvider != null) processOutputFlowItemProvider.dispose();
		if (orderOnStockThresholdItemProvider != null) orderOnStockThresholdItemProvider.dispose();
		if (cauchyItemProvider != null) cauchyItemProvider.dispose();
		if (binomialItemProvider != null) binomialItemProvider.dispose();
		if (betaItemProvider != null) betaItemProvider.dispose();
		if (scalarItemProvider != null) scalarItemProvider.dispose();
		if (chiSquareItemProvider != null) chiSquareItemProvider.dispose();
		if (diracDeltaItemProvider != null) diracDeltaItemProvider.dispose();
		if (exponentialItemProvider != null) exponentialItemProvider.dispose();
		if (fDistributionItemProvider != null) fDistributionItemProvider.dispose();
		if (gammaItemProvider != null) gammaItemProvider.dispose();
		if (geometricItemProvider != null) geometricItemProvider.dispose();
		if (logNormalItemProvider != null) logNormalItemProvider.dispose();
		if (gaussianItemProvider != null) gaussianItemProvider.dispose();
		if (paretoItemProvider != null) paretoItemProvider.dispose();
		if (poissonItemProvider != null) poissonItemProvider.dispose();
		if (studentsTItemProvider != null) studentsTItemProvider.dispose();
		if (uniformItemProvider != null) uniformItemProvider.dispose();
		if (weibullItemProvider != null) weibullItemProvider.dispose();
		if (conveyorBeltItemProvider != null) conveyorBeltItemProvider.dispose();
	}

}