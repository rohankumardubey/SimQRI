/**
 * Generated with Acceleo
 */
package be.cetic.simqri.metamodel.components;

// Start of user code for imports
import be.cetic.simqri.metamodel.Exponential;
import be.cetic.simqri.metamodel.MetamodelPackage;

import be.cetic.simqri.metamodel.parts.ExponentialPropertiesEditionPart;
import be.cetic.simqri.metamodel.parts.MetamodelViewsRepository;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.sirius.eef.components.SiriusAwarePropertiesEditingComponent;


// End of user code

/**
 * 
 * 
 */
public class ExponentialPropertiesEditionComponent extends SiriusAwarePropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	
	/**
	 * Default constructor
	 * 
	 */
	public ExponentialPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject exponential, String editing_mode) {
		super(editingContext, exponential, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = MetamodelViewsRepository.class;
		partKey = MetamodelViewsRepository.Exponential.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			
			final Exponential exponential = (Exponential)elt;
			final ExponentialPropertiesEditionPart basePart = (ExponentialPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(MetamodelViewsRepository.Exponential.Properties.scale))
				basePart.setScale(EEFConverterUtil.convertToString(MetamodelPackage.Literals.POSITIVE_DOUBLE_NOT_NULL, exponential.getScale()));
			
			// init filters
			
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}




	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == MetamodelViewsRepository.Exponential.Properties.scale) {
			return MetamodelPackage.eINSTANCE.getExponential_Scale();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Exponential exponential = (Exponential)semanticObject;
		if (MetamodelViewsRepository.Exponential.Properties.scale == event.getAffectedEditor()) {
			exponential.setScale((java.lang.Double)EEFConverterUtil.createFromString(MetamodelPackage.Literals.POSITIVE_DOUBLE_NOT_NULL, (String)event.getNewValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			ExponentialPropertiesEditionPart basePart = (ExponentialPropertiesEditionPart)editingPart;
			if (MetamodelPackage.eINSTANCE.getExponential_Scale().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(MetamodelViewsRepository.Exponential.Properties.scale)) {
				if (msg.getNewValue() != null) {
					basePart.setScale(EcoreUtil.convertToString(MetamodelPackage.Literals.POSITIVE_DOUBLE_NOT_NULL, msg.getNewValue()));
				} else {
					basePart.setScale("");
				}
			}
			
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
			MetamodelPackage.eINSTANCE.getExponential_Scale()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (MetamodelViewsRepository.Exponential.Properties.scale == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(MetamodelPackage.eINSTANCE.getExponential_Scale().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(MetamodelPackage.eINSTANCE.getExponential_Scale().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}


	

	

}
