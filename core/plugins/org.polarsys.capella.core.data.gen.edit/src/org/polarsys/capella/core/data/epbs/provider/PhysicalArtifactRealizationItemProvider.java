/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.epbs.provider;

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
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.provider.AllocationItemProvider;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalArtifactRealizationItemProvider
	extends AllocationItemProvider
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
	protected IItemPropertyDescriptor realizedPhysicalArtifactPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor realizingConfigurationItemPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PhysicalArtifactRealizationItemProvider(AdapterFactory adapterFactory) {
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
      // Process EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT
      if (realizedPhysicalArtifactPropertyDescriptor != null) {
        Object realizedPhysicalArtifactValue = eObject.eGet(EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT, true);
        if (realizedPhysicalArtifactValue != null && realizedPhysicalArtifactValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) realizedPhysicalArtifactValue)) {
          itemPropertyDescriptors.remove(realizedPhysicalArtifactPropertyDescriptor);
        } else if (realizedPhysicalArtifactValue == null && ExtensionModelManager.getAnyType(eObject, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT) != null) {
          itemPropertyDescriptors.remove(realizedPhysicalArtifactPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(realizedPhysicalArtifactPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(realizedPhysicalArtifactPropertyDescriptor);
        }
      }
      // Process EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM
      if (realizingConfigurationItemPropertyDescriptor != null) {
        Object realizingConfigurationItemValue = eObject.eGet(EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM, true);
        if (realizingConfigurationItemValue != null && realizingConfigurationItemValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) realizingConfigurationItemValue)) {
          itemPropertyDescriptors.remove(realizingConfigurationItemPropertyDescriptor);
        } else if (realizingConfigurationItemValue == null && ExtensionModelManager.getAnyType(eObject, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM) != null) {
          itemPropertyDescriptors.remove(realizingConfigurationItemPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(realizingConfigurationItemPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(realizingConfigurationItemPropertyDescriptor);
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

      addRealizedPhysicalArtifactPropertyDescriptor(object);
      addRealizingConfigurationItemPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Realized Physical Artifact feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedPhysicalArtifactPropertyDescriptor(Object object) {
    // begin-extension-code
    realizedPhysicalArtifactPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalArtifactRealization_realizedPhysicalArtifact_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalArtifactRealization_realizedPhysicalArtifact_feature", "_UI_PhysicalArtifactRealization_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(realizedPhysicalArtifactPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Realizing Configuration Item feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingConfigurationItemPropertyDescriptor(Object object) {
    // begin-extension-code
    realizingConfigurationItemPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalArtifactRealization_realizingConfigurationItem_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalArtifactRealization_realizingConfigurationItem_feature", "_UI_PhysicalArtifactRealization_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(realizingConfigurationItemPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns PhysicalArtifactRealization.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalArtifactRealization")); //$NON-NLS-1$
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

 		target = ((AbstractTrace) object).getTargetElement();
  
   	if (null != target) {
      if (target instanceof AbstractNamedElement) {
        targetName = ((AbstractNamedElement) target).getName();
      }

      if (null == targetName || "" == targetName) { //$NON-NLS-1$
        targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
   	}
   	label = "[" + getString("_UI_PhysicalArtifactRealization_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_PhysicalArtifactRealization_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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