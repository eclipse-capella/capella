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

package org.polarsys.capella.core.data.fa.provider;

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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.fa.AbstractFunction} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractFunctionItemProvider
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
	protected IItemPropertyDescriptor abstractTypePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor typePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor associationPropertyDescriptor;

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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor behaviorPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor linkedStateMachinePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor linkedFunctionSpecificationPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AbstractFunctionItemProvider(AdapterFactory adapterFactory) {
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
      // Process ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE
      if (abstractTypePropertyDescriptor != null) {
        Object abstractTypeValue = eObject.eGet(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, true);
        if (abstractTypeValue != null && abstractTypeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) abstractTypeValue)) {
          itemPropertyDescriptors.remove(abstractTypePropertyDescriptor);
        } else if (abstractTypeValue == null && ExtensionModelManager.getAnyType(eObject, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE) != null) {
          itemPropertyDescriptors.remove(abstractTypePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(abstractTypePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(abstractTypePropertyDescriptor);
        }
      }
      // Process CapellacorePackage.Literals.TYPED_ELEMENT__TYPE
      if (typePropertyDescriptor != null) {
        Object typeValue = eObject.eGet(CapellacorePackage.Literals.TYPED_ELEMENT__TYPE, true);
        if (typeValue != null && typeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) typeValue)) {
          itemPropertyDescriptors.remove(typePropertyDescriptor);
        } else if (typeValue == null && ExtensionModelManager.getAnyType(eObject, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE) != null) {
          itemPropertyDescriptors.remove(typePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(typePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(typePropertyDescriptor);
        }
      }
      // Process InformationPackage.Literals.PROPERTY__ASSOCIATION
      if (associationPropertyDescriptor != null) {
        Object associationValue = eObject.eGet(InformationPackage.Literals.PROPERTY__ASSOCIATION, true);
        if (associationValue != null && associationValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) associationValue)) {
          itemPropertyDescriptors.remove(associationPropertyDescriptor);
        } else if (associationValue == null && ExtensionModelManager.getAnyType(eObject, InformationPackage.Literals.PROPERTY__ASSOCIATION) != null) {
          itemPropertyDescriptors.remove(associationPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(associationPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(associationPropertyDescriptor);
        }
      }
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
      // Process ActivityPackage.Literals.CALL_BEHAVIOR_ACTION__BEHAVIOR
      if (behaviorPropertyDescriptor != null) {
        Object behaviorValue = eObject.eGet(ActivityPackage.Literals.CALL_BEHAVIOR_ACTION__BEHAVIOR, true);
        if (behaviorValue != null && behaviorValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) behaviorValue)) {
          itemPropertyDescriptors.remove(behaviorPropertyDescriptor);
        } else if (behaviorValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.CALL_BEHAVIOR_ACTION__BEHAVIOR) != null) {
          itemPropertyDescriptors.remove(behaviorPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(behaviorPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(behaviorPropertyDescriptor);
        }
      }
      // Process FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE
      if (linkedStateMachinePropertyDescriptor != null) {
        Object linkedStateMachineValue = eObject.eGet(FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE, true);
        if (linkedStateMachineValue != null && linkedStateMachineValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) linkedStateMachineValue)) {
          itemPropertyDescriptors.remove(linkedStateMachinePropertyDescriptor);
        } else if (linkedStateMachineValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE) != null) {
          itemPropertyDescriptors.remove(linkedStateMachinePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(linkedStateMachinePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(linkedStateMachinePropertyDescriptor);
        }
      }
      // Process FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION
      if (linkedFunctionSpecificationPropertyDescriptor != null) {
        Object linkedFunctionSpecificationValue = eObject.eGet(FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION, true);
        if (linkedFunctionSpecificationValue != null && linkedFunctionSpecificationValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) linkedFunctionSpecificationValue)) {
          itemPropertyDescriptors.remove(linkedFunctionSpecificationPropertyDescriptor);
        } else if (linkedFunctionSpecificationValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION) != null) {
          itemPropertyDescriptors.remove(linkedFunctionSpecificationPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(linkedFunctionSpecificationPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(linkedFunctionSpecificationPropertyDescriptor);
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
      addInvolvingInvolvementsPropertyDescriptor(object);
      addIsAbstractPropertyDescriptor(object);
      addIsStaticPropertyDescriptor(object);
      addVisibilityPropertyDescriptor(object);
      addAbstractTypePropertyDescriptor(object);
      addTypePropertyDescriptor(object);
      addOrderedPropertyDescriptor(object);
      addUniquePropertyDescriptor(object);
      addMinInclusivePropertyDescriptor(object);
      addMaxInclusivePropertyDescriptor(object);
      addFinalPropertyDescriptor(object);
      addAggregationKindPropertyDescriptor(object);
      addIsDerivedPropertyDescriptor(object);
      addIsReadOnlyPropertyDescriptor(object);
      addIsPartOfKeyPropertyDescriptor(object);
      addAssociationPropertyDescriptor(object);
      addRepresentingInstanceRolesPropertyDescriptor(object);
      addInActivityPartitionPropertyDescriptor(object);
      addInInterruptibleRegionPropertyDescriptor(object);
      addInStructuredNodePropertyDescriptor(object);
      addOutgoingPropertyDescriptor(object);
      addIncomingPropertyDescriptor(object);
      addContextPropertyDescriptor(object);
      addBehaviorPropertyDescriptor(object);
      addAbstractTypedElementsPropertyDescriptor(object);
      addKindPropertyDescriptor(object);
      addConditionPropertyDescriptor(object);
      addSubFunctionsPropertyDescriptor(object);
      addOutFunctionRealizationsPropertyDescriptor(object);
      addInFunctionRealizationsPropertyDescriptor(object);
      addComponentFunctionalAllocationsPropertyDescriptor(object);
      addAllocationBlocksPropertyDescriptor(object);
      addAvailableInStatesPropertyDescriptor(object);
      addInvolvingCapabilitiesPropertyDescriptor(object);
      addInvolvingCapabilityRealizationsPropertyDescriptor(object);
      addInvolvingFunctionalChainsPropertyDescriptor(object);
      addLinkedStateMachinePropertyDescriptor(object);
      addLinkedFunctionSpecificationPropertyDescriptor(object);
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
   * This adds a property descriptor for the Involving Involvements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvingInvolvementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InvolvedElement_involvingInvolvements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InvolvedElement_involvingInvolvements_feature", "_UI_InvolvedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS,
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
   * This adds a property descriptor for the Is Abstract feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsAbstractPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Feature_isAbstract_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Feature_isAbstract_feature", "_UI_Feature_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.FEATURE__IS_ABSTRACT,
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
   * This adds a property descriptor for the Is Static feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsStaticPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Feature_isStatic_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Feature_isStatic_feature", "_UI_Feature_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.FEATURE__IS_STATIC,
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
   * This adds a property descriptor for the Visibility feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addVisibilityPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Feature_visibility_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Feature_visibility_feature", "_UI_Feature_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.FEATURE__VISIBILITY,
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
   * This adds a property descriptor for the Abstract Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAbstractTypePropertyDescriptor(Object object) {
    // begin-extension-code
    abstractTypePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractTypedElement_abstractType_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractTypedElement_abstractType_feature", "_UI_AbstractTypedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(abstractTypePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTypePropertyDescriptor(Object object) {
    // begin-extension-code
    typePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TypedElement_type_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_TypedElement_type_feature", "_UI_TypedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.TYPED_ELEMENT__TYPE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(typePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Ordered feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOrderedPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_MultiplicityElement_ordered_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_ordered_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__ORDERED,
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
   * This adds a property descriptor for the Unique feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addUniquePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_MultiplicityElement_unique_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_unique_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__UNIQUE,
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
   * This adds a property descriptor for the Min Inclusive feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addMinInclusivePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_MultiplicityElement_minInclusive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_minInclusive_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE,
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
   * This adds a property descriptor for the Max Inclusive feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addMaxInclusivePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_MultiplicityElement_maxInclusive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_maxInclusive_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE,
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
   * This adds a property descriptor for the Aggregation Kind feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAggregationKindPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Property_aggregationKind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Property_aggregationKind_feature", "_UI_Property_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.PROPERTY__AGGREGATION_KIND,
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
   * This adds a property descriptor for the Is Derived feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsDerivedPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Property_isDerived_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Property_isDerived_feature", "_UI_Property_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.PROPERTY__IS_DERIVED,
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
   * This adds a property descriptor for the Is Read Only feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsReadOnlyPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Property_isReadOnly_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Property_isReadOnly_feature", "_UI_Property_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.PROPERTY__IS_READ_ONLY,
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
   * This adds a property descriptor for the Is Part Of Key feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsPartOfKeyPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Property_isPartOfKey_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Property_isPartOfKey_feature", "_UI_Property_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.PROPERTY__IS_PART_OF_KEY,
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
   * This adds a property descriptor for the Association feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAssociationPropertyDescriptor(Object object) {
    // begin-extension-code
    associationPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Property_association_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Property_association_feature", "_UI_Property_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.PROPERTY__ASSOCIATION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(associationPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Representing Instance Roles feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRepresentingInstanceRolesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractInstance_representingInstanceRoles_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractInstance_representingInstanceRoles_feature", "_UI_AbstractInstance_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES,
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
   * This adds a property descriptor for the Behavior feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addBehaviorPropertyDescriptor(Object object) {
    // begin-extension-code
    behaviorPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CallBehaviorAction_behavior_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CallBehaviorAction_behavior_feature", "_UI_CallBehaviorAction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.CALL_BEHAVIOR_ACTION__BEHAVIOR,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(behaviorPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Abstract Typed Elements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAbstractTypedElementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractType_abstractTypedElements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractType_abstractTypedElements_feature", "_UI_AbstractType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS,
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
         getString("_UI_AbstractFunction_kind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_kind_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__KIND,
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
   * This adds a property descriptor for the Condition feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addConditionPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_condition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_condition_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__CONDITION,
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
   * This adds a property descriptor for the Sub Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSubFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_subFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_subFunctions_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__SUB_FUNCTIONS,
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
   * This adds a property descriptor for the Out Function Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOutFunctionRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_outFunctionRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_outFunctionRealizations_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS,
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
   * This adds a property descriptor for the In Function Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInFunctionRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_inFunctionRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_inFunctionRealizations_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS,
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
   * This adds a property descriptor for the Component Functional Allocations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addComponentFunctionalAllocationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_componentFunctionalAllocations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_componentFunctionalAllocations_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS,
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
   * This adds a property descriptor for the Allocation Blocks feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocationBlocksPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_allocationBlocks_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_allocationBlocks_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__ALLOCATION_BLOCKS,
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
   * This adds a property descriptor for the Available In States feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAvailableInStatesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_availableInStates_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_availableInStates_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES,
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
   * This adds a property descriptor for the Involving Capabilities feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvingCapabilitiesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_involvingCapabilities_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_involvingCapabilities_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES,
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
   * This adds a property descriptor for the Involving Capability Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvingCapabilityRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_involvingCapabilityRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_involvingCapabilityRealizations_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS,
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
   * This adds a property descriptor for the Involving Functional Chains feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvingFunctionalChainsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_involvingFunctionalChains_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_involvingFunctionalChains_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS,
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
   * This adds a property descriptor for the Linked State Machine feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addLinkedStateMachinePropertyDescriptor(Object object) {
    // begin-extension-code
    linkedStateMachinePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_linkedStateMachine_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_linkedStateMachine_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(linkedStateMachinePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Linked Function Specification feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addLinkedFunctionSpecificationPropertyDescriptor(Object object) {
    // begin-extension-code
    linkedFunctionSpecificationPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractFunction_linkedFunctionSpecification_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractFunction_linkedFunctionSpecification_feature", "_UI_AbstractFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(linkedFunctionSpecificationPropertyDescriptor);
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
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH);
      childrenFeatures.add(FaPackage.Literals.ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS);
      childrenFeatures.add(ActivityPackage.Literals.EXECUTABLE_NODE__OWNED_HANDLERS);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_PRECONDITION);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_POSTCONDITION);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS);
      childrenFeatures.add(ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS);
      childrenFeatures.add(ActivityPackage.Literals.INVOCATION_ACTION__ARGUMENTS);
      childrenFeatures.add(ActivityPackage.Literals.CALL_ACTION__RESULTS);
      childrenFeatures.add(FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS);
      childrenFeatures.add(FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS);
      childrenFeatures.add(FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES);
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
    String label = ((AbstractFunction)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_AbstractFunction_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(AbstractFunction.class)) {
      case FaPackage.ABSTRACT_FUNCTION__IS_ABSTRACT:
      case FaPackage.ABSTRACT_FUNCTION__IS_STATIC:
      case FaPackage.ABSTRACT_FUNCTION__VISIBILITY:
      case FaPackage.ABSTRACT_FUNCTION__ORDERED:
      case FaPackage.ABSTRACT_FUNCTION__UNIQUE:
      case FaPackage.ABSTRACT_FUNCTION__MIN_INCLUSIVE:
      case FaPackage.ABSTRACT_FUNCTION__MAX_INCLUSIVE:
      case FaPackage.ABSTRACT_FUNCTION__FINAL:
      case FaPackage.ABSTRACT_FUNCTION__AGGREGATION_KIND:
      case FaPackage.ABSTRACT_FUNCTION__IS_DERIVED:
      case FaPackage.ABSTRACT_FUNCTION__IS_READ_ONLY:
      case FaPackage.ABSTRACT_FUNCTION__IS_PART_OF_KEY:
      case FaPackage.ABSTRACT_FUNCTION__KIND:
      case FaPackage.ABSTRACT_FUNCTION__CONDITION:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case FaPackage.ABSTRACT_FUNCTION__OWNED_TRACES:
      case FaPackage.ABSTRACT_FUNCTION__NAMING_RULES:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_DEFAULT_VALUE:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_VALUE:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_VALUE:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_NULL_VALUE:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_CARD:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_LENGTH:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_CARD:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_LENGTH:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_CHAINS:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_HANDLERS:
      case FaPackage.ABSTRACT_FUNCTION__LOCAL_PRECONDITION:
      case FaPackage.ABSTRACT_FUNCTION__LOCAL_POSTCONDITION:
      case FaPackage.ABSTRACT_FUNCTION__INPUTS:
      case FaPackage.ABSTRACT_FUNCTION__OUTPUTS:
      case FaPackage.ABSTRACT_FUNCTION__ARGUMENTS:
      case FaPackage.ABSTRACT_FUNCTION__RESULTS:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONS:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS:
      case FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES:
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
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS,
                         FaFactory.eINSTANCE.createFunctionalChain());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS,
                         OaFactory.eINSTANCE.createOperationalProcess());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_PRECONDITION,
                         CapellacoreFactory.eINSTANCE.createConstraint());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_POSTCONDITION,
                         CapellacoreFactory.eINSTANCE.createConstraint());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS,
                         FaFactory.eINSTANCE.createFunctionInputPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS,
                         FaFactory.eINSTANCE.createFunctionOutputPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.INVOCATION_ACTION__ARGUMENTS,
                         FaFactory.eINSTANCE.createFunctionInputPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.CALL_ACTION__RESULTS,
                         FaFactory.eINSTANCE.createFunctionOutputPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
                         OaFactory.eINSTANCE.createOperationalActivity());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
                         CtxFactory.eINSTANCE.createSystemFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
                         LaFactory.eINSTANCE.createLogicalFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
                         PaFactory.eINSTANCE.createPhysicalFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS,
                         FaFactory.eINSTANCE.createFunctionRealization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES,
                         FaFactory.eINSTANCE.createFunctionalExchange());
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
      childFeature == ModellingcorePackage.Literals.MODEL_ELEMENT__OWNED_CONSTRAINTS ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_PRECONDITION ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_POSTCONDITION ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS ||
      childFeature == ActivityPackage.Literals.INVOCATION_ACTION__ARGUMENTS ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS ||
      childFeature == ActivityPackage.Literals.CALL_ACTION__RESULTS;

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
