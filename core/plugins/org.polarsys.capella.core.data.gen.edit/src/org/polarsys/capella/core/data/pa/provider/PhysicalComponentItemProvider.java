/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.pa.provider;

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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.pa.PhysicalComponent} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalComponentItemProvider extends AbstractPhysicalComponentItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
    ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  /**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalComponentItemProvider(AdapterFactory adapterFactory) {
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

			addAllocatorConfigurationItemsPropertyDescriptor(object);
			addLogicalComponentRealizationsPropertyDescriptor(object);
			addLogicalInterfaceRealizationsPropertyDescriptor(object);
			addSubPhysicalComponentsPropertyDescriptor(object);
			addRealizedLogicalComponentsPropertyDescriptor(object);
			addAllocatedPhysicalFunctionsPropertyDescriptor(object);
			addDeployedPhysicalComponentsPropertyDescriptor(object);
			addDeployingPhysicalComponentsPropertyDescriptor(object);
			addDeployingPhysicalActorsPropertyDescriptor(object);
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
	 * This adds a property descriptor for the Logical Component Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLogicalComponentRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_logicalComponentRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_logicalComponentRealizations_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS,
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
	 * This adds a property descriptor for the Logical Interface Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLogicalInterfaceRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_logicalInterfaceRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_logicalInterfaceRealizations_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS,
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
	 * This adds a property descriptor for the Sub Physical Components feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSubPhysicalComponentsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_subPhysicalComponents_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_subPhysicalComponents_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS,
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
	 * This adds a property descriptor for the Realized Logical Components feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRealizedLogicalComponentsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_realizedLogicalComponents_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_realizedLogicalComponents_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS,
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
	 * This adds a property descriptor for the Allocated Physical Functions feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatedPhysicalFunctionsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_allocatedPhysicalFunctions_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_allocatedPhysicalFunctions_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS,
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
	 * This adds a property descriptor for the Deployed Physical Components feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeployedPhysicalComponentsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_deployedPhysicalComponents_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_deployedPhysicalComponents_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS,
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
	 * This adds a property descriptor for the Deploying Physical Components feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeployingPhysicalComponentsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_deployingPhysicalComponents_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_deployingPhysicalComponents_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS,
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
	 * This adds a property descriptor for the Deploying Physical Actors feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeployingPhysicalActorsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalComponent_deployingPhysicalActors_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalComponent_deployingPhysicalActors_feature", "_UI_PhysicalComponent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS,
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
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS);
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS);
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS);
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
	 * This returns PhysicalComponent.gif.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Object getImageGen(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalComponent")); //$NON-NLS-1$
	}

  /**
   * This returns PhysicalComponent.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  @Override
  public Object getImage(Object object) {
    PhysicalComponent pc = (PhysicalComponent) object;
    if (pc.getNature().equals(PhysicalComponentNature.NODE)) {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalComponentNode")); //$NON-NLS-1$
    }
    return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalComponent")); //$NON-NLS-1$
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
		String label = ((PhysicalComponent)object).getName();
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_PhysicalComponent_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		switch (notification.getFeatureID(PhysicalComponent.class)) {
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
			case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
			case PaPackage.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS:
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
                        (PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS,
                         PaFactory.eINSTANCE.createPhysicalComponent());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS,
                         PaFactory.eINSTANCE.createPhysicalNode());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS,
                         PaFactory.eINSTANCE.createPhysicalComponentPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS,
                         PaFactory.eINSTANCE.createLogicalComponentRealization());
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
	protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, Helper helper) {
		return new SharedInitializeCopyCommand(domain, owner, helper);
	}
}
