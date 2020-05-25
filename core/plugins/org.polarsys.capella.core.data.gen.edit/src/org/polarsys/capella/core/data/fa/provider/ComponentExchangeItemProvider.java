/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.provider.AbstractTypeItemProvider;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.fa.ComponentExchange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentExchangeItemProvider
	extends AbstractTypeItemProvider
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
	protected IItemPropertyDescriptor statusPropertyDescriptor;

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
	protected IItemPropertyDescriptor sourcePropertyDescriptor;

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
	protected IItemPropertyDescriptor containingLinkPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor linkPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor sourcePortPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor sourcePartPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor targetPortPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor targetPartPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentExchangeItemProvider(AdapterFactory adapterFactory) {
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
			// Process CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS
			if (statusPropertyDescriptor != null) {
				Object statusValue = eObject.eGet(CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS, true);
				if (statusValue != null && statusValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) statusValue)) {
					itemPropertyDescriptors.remove(statusPropertyDescriptor);
				} else if (statusValue == null && ExtensionModelManager.getAnyType(eObject, CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS) != null) {
					itemPropertyDescriptors.remove(statusPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(statusPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(statusPropertyDescriptor);
				}
			}
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
			// Process ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE
			if (sourcePropertyDescriptor != null) {
				Object sourceValue = eObject.eGet(ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE, true);
				if (sourceValue != null && sourceValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourceValue)) {
					itemPropertyDescriptors.remove(sourcePropertyDescriptor);
				} else if (sourceValue == null && ExtensionModelManager.getAnyType(eObject, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE) != null) {
					itemPropertyDescriptors.remove(sourcePropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(sourcePropertyDescriptor) == false) {
					itemPropertyDescriptors.add(sourcePropertyDescriptor);
				}
			}
			// Process ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET
			if (targetPropertyDescriptor != null) {
				Object targetValue = eObject.eGet(ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET, true);
				if (targetValue != null && targetValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetValue)) {
					itemPropertyDescriptors.remove(targetPropertyDescriptor);
				} else if (targetValue == null && ExtensionModelManager.getAnyType(eObject, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET) != null) {
					itemPropertyDescriptors.remove(targetPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(targetPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(targetPropertyDescriptor);
				}
			}
			// Process FaPackage.Literals.EXCHANGE_SPECIFICATION__CONTAINING_LINK
			if (containingLinkPropertyDescriptor != null) {
				Object containingLinkValue = eObject.eGet(FaPackage.Literals.EXCHANGE_SPECIFICATION__CONTAINING_LINK, true);
				if (containingLinkValue != null && containingLinkValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containingLinkValue)) {
					itemPropertyDescriptors.remove(containingLinkPropertyDescriptor);
				} else if (containingLinkValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.EXCHANGE_SPECIFICATION__CONTAINING_LINK) != null) {
					itemPropertyDescriptors.remove(containingLinkPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(containingLinkPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(containingLinkPropertyDescriptor);
				}
			}
			// Process FaPackage.Literals.EXCHANGE_SPECIFICATION__LINK
			if (linkPropertyDescriptor != null) {
				Object linkValue = eObject.eGet(FaPackage.Literals.EXCHANGE_SPECIFICATION__LINK, true);
				if (linkValue != null && linkValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) linkValue)) {
					itemPropertyDescriptors.remove(linkPropertyDescriptor);
				} else if (linkValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.EXCHANGE_SPECIFICATION__LINK) != null) {
					itemPropertyDescriptors.remove(linkPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(linkPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(linkPropertyDescriptor);
				}
			}
			// Process FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PORT
			if (sourcePortPropertyDescriptor != null) {
				Object sourcePortValue = eObject.eGet(FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PORT, true);
				if (sourcePortValue != null && sourcePortValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourcePortValue)) {
					itemPropertyDescriptors.remove(sourcePortPropertyDescriptor);
				} else if (sourcePortValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PORT) != null) {
					itemPropertyDescriptors.remove(sourcePortPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(sourcePortPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(sourcePortPropertyDescriptor);
				}
			}
			// Process FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PART
			if (sourcePartPropertyDescriptor != null) {
				Object sourcePartValue = eObject.eGet(FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PART, true);
				if (sourcePartValue != null && sourcePartValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourcePartValue)) {
					itemPropertyDescriptors.remove(sourcePartPropertyDescriptor);
				} else if (sourcePartValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PART) != null) {
					itemPropertyDescriptors.remove(sourcePartPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(sourcePartPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(sourcePartPropertyDescriptor);
				}
			}
			// Process FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PORT
			if (targetPortPropertyDescriptor != null) {
				Object targetPortValue = eObject.eGet(FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PORT, true);
				if (targetPortValue != null && targetPortValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetPortValue)) {
					itemPropertyDescriptors.remove(targetPortPropertyDescriptor);
				} else if (targetPortValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PORT) != null) {
					itemPropertyDescriptors.remove(targetPortPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(targetPortPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(targetPortPropertyDescriptor);
				}
			}
			// Process FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PART
			if (targetPartPropertyDescriptor != null) {
				Object targetPartValue = eObject.eGet(FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PART, true);
				if (targetPartValue != null && targetPartValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetPartValue)) {
					itemPropertyDescriptors.remove(targetPartPropertyDescriptor);
				} else if (targetPartValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PART) != null) {
					itemPropertyDescriptors.remove(targetPartPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(targetPartPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(targetPartPropertyDescriptor);
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

			addIncomingTracesPropertyDescriptor(object);
			addOutgoingTracesPropertyDescriptor(object);
			addVisibleInDocPropertyDescriptor(object);
			addVisibleInLMPropertyDescriptor(object);
			addSummaryPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addReviewPropertyDescriptor(object);
			addAppliedPropertyValuesPropertyDescriptor(object);
			addAppliedPropertyValueGroupsPropertyDescriptor(object);
			addStatusPropertyDescriptor(object);
			addFeaturesPropertyDescriptor(object);
			addAppliedRequirementsPropertyDescriptor(object);
			addInvokingSequenceMessagesPropertyDescriptor(object);
			addRealizedFlowPropertyDescriptor(object);
			addRealizationsPropertyDescriptor(object);
			addConvoyedInformationsPropertyDescriptor(object);
			addSourcePropertyDescriptor(object);
			addTargetPropertyDescriptor(object);
			addRealizingActivityFlowsPropertyDescriptor(object);
			addContainingLinkPropertyDescriptor(object);
			addLinkPropertyDescriptor(object);
			addOutgoingExchangeSpecificationRealizationsPropertyDescriptor(object);
			addIncomingExchangeSpecificationRealizationsPropertyDescriptor(object);
			addKindPropertyDescriptor(object);
			addOrientedPropertyDescriptor(object);
			addAllocatedFunctionalExchangesPropertyDescriptor(object);
			addIncomingComponentExchangeRealizationsPropertyDescriptor(object);
			addOutgoingComponentExchangeRealizationsPropertyDescriptor(object);
			addOutgoingComponentExchangeFunctionalExchangeAllocationsPropertyDescriptor(object);
			addSourcePortPropertyDescriptor(object);
			addSourcePartPropertyDescriptor(object);
			addTargetPortPropertyDescriptor(object);
			addTargetPartPropertyDescriptor(object);
			addCategoriesPropertyDescriptor(object);
			addAllocatorPhysicalLinksPropertyDescriptor(object);
			addRealizedComponentExchangesPropertyDescriptor(object);
			addRealizingComponentExchangesPropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Incoming Traces feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingTracesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TraceableElement_incomingTraces_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_TraceableElement_incomingTraces_feature", "_UI_TraceableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES,
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
	 * This adds a property descriptor for the Outgoing Traces feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutgoingTracesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TraceableElement_outgoingTraces_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_TraceableElement_outgoingTraces_feature", "_UI_TraceableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
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
	 * This adds a property descriptor for the Visible In Doc feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVisibleInDocPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PublishableElement_visibleInDoc_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PublishableElement_visibleInDoc_feature", "_UI_PublishableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC,
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
	 * This adds a property descriptor for the Visible In LM feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVisibleInLMPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PublishableElement_visibleInLM_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PublishableElement_visibleInLM_feature", "_UI_PublishableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM,
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
	 * This adds a property descriptor for the Summary feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSummaryPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_summary_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_summary_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY,
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
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_description_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_description_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION,
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
	 * This adds a property descriptor for the Review feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReviewPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_review_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_review_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__REVIEW,
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
	 * This adds a property descriptor for the Applied Property Values feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAppliedPropertyValuesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_appliedPropertyValues_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_appliedPropertyValues_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES,
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
	 * This adds a property descriptor for the Applied Property Value Groups feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAppliedPropertyValueGroupsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_appliedPropertyValueGroups_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_appliedPropertyValueGroups_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS,
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
	 * This adds a property descriptor for the Status feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStatusPropertyDescriptor(Object object) {
		// begin-extension-code
		statusPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_status_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_status_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS,
				 true,
				 false,
				 true,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(statusPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Features feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFeaturesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_features_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_features_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__FEATURES,
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
	 * This adds a property descriptor for the Applied Requirements feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAppliedRequirementsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CapellaElement_appliedRequirements_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_appliedRequirements_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_REQUIREMENTS,
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
	 * This adds a property descriptor for the Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractInformationFlow_realizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractInformationFlow_realizations_feature", "_UI_AbstractInformationFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__REALIZATIONS,
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
	 * This adds a property descriptor for the Convoyed Informations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConvoyedInformationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractInformationFlow_convoyedInformations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractInformationFlow_convoyedInformations_feature", "_UI_AbstractInformationFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS,
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
				 getString("_UI_AbstractInformationFlow_source_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractInformationFlow_source_feature", "_UI_AbstractInformationFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE,
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
				 getString("_UI_AbstractInformationFlow_target_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractInformationFlow_target_feature", "_UI_AbstractInformationFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET,
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
	 * This adds a property descriptor for the Realizing Activity Flows feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizingActivityFlowsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ActivityExchange_realizingActivityFlows_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ActivityExchange_realizingActivityFlows_feature", "_UI_ActivityExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ActivityPackage.Literals.ACTIVITY_EXCHANGE__REALIZING_ACTIVITY_FLOWS,
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
	 * This adds a property descriptor for the Containing Link feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContainingLinkPropertyDescriptor(Object object) {
		// begin-extension-code
		containingLinkPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExchangeSpecification_containingLink_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ExchangeSpecification_containingLink_feature", "_UI_ExchangeSpecification_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.EXCHANGE_SPECIFICATION__CONTAINING_LINK,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(containingLinkPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Link feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLinkPropertyDescriptor(Object object) {
		// begin-extension-code
		linkPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExchangeSpecification_link_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ExchangeSpecification_link_feature", "_UI_ExchangeSpecification_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.EXCHANGE_SPECIFICATION__LINK,
				 true,
				 false,
				 true,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(linkPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Outgoing Exchange Specification Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutgoingExchangeSpecificationRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExchangeSpecification_outgoingExchangeSpecificationRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ExchangeSpecification_outgoingExchangeSpecificationRealizations_feature", "_UI_ExchangeSpecification_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS,
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
	 * This adds a property descriptor for the Incoming Exchange Specification Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingExchangeSpecificationRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExchangeSpecification_incomingExchangeSpecificationRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ExchangeSpecification_incomingExchangeSpecificationRealizations_feature", "_UI_ExchangeSpecification_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS,
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
				 getString("_UI_ComponentExchange_kind_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_kind_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__KIND,
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
	 * This adds a property descriptor for the Oriented feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOrientedPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_oriented_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_oriented_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__ORIENTED,
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
	 * This adds a property descriptor for the Allocated Functional Exchanges feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatedFunctionalExchangesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_allocatedFunctionalExchanges_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_allocatedFunctionalExchanges_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__ALLOCATED_FUNCTIONAL_EXCHANGES,
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
	 * This adds a property descriptor for the Incoming Component Exchange Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingComponentExchangeRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_incomingComponentExchangeRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_incomingComponentExchangeRealizations_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_REALIZATIONS,
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
	 * This adds a property descriptor for the Outgoing Component Exchange Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutgoingComponentExchangeRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_outgoingComponentExchangeRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_outgoingComponentExchangeRealizations_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_REALIZATIONS,
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
	 * This adds a property descriptor for the Outgoing Component Exchange Functional Exchange Allocations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutgoingComponentExchangeFunctionalExchangeAllocationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_outgoingComponentExchangeFunctionalExchangeAllocations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_outgoingComponentExchangeFunctionalExchangeAllocations_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS,
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
	 * This adds a property descriptor for the Source Port feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourcePortPropertyDescriptor(Object object) {
		// begin-extension-code
		sourcePortPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_sourcePort_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_sourcePort_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PORT,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(sourcePortPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Source Part feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourcePartPropertyDescriptor(Object object) {
		// begin-extension-code
		sourcePartPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_sourcePart_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_sourcePart_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__SOURCE_PART,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(sourcePartPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Target Port feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPortPropertyDescriptor(Object object) {
		// begin-extension-code
		targetPortPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_targetPort_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_targetPort_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PORT,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(targetPortPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Target Part feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPartPropertyDescriptor(Object object) {
		// begin-extension-code
		targetPartPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_targetPart_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_targetPart_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__TARGET_PART,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(targetPartPropertyDescriptor);
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
				 getString("_UI_ComponentExchange_categories_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_categories_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__CATEGORIES,
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
	 * This adds a property descriptor for the Allocator Physical Links feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatorPhysicalLinksPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_allocatorPhysicalLinks_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_allocatorPhysicalLinks_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__ALLOCATOR_PHYSICAL_LINKS,
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
	 * This adds a property descriptor for the Realized Component Exchanges feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizedComponentExchangesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_realizedComponentExchanges_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_realizedComponentExchanges_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__REALIZED_COMPONENT_EXCHANGES,
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
	 * This adds a property descriptor for the Realizing Component Exchanges feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizingComponentExchangesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponentExchange_realizingComponentExchanges_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchange_realizingComponentExchanges_feature", "_UI_ComponentExchange_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.COMPONENT_EXCHANGE__REALIZING_COMPONENT_EXCHANGES,
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
			childrenFeatures.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES);
			childrenFeatures.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES);
			childrenFeatures.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS);
			childrenFeatures.add(FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
			childrenFeatures.add(FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS);
			childrenFeatures.add(FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_ENDS);
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
	 * This returns ComponentExchange.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ComponentExchange")); //$NON-NLS-1$
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
		String label = ((ComponentExchange)object).getName();
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_ComponentExchange_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		switch (notification.getFeatureID(ComponentExchange.class)) {
			case FaPackage.COMPONENT_EXCHANGE__VISIBLE_IN_DOC:
			case FaPackage.COMPONENT_EXCHANGE__VISIBLE_IN_LM:
			case FaPackage.COMPONENT_EXCHANGE__SUMMARY:
			case FaPackage.COMPONENT_EXCHANGE__DESCRIPTION:
			case FaPackage.COMPONENT_EXCHANGE__REVIEW:
			case FaPackage.COMPONENT_EXCHANGE__KIND:
			case FaPackage.COMPONENT_EXCHANGE__ORIENTED:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case FaPackage.COMPONENT_EXCHANGE__OWNED_PROPERTY_VALUES:
			case FaPackage.COMPONENT_EXCHANGE__OWNED_ENUMERATION_PROPERTY_TYPES:
			case FaPackage.COMPONENT_EXCHANGE__OWNED_PROPERTY_VALUE_GROUPS:
			case FaPackage.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS:
			case FaPackage.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS:
			case FaPackage.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_ENDS:
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
                        (ModellingcorePackage.Literals.MODEL_ELEMENT__OWNED_CONSTRAINTS,
                         CapellacoreFactory.eINSTANCE.createConstraint());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createStringPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createIntegerPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createBooleanPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createFloatPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createEnumerationPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES,
                         CapellacoreFactory.eINSTANCE.createEnumerationPropertyType());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS,
                         CapellacoreFactory.eINSTANCE.createPropertyValueGroup());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS,
                         FaFactory.eINSTANCE.createComponentExchangeFunctionalExchangeAllocation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS,
                         FaFactory.eINSTANCE.createComponentExchangeRealization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_ENDS,
                         FaFactory.eINSTANCE.createComponentExchangeEnd());
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
