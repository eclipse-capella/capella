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
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.common.data.activity.ActivityPartition} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActivityPartitionItemProvider
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
	protected IItemPropertyDescriptor representedElementPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor superPartitionPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ActivityPartitionItemProvider(AdapterFactory adapterFactory) {
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
      // Process ActivityPackage.Literals.ACTIVITY_PARTITION__REPRESENTED_ELEMENT
      if (representedElementPropertyDescriptor != null) {
        Object representedElementValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_PARTITION__REPRESENTED_ELEMENT, true);
        if (representedElementValue != null && representedElementValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) representedElementValue)) {
          itemPropertyDescriptors.remove(representedElementPropertyDescriptor);
        } else if (representedElementValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_PARTITION__REPRESENTED_ELEMENT) != null) {
          itemPropertyDescriptors.remove(representedElementPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(representedElementPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(representedElementPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_PARTITION__SUPER_PARTITION
      if (superPartitionPropertyDescriptor != null) {
        Object superPartitionValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_PARTITION__SUPER_PARTITION, true);
        if (superPartitionValue != null && superPartitionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) superPartitionValue)) {
          itemPropertyDescriptors.remove(superPartitionPropertyDescriptor);
        } else if (superPartitionValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_PARTITION__SUPER_PARTITION) != null) {
          itemPropertyDescriptors.remove(superPartitionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(superPartitionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(superPartitionPropertyDescriptor);
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
      addIsDimensionPropertyDescriptor(object);
      addIsExternalPropertyDescriptor(object);
      addRepresentedElementPropertyDescriptor(object);
      addSuperPartitionPropertyDescriptor(object);
      addSubPartitionsPropertyDescriptor(object);
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
   * This adds a property descriptor for the Is Dimension feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsDimensionPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityPartition_isDimension_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityPartition_isDimension_feature", "_UI_ActivityPartition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_PARTITION__IS_DIMENSION,
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
   * This adds a property descriptor for the Is External feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsExternalPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityPartition_isExternal_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityPartition_isExternal_feature", "_UI_ActivityPartition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_PARTITION__IS_EXTERNAL,
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
   * This adds a property descriptor for the Represented Element feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRepresentedElementPropertyDescriptor(Object object) {
    // begin-extension-code
    representedElementPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityPartition_representedElement_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityPartition_representedElement_feature", "_UI_ActivityPartition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_PARTITION__REPRESENTED_ELEMENT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(representedElementPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Super Partition feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSuperPartitionPropertyDescriptor(Object object) {
    // begin-extension-code
    superPartitionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityPartition_superPartition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityPartition_superPartition_feature", "_UI_ActivityPartition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_PARTITION__SUPER_PARTITION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(superPartitionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Sub Partitions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSubPartitionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityPartition_subPartitions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityPartition_subPartitions_feature", "_UI_ActivityPartition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_PARTITION__SUB_PARTITIONS,
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
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getText(Object object) {
   String[] result = new String[] { null };

    	//begin-capella-code
    String label = ((ActivityPartition)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_ActivityPartition_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(ActivityPartition.class)) {
      case ActivityPackage.ACTIVITY_PARTITION__NAME:
      case ActivityPackage.ACTIVITY_PARTITION__IS_DIMENSION:
      case ActivityPackage.ACTIVITY_PARTITION__IS_EXTERNAL:
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
