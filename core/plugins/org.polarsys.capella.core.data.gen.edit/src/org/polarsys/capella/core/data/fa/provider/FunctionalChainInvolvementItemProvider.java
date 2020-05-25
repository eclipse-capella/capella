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
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.provider.RelationshipItemProvider;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FunctionalChainInvolvementItemProvider
	extends RelationshipItemProvider
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
	protected IItemPropertyDescriptor involverPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor involvedPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor involvedElementPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionalChainInvolvementItemProvider(AdapterFactory adapterFactory) {
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
			// Process CapellacorePackage.Literals.INVOLVEMENT__INVOLVER
			if (involverPropertyDescriptor != null) {
				Object involverValue = eObject.eGet(CapellacorePackage.Literals.INVOLVEMENT__INVOLVER, true);
				if (involverValue != null && involverValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) involverValue)) {
					itemPropertyDescriptors.remove(involverPropertyDescriptor);
				} else if (involverValue == null && ExtensionModelManager.getAnyType(eObject, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER) != null) {
					itemPropertyDescriptors.remove(involverPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(involverPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(involverPropertyDescriptor);
				}
			}
			// Process CapellacorePackage.Literals.INVOLVEMENT__INVOLVED
			if (involvedPropertyDescriptor != null) {
				Object involvedValue = eObject.eGet(CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, true);
				if (involvedValue != null && involvedValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) involvedValue)) {
					itemPropertyDescriptors.remove(involvedPropertyDescriptor);
				} else if (involvedValue == null && ExtensionModelManager.getAnyType(eObject, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED) != null) {
					itemPropertyDescriptors.remove(involvedPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(involvedPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(involvedPropertyDescriptor);
				}
			}
			// Process FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT
			if (involvedElementPropertyDescriptor != null) {
				Object involvedElementValue = eObject.eGet(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT, true);
				if (involvedElementValue != null && involvedElementValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) involvedElementValue)) {
					itemPropertyDescriptors.remove(involvedElementPropertyDescriptor);
				} else if (involvedElementValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT) != null) {
					itemPropertyDescriptors.remove(involvedElementPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(involvedElementPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(involvedElementPropertyDescriptor);
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

			addInvolverPropertyDescriptor(object);
			addInvolvedPropertyDescriptor(object);
			addNextFunctionalChainInvolvementsPropertyDescriptor(object);
			addPreviousFunctionalChainInvolvementsPropertyDescriptor(object);
			addInvolvedElementPropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Involver feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInvolverPropertyDescriptor(Object object) {
		// begin-extension-code
		involverPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Involvement_involver_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Involvement_involver_feature", "_UI_Involvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.INVOLVEMENT__INVOLVER,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(involverPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Involved feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInvolvedPropertyDescriptor(Object object) {
		// begin-extension-code
		involvedPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Involvement_involved_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Involvement_involved_feature", "_UI_Involvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CapellacorePackage.Literals.INVOLVEMENT__INVOLVED,
				 true,
				 false,
				 true,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(involvedPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Next Functional Chain Involvements feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNextFunctionalChainInvolvementsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FunctionalChainInvolvement_nextFunctionalChainInvolvements_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChainInvolvement_nextFunctionalChainInvolvements_feature", "_UI_FunctionalChainInvolvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS,
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
	 * This adds a property descriptor for the Previous Functional Chain Involvements feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPreviousFunctionalChainInvolvementsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FunctionalChainInvolvement_previousFunctionalChainInvolvements_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChainInvolvement_previousFunctionalChainInvolvements_feature", "_UI_FunctionalChainInvolvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS,
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
	 * This adds a property descriptor for the Involved Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInvolvedElementPropertyDescriptor(Object object) {
		// begin-extension-code
		involvedElementPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FunctionalChainInvolvement_involvedElement_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChainInvolvement_involvedElement_feature", "_UI_FunctionalChainInvolvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(involvedElementPropertyDescriptor);
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
        String label = ""; //$NON-NLS-1$
        String targetName = ""; //$NON-NLS-1$
        EObject target = null;

 		target = ((Involvement) object).getInvolved();
	
	 	if (null != target) {
			if (target instanceof AbstractNamedElement) {
				targetName = ((AbstractNamedElement) target).getName();
			}

			if (null == targetName || "" == targetName) { //$NON-NLS-1$
				targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
			}
	 	}
	 	label = "[" + getString("_UI_FunctionalChainInvolvement_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_FunctionalChainInvolvement_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
