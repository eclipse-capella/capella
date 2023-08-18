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
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.fa.FunctionalExchange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FunctionalExchangeItemProvider
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
	protected IItemPropertyDescriptor targetPropertyDescriptor;
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
	protected IItemPropertyDescriptor interruptsPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor transformationPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor selectionPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor sourceFunctionOutputPortPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor targetFunctionInputPortPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public FunctionalExchangeItemProvider(AdapterFactory adapterFactory) {
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
      // Process ActivityPackage.Literals.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION
      if (inActivityPartitionPropertyDescriptor != null) {
        Object inActivityPartitionValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION, true);
        if (inActivityPartitionValue != null && inActivityPartitionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) inActivityPartitionValue)) {
          itemPropertyDescriptors.remove(inActivityPartitionPropertyDescriptor);
        } else if (inActivityPartitionValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION) != null) {
          itemPropertyDescriptors.remove(inActivityPartitionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(inActivityPartitionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(inActivityPartitionPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION
      if (inInterruptibleRegionPropertyDescriptor != null) {
        Object inInterruptibleRegionValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION, true);
        if (inInterruptibleRegionValue != null && inInterruptibleRegionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) inInterruptibleRegionValue)) {
          itemPropertyDescriptors.remove(inInterruptibleRegionPropertyDescriptor);
        } else if (inInterruptibleRegionValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION) != null) {
          itemPropertyDescriptors.remove(inInterruptibleRegionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(inInterruptibleRegionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(inInterruptibleRegionPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_EDGE__IN_STRUCTURED_NODE
      if (inStructuredNodePropertyDescriptor != null) {
        Object inStructuredNodeValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_EDGE__IN_STRUCTURED_NODE, true);
        if (inStructuredNodeValue != null && inStructuredNodeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) inStructuredNodeValue)) {
          itemPropertyDescriptors.remove(inStructuredNodePropertyDescriptor);
        } else if (inStructuredNodeValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_EDGE__IN_STRUCTURED_NODE) != null) {
          itemPropertyDescriptors.remove(inStructuredNodePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(inStructuredNodePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(inStructuredNodePropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_EDGE__TARGET
      if (targetPropertyDescriptor != null) {
        Object targetValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_EDGE__TARGET, true);
        if (targetValue != null && targetValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetValue)) {
          itemPropertyDescriptors.remove(targetPropertyDescriptor);
        } else if (targetValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET) != null) {
          itemPropertyDescriptors.remove(targetPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(targetPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(targetPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE
      if (sourcePropertyDescriptor != null) {
        Object sourceValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE, true);
        if (sourceValue != null && sourceValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourceValue)) {
          itemPropertyDescriptors.remove(sourcePropertyDescriptor);
        } else if (sourceValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE) != null) {
          itemPropertyDescriptors.remove(sourcePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sourcePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sourcePropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.ACTIVITY_EDGE__INTERRUPTS
      if (interruptsPropertyDescriptor != null) {
        Object interruptsValue = eObject.eGet(ActivityPackage.Literals.ACTIVITY_EDGE__INTERRUPTS, true);
        if (interruptsValue != null && interruptsValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) interruptsValue)) {
          itemPropertyDescriptors.remove(interruptsPropertyDescriptor);
        } else if (interruptsValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.ACTIVITY_EDGE__INTERRUPTS) != null) {
          itemPropertyDescriptors.remove(interruptsPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(interruptsPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(interruptsPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.OBJECT_FLOW__TRANSFORMATION
      if (transformationPropertyDescriptor != null) {
        Object transformationValue = eObject.eGet(ActivityPackage.Literals.OBJECT_FLOW__TRANSFORMATION, true);
        if (transformationValue != null && transformationValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) transformationValue)) {
          itemPropertyDescriptors.remove(transformationPropertyDescriptor);
        } else if (transformationValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.OBJECT_FLOW__TRANSFORMATION) != null) {
          itemPropertyDescriptors.remove(transformationPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(transformationPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(transformationPropertyDescriptor);
        }
      }
      // Process ActivityPackage.Literals.OBJECT_FLOW__SELECTION
      if (selectionPropertyDescriptor != null) {
        Object selectionValue = eObject.eGet(ActivityPackage.Literals.OBJECT_FLOW__SELECTION, true);
        if (selectionValue != null && selectionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) selectionValue)) {
          itemPropertyDescriptors.remove(selectionPropertyDescriptor);
        } else if (selectionValue == null && ExtensionModelManager.getAnyType(eObject, ActivityPackage.Literals.OBJECT_FLOW__SELECTION) != null) {
          itemPropertyDescriptors.remove(selectionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(selectionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(selectionPropertyDescriptor);
        }
      }
      // Process FaPackage.Literals.FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT
      if (sourceFunctionOutputPortPropertyDescriptor != null) {
        Object sourceFunctionOutputPortValue = eObject.eGet(FaPackage.Literals.FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT, true);
        if (sourceFunctionOutputPortValue != null && sourceFunctionOutputPortValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourceFunctionOutputPortValue)) {
          itemPropertyDescriptors.remove(sourceFunctionOutputPortPropertyDescriptor);
        } else if (sourceFunctionOutputPortValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT) != null) {
          itemPropertyDescriptors.remove(sourceFunctionOutputPortPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sourceFunctionOutputPortPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sourceFunctionOutputPortPropertyDescriptor);
        }
      }
      // Process FaPackage.Literals.FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT
      if (targetFunctionInputPortPropertyDescriptor != null) {
        Object targetFunctionInputPortValue = eObject.eGet(FaPackage.Literals.FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT, true);
        if (targetFunctionInputPortValue != null && targetFunctionInputPortValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetFunctionInputPortValue)) {
          itemPropertyDescriptors.remove(targetFunctionInputPortPropertyDescriptor);
        } else if (targetFunctionInputPortValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT) != null) {
          itemPropertyDescriptors.remove(targetFunctionInputPortPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(targetFunctionInputPortPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(targetFunctionInputPortPropertyDescriptor);
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
      addInvolvingInvolvementsPropertyDescriptor(object);
      addKindOfRatePropertyDescriptor(object);
      addInActivityPartitionPropertyDescriptor(object);
      addInInterruptibleRegionPropertyDescriptor(object);
      addInStructuredNodePropertyDescriptor(object);
      addTargetPropertyDescriptor(object);
      addSourcePropertyDescriptor(object);
      addInterruptsPropertyDescriptor(object);
      addIsMulticastPropertyDescriptor(object);
      addIsMultireceivePropertyDescriptor(object);
      addTransformationPropertyDescriptor(object);
      addSelectionPropertyDescriptor(object);
      addAbstractTypedElementsPropertyDescriptor(object);
      addInvokingSequenceMessagesPropertyDescriptor(object);
      addExchangeSpecificationsPropertyDescriptor(object);
      addInvolvingFunctionalChainsPropertyDescriptor(object);
      addExchangedItemsPropertyDescriptor(object);
      addAllocatingComponentExchangesPropertyDescriptor(object);
      addIncomingComponentExchangeFunctionalExchangeRealizationsPropertyDescriptor(object);
      addIncomingFunctionalExchangeRealizationsPropertyDescriptor(object);
      addOutgoingFunctionalExchangeRealizationsPropertyDescriptor(object);
      addCategoriesPropertyDescriptor(object);
      addSourceFunctionOutputPortPropertyDescriptor(object);
      addTargetFunctionInputPortPropertyDescriptor(object);
      addRealizedFunctionalExchangesPropertyDescriptor(object);
      addRealizingFunctionalExchangesPropertyDescriptor(object);
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
   * This adds a property descriptor for the Kind Of Rate feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addKindOfRatePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityEdge_kindOfRate_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityEdge_kindOfRate_feature", "_UI_ActivityEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_EDGE__KIND_OF_RATE,
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
         getString("_UI_ActivityEdge_inActivityPartition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityEdge_inActivityPartition_feature", "_UI_ActivityEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION,
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
         getString("_UI_ActivityEdge_inInterruptibleRegion_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityEdge_inInterruptibleRegion_feature", "_UI_ActivityEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION,
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
         getString("_UI_ActivityEdge_inStructuredNode_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityEdge_inStructuredNode_feature", "_UI_ActivityEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_EDGE__IN_STRUCTURED_NODE,
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
         getString("_UI_ActivityEdge_target_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityEdge_target_feature", "_UI_ActivityEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_EDGE__TARGET,
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
         getString("_UI_ActivityEdge_source_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityEdge_source_feature", "_UI_ActivityEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE,
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
   * This adds a property descriptor for the Interrupts feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInterruptsPropertyDescriptor(Object object) {
    // begin-extension-code
    interruptsPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ActivityEdge_interrupts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ActivityEdge_interrupts_feature", "_UI_ActivityEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.ACTIVITY_EDGE__INTERRUPTS,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(interruptsPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Is Multicast feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsMulticastPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ObjectFlow_isMulticast_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ObjectFlow_isMulticast_feature", "_UI_ObjectFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.OBJECT_FLOW__IS_MULTICAST,
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
   * This adds a property descriptor for the Is Multireceive feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsMultireceivePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ObjectFlow_isMultireceive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ObjectFlow_isMultireceive_feature", "_UI_ObjectFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.OBJECT_FLOW__IS_MULTIRECEIVE,
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
   * This adds a property descriptor for the Transformation feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTransformationPropertyDescriptor(Object object) {
    // begin-extension-code
    transformationPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ObjectFlow_transformation_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ObjectFlow_transformation_feature", "_UI_ObjectFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.OBJECT_FLOW__TRANSFORMATION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(transformationPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Selection feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSelectionPropertyDescriptor(Object object) {
    // begin-extension-code
    selectionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ObjectFlow_selection_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ObjectFlow_selection_feature", "_UI_ObjectFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ActivityPackage.Literals.OBJECT_FLOW__SELECTION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(selectionPropertyDescriptor);
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
   * This adds a property descriptor for the Exchange Specifications feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExchangeSpecificationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_exchangeSpecifications_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_exchangeSpecifications_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGE_SPECIFICATIONS,
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
         getString("_UI_FunctionalExchange_involvingFunctionalChains_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_involvingFunctionalChains_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__INVOLVING_FUNCTIONAL_CHAINS,
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
         getString("_UI_FunctionalExchange_exchangedItems_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_exchangedItems_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS,
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
   * This adds a property descriptor for the Allocating Component Exchanges feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatingComponentExchangesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_allocatingComponentExchanges_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_allocatingComponentExchanges_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__ALLOCATING_COMPONENT_EXCHANGES,
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
   * This adds a property descriptor for the Incoming Component Exchange Functional Exchange Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIncomingComponentExchangeFunctionalExchangeRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_incomingComponentExchangeFunctionalExchangeRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_incomingComponentExchangeFunctionalExchangeRealizations_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_REALIZATIONS,
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
   * This adds a property descriptor for the Incoming Functional Exchange Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIncomingFunctionalExchangeRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_incomingFunctionalExchangeRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_incomingFunctionalExchangeRealizations_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__INCOMING_FUNCTIONAL_EXCHANGE_REALIZATIONS,
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
   * This adds a property descriptor for the Outgoing Functional Exchange Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOutgoingFunctionalExchangeRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_outgoingFunctionalExchangeRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_outgoingFunctionalExchangeRealizations_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__OUTGOING_FUNCTIONAL_EXCHANGE_REALIZATIONS,
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
   * This adds a property descriptor for the Categories feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addCategoriesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_categories_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_categories_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES,
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
   * This adds a property descriptor for the Source Function Output Port feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSourceFunctionOutputPortPropertyDescriptor(Object object) {
    // begin-extension-code
    sourceFunctionOutputPortPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_sourceFunctionOutputPort_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_sourceFunctionOutputPort_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(sourceFunctionOutputPortPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Target Function Input Port feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTargetFunctionInputPortPropertyDescriptor(Object object) {
    // begin-extension-code
    targetFunctionInputPortPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_targetFunctionInputPort_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_targetFunctionInputPort_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(targetFunctionInputPortPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Realized Functional Exchanges feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedFunctionalExchangesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_realizedFunctionalExchanges_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_realizedFunctionalExchanges_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__REALIZED_FUNCTIONAL_EXCHANGES,
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
   * This adds a property descriptor for the Realizing Functional Exchanges feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingFunctionalExchangesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalExchange_realizingFunctionalExchanges_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalExchange_realizingFunctionalExchanges_feature", "_UI_FunctionalExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_EXCHANGE__REALIZING_FUNCTIONAL_EXCHANGES,
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
      childrenFeatures.add(ActivityPackage.Literals.ACTIVITY_EDGE__RATE);
      childrenFeatures.add(ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY);
      childrenFeatures.add(ActivityPackage.Literals.ACTIVITY_EDGE__GUARD);
      childrenFeatures.add(ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT);
      childrenFeatures.add(FaPackage.Literals.FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS);
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
   * This returns FunctionalExchange.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImageGen(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/FunctionalExchange")); //$NON-NLS-1$
  }

  /**
   * This returns FunctionalExchange.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  @Override
  public Object getImage(Object object) {
    FunctionalExchange item = (FunctionalExchange)object;
    String imagePath = "full/obj16/FunctionalExchange"; //$NON-NLS-1$
    if (item.getSource() instanceof OperationalActivity
     && item.getTarget() instanceof OperationalActivity)
    {
      imagePath = "full/obj16/FunctionalExchange_OA"; //$NON-NLS-1$
    }

    return overlayImage(object, getResourceLocator().getImage(imagePath));
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
    String label = ((FunctionalExchange)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_FunctionalExchange_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(FunctionalExchange.class)) {
      case FaPackage.FUNCTIONAL_EXCHANGE__KIND_OF_RATE:
      case FaPackage.FUNCTIONAL_EXCHANGE__IS_MULTICAST:
      case FaPackage.FUNCTIONAL_EXCHANGE__IS_MULTIRECEIVE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case FaPackage.FUNCTIONAL_EXCHANGE__RATE:
      case FaPackage.FUNCTIONAL_EXCHANGE__PROBABILITY:
      case FaPackage.FUNCTIONAL_EXCHANGE__GUARD:
      case FaPackage.FUNCTIONAL_EXCHANGE__WEIGHT:
      case FaPackage.FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS:
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
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__RATE,
                         DatavalueFactory.eINSTANCE.createOpaqueExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY,
                         DatavalueFactory.eINSTANCE.createOpaqueExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__GUARD,
                         DatavalueFactory.eINSTANCE.createOpaqueExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT,
                         DatavalueFactory.eINSTANCE.createOpaqueExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS,
                         FaFactory.eINSTANCE.createFunctionalExchangeRealization());
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
      childFeature == ActivityPackage.Literals.ACTIVITY_EDGE__RATE ||
      childFeature == ActivityPackage.Literals.ACTIVITY_EDGE__PROBABILITY ||
      childFeature == ActivityPackage.Literals.ACTIVITY_EDGE__GUARD ||
      childFeature == ActivityPackage.Literals.ACTIVITY_EDGE__WEIGHT;

    if (qualify) {
      return getString
        ("_UI_CreateChild_text2", //$NON-NLS-1$
         new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner, feature, child, selection);
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
