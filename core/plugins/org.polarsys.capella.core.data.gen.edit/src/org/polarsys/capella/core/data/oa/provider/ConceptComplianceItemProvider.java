/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.oa.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.provider.RelationshipItemProvider;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.oa.ConceptCompliance} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConceptComplianceItemProvider
	extends RelationshipItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor complyWithConceptPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor compliantCapabilityPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ConceptComplianceItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void checkChildCreationExtender(Object object) {
    super.checkChildCreationExtender(object);
    if (object instanceof EObject) {
      EObject eObject = (EObject) object;
      // Process OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT
      if (complyWithConceptPropertyDescriptor != null) {
        Object complyWithConceptValue = eObject.eGet(OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT, true);
        if (complyWithConceptValue != null && complyWithConceptValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) complyWithConceptValue)) {
          itemPropertyDescriptors.remove(complyWithConceptPropertyDescriptor);
        } else if (complyWithConceptValue == null && ExtensionModelManager.getAnyType(eObject, OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT) != null) {
          itemPropertyDescriptors.remove(complyWithConceptPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(complyWithConceptPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(complyWithConceptPropertyDescriptor);
        }
      }
      // Process OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY
      if (compliantCapabilityPropertyDescriptor != null) {
        Object compliantCapabilityValue = eObject.eGet(OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY, true);
        if (compliantCapabilityValue != null && compliantCapabilityValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) compliantCapabilityValue)) {
          itemPropertyDescriptors.remove(compliantCapabilityPropertyDescriptor);
        } else if (compliantCapabilityValue == null && ExtensionModelManager.getAnyType(eObject, OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY) != null) {
          itemPropertyDescriptors.remove(compliantCapabilityPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(compliantCapabilityPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(compliantCapabilityPropertyDescriptor);
        }
      }
    }		
  }

	/**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
    if (itemPropertyDescriptors == null) {
      super.getPropertyDescriptors(object);

      addComplyWithConceptPropertyDescriptor(object);
      addCompliantCapabilityPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Comply With Concept feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addComplyWithConceptPropertyDescriptor(Object object) {
    // begin-extension-code
    complyWithConceptPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ConceptCompliance_complyWithConcept_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ConceptCompliance_complyWithConcept_feature", "_UI_ConceptCompliance_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(complyWithConceptPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Compliant Capability feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addCompliantCapabilityPropertyDescriptor(Object object) {
    // begin-extension-code
    compliantCapabilityPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ConceptCompliance_compliantCapability_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ConceptCompliance_compliantCapability_feature", "_UI_ConceptCompliance_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         OaPackage.Literals.CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(compliantCapabilityPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns ConceptCompliance.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/ConceptCompliance")); //$NON-NLS-1$
  }

	/**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getText(Object object) {
   String[] result = new String[] { null };

    	//begin-capella-code
        String label = ""; //$NON-NLS-1$
        String targetName = ""; //$NON-NLS-1$
        EObject target = null;

   	if (null != target) {
      if (target instanceof AbstractNamedElement) {
        targetName = ((AbstractNamedElement) target).getName();
      }

      if (null == targetName || "" == targetName) { //$NON-NLS-1$
        targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
   	}
   	label = "[" + getString("_UI_ConceptCompliance_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_ConceptCompliance_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      //end-capella-code

    return result[0];

  }

	/**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void notifyChanged(Notification notification) {
    updateChildren(notification);
    super.notifyChanged(notification);
  }

	/**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

	// begin-capella-code
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, Helper helper) {
    return new SharedInitializeCopyCommand(domain, owner, helper);
  }
	// end-capella-code
}
