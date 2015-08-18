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
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.ctx.Mission} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MissionItemProvider
	extends NamedElementItemProvider
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
	protected IItemPropertyDescriptor participatingSystemPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor involvedSystemPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public MissionItemProvider(AdapterFactory adapterFactory) {
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
      // Process CtxPackage.Literals.MISSION__PARTICIPATING_SYSTEM
      if (participatingSystemPropertyDescriptor != null) {
        Object participatingSystemValue = eObject.eGet(CtxPackage.Literals.MISSION__PARTICIPATING_SYSTEM, true);
        if (participatingSystemValue != null && participatingSystemValue instanceof EObject && ModelExtensionHelper.getInstance().isExtensionModelDisabled((EObject) participatingSystemValue)) {
          itemPropertyDescriptors.remove(participatingSystemPropertyDescriptor);
        } else if (participatingSystemValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.MISSION__PARTICIPATING_SYSTEM) != null) {
          itemPropertyDescriptors.remove(participatingSystemPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(participatingSystemPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(participatingSystemPropertyDescriptor);
        }
      }
      // Process CtxPackage.Literals.MISSION__INVOLVED_SYSTEM
      if (involvedSystemPropertyDescriptor != null) {
        Object involvedSystemValue = eObject.eGet(CtxPackage.Literals.MISSION__INVOLVED_SYSTEM, true);
        if (involvedSystemValue != null && involvedSystemValue instanceof EObject && ModelExtensionHelper.getInstance().isExtensionModelDisabled((EObject) involvedSystemValue)) {
          itemPropertyDescriptors.remove(involvedSystemPropertyDescriptor);
        } else if (involvedSystemValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.MISSION__INVOLVED_SYSTEM) != null) {
          itemPropertyDescriptors.remove(involvedSystemPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(involvedSystemPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(involvedSystemPropertyDescriptor);
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

      addInvolvedInvolvementsPropertyDescriptor(object);
      addParticipatingActorsPropertyDescriptor(object);
      addParticipatingSystemPropertyDescriptor(object);
      addInvolvedActorsPropertyDescriptor(object);
      addInvolvedSystemPropertyDescriptor(object);
      addExploitedCapabilitiesPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
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
         getString("_UI_Mission_participatingActors_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Mission_participatingActors_feature", "_UI_Mission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.MISSION__PARTICIPATING_ACTORS,
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
         getString("_UI_Mission_participatingSystem_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Mission_participatingSystem_feature", "_UI_Mission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.MISSION__PARTICIPATING_SYSTEM,
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
         getString("_UI_Mission_involvedActors_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Mission_involvedActors_feature", "_UI_Mission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.MISSION__INVOLVED_ACTORS,
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
         getString("_UI_Mission_involvedSystem_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Mission_involvedSystem_feature", "_UI_Mission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.MISSION__INVOLVED_SYSTEM,
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
   * This adds a property descriptor for the Exploited Capabilities feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExploitedCapabilitiesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Mission_exploitedCapabilities_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Mission_exploitedCapabilities_feature", "_UI_Mission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.MISSION__EXPLOITED_CAPABILITIES,
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
      childrenFeatures.add(CtxPackage.Literals.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS);
      childrenFeatures.add(CtxPackage.Literals.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT);
      childrenFeatures.add(CtxPackage.Literals.MISSION__OWNED_CAPABILITY_EXPLOITATIONS);
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
   * This returns Mission.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Mission")); //$NON-NLS-1$
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
    String label = ((Mission)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_Mission_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(Mission.class)) {
      case CtxPackage.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS:
      case CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT:
      case CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS:
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
                        (CtxPackage.Literals.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS,
                         CtxFactory.eINSTANCE.createActorMissionInvolvement());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CtxPackage.Literals.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT,
                         CtxFactory.eINSTANCE.createSystemMissionInvolvement());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CtxPackage.Literals.MISSION__OWNED_CAPABILITY_EXPLOITATIONS,
                         CtxFactory.eINSTANCE.createCapabilityExploitation());
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
