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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.provider.RelationshipItemProvider;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExchangeItemAllocationItemProvider
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
	protected IItemPropertyDescriptor allocatedItemPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor allocatingInterfacePropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ExchangeItemAllocationItemProvider(AdapterFactory adapterFactory) {
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
      // Process CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM
      if (allocatedItemPropertyDescriptor != null) {
        Object allocatedItemValue = eObject.eGet(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM, true);
        if (allocatedItemValue != null && allocatedItemValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) allocatedItemValue)) {
          itemPropertyDescriptors.remove(allocatedItemPropertyDescriptor);
        } else if (allocatedItemValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM) != null) {
          itemPropertyDescriptors.remove(allocatedItemPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(allocatedItemPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(allocatedItemPropertyDescriptor);
        }
      }
      // Process CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE
      if (allocatingInterfacePropertyDescriptor != null) {
        Object allocatingInterfaceValue = eObject.eGet(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE, true);
        if (allocatingInterfaceValue != null && allocatingInterfaceValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) allocatingInterfaceValue)) {
          itemPropertyDescriptors.remove(allocatingInterfacePropertyDescriptor);
        } else if (allocatingInterfaceValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE) != null) {
          itemPropertyDescriptors.remove(allocatingInterfacePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(allocatingInterfacePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(allocatingInterfacePropertyDescriptor);
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

      addNamePropertyDescriptor(object);
      addInvokingSequenceMessagesPropertyDescriptor(object);
      addFinalPropertyDescriptor(object);
      addSendProtocolPropertyDescriptor(object);
      addReceiveProtocolPropertyDescriptor(object);
      addAllocatedItemPropertyDescriptor(object);
      addAllocatingInterfacePropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Name feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addNamePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractNamedElement_name_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractNamedElement_name_feature", "_UI_AbstractNamedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Invoking Sequence Messages feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvokingSequenceMessagesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractEventOperation_invokingSequenceMessages_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractEventOperation_invokingSequenceMessages_feature", "_UI_AbstractEventOperation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Final feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addFinalPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FinalizableElement_final_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FinalizableElement_final_feature", "_UI_FinalizableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Send Protocol feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSendProtocolPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ExchangeItemAllocation_sendProtocol_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ExchangeItemAllocation_sendProtocol_feature", "_UI_ExchangeItemAllocation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Receive Protocol feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReceiveProtocolPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ExchangeItemAllocation_receiveProtocol_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ExchangeItemAllocation_receiveProtocol_feature", "_UI_ExchangeItemAllocation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Allocated Item feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedItemPropertyDescriptor(Object object) {
    // begin-extension-code
    allocatedItemPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ExchangeItemAllocation_allocatedItem_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ExchangeItemAllocation_allocatedItem_feature", "_UI_ExchangeItemAllocation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(allocatedItemPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Allocating Interface feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatingInterfacePropertyDescriptor(Object object) {
    // begin-extension-code
    allocatingInterfacePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ExchangeItemAllocation_allocatingInterface_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ExchangeItemAllocation_allocatingInterface_feature", "_UI_ExchangeItemAllocation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(allocatingInterfacePropertyDescriptor);
    // end-extension-code
  }

	/**
	 * This returns ExchangeItemAllocation.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public Object getImage(Object object) {
	  ExchangeItemAllocation allocation = (ExchangeItemAllocation)object;
	  if (allocation.getAllocatedItem()!=null) {
	    IItemLabelProvider provider = (IItemLabelProvider)getRootAdapterFactory().adapt(allocation.getAllocatedItem(), IItemLabelProvider.class);
	    if (provider != null) {
	      return provider.getImage(allocation.getAllocatedItem());
	    }
	  }
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ExchangeItemAllocation")); //$NON-NLS-1$
	}

  /**
   * This returns ExchangeItemAllocation.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImageGen(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/ExchangeItemAllocation")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTextGen(Object object) {
   String[] result = new String[] { null };

    	//begin-capella-code
    String label = ((ExchangeItemAllocation)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_ExchangeItemAllocation_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      //end-capella-code

    return result[0];

  }

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String getText(Object object) {
	 String[] result = new String[] { null };

		//begin-capella-code
		
		String label = ""; //$NON-NLS-1$
	 	String targetName = ""; //$NON-NLS-1$
	 	EObject target = null;

 		target = ((ExchangeItemAllocation) object).getAllocatedItem();
	
	 	if (null != target) {
			if (target instanceof AbstractNamedElement) {
				targetName = "--> "+((AbstractNamedElement) target).getName();//$NON-NLS-1$
			}

			if (null == targetName || "" == targetName) { //$NON-NLS-1$
				targetName = "--> [" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
			}
	 	}
	 	label = targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		//end-capella-code
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_ExchangeItemAllocation_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(ExchangeItemAllocation.class)) {
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__NAME:
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__FINAL:
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL:
      case CsPackage.EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
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

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, Helper helper) {
    return new SharedInitializeCopyCommand(domain, owner, helper);
  }

}
