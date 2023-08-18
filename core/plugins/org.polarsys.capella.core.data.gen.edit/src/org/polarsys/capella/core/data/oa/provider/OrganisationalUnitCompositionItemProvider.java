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

package org.polarsys.capella.core.data.oa.provider;

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
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OrganisationalUnitComposition;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OrganisationalUnitCompositionItemProvider
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
	protected IItemPropertyDescriptor organisationalUnitPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor participatingEntityPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OrganisationalUnitCompositionItemProvider(AdapterFactory adapterFactory) {
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
      // Process OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT
      if (organisationalUnitPropertyDescriptor != null) {
        Object organisationalUnitValue = eObject.eGet(OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT, true);
        if (organisationalUnitValue != null && organisationalUnitValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) organisationalUnitValue)) {
          itemPropertyDescriptors.remove(organisationalUnitPropertyDescriptor);
        } else if (organisationalUnitValue == null && ExtensionModelManager.getAnyType(eObject, OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT) != null) {
          itemPropertyDescriptors.remove(organisationalUnitPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(organisationalUnitPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(organisationalUnitPropertyDescriptor);
        }
      }
      // Process OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY
      if (participatingEntityPropertyDescriptor != null) {
        Object participatingEntityValue = eObject.eGet(OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY, true);
        if (participatingEntityValue != null && participatingEntityValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) participatingEntityValue)) {
          itemPropertyDescriptors.remove(participatingEntityPropertyDescriptor);
        } else if (participatingEntityValue == null && ExtensionModelManager.getAnyType(eObject, OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY) != null) {
          itemPropertyDescriptors.remove(participatingEntityPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(participatingEntityPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(participatingEntityPropertyDescriptor);
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

      addOrganisationalUnitPropertyDescriptor(object);
      addParticipatingEntityPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Organisational Unit feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOrganisationalUnitPropertyDescriptor(Object object) {
    // begin-extension-code
    organisationalUnitPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_OrganisationalUnitComposition_organisationalUnit_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_OrganisationalUnitComposition_organisationalUnit_feature", "_UI_OrganisationalUnitComposition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(organisationalUnitPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Participating Entity feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addParticipatingEntityPropertyDescriptor(Object object) {
    // begin-extension-code
    participatingEntityPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_OrganisationalUnitComposition_participatingEntity_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_OrganisationalUnitComposition_participatingEntity_feature", "_UI_OrganisationalUnitComposition_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(participatingEntityPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns OrganisationalUnitComposition.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/OrganisationalUnitComposition")); //$NON-NLS-1$
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
    String label = ((OrganisationalUnitComposition)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_OrganisationalUnitComposition_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
