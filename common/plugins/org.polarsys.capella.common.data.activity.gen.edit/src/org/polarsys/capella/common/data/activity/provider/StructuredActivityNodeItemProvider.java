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

package org.polarsys.capella.common.data.activity.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.common.data.activity.StructuredActivityNode} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StructuredActivityNodeItemProvider
	extends ActivityGroupItemProvider
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
	protected IItemPropertyDescriptor inActivityPartitionPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor inInterruptibleRegionPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor inStructuredNodePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor contextPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StructuredActivityNodeItemProvider(AdapterFactory adapterFactory) {
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
      // Process ActivityPackage.Literals.ACTIVITY_NODE__IN_ACTIVITY_PARTITION
      if (inActivityPartitionPropertyDescriptor != null) {
        Object inActivityPartitionValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_NODE__IN_ACTIVITY_PARTITION, true);
        if (inActivityPartitionValue != null && inActivityPartitionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) inActivityPartitionValue)) {
          itemPropertyDescriptors.remove(inActivityPartitionPropertyDescriptor);
        } else if (inActivityPartitionValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_NODE__IN_ACTIVITY_PARTITION) != null) {
          itemPropertyDescriptors.remove(inActivityPartitionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(inActivityPartitionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(inActivityPartitionPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION
      if (inInterruptibleRegionPropertyDescriptor != null) {
        Object inInterruptibleRegionValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION, true);
        if (inInterruptibleRegionValue != null && inInterruptibleRegionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) inInterruptibleRegionValue)) {
          itemPropertyDescriptors.remove(inInterruptibleRegionPropertyDescriptor);
        } else if (inInterruptibleRegionValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION) != null) {
          itemPropertyDescriptors.remove(inInterruptibleRegionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(inInterruptibleRegionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(inInterruptibleRegionPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_NODE__IN_STRUCTURED_NODE
      if (inStructuredNodePropertyDescriptor != null) {
        Object inStructuredNodeValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_NODE__IN_STRUCTURED_NODE, true);
        if (inStructuredNodeValue != null && inStructuredNodeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) inStructuredNodeValue)) {
          itemPropertyDescriptors.remove(inStructuredNodePropertyDescriptor);
        } else if (inStructuredNodeValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_NODE__IN_STRUCTURED_NODE) != null) {
          itemPropertyDescriptors.remove(inStructuredNodePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(inStructuredNodePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(inStructuredNodePropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ABSTRACT_ACTION__CONTEXT
      if (contextPropertyDescriptor != null) {
        Object contextValue = eObject.eGet(ActivityPackage.Literals.ABSTRACT_ACTION__CONTEXT, true);
        if (contextValue != null && contextValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) contextValue)) {
          itemPropertyDescriptors.remove(contextPropertyDescriptor);
        } else if (contextValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ABSTRACT_ACTION__CONTEXT) != null) {
          itemPropertyDescriptors.remove(contextPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(contextPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(contextPropertyDescriptor);
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
      addInActivityPartitionPropertyDescriptor(object);
      addInInterruptibleRegionPropertyDescriptor(object);
      addInStructuredNodePropertyDescriptor(object);
      addOutgoingPropertyDescriptor(object);
      addIncomingPropertyDescriptor(object);
      addContextPropertyDescriptor(object);
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
   * This adds a property descriptor for the In Activity Partition feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInActivityPartitionPropertyDescriptor(Object object) {
    // begin-extension-code
    inActivityPartitionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityNode_inActivityPartition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityNode_inActivityPartition_feature", "_UI_ActivityNode_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_NODE__IN_ACTIVITY_PARTITION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(inActivityPartitionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the In Interruptible Region feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInInterruptibleRegionPropertyDescriptor(Object object) {
    // begin-extension-code
    inInterruptibleRegionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityNode_inInterruptibleRegion_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityNode_inInterruptibleRegion_feature", "_UI_ActivityNode_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(inInterruptibleRegionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the In Structured Node feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInStructuredNodePropertyDescriptor(Object object) {
    // begin-extension-code
    inStructuredNodePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityNode_inStructuredNode_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityNode_inStructuredNode_feature", "_UI_ActivityNode_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_NODE__IN_STRUCTURED_NODE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(inStructuredNodePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Outgoing feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOutgoingPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityNode_outgoing_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityNode_outgoing_feature", "_UI_ActivityNode_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_NODE__OUTGOING,
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
   * This adds a property descriptor for the Incoming feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIncomingPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityNode_incoming_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityNode_incoming_feature", "_UI_ActivityNode_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_NODE__INCOMING,
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
   * This adds a property descriptor for the Context feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContextPropertyDescriptor(Object object) {
    // begin-extension-code
    contextPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractAction_context_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractAction_context_feature", "_UI_AbstractAction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ABSTRACT_ACTION__CONTEXT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(contextPropertyDescriptor);
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
      childrenFeatures.add(ActivityPackage.Literals.EXECUTABLE_NODE__OWNED_HANDLERS);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_PRECONDITION);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_POSTCONDITION);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS);
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
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getText(Object object) {
   String[] result = new String[] { null };

    	//begin-capella-code
    String label = ((StructuredActivityNode)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_StructuredActivityNode_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(StructuredActivityNode.class)) {
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__NAME:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OWNED_HANDLERS:
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_PRECONDITION:
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__LOCAL_POSTCONDITION:
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__INPUTS:
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE__OUTPUTS:
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
