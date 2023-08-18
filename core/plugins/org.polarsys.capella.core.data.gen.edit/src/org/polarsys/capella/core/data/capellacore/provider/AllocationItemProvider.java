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

package org.polarsys.capella.core.data.capellacore.provider;

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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.capellacore.Allocation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AllocationItemProvider
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
	protected IItemPropertyDescriptor targetElementPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor sourceElementPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AllocationItemProvider(AdapterFactory adapterFactory) {
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
      // Process ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT
      if (targetElementPropertyDescriptor != null) {
        Object targetElementValue = eObject.eGet(ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, true);
        if (targetElementValue != null && targetElementValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetElementValue)) {
          itemPropertyDescriptors.remove(targetElementPropertyDescriptor);
        } else if (targetElementValue == null && ExtensionModelManager.getAnyType(eObject, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT) != null) {
          itemPropertyDescriptors.remove(targetElementPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(targetElementPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(targetElementPropertyDescriptor);
        }
      }
      // Process ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT
      if (sourceElementPropertyDescriptor != null) {
        Object sourceElementValue = eObject.eGet(ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, true);
        if (sourceElementValue != null && sourceElementValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourceElementValue)) {
          itemPropertyDescriptors.remove(sourceElementPropertyDescriptor);
        } else if (sourceElementValue == null && ExtensionModelManager.getAnyType(eObject, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT) != null) {
          itemPropertyDescriptors.remove(sourceElementPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sourceElementPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sourceElementPropertyDescriptor);
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

      addTargetElementPropertyDescriptor(object);
      addSourceElementPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Target Element feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTargetElementPropertyDescriptor(Object object) {
    // begin-extension-code
    targetElementPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractTrace_targetElement_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractTrace_targetElement_feature", "_UI_AbstractTrace_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(targetElementPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Source Element feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSourceElementPropertyDescriptor(Object object) {
    // begin-extension-code
    sourceElementPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractTrace_sourceElement_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractTrace_sourceElement_feature", "_UI_AbstractTrace_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(sourceElementPropertyDescriptor);
    // end-extension-code
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
   	label = "[" + getString("_UI_Allocation_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_Allocation_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
