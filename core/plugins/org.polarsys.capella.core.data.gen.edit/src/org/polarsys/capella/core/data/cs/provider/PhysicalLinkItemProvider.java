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
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.PhysicalLink} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalLinkItemProvider
	extends AbstractPhysicalPathLinkItemProvider
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
	protected IItemPropertyDescriptor sourcePhysicalPortPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor targetPhysicalPortPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalLinkItemProvider(AdapterFactory adapterFactory) {
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
			// Process CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT
			if (sourcePhysicalPortPropertyDescriptor != null) {
				Object sourcePhysicalPortValue = eObject.eGet(CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT, true);
				if (sourcePhysicalPortValue != null && sourcePhysicalPortValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourcePhysicalPortValue)) {
					itemPropertyDescriptors.remove(sourcePhysicalPortPropertyDescriptor);
				} else if (sourcePhysicalPortValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT) != null) {
					itemPropertyDescriptors.remove(sourcePhysicalPortPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(sourcePhysicalPortPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(sourcePhysicalPortPropertyDescriptor);
				}
			}
			// Process CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT
			if (targetPhysicalPortPropertyDescriptor != null) {
				Object targetPhysicalPortValue = eObject.eGet(CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT, true);
				if (targetPhysicalPortValue != null && targetPhysicalPortValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetPhysicalPortValue)) {
					itemPropertyDescriptors.remove(targetPhysicalPortPropertyDescriptor);
				} else if (targetPhysicalPortValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT) != null) {
					itemPropertyDescriptors.remove(targetPhysicalPortPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(targetPhysicalPortPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(targetPhysicalPortPropertyDescriptor);
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
			addInvolvingInvolvementsPropertyDescriptor(object);
			addLinkEndsPropertyDescriptor(object);
			addCategoriesPropertyDescriptor(object);
			addSourcePhysicalPortPropertyDescriptor(object);
			addTargetPhysicalPortPropertyDescriptor(object);
			addRealizedPhysicalLinksPropertyDescriptor(object);
			addRealizingPhysicalLinksPropertyDescriptor(object);
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
	 * This adds a property descriptor for the Link Ends feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLinkEndsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalLink_linkEnds_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLink_linkEnds_feature", "_UI_PhysicalLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.PHYSICAL_LINK__LINK_ENDS,
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
				 getString("_UI_PhysicalLink_categories_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLink_categories_feature", "_UI_PhysicalLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.PHYSICAL_LINK__CATEGORIES,
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
	 * This adds a property descriptor for the Source Physical Port feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourcePhysicalPortPropertyDescriptor(Object object) {
		// begin-extension-code
		sourcePhysicalPortPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalLink_sourcePhysicalPort_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLink_sourcePhysicalPort_feature", "_UI_PhysicalLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.PHYSICAL_LINK__SOURCE_PHYSICAL_PORT,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(sourcePhysicalPortPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Target Physical Port feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPhysicalPortPropertyDescriptor(Object object) {
		// begin-extension-code
		targetPhysicalPortPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalLink_targetPhysicalPort_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLink_targetPhysicalPort_feature", "_UI_PhysicalLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.PHYSICAL_LINK__TARGET_PHYSICAL_PORT,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(targetPhysicalPortPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Realized Physical Links feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizedPhysicalLinksPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalLink_realizedPhysicalLinks_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLink_realizedPhysicalLinks_feature", "_UI_PhysicalLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS,
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
	 * This adds a property descriptor for the Realizing Physical Links feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizingPhysicalLinksPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalLink_realizingPhysicalLinks_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLink_realizingPhysicalLinks_feature", "_UI_PhysicalLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS,
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
			childrenFeatures.add(CsPackage.Literals.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
			childrenFeatures.add(CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS);
			childrenFeatures.add(CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS);
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
	 * This returns PhysicalLink.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalLink")); //$NON-NLS-1$
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
		String label = ((PhysicalLink)object).getName();
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_PhysicalLink_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		switch (notification.getFeatureID(PhysicalLink.class)) {
			case CsPackage.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS:
			case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS:
			case CsPackage.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS:
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
                        (CsPackage.Literals.PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS,
                         FaFactory.eINSTANCE.createComponentExchangeFunctionalExchangeAllocation());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS,
                         CsFactory.eINSTANCE.createPhysicalLinkEnd());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS,
                         CsFactory.eINSTANCE.createPhysicalLinkRealization());
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
