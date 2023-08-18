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
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.Component} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentItemProvider
	extends BlockItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ComponentItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
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

      addAbstractPropertyDescriptor(object);
      addSuperGeneralizationsPropertyDescriptor(object);
      addSubGeneralizationsPropertyDescriptor(object);
      addSuperPropertyDescriptor(object);
      addSubPropertyDescriptor(object);
      addContainedPropertiesPropertyDescriptor(object);
      addProvisionedInterfaceAllocationsPropertyDescriptor(object);
      addAllocatedInterfacesPropertyDescriptor(object);
      addProducePropertyDescriptor(object);
      addConsumePropertyDescriptor(object);
      addSendPropertyDescriptor(object);
      addReceivePropertyDescriptor(object);
      addCallPropertyDescriptor(object);
      addExecutePropertyDescriptor(object);
      addWritePropertyDescriptor(object);
      addAccessPropertyDescriptor(object);
      addAcquirePropertyDescriptor(object);
      addTransmitPropertyDescriptor(object);
      addActorPropertyDescriptor(object);
      addHumanPropertyDescriptor(object);
      addUsedInterfaceLinksPropertyDescriptor(object);
      addUsedInterfacesPropertyDescriptor(object);
      addImplementedInterfaceLinksPropertyDescriptor(object);
      addImplementedInterfacesPropertyDescriptor(object);
      addRealizedComponentsPropertyDescriptor(object);
      addRealizingComponentsPropertyDescriptor(object);
      addProvidedInterfacesPropertyDescriptor(object);
      addRequiredInterfacesPropertyDescriptor(object);
      addContainedComponentPortsPropertyDescriptor(object);
      addContainedPartsPropertyDescriptor(object);
      addContainedPhysicalPortsPropertyDescriptor(object);
      addRepresentingPartsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Contained Properties feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedPropertiesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Classifier_containedProperties_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Classifier_containedProperties_feature", "_UI_Classifier_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CLASSIFIER__CONTAINED_PROPERTIES,
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
   * This adds a property descriptor for the Abstract feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAbstractPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GeneralizableElement_abstract_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_GeneralizableElement_abstract_feature", "_UI_GeneralizableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT,
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
   * This adds a property descriptor for the Super Generalizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSuperGeneralizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GeneralizableElement_superGeneralizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_GeneralizableElement_superGeneralizations_feature", "_UI_GeneralizableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS,
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
   * This adds a property descriptor for the Sub Generalizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSubGeneralizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GeneralizableElement_subGeneralizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_GeneralizableElement_subGeneralizations_feature", "_UI_GeneralizableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS,
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
   * This adds a property descriptor for the Super feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSuperPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GeneralizableElement_super_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_GeneralizableElement_super_feature", "_UI_GeneralizableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER,
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
   * This adds a property descriptor for the Sub feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSubPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GeneralizableElement_sub_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_GeneralizableElement_sub_feature", "_UI_GeneralizableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB,
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
   * This adds a property descriptor for the Provisioned Interface Allocations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addProvisionedInterfaceAllocationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InterfaceAllocator_provisionedInterfaceAllocations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InterfaceAllocator_provisionedInterfaceAllocations_feature", "_UI_InterfaceAllocator_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS,
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
   * This adds a property descriptor for the Allocated Interfaces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedInterfacesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InterfaceAllocator_allocatedInterfaces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InterfaceAllocator_allocatedInterfaces_feature", "_UI_InterfaceAllocator_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES,
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
   * This adds a property descriptor for the Produce feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addProducePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_produce_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_produce_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE,
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
   * This adds a property descriptor for the Consume feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addConsumePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_consume_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_consume_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME,
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
   * This adds a property descriptor for the Send feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSendPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_send_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_send_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND,
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
   * This adds a property descriptor for the Receive feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReceivePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_receive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_receive_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE,
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
   * This adds a property descriptor for the Call feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addCallPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_call_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_call_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL,
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
   * This adds a property descriptor for the Execute feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExecutePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_execute_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_execute_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE,
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
   * This adds a property descriptor for the Write feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addWritePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_write_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_write_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE,
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
   * This adds a property descriptor for the Access feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAccessPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_access_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_access_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS,
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
   * This adds a property descriptor for the Acquire feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAcquirePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_acquire_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_acquire_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE,
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
   * This adds a property descriptor for the Transmit feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTransmitPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CommunicationLinkExchanger_transmit_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CommunicationLinkExchanger_transmit_feature", "_UI_CommunicationLinkExchanger_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT,
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
   * This adds a property descriptor for the Actor feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addActorPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_actor_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_actor_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__ACTOR,
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
   * This adds a property descriptor for the Human feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addHumanPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_human_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_human_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__HUMAN,
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
   * This adds a property descriptor for the Used Interface Links feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addUsedInterfaceLinksPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_usedInterfaceLinks_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_usedInterfaceLinks_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__USED_INTERFACE_LINKS,
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
   * This adds a property descriptor for the Used Interfaces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addUsedInterfacesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_usedInterfaces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_usedInterfaces_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__USED_INTERFACES,
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
   * This adds a property descriptor for the Implemented Interface Links feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addImplementedInterfaceLinksPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_implementedInterfaceLinks_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_implementedInterfaceLinks_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACE_LINKS,
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
   * This adds a property descriptor for the Implemented Interfaces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addImplementedInterfacesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_implementedInterfaces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_implementedInterfaces_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES,
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
   * This adds a property descriptor for the Realized Components feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedComponentsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_realizedComponents_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_realizedComponents_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__REALIZED_COMPONENTS,
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
   * This adds a property descriptor for the Realizing Components feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingComponentsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_realizingComponents_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_realizingComponents_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__REALIZING_COMPONENTS,
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
   * This adds a property descriptor for the Provided Interfaces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addProvidedInterfacesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_providedInterfaces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_providedInterfaces_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES,
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
   * This adds a property descriptor for the Required Interfaces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRequiredInterfacesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_requiredInterfaces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_requiredInterfaces_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES,
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
   * This adds a property descriptor for the Contained Component Ports feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedComponentPortsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_containedComponentPorts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_containedComponentPorts_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__CONTAINED_COMPONENT_PORTS,
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
         getString("_UI_Component_containedParts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_containedParts_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__CONTAINED_PARTS,
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
   * This adds a property descriptor for the Contained Physical Ports feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedPhysicalPortsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_containedPhysicalPorts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_containedPhysicalPorts_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__CONTAINED_PHYSICAL_PORTS,
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
   * This adds a property descriptor for the Representing Parts feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRepresentingPartsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Component_representingParts_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Component_representingParts_feature", "_UI_Component_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.COMPONENT__REPRESENTING_PARTS,
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
      childrenFeatures.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS);
      childrenFeatures.add(CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES);
      childrenFeatures.add(CsPackage.Literals.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS);
      childrenFeatures.add(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS);
      childrenFeatures.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_USES);
      childrenFeatures.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
      childrenFeatures.add(CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
      childrenFeatures.add(CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_PATH);
      childrenFeatures.add(CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_LINKS);
      childrenFeatures.add(CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES);
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
    String label = ((Component)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_Component_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(Component.class)) {
      case CsPackage.COMPONENT__ABSTRACT:
      case CsPackage.COMPONENT__ACTOR:
      case CsPackage.COMPONENT__HUMAN:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case CsPackage.COMPONENT__OWNED_GENERALIZATIONS:
      case CsPackage.COMPONENT__OWNED_FEATURES:
      case CsPackage.COMPONENT__OWNED_INTERFACE_ALLOCATIONS:
      case CsPackage.COMPONENT__OWNED_COMMUNICATION_LINKS:
      case CsPackage.COMPONENT__OWNED_INTERFACE_USES:
      case CsPackage.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS:
      case CsPackage.COMPONENT__OWNED_COMPONENT_REALIZATIONS:
      case CsPackage.COMPONENT__OWNED_PHYSICAL_PATH:
      case CsPackage.COMPONENT__OWNED_PHYSICAL_LINKS:
      case CsPackage.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES:
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
                        (CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS,
                         CapellacoreFactory.eINSTANCE.createGeneralization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         CsFactory.eINSTANCE.createPart());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         CsFactory.eINSTANCE.createPhysicalPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         OaFactory.eINSTANCE.createOperationalActivity());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         OaFactory.eINSTANCE.createRole());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         CtxFactory.eINSTANCE.createSystemFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         LaFactory.eINSTANCE.createLogicalFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         PaFactory.eINSTANCE.createPhysicalFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         InformationFactory.eINSTANCE.createProperty());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         InformationFactory.eINSTANCE.createService());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         InformationFactory.eINSTANCE.createUnionProperty());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         InformationFactory.eINSTANCE.createExchangeItemInstance());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         CommunicationFactory.eINSTANCE.createSignalInstance());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES,
                         FaFactory.eINSTANCE.createComponentPort());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS,
                         LaFactory.eINSTANCE.createContextInterfaceRealization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS,
                         PaFactory.eINSTANCE.createLogicalInterfaceRealization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS,
                         CommunicationFactory.eINSTANCE.createCommunicationLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.COMPONENT__OWNED_INTERFACE_USES,
                         CsFactory.eINSTANCE.createInterfaceUse());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS,
                         CsFactory.eINSTANCE.createInterfaceImplementation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS,
                         CsFactory.eINSTANCE.createComponentRealization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_PATH,
                         CsFactory.eINSTANCE.createPhysicalPath());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_LINKS,
                         CsFactory.eINSTANCE.createPhysicalLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES,
                         CsFactory.eINSTANCE.createPhysicalLinkCategory());
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
