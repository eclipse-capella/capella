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

package org.polarsys.capella.core.data.interaction.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.interaction.StateFragment} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StateFragmentItemProvider
	extends TimeLapseItemProvider
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
	protected IItemPropertyDescriptor relatedAbstractStatePropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor relatedAbstractFunctionPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateFragmentItemProvider(AdapterFactory adapterFactory) {
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
			// Process InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE
			if (relatedAbstractStatePropertyDescriptor != null) {
				Object relatedAbstractStateValue = eObject.eGet(InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE, true);
				if (relatedAbstractStateValue != null && relatedAbstractStateValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) relatedAbstractStateValue)) {
					itemPropertyDescriptors.remove(relatedAbstractStatePropertyDescriptor);
				} else if (relatedAbstractStateValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE) != null) {
					itemPropertyDescriptors.remove(relatedAbstractStatePropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(relatedAbstractStatePropertyDescriptor) == false) {
					itemPropertyDescriptors.add(relatedAbstractStatePropertyDescriptor);
				}
			}
			// Process InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION
			if (relatedAbstractFunctionPropertyDescriptor != null) {
				Object relatedAbstractFunctionValue = eObject.eGet(InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION, true);
				if (relatedAbstractFunctionValue != null && relatedAbstractFunctionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) relatedAbstractFunctionValue)) {
					itemPropertyDescriptors.remove(relatedAbstractFunctionPropertyDescriptor);
				} else if (relatedAbstractFunctionValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION) != null) {
					itemPropertyDescriptors.remove(relatedAbstractFunctionPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(relatedAbstractFunctionPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(relatedAbstractFunctionPropertyDescriptor);
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

			addRelatedAbstractStatePropertyDescriptor(object);
			addRelatedAbstractFunctionPropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Related Abstract State feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRelatedAbstractStatePropertyDescriptor(Object object) {
		// begin-extension-code
		relatedAbstractStatePropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StateFragment_relatedAbstractState_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_StateFragment_relatedAbstractState_feature", "_UI_StateFragment_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE,
				 true,
				 false,
				 true,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(relatedAbstractStatePropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Related Abstract Function feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRelatedAbstractFunctionPropertyDescriptor(Object object) {
		// begin-extension-code
		relatedAbstractFunctionPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StateFragment_relatedAbstractFunction_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_StateFragment_relatedAbstractFunction_feature", "_UI_StateFragment_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION,
				 true,
				 false,
				 true,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(relatedAbstractFunctionPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This returns StateFragment.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/StateFragment")); //$NON-NLS-1$
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
		String label = ((StateFragment)object).getName();
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_StateFragment_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
