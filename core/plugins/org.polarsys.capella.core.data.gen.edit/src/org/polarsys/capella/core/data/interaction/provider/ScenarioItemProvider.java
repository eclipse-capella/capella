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
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.interaction.Scenario} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ScenarioItemProvider
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
	protected IItemPropertyDescriptor preConditionPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor postConditionPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ScenarioItemProvider(AdapterFactory adapterFactory) {
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
      // Process InteractionPackage.Literals.SCENARIO__PRE_CONDITION
      if (preConditionPropertyDescriptor != null) {
        Object preConditionValue = eObject.eGet(InteractionPackage.Literals.SCENARIO__PRE_CONDITION, true);
        if (preConditionValue != null && preConditionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) preConditionValue)) {
          itemPropertyDescriptors.remove(preConditionPropertyDescriptor);
        } else if (preConditionValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SCENARIO__PRE_CONDITION) != null) {
          itemPropertyDescriptors.remove(preConditionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(preConditionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(preConditionPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.SCENARIO__POST_CONDITION
      if (postConditionPropertyDescriptor != null) {
        Object postConditionValue = eObject.eGet(InteractionPackage.Literals.SCENARIO__POST_CONDITION, true);
        if (postConditionValue != null && postConditionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) postConditionValue)) {
          itemPropertyDescriptors.remove(postConditionPropertyDescriptor);
        } else if (postConditionValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.SCENARIO__POST_CONDITION) != null) {
          itemPropertyDescriptors.remove(postConditionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(postConditionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(postConditionPropertyDescriptor);
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

      addContainedGenericTracesPropertyDescriptor(object);
      addIsControlOperatorPropertyDescriptor(object);
      addOwnedParameterSetPropertyDescriptor(object);
      addOwnedParameterPropertyDescriptor(object);
      addKindPropertyDescriptor(object);
      addMergedPropertyDescriptor(object);
      addPreConditionPropertyDescriptor(object);
      addPostConditionPropertyDescriptor(object);
      addContainedFunctionsPropertyDescriptor(object);
      addContainedPartsPropertyDescriptor(object);
      addReferencedScenariosPropertyDescriptor(object);
      addRealizedScenariosPropertyDescriptor(object);
      addRealizingScenariosPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Contained Generic Traces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedGenericTracesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Namespace_containedGenericTraces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Namespace_containedGenericTraces_feature", "_UI_Namespace_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES,
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
   * This adds a property descriptor for the Is Control Operator feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsControlOperatorPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractBehavior_isControlOperator_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractBehavior_isControlOperator_feature", "_UI_AbstractBehavior_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         BehaviorPackage.Literals.ABSTRACT_BEHAVIOR__IS_CONTROL_OPERATOR,
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
   * This adds a property descriptor for the Owned Parameter Set feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOwnedParameterSetPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractBehavior_ownedParameterSet_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractBehavior_ownedParameterSet_feature", "_UI_AbstractBehavior_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         BehaviorPackage.Literals.ABSTRACT_BEHAVIOR__OWNED_PARAMETER_SET,
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
   * This adds a property descriptor for the Owned Parameter feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOwnedParameterPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractBehavior_ownedParameter_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractBehavior_ownedParameter_feature", "_UI_AbstractBehavior_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         BehaviorPackage.Literals.ABSTRACT_BEHAVIOR__OWNED_PARAMETER,
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
   * This adds a property descriptor for the Pre Condition feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addPreConditionPropertyDescriptor(Object object) {
    // begin-extension-code
    preConditionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_preCondition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_preCondition_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__PRE_CONDITION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(preConditionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Post Condition feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addPostConditionPropertyDescriptor(Object object) {
    // begin-extension-code
    postConditionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_postCondition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_postCondition_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__POST_CONDITION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(postConditionPropertyDescriptor);
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
         getString("_UI_Scenario_kind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_kind_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__KIND,
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
   * This adds a property descriptor for the Merged feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addMergedPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_merged_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_merged_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__MERGED,
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
   * This adds a property descriptor for the Contained Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_containedFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_containedFunctions_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__CONTAINED_FUNCTIONS,
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
   * This adds a property descriptor for the Contained Parts feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedPartsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_containedParts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_containedParts_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__CONTAINED_PARTS,
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
   * This adds a property descriptor for the Referenced Scenarios feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReferencedScenariosPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_referencedScenarios_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_referencedScenarios_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__REFERENCED_SCENARIOS,
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
   * This adds a property descriptor for the Realized Scenarios feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedScenariosPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_realizedScenarios_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_realizedScenarios_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__REALIZED_SCENARIOS,
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
   * This adds a property descriptor for the Realizing Scenarios feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingScenariosPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Scenario_realizingScenarios_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Scenario_realizingScenarios_feature", "_UI_Scenario_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.SCENARIO__REALIZING_SCENARIOS,
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
      childrenFeatures.add(CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES);
      childrenFeatures.add(CapellacorePackage.Literals.NAMESPACE__NAMING_RULES);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_MESSAGES);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_EVENTS);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_FORMAL_GATES);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_SCENARIO_REALIZATION);
      childrenFeatures.add(InteractionPackage.Literals.SCENARIO__OWNED_CONSTRAINT_DURATIONS);
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
   * This returns Scenario.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Scenario")); //$NON-NLS-1$
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
    String label = ((Scenario)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_Scenario_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(Scenario.class)) {
      case InteractionPackage.SCENARIO__IS_CONTROL_OPERATOR:
      case InteractionPackage.SCENARIO__KIND:
      case InteractionPackage.SCENARIO__MERGED:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case InteractionPackage.SCENARIO__OWNED_TRACES:
      case InteractionPackage.SCENARIO__NAMING_RULES:
      case InteractionPackage.SCENARIO__OWNED_INSTANCE_ROLES:
      case InteractionPackage.SCENARIO__OWNED_MESSAGES:
      case InteractionPackage.SCENARIO__OWNED_INTERACTION_FRAGMENTS:
      case InteractionPackage.SCENARIO__OWNED_TIME_LAPSES:
      case InteractionPackage.SCENARIO__OWNED_EVENTS:
      case InteractionPackage.SCENARIO__OWNED_FORMAL_GATES:
      case InteractionPackage.SCENARIO__OWNED_SCENARIO_REALIZATION:
      case InteractionPackage.SCENARIO__OWNED_CONSTRAINT_DURATIONS:
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
                        (CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES,
                         InteractionFactory.eINSTANCE.createMergeLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES,
                         InteractionFactory.eINSTANCE.createRefinementLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES,
                         CapellacommonFactory.eINSTANCE.createGenericTrace());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES,
                         CapellacommonFactory.eINSTANCE.createTransfoLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES,
                         CapellacommonFactory.eINSTANCE.createJustificationLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.NAMESPACE__NAMING_RULES,
                         CapellacoreFactory.eINSTANCE.createNamingRule());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES,
                         InteractionFactory.eINSTANCE.createInstanceRole());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_MESSAGES,
                         InteractionFactory.eINSTANCE.createSequenceMessage());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                         InteractionFactory.eINSTANCE.createMessageEnd());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                         InteractionFactory.eINSTANCE.createExecutionEnd());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                         InteractionFactory.eINSTANCE.createInteractionState());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                         InteractionFactory.eINSTANCE.createGate());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                         InteractionFactory.eINSTANCE.createInteractionOperand());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                         InteractionFactory.eINSTANCE.createFragmentEnd());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES,
                         InteractionFactory.eINSTANCE.createExecution());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES,
                         InteractionFactory.eINSTANCE.createInteractionUse());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES,
                         InteractionFactory.eINSTANCE.createCombinedFragment());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES,
                         InteractionFactory.eINSTANCE.createStateFragment());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                         InteractionFactory.eINSTANCE.createCreationEvent());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                         InteractionFactory.eINSTANCE.createDestructionEvent());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                         InteractionFactory.eINSTANCE.createExecutionEvent());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                         InteractionFactory.eINSTANCE.createEventReceiptOperation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                         InteractionFactory.eINSTANCE.createEventSentOperation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                         InteractionFactory.eINSTANCE.createArmTimerEvent());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                         InteractionFactory.eINSTANCE.createCancelTimerEvent());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_FORMAL_GATES,
                         InteractionFactory.eINSTANCE.createGate());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_SCENARIO_REALIZATION,
                         InteractionFactory.eINSTANCE.createScenarioRealization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InteractionPackage.Literals.SCENARIO__OWNED_CONSTRAINT_DURATIONS,
                         InteractionFactory.eINSTANCE.createConstraintDuration());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


  }

	/**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
    Object childFeature = feature;
    Object childObject = child;

    boolean qualify =
      childFeature == InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS ||
      childFeature == InteractionPackage.Literals.SCENARIO__OWNED_FORMAL_GATES;

    if (qualify) {
      return getString
        ("_UI_CreateChild_text2", //$NON-NLS-1$
         new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner, feature, child, selection);
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
