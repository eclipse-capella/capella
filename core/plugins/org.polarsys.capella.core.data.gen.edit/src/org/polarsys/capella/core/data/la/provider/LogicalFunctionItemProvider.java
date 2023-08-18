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
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.provider.AbstractFunctionItemProvider;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.la.LogicalFunction} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LogicalFunctionItemProvider
	extends AbstractFunctionItemProvider
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
	public LogicalFunctionItemProvider(AdapterFactory adapterFactory) {
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

      addAllocatingLogicalComponentsPropertyDescriptor(object);
      addRealizedSystemFunctionsPropertyDescriptor(object);
      addRealizingPhysicalFunctionsPropertyDescriptor(object);
      addContainedLogicalFunctionsPropertyDescriptor(object);
      addChildrenLogicalFunctionsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Allocating Logical Components feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAllocatingLogicalComponentsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalFunction_allocatingLogicalComponents_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalFunction_allocatingLogicalComponents_feature", "_UI_LogicalFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS,
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
   * This adds a property descriptor for the Realized System Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedSystemFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalFunction_realizedSystemFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalFunction_realizedSystemFunctions_feature", "_UI_LogicalFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS,
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
   * This adds a property descriptor for the Realizing Physical Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingPhysicalFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalFunction_realizingPhysicalFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalFunction_realizingPhysicalFunctions_feature", "_UI_LogicalFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS,
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
   * This adds a property descriptor for the Contained Logical Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedLogicalFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalFunction_containedLogicalFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalFunction_containedLogicalFunctions_feature", "_UI_LogicalFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS,
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
   * This adds a property descriptor for the Children Logical Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addChildrenLogicalFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LogicalFunction_childrenLogicalFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LogicalFunction_childrenLogicalFunctions_feature", "_UI_LogicalFunction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LaPackage.Literals.LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS,
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
      childrenFeatures.add(LaPackage.Literals.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS);
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
   * This returns LogicalFunction.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Object getImageGen(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/LogicalFunction")); //$NON-NLS-1$
  }

  /**
   * This returns LogicalFunction.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  @Override
  public Object getImage(Object object) {
    LogicalFunction fct = (LogicalFunction) object;
    String imagePath = "full/obj16/LogicalFunction"; //$NON-NLS-1$
    if (FunctionKind.DUPLICATE.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Duplicate"; //$NON-NLS-1$
    } else if (FunctionKind.GATHER.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Gather"; //$NON-NLS-1$
    } else if (FunctionKind.ROUTE.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Route"; //$NON-NLS-1$
    } else if (FunctionKind.SELECT.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Select"; //$NON-NLS-1$
    } else if (FunctionKind.SPLIT.equals(fct.getKind())) {
      imagePath = "full/obj16/FunctionKind_Split"; //$NON-NLS-1$
    }
    return overlayImage(object, getResourceLocator().getImage(imagePath));
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
    String label = ((LogicalFunction)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_LogicalFunction_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(LogicalFunction.class)) {
      case LaPackage.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS:
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
                        (LaPackage.Literals.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS,
                         LaFactory.eINSTANCE.createLogicalFunctionPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


  }

		/**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
    Object childFeature = feature;
    Object childObject = child;

    boolean qualify =
      childFeature == ModellingcorePackage.Literals.MODEL_ELEMENT__OWNED_CONSTRAINTS ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_PRECONDITION ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_POSTCONDITION ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS ||
      childFeature == ActivityPackage.Literals.INVOCATION_ACTION__ARGUMENTS ||
      childFeature == ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS ||
      childFeature == ActivityPackage.Literals.CALL_ACTION__RESULTS;

    if (qualify) {
      return getString
        ("_UI_CreateChild_text2", //$NON-NLS-1$
         new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner, feature, child, selection);
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
