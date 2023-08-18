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

package org.polarsys.capella.core.data.la.provider;

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
import org.polarsys.capella.core.data.cs.provider.ComponentArchitectureItemProvider;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.la.LogicalArchitecture} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LogicalArchitectureItemProvider
	extends ComponentArchitectureItemProvider
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
	protected IItemPropertyDescriptor containedCapabilityRealizationPkgPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor containedLogicalFunctionPkgPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LogicalArchitectureItemProvider(AdapterFactory adapterFactory) {
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
      // Process LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG
      if (containedCapabilityRealizationPkgPropertyDescriptor != null) {
        Object containedCapabilityRealizationPkgValue = eObject.eGet(LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG, true);
        if (containedCapabilityRealizationPkgValue != null && containedCapabilityRealizationPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedCapabilityRealizationPkgValue)) {
          itemPropertyDescriptors.remove(containedCapabilityRealizationPkgPropertyDescriptor);
        } else if (containedCapabilityRealizationPkgValue == null && ExtensionModelManager.getAnyType(eObject, LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG) != null) {
          itemPropertyDescriptors.remove(containedCapabilityRealizationPkgPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(containedCapabilityRealizationPkgPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(containedCapabilityRealizationPkgPropertyDescriptor);
        }
      }
      // Process LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG
      if (containedLogicalFunctionPkgPropertyDescriptor != null) {
        Object containedLogicalFunctionPkgValue = eObject.eGet(LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG, true);
        if (containedLogicalFunctionPkgValue != null && containedLogicalFunctionPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedLogicalFunctionPkgValue)) {
          itemPropertyDescriptors.remove(containedLogicalFunctionPkgPropertyDescriptor);
        } else if (containedLogicalFunctionPkgValue == null && ExtensionModelManager.getAnyType(eObject, LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG) != null) {
          itemPropertyDescriptors.remove(containedLogicalFunctionPkgPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(containedLogicalFunctionPkgPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(containedLogicalFunctionPkgPropertyDescriptor);
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

      addContainedCapabilityRealizationPkgPropertyDescriptor(object);
      addContainedLogicalFunctionPkgPropertyDescriptor(object);
      addAllocatedSystemAnalysisRealizationsPropertyDescriptor(object);
      addAllocatedSystemAnalysesPropertyDescriptor(object);
      addAllocatingPhysicalArchitecturesPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Contained Capability Realization Pkg feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedCapabilityRealizationPkgPropertyDescriptor(Object object) {
    // begin-extension-code
    containedCapabilityRealizationPkgPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalArchitecture_containedCapabilityRealizationPkg_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalArchitecture_containedCapabilityRealizationPkg_feature", "_UI_LogicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(containedCapabilityRealizationPkgPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Contained Logical Function Pkg feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedLogicalFunctionPkgPropertyDescriptor(Object object) {
    // begin-extension-code
    containedLogicalFunctionPkgPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalArchitecture_containedLogicalFunctionPkg_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalArchitecture_containedLogicalFunctionPkg_feature", "_UI_LogicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(containedLogicalFunctionPkgPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Allocated System Analysis Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedSystemAnalysisRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalArchitecture_allocatedSystemAnalysisRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalArchitecture_allocatedSystemAnalysisRealizations_feature", "_UI_LogicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS,
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
   * This adds a property descriptor for the Allocated System Analyses feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedSystemAnalysesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalArchitecture_allocatedSystemAnalyses_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalArchitecture_allocatedSystemAnalyses_feature", "_UI_LogicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES,
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
   * This adds a property descriptor for the Allocating Physical Architectures feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatingPhysicalArchitecturesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalArchitecture_allocatingPhysicalArchitectures_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalArchitecture_allocatingPhysicalArchitectures_feature", "_UI_LogicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES,
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
      childrenFeatures.add(LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG);
      childrenFeatures.add(LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS);
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
   * This returns LogicalArchitecture.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/LogicalArchitecture")); //$NON-NLS-1$
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
    String label = ((LogicalArchitecture)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_LogicalArchitecture_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(LogicalArchitecture.class)) {
      case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG:
      case LaPackage.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS:
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
                        (LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG,
                         LaFactory.eINSTANCE.createLogicalComponentPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS,
                         LaFactory.eINSTANCE.createSystemAnalysisRealization());
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
