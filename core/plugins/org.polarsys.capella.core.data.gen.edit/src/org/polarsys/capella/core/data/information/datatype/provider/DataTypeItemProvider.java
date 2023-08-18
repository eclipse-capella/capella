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

package org.polarsys.capella.core.data.information.datatype.provider;

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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.provider.GeneralizableElementItemProvider;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.information.datatype.DataType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DataTypeItemProvider
	extends GeneralizableElementItemProvider
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
	protected IItemPropertyDescriptor defaultValuePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor nullValuePropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DataTypeItemProvider(AdapterFactory adapterFactory) {
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
      // Process DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE
      if (defaultValuePropertyDescriptor != null) {
        Object defaultValueValue = eObject.eGet(DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE, true);
        if (defaultValueValue != null && defaultValueValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) defaultValueValue)) {
          itemPropertyDescriptors.remove(defaultValuePropertyDescriptor);
        } else if (defaultValueValue == null && ExtensionModelManager.getAnyType(eObject, DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE) != null) {
          itemPropertyDescriptors.remove(defaultValuePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(defaultValuePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(defaultValuePropertyDescriptor);
        }
      }
      // Process DatatypePackage.Literals.DATA_TYPE__NULL_VALUE
      if (nullValuePropertyDescriptor != null) {
        Object nullValueValue = eObject.eGet(DatatypePackage.Literals.DATA_TYPE__NULL_VALUE, true);
        if (nullValueValue != null && nullValueValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) nullValueValue)) {
          itemPropertyDescriptors.remove(nullValuePropertyDescriptor);
        } else if (nullValueValue == null && ExtensionModelManager.getAnyType(eObject, DatatypePackage.Literals.DATA_TYPE__NULL_VALUE) != null) {
          itemPropertyDescriptors.remove(nullValuePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(nullValuePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(nullValuePropertyDescriptor);
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

      addFinalPropertyDescriptor(object);
      addDiscretePropertyDescriptor(object);
      addMinInclusivePropertyDescriptor(object);
      addMaxInclusivePropertyDescriptor(object);
      addPatternPropertyDescriptor(object);
      addVisibilityPropertyDescriptor(object);
      addDefaultValuePropertyDescriptor(object);
      addNullValuePropertyDescriptor(object);
      addRealizedDataTypesPropertyDescriptor(object);
      addRealizingDataTypesPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Final feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addFinalPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FinalizableElement_final_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FinalizableElement_final_feature", "_UI_FinalizableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Discrete feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addDiscretePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_discrete_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_discrete_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__DISCRETE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Min Inclusive feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addMinInclusivePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_minInclusive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_minInclusive_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__MIN_INCLUSIVE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Max Inclusive feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addMaxInclusivePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_maxInclusive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_maxInclusive_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__MAX_INCLUSIVE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Pattern feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addPatternPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_pattern_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_pattern_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__PATTERN,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Visibility feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addVisibilityPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_visibility_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_visibility_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__VISIBILITY,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Default Value feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addDefaultValuePropertyDescriptor(Object object) {
    // begin-extension-code
    defaultValuePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_defaultValue_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_defaultValue_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(defaultValuePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Null Value feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addNullValuePropertyDescriptor(Object object) {
    // begin-extension-code
    nullValuePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_nullValue_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_nullValue_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__NULL_VALUE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(nullValuePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Realized Data Types feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedDataTypesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_realizedDataTypes_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_realizedDataTypes_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__REALIZED_DATA_TYPES,
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
   * This adds a property descriptor for the Realizing Data Types feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingDataTypesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_DataType_realizingDataTypes_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_DataType_realizingDataTypes_feature", "_UI_DataType_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatatypePackage.Literals.DATA_TYPE__REALIZING_DATA_TYPES,
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
      childrenFeatures.add(CapellacorePackage.Literals.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS);
      childrenFeatures.add(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES);
      childrenFeatures.add(DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS);
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
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getText(Object object) {
   String[] result = new String[] { null };

    	//begin-capella-code
    String label = ((DataType)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_DataType_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(DataType.class)) {
      case DatatypePackage.DATA_TYPE__FINAL:
      case DatatypePackage.DATA_TYPE__DISCRETE:
      case DatatypePackage.DATA_TYPE__MIN_INCLUSIVE:
      case DatatypePackage.DATA_TYPE__MAX_INCLUSIVE:
      case DatatypePackage.DATA_TYPE__PATTERN:
      case DatatypePackage.DATA_TYPE__VISIBILITY:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case DatatypePackage.DATA_TYPE__OWNED_PROPERTY_VALUE_PKGS:
      case DatatypePackage.DATA_TYPE__OWNED_DATA_VALUES:
      case DatatypePackage.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS:
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
                        (CapellacorePackage.Literals.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS,
                         CapellacoreFactory.eINSTANCE.createPropertyValuePkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS,
                         InformationFactory.eINSTANCE.createInformationRealization());
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
