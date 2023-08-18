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
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.provider.PortItemProvider;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.PhysicalPort} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalPortItemProvider
	extends PortItemProvider
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
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PhysicalPortItemProvider(AdapterFactory adapterFactory) {
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

      addAllocatorConfigurationItemsPropertyDescriptor(object);
      addIncomingInformationFlowsPropertyDescriptor(object);
      addOutgoingInformationFlowsPropertyDescriptor(object);
      addInformationFlowsPropertyDescriptor(object);
      addInvolvedLinksPropertyDescriptor(object);
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
      addAllocatedComponentPortsPropertyDescriptor(object);
      addRealizedPhysicalPortsPropertyDescriptor(object);
      addRealizingPhysicalPortsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Allocator Configuration Items feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatorConfigurationItemsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractPhysicalArtifact_allocatorConfigurationItems_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractPhysicalArtifact_allocatorConfigurationItems_feature", "_UI_AbstractPhysicalArtifact_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS,
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
   * This adds a property descriptor for the Incoming Information Flows feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIncomingInformationFlowsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InformationsExchanger_incomingInformationFlows_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InformationsExchanger_incomingInformationFlows_feature", "_UI_InformationsExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS,
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
   * This adds a property descriptor for the Outgoing Information Flows feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOutgoingInformationFlowsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InformationsExchanger_outgoingInformationFlows_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InformationsExchanger_outgoingInformationFlows_feature", "_UI_InformationsExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS,
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
   * This adds a property descriptor for the Information Flows feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInformationFlowsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InformationsExchanger_informationFlows_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InformationsExchanger_informationFlows_feature", "_UI_InformationsExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS,
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
   * This adds a property descriptor for the Involved Links feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedLinksPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractPhysicalLinkEnd_involvedLinks_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractPhysicalLinkEnd_involvedLinks_feature", "_UI_AbstractPhysicalLinkEnd_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS,
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
   * This adds a property descriptor for the Allocated Component Ports feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedComponentPortsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalPort_allocatedComponentPorts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalPort_allocatedComponentPorts_feature", "_UI_PhysicalPort_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS,
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
   * This adds a property descriptor for the Realized Physical Ports feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedPhysicalPortsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalPort_realizedPhysicalPorts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalPort_realizedPhysicalPorts_feature", "_UI_PhysicalPort_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS,
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
   * This adds a property descriptor for the Realizing Physical Ports feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingPhysicalPortsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalPort_realizingPhysicalPorts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalPort_realizingPhysicalPorts_feature", "_UI_PhysicalPort_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS,
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
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH);
      childrenFeatures.add(CsPackage.Literals.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS);
      childrenFeatures.add(CsPackage.Literals.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS);
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
   * This returns PhysicalPort.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalPort")); //$NON-NLS-1$
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
    String label = ((PhysicalPort)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_PhysicalPort_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(PhysicalPort.class)) {
      case CsPackage.PHYSICAL_PORT__IS_ABSTRACT:
      case CsPackage.PHYSICAL_PORT__IS_STATIC:
      case CsPackage.PHYSICAL_PORT__VISIBILITY:
      case CsPackage.PHYSICAL_PORT__ORDERED:
      case CsPackage.PHYSICAL_PORT__UNIQUE:
      case CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE:
      case CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE:
      case CsPackage.PHYSICAL_PORT__FINAL:
      case CsPackage.PHYSICAL_PORT__AGGREGATION_KIND:
      case CsPackage.PHYSICAL_PORT__IS_DERIVED:
      case CsPackage.PHYSICAL_PORT__IS_READ_ONLY:
      case CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE:
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE:
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE:
      case CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE:
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD:
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH:
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD:
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH:
      case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
      case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
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
                        (CsPackage.Literals.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS,
                         FaFactory.eINSTANCE.createComponentPortAllocation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS,
                         CsFactory.eINSTANCE.createPhysicalPortRealization());
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
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH;

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
