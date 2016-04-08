/**
 */
package be.cetic.simqri.metamodel.impl;

import be.cetic.simqri.metamodel.MetamodelPackage;
import be.cetic.simqri.metamodel.StudentsT;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Students T</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link be.cetic.simqri.metamodel.impl.StudentsTImpl#getDegreeOfFreedom <em>Degree Of Freedom</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StudentsTImpl extends DistributionImpl implements StudentsT {
	/**
	 * The default value of the '{@link #getDegreeOfFreedom() <em>Degree Of Freedom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDegreeOfFreedom()
	 * @generated
	 * @ordered
	 */
	protected static final Double DEGREE_OF_FREEDOM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDegreeOfFreedom() <em>Degree Of Freedom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDegreeOfFreedom()
	 * @generated
	 * @ordered
	 */
	protected Double degreeOfFreedom = DEGREE_OF_FREEDOM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentsTImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetamodelPackage.Literals.STUDENTS_T;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getDegreeOfFreedom() {
		return degreeOfFreedom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDegreeOfFreedom(Double newDegreeOfFreedom) {
		Double oldDegreeOfFreedom = degreeOfFreedom;
		degreeOfFreedom = newDegreeOfFreedom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetamodelPackage.STUDENTS_T__DEGREE_OF_FREEDOM, oldDegreeOfFreedom, degreeOfFreedom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetamodelPackage.STUDENTS_T__DEGREE_OF_FREEDOM:
				return getDegreeOfFreedom();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetamodelPackage.STUDENTS_T__DEGREE_OF_FREEDOM:
				setDegreeOfFreedom((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetamodelPackage.STUDENTS_T__DEGREE_OF_FREEDOM:
				setDegreeOfFreedom(DEGREE_OF_FREEDOM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetamodelPackage.STUDENTS_T__DEGREE_OF_FREEDOM:
				return DEGREE_OF_FREEDOM_EDEFAULT == null ? degreeOfFreedom != null : !DEGREE_OF_FREEDOM_EDEFAULT.equals(degreeOfFreedom);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (degreeOfFreedom: ");
		result.append(degreeOfFreedom);
		result.append(')');
		return result.toString();
	}

} //StudentsTImpl