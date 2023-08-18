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

package org.polarsys.capella.core.data.interaction.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
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
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.interaction.SequenceMessage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SequenceMessageItemProvider
	extends NamedElementItemProvider
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
	protected IItemPropertyDescriptor exchangeContextPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor sendingEndPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor receivingEndPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor invokedOperationPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor sendingPartPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor receivingPartPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor sendingFunctionPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor receivingFunctionPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SequenceMessageItemProvider(AdapterFactory adapterFactory) {
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
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT
      if (exchangeContextPropertyDescriptor != null) {
        Object exchangeContextValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT, true);
        if (exchangeContextValue != null && exchangeContextValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) exchangeContextValue)) {
          itemPropertyDescriptors.remove(exchangeContextPropertyDescriptor);
        } else if (exchangeContextValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT) != null) {
          itemPropertyDescriptors.remove(exchangeContextPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(exchangeContextPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(exchangeContextPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END
      if (sendingEndPropertyDescriptor != null) {
        Object sendingEndValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END, true);
        if (sendingEndValue != null && sendingEndValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sendingEndValue)) {
          itemPropertyDescriptors.remove(sendingEndPropertyDescriptor);
        } else if (sendingEndValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END) != null) {
          itemPropertyDescriptors.remove(sendingEndPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sendingEndPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sendingEndPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END
      if (receivingEndPropertyDescriptor != null) {
        Object receivingEndValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END, true);
        if (receivingEndValue != null && receivingEndValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) receivingEndValue)) {
          itemPropertyDescriptors.remove(receivingEndPropertyDescriptor);
        } else if (receivingEndValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END) != null) {
          itemPropertyDescriptors.remove(receivingEndPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(receivingEndPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(receivingEndPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION
      if (invokedOperationPropertyDescriptor != null) {
        Object invokedOperationValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION, true);
        if (invokedOperationValue != null && invokedOperationValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) invokedOperationValue)) {
          itemPropertyDescriptors.remove(invokedOperationPropertyDescriptor);
        } else if (invokedOperationValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION) != null) {
          itemPropertyDescriptors.remove(invokedOperationPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(invokedOperationPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(invokedOperationPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART
      if (sendingPartPropertyDescriptor != null) {
        Object sendingPartValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART, true);
        if (sendingPartValue != null && sendingPartValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sendingPartValue)) {
          itemPropertyDescriptors.remove(sendingPartPropertyDescriptor);
        } else if (sendingPartValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART) != null) {
          itemPropertyDescriptors.remove(sendingPartPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sendingPartPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sendingPartPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART
      if (receivingPartPropertyDescriptor != null) {
        Object receivingPartValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART, true);
        if (receivingPartValue != null && receivingPartValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) receivingPartValue)) {
          itemPropertyDescriptors.remove(receivingPartPropertyDescriptor);
        } else if (receivingPartValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART) != null) {
          itemPropertyDescriptors.remove(receivingPartPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(receivingPartPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(receivingPartPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION
      if (sendingFunctionPropertyDescriptor != null) {
        Object sendingFunctionValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION, true);
        if (sendingFunctionValue != null && sendingFunctionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sendingFunctionValue)) {
          itemPropertyDescriptors.remove(sendingFunctionPropertyDescriptor);
        } else if (sendingFunctionValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION) != null) {
          itemPropertyDescriptors.remove(sendingFunctionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sendingFunctionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sendingFunctionPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION
      if (receivingFunctionPropertyDescriptor != null) {
        Object receivingFunctionValue = eObject.eGet(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION, true);
        if (receivingFunctionValue != null && receivingFunctionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) receivingFunctionValue)) {
          itemPropertyDescriptors.remove(receivingFunctionPropertyDescriptor);
        } else if (receivingFunctionValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION) != null) {
          itemPropertyDescriptors.remove(receivingFunctionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(receivingFunctionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(receivingFunctionPropertyDescriptor);
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

      addKindPropertyDescriptor(object);
      addExchangeContextPropertyDescriptor(object);
      addSendingEndPropertyDescriptor(object);
      addReceivingEndPropertyDescriptor(object);
      addInvokedOperationPropertyDescriptor(object);
      addExchangedItemsPropertyDescriptor(object);
      addSendingPartPropertyDescriptor(object);
      addReceivingPartPropertyDescriptor(object);
      addSendingFunctionPropertyDescriptor(object);
      addReceivingFunctionPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Kind feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addKindPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_kind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_kind_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__KIND,
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
   * This adds a property descriptor for the Exchange Context feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExchangeContextPropertyDescriptor(Object object) {
    // begin-extension-code
    exchangeContextPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_exchangeContext_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_exchangeContext_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(exchangeContextPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Sending End feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSendingEndPropertyDescriptor(Object object) {
    // begin-extension-code
    sendingEndPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_sendingEnd_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_sendingEnd_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(sendingEndPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Receiving End feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReceivingEndPropertyDescriptor(Object object) {
    // begin-extension-code
    receivingEndPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_receivingEnd_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_receivingEnd_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(receivingEndPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Invoked Operation feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvokedOperationPropertyDescriptor(Object object) {
    // begin-extension-code
    invokedOperationPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_invokedOperation_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_invokedOperation_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__INVOKED_OPERATION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(invokedOperationPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Exchanged Items feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExchangedItemsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_exchangedItems_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_exchangedItems_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Sending Part feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSendingPartPropertyDescriptor(Object object) {
    // begin-extension-code
    sendingPartPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_sendingPart_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_sendingPart_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_PART,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(sendingPartPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Receiving Part feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReceivingPartPropertyDescriptor(Object object) {
    // begin-extension-code
    receivingPartPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_receivingPart_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_receivingPart_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_PART,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(receivingPartPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Sending Function feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSendingFunctionPropertyDescriptor(Object object) {
    // begin-extension-code
    sendingFunctionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_sendingFunction_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_sendingFunction_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_FUNCTION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(sendingFunctionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Receiving Function feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReceivingFunctionPropertyDescriptor(Object object) {
    // begin-extension-code
    receivingFunctionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SequenceMessage_receivingFunction_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SequenceMessage_receivingFunction_feature", "_UI_SequenceMessage_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_FUNCTION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(receivingFunctionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
    if (childrenFeatures == null) {
      super.getChildrenFeatures(object);
      childrenFeatures.add(InteractionPackage.Literals.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS);
    }
    return childrenFeatures;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

	/**
   * This returns SequenceMessage.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/SequenceMessage")); //$NON-NLS-1$
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
    String label = ((SequenceMessage)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_SequenceMessage_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(SequenceMessage.class)) {
      case InteractionPackage.SEQUENCE_MESSAGE__KIND:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case InteractionPackage.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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
                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS,
                         InteractionFactory.eINSTANCE.createSequenceMessageValuation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


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
