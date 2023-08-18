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
import org.polarsys.capella.core.data.cs.provider.ComponentArchitectureItemProvider;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.ctx.SystemAnalysis} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SystemAnalysisItemProvider
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
	protected IItemPropertyDescriptor containedCapabilityPkgPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor containedSystemFunctionPkgPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SystemAnalysisItemProvider(AdapterFactory adapterFactory) {
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
      // Process CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG
      if (containedCapabilityPkgPropertyDescriptor != null) {
        Object containedCapabilityPkgValue = eObject.eGet(CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG, true);
        if (containedCapabilityPkgValue != null && containedCapabilityPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedCapabilityPkgValue)) {
          itemPropertyDescriptors.remove(containedCapabilityPkgPropertyDescriptor);
        } else if (containedCapabilityPkgValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG) != null) {
          itemPropertyDescriptors.remove(containedCapabilityPkgPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(containedCapabilityPkgPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(containedCapabilityPkgPropertyDescriptor);
        }
      }
      // Process CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG
      if (containedSystemFunctionPkgPropertyDescriptor != null) {
        Object containedSystemFunctionPkgValue = eObject.eGet(CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG, true);
        if (containedSystemFunctionPkgValue != null && containedSystemFunctionPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedSystemFunctionPkgValue)) {
          itemPropertyDescriptors.remove(containedSystemFunctionPkgPropertyDescriptor);
        } else if (containedSystemFunctionPkgValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG) != null) {
          itemPropertyDescriptors.remove(containedSystemFunctionPkgPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(containedSystemFunctionPkgPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(containedSystemFunctionPkgPropertyDescriptor);
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

      addContainedCapabilityPkgPropertyDescriptor(object);
      addContainedSystemFunctionPkgPropertyDescriptor(object);
      addAllocatedOperationalAnalysisRealizationsPropertyDescriptor(object);
      addAllocatedOperationalAnalysesPropertyDescriptor(object);
      addAllocatingLogicalArchitecturesPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Contained Capability Pkg feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedCapabilityPkgPropertyDescriptor(Object object) {
    // begin-extension-code
    containedCapabilityPkgPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemAnalysis_containedCapabilityPkg_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemAnalysis_containedCapabilityPkg_feature", "_UI_SystemAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(containedCapabilityPkgPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Contained System Function Pkg feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedSystemFunctionPkgPropertyDescriptor(Object object) {
    // begin-extension-code
    containedSystemFunctionPkgPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemAnalysis_containedSystemFunctionPkg_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemAnalysis_containedSystemFunctionPkg_feature", "_UI_SystemAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(containedSystemFunctionPkgPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Allocated Operational Analysis Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedOperationalAnalysisRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemAnalysis_allocatedOperationalAnalysisRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemAnalysis_allocatedOperationalAnalysisRealizations_feature", "_UI_SystemAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS,
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
   * This adds a property descriptor for the Allocated Operational Analyses feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatedOperationalAnalysesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemAnalysis_allocatedOperationalAnalyses_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemAnalysis_allocatedOperationalAnalyses_feature", "_UI_SystemAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES,
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
   * This adds a property descriptor for the Allocating Logical Architectures feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatingLogicalArchitecturesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SystemAnalysis_allocatingLogicalArchitectures_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_SystemAnalysis_allocatingLogicalArchitectures_feature", "_UI_SystemAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES,
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
      childrenFeatures.add(CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG);
      childrenFeatures.add(CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_MISSION_PKG);
      childrenFeatures.add(CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS);
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
   * This returns SystemAnalysis.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/SystemAnalysis")); //$NON-NLS-1$
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
    String label = ((SystemAnalysis)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_SystemAnalysis_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(SystemAnalysis.class)) {
      case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG:
      case CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG:
      case CtxPackage.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS:
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
                        (CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG,
                         CtxFactory.eINSTANCE.createSystemComponentPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_MISSION_PKG,
                         CtxFactory.eINSTANCE.createMissionPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS,
                         CtxFactory.eINSTANCE.createOperationalAnalysisRealization());
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
