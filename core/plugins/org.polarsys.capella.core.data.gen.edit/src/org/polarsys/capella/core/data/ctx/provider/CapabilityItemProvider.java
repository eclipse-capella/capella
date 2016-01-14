/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx.provider;

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
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.provider.AbstractCapabilityItemProvider;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.ctx.Capability} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CapabilityItemProvider
	extends AbstractCapabilityItemProvider
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
	protected IItemPropertyDescriptor involvedSystemPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor participatingSystemPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapabilityItemProvider(AdapterFactory adapterFactory) {
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
			// Process CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM
			if (involvedSystemPropertyDescriptor != null) {
				Object involvedSystemValue = eObject.eGet(CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM, true);
				if (involvedSystemValue != null && involvedSystemValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) involvedSystemValue)) {
					itemPropertyDescriptors.remove(involvedSystemPropertyDescriptor);
				} else if (involvedSystemValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM) != null) {
					itemPropertyDescriptors.remove(involvedSystemPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(involvedSystemPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(involvedSystemPropertyDescriptor);
				}
			}
			// Process CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM
			if (participatingSystemPropertyDescriptor != null) {
				Object participatingSystemValue = eObject.eGet(CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM, true);
				if (participatingSystemValue != null && participatingSystemValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) participatingSystemValue)) {
					itemPropertyDescriptors.remove(participatingSystemPropertyDescriptor);
				} else if (participatingSystemValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM) != null) {
					itemPropertyDescriptors.remove(participatingSystemPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(participatingSystemPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(participatingSystemPropertyDescriptor);
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

			addInvolvedActorsPropertyDescriptor(object);
			addInvolvedSystemPropertyDescriptor(object);
			addParticipatingActorsPropertyDescriptor(object);
			addParticipatingSystemPropertyDescriptor(object);
			addPurposesPropertyDescriptor(object);
			addPurposeMissionsPropertyDescriptor(object);
			addRealizedOperationalCapabilitiesPropertyDescriptor(object);
			addRealizingCapabilityRealizationsPropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Involved Actors feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInvolvedActorsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_involvedActors_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_involvedActors_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__INVOLVED_ACTORS,
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
	 * This adds a property descriptor for the Involved System feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInvolvedSystemPropertyDescriptor(Object object) {
		// begin-extension-code
		involvedSystemPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_involvedSystem_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_involvedSystem_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(involvedSystemPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Participating Actors feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParticipatingActorsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_participatingActors_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_participatingActors_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__PARTICIPATING_ACTORS,
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
	 * This adds a property descriptor for the Participating System feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParticipatingSystemPropertyDescriptor(Object object) {
		// begin-extension-code
		participatingSystemPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_participatingSystem_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_participatingSystem_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(participatingSystemPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Purposes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPurposesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_purposes_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_purposes_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__PURPOSES,
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
	 * This adds a property descriptor for the Purpose Missions feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPurposeMissionsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_purposeMissions_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_purposeMissions_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__PURPOSE_MISSIONS,
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
	 * This adds a property descriptor for the Realized Operational Capabilities feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizedOperationalCapabilitiesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_realizedOperationalCapabilities_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_realizedOperationalCapabilities_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES,
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
	 * This adds a property descriptor for the Realizing Capability Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizingCapabilityRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Capability_realizingCapabilityRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Capability_realizingCapabilityRealizations_feature", "_UI_Capability_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CtxPackage.Literals.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS,
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
			childrenFeatures.add(CtxPackage.Literals.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS);
			childrenFeatures.add(CtxPackage.Literals.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT);
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
	 * This returns Capability.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Capability")); //$NON-NLS-1$
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
		String label = ((Capability)object).getName();
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_Capability_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		switch (notification.getFeatureID(Capability.class)) {
			case CtxPackage.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS:
			case CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT:
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
                        (CtxPackage.Literals.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS,
                         CtxFactory.eINSTANCE.createActorCapabilityInvolvement());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CtxPackage.Literals.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT,
                         CtxFactory.eINSTANCE.createSystemCapabilityInvolvement());
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
	protected Command createInitializeCopyCommand(EditingDomain domain_p, EObject owner_p, Helper helper_p) {
		return new SharedInitializeCopyCommand(domain_p, owner_p, helper_p);
	}
	// end-capella-code
}
