/**
 *
 *  Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.fa.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
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
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.kitalpha.emde.extension.edit.ExtensionItemProviderAdapter;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext} object.
 * <!-- begin-user-doc -->
 * @superClass ExtensionItemProviderAdapter
 * <!-- end-user-doc -->
 * @generated
 */
public class ReferenceHierarchyContextItemProvider
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
	public ReferenceHierarchyContextItemProvider(AdapterFactory adapterFactory) {
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

			addSourceReferenceHierarchyPropertyDescriptor(object);
			addTargetReferenceHierarchyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Source Reference Hierarchy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceReferenceHierarchyPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ReferenceHierarchyContext_sourceReferenceHierarchy_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ReferenceHierarchyContext_sourceReferenceHierarchy_feature", "_UI_ReferenceHierarchyContext_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY,
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
	 * This adds a property descriptor for the Target Reference Hierarchy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetReferenceHierarchyPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ReferenceHierarchyContext_targetReferenceHierarchy_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ReferenceHierarchyContext_targetReferenceHierarchy_feature", "_UI_ReferenceHierarchyContext_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY,
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
	 String[] result = new String[] { null };

		//begin-capella-code
		result[0] = "[" + getString("_UI_ReferenceHierarchyContext_type") + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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