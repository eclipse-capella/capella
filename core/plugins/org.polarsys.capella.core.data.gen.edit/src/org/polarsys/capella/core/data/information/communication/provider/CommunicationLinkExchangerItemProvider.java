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

package org.polarsys.capella.core.data.information.communication.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.kitalpha.emde.extension.edit.ExtensionItemProviderAdapter;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger} object.
 * <!-- begin-user-doc -->
 * @superClass ExtensionItemProviderAdapter
 * <!-- end-user-doc -->
 * @generated
 */
public class CommunicationLinkExchangerItemProvider
	extends ExtensionItemProviderAdapter
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
	public CommunicationLinkExchangerItemProvider(AdapterFactory adapterFactory) {
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
    }
    return itemPropertyDescriptors;
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
      childrenFeatures.add(CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS);
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
    result[0] = "[" + getString("_UI_CommunicationLinkExchanger_type") + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(CommunicationLinkExchanger.class)) {
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS:
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

                newChildDescriptors.add
                    (createChildParameter
                        (CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS,
                         CommunicationFactory.eINSTANCE.createCommunicationLink()));


  }

	/**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ResourceLocator getResourceLocator() {
    return ((IChildCreationExtender)adapterFactory).getResourceLocator();
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
