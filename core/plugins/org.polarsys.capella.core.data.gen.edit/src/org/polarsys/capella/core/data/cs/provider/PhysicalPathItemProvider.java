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
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.PhysicalPath} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalPathItemProvider
	extends NamedElementItemProvider
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
	public PhysicalPathItemProvider(AdapterFactory adapterFactory) {
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

      addAllocatedComponentExchangesPropertyDescriptor(object);
      addInvolvingInvolvementsPropertyDescriptor(object);
      addInvolvedInvolvementsPropertyDescriptor(object);
      addInvolvedLinksPropertyDescriptor(object);
      addFirstPhysicalPathInvolvementsPropertyDescriptor(object);
      addRealizedPhysicalPathsPropertyDescriptor(object);
      addRealizingPhysicalPathsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Allocated Component Exchanges feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedComponentExchangesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ComponentExchangeAllocator_allocatedComponentExchanges_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ComponentExchangeAllocator_allocatedComponentExchanges_feature", "_UI_ComponentExchangeAllocator_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__ALLOCATED_COMPONENT_EXCHANGES,
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
   * This adds a property descriptor for the Involved Involvements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedInvolvementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InvolverElement_involvedInvolvements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InvolverElement_involvedInvolvements_feature", "_UI_InvolverElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS,
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
   * @deprecated See {@link org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks() model documentation} for details.
   * @generated
   */
	@Deprecated
	protected void addInvolvedLinksPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalPath_involvedLinks_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalPath_involvedLinks_feature", "_UI_PhysicalPath_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_PATH__INVOLVED_LINKS,
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
   * This adds a property descriptor for the First Physical Path Involvements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addFirstPhysicalPathInvolvementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalPath_firstPhysicalPathInvolvements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalPath_firstPhysicalPathInvolvements_feature", "_UI_PhysicalPath_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_PATH__FIRST_PHYSICAL_PATH_INVOLVEMENTS,
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
   * This adds a property descriptor for the Realized Physical Paths feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedPhysicalPathsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalPath_realizedPhysicalPaths_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalPath_realizedPhysicalPaths_feature", "_UI_PhysicalPath_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_PATH__REALIZED_PHYSICAL_PATHS,
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
   * This adds a property descriptor for the Realizing Physical Paths feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingPhysicalPathsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalPath_realizingPhysicalPaths_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalPath_realizingPhysicalPaths_feature", "_UI_PhysicalPath_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_PATH__REALIZING_PHYSICAL_PATHS,
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
      childrenFeatures.add(FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
      childrenFeatures.add(CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS);
      childrenFeatures.add(CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS);
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
   * This returns PhysicalPath.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalPath")); //$NON-NLS-1$
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
    String label = ((PhysicalPath)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_PhysicalPath_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(PhysicalPath.class)) {
      case CsPackage.PHYSICAL_PATH__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS:
      case CsPackage.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS:
      case CsPackage.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS:
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
                        (FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS,
                         FaFactory.eINSTANCE.createComponentExchangeAllocation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS,
                         CsFactory.eINSTANCE.createPhysicalPathInvolvement());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS,
                         CsFactory.eINSTANCE.createPhysicalPathReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS,
                         CsFactory.eINSTANCE.createPhysicalPathRealization());
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
