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

package org.polarsys.capella.core.data.capellacommon.provider;

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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.capellacommon.StateTransition} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StateTransitionItemProvider
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
	protected IItemPropertyDescriptor realizedFlowPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor guardPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor sourcePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor targetPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StateTransitionItemProvider(AdapterFactory adapterFactory) {
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
      // Process ModellingcorePackage.Literals.ABSTRACT_RELATIONSHIP__REALIZED_FLOW
      if (realizedFlowPropertyDescriptor != null) {
        Object realizedFlowValue = eObject.eGet(ModellingcorePackage.Literals.ABSTRACT_RELATIONSHIP__REALIZED_FLOW, true);
        if (realizedFlowValue != null && realizedFlowValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) realizedFlowValue)) {
          itemPropertyDescriptors.remove(realizedFlowPropertyDescriptor);
        } else if (realizedFlowValue == null && ExtensionModelManager.getAnyType(eObject, ModellingcorePackage.Literals.ABSTRACT_RELATIONSHIP__REALIZED_FLOW) != null) {
          itemPropertyDescriptors.remove(realizedFlowPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(realizedFlowPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(realizedFlowPropertyDescriptor);
        }
      }
      // Process CapellacommonPackage.Literals.STATE_TRANSITION__GUARD
      if (guardPropertyDescriptor != null) {
        Object guardValue = eObject.eGet(CapellacommonPackage.Literals.STATE_TRANSITION__GUARD, true);
        if (guardValue != null && guardValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) guardValue)) {
          itemPropertyDescriptors.remove(guardPropertyDescriptor);
        } else if (guardValue == null && ExtensionModelManager.getAnyType(eObject, CapellacommonPackage.Literals.STATE_TRANSITION__GUARD) != null) {
          itemPropertyDescriptors.remove(guardPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(guardPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(guardPropertyDescriptor);
        }
      }
      // Process CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE
      if (sourcePropertyDescriptor != null) {
        Object sourceValue = eObject.eGet(CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE, true);
        if (sourceValue != null && sourceValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourceValue)) {
          itemPropertyDescriptors.remove(sourcePropertyDescriptor);
        } else if (sourceValue == null && ExtensionModelManager.getAnyType(eObject, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE) != null) {
          itemPropertyDescriptors.remove(sourcePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sourcePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sourcePropertyDescriptor);
        }
      }
      // Process CapellacommonPackage.Literals.STATE_TRANSITION__TARGET
      if (targetPropertyDescriptor != null) {
        Object targetValue = eObject.eGet(CapellacommonPackage.Literals.STATE_TRANSITION__TARGET, true);
        if (targetValue != null && targetValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetValue)) {
          itemPropertyDescriptors.remove(targetPropertyDescriptor);
        } else if (targetValue == null && ExtensionModelManager.getAnyType(eObject, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET) != null) {
          itemPropertyDescriptors.remove(targetPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(targetPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(targetPropertyDescriptor);
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

      addRealizedFlowPropertyDescriptor(object);
      addKindPropertyDescriptor(object);
      addTriggerDescriptionPropertyDescriptor(object);
      addGuardPropertyDescriptor(object);
      addSourcePropertyDescriptor(object);
      addTargetPropertyDescriptor(object);
      addEffectPropertyDescriptor(object);
      addTriggersPropertyDescriptor(object);
      addRealizedStateTransitionsPropertyDescriptor(object);
      addRealizingStateTransitionsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Realized Flow feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedFlowPropertyDescriptor(Object object) {
    // begin-extension-code
    realizedFlowPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractRelationship_realizedFlow_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractRelationship_realizedFlow_feature", "_UI_AbstractRelationship_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.ABSTRACT_RELATIONSHIP__REALIZED_FLOW,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(realizedFlowPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Guard feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addGuardPropertyDescriptor(Object object) {
    // begin-extension-code
    guardPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_guard_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_guard_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__GUARD,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(guardPropertyDescriptor);
    // end-extension-code
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
         getString("_UI_StateTransition_kind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_kind_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__KIND,
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
   * This adds a property descriptor for the Trigger Description feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTriggerDescriptionPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_triggerDescription_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_triggerDescription_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGER_DESCRIPTION,
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
   * This adds a property descriptor for the Source feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSourcePropertyDescriptor(Object object) {
    // begin-extension-code
    sourcePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_source_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_source_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(sourcePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Target feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTargetPropertyDescriptor(Object object) {
    // begin-extension-code
    targetPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_target_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_target_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__TARGET,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(targetPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Effect feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addEffectPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_effect_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_effect_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT,
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
   * This adds a property descriptor for the Triggers feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTriggersPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_triggers_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_triggers_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS,
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
   * This adds a property descriptor for the Realized State Transitions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedStateTransitionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_realizedStateTransitions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_realizedStateTransitions_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS,
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
   * This adds a property descriptor for the Realizing State Transitions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingStateTransitionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StateTransition_realizingStateTransitions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_StateTransition_realizingStateTransitions_feature", "_UI_StateTransition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS,
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
      childrenFeatures.add(CapellacommonPackage.Literals.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS);
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
   * This returns StateTransition.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/StateTransition")); //$NON-NLS-1$
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
    String label = ((StateTransition)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_StateTransition_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(StateTransition.class)) {
      case CapellacommonPackage.STATE_TRANSITION__KIND:
      case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case CapellacommonPackage.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS:
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
                        (CapellacommonPackage.Literals.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS,
                         CapellacommonFactory.eINSTANCE.createStateTransitionRealization());
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
