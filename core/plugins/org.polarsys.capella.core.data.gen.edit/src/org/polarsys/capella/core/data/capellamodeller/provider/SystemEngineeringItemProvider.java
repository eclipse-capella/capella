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

package org.polarsys.capella.core.data.capellamodeller.provider;

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
import org.polarsys.capella.core.data.capellacore.provider.AbstractModellingStructureItemProvider;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SystemEngineeringItemProvider
	extends AbstractModellingStructureItemProvider
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
	public SystemEngineeringItemProvider(AdapterFactory adapterFactory) {
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

      addContainedOperationalAnalysisPropertyDescriptor(object);
      addContainedSystemAnalysisPropertyDescriptor(object);
      addContainedLogicalArchitecturesPropertyDescriptor(object);
      addContainedPhysicalArchitecturesPropertyDescriptor(object);
      addContainedEPBSArchitecturesPropertyDescriptor(object);
      addContainedSharedPkgsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Contained Operational Analysis feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedOperationalAnalysisPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemEngineering_containedOperationalAnalysis_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemEngineering_containedOperationalAnalysis_feature", "_UI_SystemEngineering_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS,
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
   * This adds a property descriptor for the Contained System Analysis feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedSystemAnalysisPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemEngineering_containedSystemAnalysis_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemEngineering_containedSystemAnalysis_feature", "_UI_SystemEngineering_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS,
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
   * This adds a property descriptor for the Contained Logical Architectures feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedLogicalArchitecturesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemEngineering_containedLogicalArchitectures_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemEngineering_containedLogicalArchitectures_feature", "_UI_SystemEngineering_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES,
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
   * This adds a property descriptor for the Contained Physical Architectures feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedPhysicalArchitecturesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemEngineering_containedPhysicalArchitectures_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemEngineering_containedPhysicalArchitectures_feature", "_UI_SystemEngineering_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES,
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
   * This adds a property descriptor for the Contained EPBS Architectures feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedEPBSArchitecturesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemEngineering_containedEPBSArchitectures_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemEngineering_containedEPBSArchitectures_feature", "_UI_SystemEngineering_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES,
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
   * This adds a property descriptor for the Contained Shared Pkgs feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedSharedPkgsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemEngineering_containedSharedPkgs_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemEngineering_containedSharedPkgs_feature", "_UI_SystemEngineering_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS,
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
   * This returns SystemEngineering.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImageGen(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/SystemEngineering")); //$NON-NLS-1$
  }

  /**
   * This returns SystemEngineering.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  @Override
  public Object getImage(Object object) {
    SystemEngineering sys = (SystemEngineering) object;
    EObject owner = sys.eContainer();
    if (owner instanceof Library) {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/SystemEngineeringInLibrary")); //$NON-NLS-1$
    }
    return overlayImage(object, getResourceLocator().getImage("full/obj16/SystemEngineering")); //$NON-NLS-1$
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
    String label = ((SystemEngineering)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_SystemEngineering_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
