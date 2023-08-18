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

package org.polarsys.capella.core.data.cs.provider;

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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.InterfaceImplementation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class InterfaceImplementationItemProvider
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
	protected IItemPropertyDescriptor interfaceImplementorPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor implementedInterfacePropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InterfaceImplementationItemProvider(AdapterFactory adapterFactory) {
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
      // Process CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR
      if (interfaceImplementorPropertyDescriptor != null) {
        Object interfaceImplementorValue = eObject.eGet(CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR, true);
        if (interfaceImplementorValue != null && interfaceImplementorValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) interfaceImplementorValue)) {
          itemPropertyDescriptors.remove(interfaceImplementorPropertyDescriptor);
        } else if (interfaceImplementorValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR) != null) {
          itemPropertyDescriptors.remove(interfaceImplementorPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(interfaceImplementorPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(interfaceImplementorPropertyDescriptor);
        }
      }
      // Process CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE
      if (implementedInterfacePropertyDescriptor != null) {
        Object implementedInterfaceValue = eObject.eGet(CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE, true);
        if (implementedInterfaceValue != null && implementedInterfaceValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) implementedInterfaceValue)) {
          itemPropertyDescriptors.remove(implementedInterfacePropertyDescriptor);
        } else if (implementedInterfaceValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE) != null) {
          itemPropertyDescriptors.remove(implementedInterfacePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(implementedInterfacePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(implementedInterfacePropertyDescriptor);
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

      addInterfaceImplementorPropertyDescriptor(object);
      addImplementedInterfacePropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Interface Implementor feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInterfaceImplementorPropertyDescriptor(Object object) {
    // begin-extension-code
    interfaceImplementorPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InterfaceImplementation_interfaceImplementor_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InterfaceImplementation_interfaceImplementor_feature", "_UI_InterfaceImplementation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(interfaceImplementorPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Implemented Interface feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addImplementedInterfacePropertyDescriptor(Object object) {
    // begin-extension-code
    implementedInterfacePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InterfaceImplementation_implementedInterface_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InterfaceImplementation_implementedInterface_feature", "_UI_InterfaceImplementation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(implementedInterfacePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns InterfaceImplementation.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/InterfaceImplementation")); //$NON-NLS-1$
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

 		target = ((InterfaceImplementation) object).getImplementedInterface();
  
   	if (null != target) {
      if (target instanceof AbstractNamedElement) {
        targetName = ((AbstractNamedElement) target).getName();
      }

      if (null == targetName || "" == targetName) { //$NON-NLS-1$
        targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
   	}
   	label = "[" + getString("_UI_InterfaceImplementation_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_InterfaceImplementation_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
