/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.provider;

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
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.common.re.CatalogElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CatalogElementItemProvider extends
		ReDescriptionElementItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CatalogElementItemProvider(AdapterFactory adapterFactory) {
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

			addKindPropertyDescriptor(object);
			addAuthorPropertyDescriptor(object);
			addEnvironmentPropertyDescriptor(object);
			addTagsPropertyDescriptor(object);
			addOriginPropertyDescriptor(object);
			addCurrentCompliancyPropertyDescriptor(object);
			addDefaultReplicaCompliancyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
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
				 getString("_UI_CatalogElement_kind_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_kind_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RePackage.Literals.CATALOG_ELEMENT__KIND,
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
	 * This adds a property descriptor for the Author feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAuthorPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CatalogElement_author_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_author_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RePackage.Literals.CATALOG_ELEMENT__AUTHOR,
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
	 * This adds a property descriptor for the Environment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEnvironmentPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CatalogElement_environment_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_environment_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RePackage.Literals.CATALOG_ELEMENT__ENVIRONMENT,
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
	 * This adds a property descriptor for the Tags feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTagsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CatalogElement_tags_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_tags_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RePackage.Literals.CATALOG_ELEMENT__TAGS,
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
	 * This adds a property descriptor for the Origin feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOriginPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CatalogElement_origin_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_origin_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RePackage.Literals.CATALOG_ELEMENT__ORIGIN,
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
	 * This adds a property descriptor for the Current Compliancy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCurrentCompliancyPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CatalogElement_currentCompliancy_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_currentCompliancy_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RePackage.Literals.CATALOG_ELEMENT__CURRENT_COMPLIANCY,
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
	 * This adds a property descriptor for the Default Replica Compliancy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDefaultReplicaCompliancyPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CatalogElement_defaultReplicaCompliancy_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_defaultReplicaCompliancy_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RePackage.Literals.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY,
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
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(RePackage.Literals.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS);
			childrenFeatures.add(RePackage.Literals.CATALOG_ELEMENT__OWNED_LINKS);
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
	 * This returns CatalogElement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {

		if (object instanceof CatalogElement) {
			if (((CatalogElement) object).getKind() == CatalogElementKind.REC) {
				return overlayImage(
						object,
						getResourceLocator().getImage(
								"full/obj16/ReplicableElement")); //$NON-NLS-1$
			} else if (((CatalogElement) object).getKind() == CatalogElementKind.RPL) {
				return overlayImage(object,
						getResourceLocator().getImage("full/obj16/Replica")); //$NON-NLS-1$
			} else if (((CatalogElement) object).getKind() == CatalogElementKind.REC_RPL) {
				return overlayImage(
						object,
						getResourceLocator().getImage(
								"full/obj16/ReplicableElementReplica")); //$NON-NLS-1$
			} else if (((CatalogElement) object).getKind() == CatalogElementKind.GATHERING) {
				return overlayImage(
						object,
						getResourceLocator().getImage(
								"full/obj16/GatheringElement")); //$NON-NLS-1$
			}
		}
		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/CatalogElement")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {

		CatalogElement element = (CatalogElement) object;
		// begin-extension-code

		String label = element.getName();
		label = (label == null) || (label.length() == 0) ? "["
				+ getString("_UI_CatalogElement_type") + "]" : label;

		if (element.getOrigin() != null) {
			String labelOrigin = element.getOrigin().getName();
			label += NLS.bind(" [{0}]", labelOrigin);
		}

		return label;
		// end-extension-code
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

		switch (notification.getFeatureID(CatalogElement.class)) {
			case RePackage.CATALOG_ELEMENT__KIND:
			case RePackage.CATALOG_ELEMENT__AUTHOR:
			case RePackage.CATALOG_ELEMENT__ENVIRONMENT:
			case RePackage.CATALOG_ELEMENT__TAGS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS:
			case RePackage.CATALOG_ELEMENT__OWNED_LINKS:
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
                        (RePackage.Literals.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS,
                         ReFactory.eINSTANCE.createCatalogElement());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (RePackage.Literals.CATALOG_ELEMENT__OWNED_LINKS,
                         ReFactory.eINSTANCE.createCatalogElementLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Command createInitializeCopyCommand(EditingDomain domain_p, EObject owner_p, Helper helper_p) {
		return new SharedInitializeCopyCommand(domain_p, owner_p, helper_p);
	}


}