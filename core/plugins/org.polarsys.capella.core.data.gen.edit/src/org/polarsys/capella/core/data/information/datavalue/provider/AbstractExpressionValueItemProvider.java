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

package org.polarsys.capella.core.data.information.datavalue.provider;

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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractExpressionValueItemProvider
	extends AbstractBooleanValueItemProvider
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
	protected IItemPropertyDescriptor complexTypePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor enumerationTypePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor unitPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor numericTypePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor stringTypePropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor expressionTypePropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AbstractExpressionValueItemProvider(AdapterFactory adapterFactory) {
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
      // Process DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE
      if (complexTypePropertyDescriptor != null) {
        Object complexTypeValue = eObject.eGet(DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE, true);
        if (complexTypeValue != null && complexTypeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) complexTypeValue)) {
          itemPropertyDescriptors.remove(complexTypePropertyDescriptor);
        } else if (complexTypeValue == null && ExtensionModelManager.getAnyType(eObject, DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE) != null) {
          itemPropertyDescriptors.remove(complexTypePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(complexTypePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(complexTypePropertyDescriptor);
        }
      }
      // Process DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE
      if (enumerationTypePropertyDescriptor != null) {
        Object enumerationTypeValue = eObject.eGet(DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE, true);
        if (enumerationTypeValue != null && enumerationTypeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) enumerationTypeValue)) {
          itemPropertyDescriptors.remove(enumerationTypePropertyDescriptor);
        } else if (enumerationTypeValue == null && ExtensionModelManager.getAnyType(eObject, DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE) != null) {
          itemPropertyDescriptors.remove(enumerationTypePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(enumerationTypePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(enumerationTypePropertyDescriptor);
        }
      }
      // Process DatavaluePackage.Literals.NUMERIC_VALUE__UNIT
      if (unitPropertyDescriptor != null) {
        Object unitValue = eObject.eGet(DatavaluePackage.Literals.NUMERIC_VALUE__UNIT, true);
        if (unitValue != null && unitValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) unitValue)) {
          itemPropertyDescriptors.remove(unitPropertyDescriptor);
        } else if (unitValue == null && ExtensionModelManager.getAnyType(eObject, DatavaluePackage.Literals.NUMERIC_VALUE__UNIT) != null) {
          itemPropertyDescriptors.remove(unitPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(unitPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(unitPropertyDescriptor);
        }
      }
      // Process DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE
      if (numericTypePropertyDescriptor != null) {
        Object numericTypeValue = eObject.eGet(DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE, true);
        if (numericTypeValue != null && numericTypeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) numericTypeValue)) {
          itemPropertyDescriptors.remove(numericTypePropertyDescriptor);
        } else if (numericTypeValue == null && ExtensionModelManager.getAnyType(eObject, DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE) != null) {
          itemPropertyDescriptors.remove(numericTypePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(numericTypePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(numericTypePropertyDescriptor);
        }
      }
      // Process DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE
      if (stringTypePropertyDescriptor != null) {
        Object stringTypeValue = eObject.eGet(DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE, true);
        if (stringTypeValue != null && stringTypeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) stringTypeValue)) {
          itemPropertyDescriptors.remove(stringTypePropertyDescriptor);
        } else if (stringTypeValue == null && ExtensionModelManager.getAnyType(eObject, DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE) != null) {
          itemPropertyDescriptors.remove(stringTypePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(stringTypePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(stringTypePropertyDescriptor);
        }
      }
      // Process DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE
      if (expressionTypePropertyDescriptor != null) {
        Object expressionTypeValue = eObject.eGet(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE, true);
        if (expressionTypeValue != null && expressionTypeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) expressionTypeValue)) {
          itemPropertyDescriptors.remove(expressionTypePropertyDescriptor);
        } else if (expressionTypeValue == null && ExtensionModelManager.getAnyType(eObject, DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE) != null) {
          itemPropertyDescriptors.remove(expressionTypePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(expressionTypePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(expressionTypePropertyDescriptor);
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

      addComplexTypePropertyDescriptor(object);
      addEnumerationTypePropertyDescriptor(object);
      addUnitPropertyDescriptor(object);
      addNumericTypePropertyDescriptor(object);
      addStringTypePropertyDescriptor(object);
      addExpressionPropertyDescriptor(object);
      addUnparsedExpressionPropertyDescriptor(object);
      addExpressionTypePropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Complex Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addComplexTypePropertyDescriptor(Object object) {
    // begin-extension-code
    complexTypePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractComplexValue_complexType_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractComplexValue_complexType_feature", "_UI_AbstractComplexValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(complexTypePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Enumeration Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addEnumerationTypePropertyDescriptor(Object object) {
    // begin-extension-code
    enumerationTypePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractEnumerationValue_enumerationType_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractEnumerationValue_enumerationType_feature", "_UI_AbstractEnumerationValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(enumerationTypePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Unit feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addUnitPropertyDescriptor(Object object) {
    // begin-extension-code
    unitPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_NumericValue_unit_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_NumericValue_unit_feature", "_UI_NumericValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.NUMERIC_VALUE__UNIT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(unitPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Numeric Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addNumericTypePropertyDescriptor(Object object) {
    // begin-extension-code
    numericTypePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_NumericValue_numericType_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_NumericValue_numericType_feature", "_UI_NumericValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(numericTypePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the String Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addStringTypePropertyDescriptor(Object object) {
    // begin-extension-code
    stringTypePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractStringValue_stringType_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractStringValue_stringType_feature", "_UI_AbstractStringValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(stringTypePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Expression feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExpressionPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractExpressionValue_expression_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractExpressionValue_expression_feature", "_UI_AbstractExpressionValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION,
         false,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Unparsed Expression feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addUnparsedExpressionPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractExpressionValue_unparsedExpression_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractExpressionValue_unparsedExpression_feature", "_UI_AbstractExpressionValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION,
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
   * This adds a property descriptor for the Expression Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExpressionTypePropertyDescriptor(Object object) {
    // begin-extension-code
    expressionTypePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractExpressionValue_expressionType_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractExpressionValue_expressionType_feature", "_UI_AbstractExpressionValue_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(expressionTypePropertyDescriptor);
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
    String label = ((AbstractExpressionValue)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_AbstractExpressionValue_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(AbstractExpressionValue.class)) {
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__EXPRESSION:
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
