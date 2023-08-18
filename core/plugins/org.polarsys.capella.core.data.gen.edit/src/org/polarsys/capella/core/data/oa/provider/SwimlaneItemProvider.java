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
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Swimlane;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.oa.Swimlane} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SwimlaneItemProvider
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
	protected IItemPropertyDescriptor representedElementPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor superPartitionPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor representedEntityPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SwimlaneItemProvider(AdapterFactory adapterFactory) {
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
      // Process OaPackage.Literals.SWIMLANE__REPRESENTED_ENTITY
      if (representedEntityPropertyDescriptor != null) {
        Object representedEntityValue = eObject.eGet(OaPackage.Literals.SWIMLANE__REPRESENTED_ENTITY, true);
        if (representedEntityValue != null && representedEntityValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) representedEntityValue)) {
          itemPropertyDescriptors.remove(representedEntityPropertyDescriptor);
        } else if (representedEntityValue == null && ExtensionModelManager.getAnyType(eObject, OaPackage.Literals.SWIMLANE__REPRESENTED_ENTITY) != null) {
          itemPropertyDescriptors.remove(representedEntityPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(representedEntityPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(representedEntityPropertyDescriptor);
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

      addIsDimensionPropertyDescriptor(object);
      addIsExternalPropertyDescriptor(object);
      addRepresentedElementPropertyDescriptor(object);
      addSuperPartitionPropertyDescriptor(object);
      addSubPartitionsPropertyDescriptor(object);
      addRepresentedEntityPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
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
   * This adds a property descriptor for the Represented Entity feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRepresentedEntityPropertyDescriptor(Object object) {
    // begin-extension-code
    representedEntityPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Swimlane_representedEntity_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Swimlane_representedEntity_feature", "_UI_Swimlane_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         OaPackage.Literals.SWIMLANE__REPRESENTED_ENTITY,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(representedEntityPropertyDescriptor);
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
      childrenFeatures.add(ActivityPackage.Literals.ACTIVITY_GROUP__SUB_GROUPS);
      childrenFeatures.add(ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_NODES);
      childrenFeatures.add(ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_EDGES);
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
   * This returns Swimlane.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Swimlane")); //$NON-NLS-1$
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
    String label = ((Swimlane)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_Swimlane_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(Swimlane.class)) {
      case OaPackage.SWIMLANE__IS_DIMENSION:
      case OaPackage.SWIMLANE__IS_EXTERNAL:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case OaPackage.SWIMLANE__SUB_GROUPS:
      case OaPackage.SWIMLANE__OWNED_NODES:
      case OaPackage.SWIMLANE__OWNED_EDGES:
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
                        (ActivityPackage.Literals.ACTIVITY_GROUP__SUB_GROUPS,
                         OaFactory.eINSTANCE.createSwimlane());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_NODES,
                         OaFactory.eINSTANCE.createOperationalActivity());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_NODES,
                         CtxFactory.eINSTANCE.createSystemFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_NODES,
                         LaFactory.eINSTANCE.createLogicalFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_NODES,
                         PaFactory.eINSTANCE.createPhysicalFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_NODES,
                         FaFactory.eINSTANCE.createFunctionInputPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_NODES,
                         FaFactory.eINSTANCE.createFunctionOutputPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_GROUP__OWNED_EDGES,
                         FaFactory.eINSTANCE.createFunctionalExchange());
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
