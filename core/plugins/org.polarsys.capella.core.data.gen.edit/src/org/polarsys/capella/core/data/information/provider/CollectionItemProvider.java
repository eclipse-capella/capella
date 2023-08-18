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

package org.polarsys.capella.core.data.information.provider;

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
import org.polarsys.capella.core.data.capellacore.provider.ClassifierItemProvider;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.information.Collection} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CollectionItemProvider
	extends ClassifierItemProvider
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
	protected IItemPropertyDescriptor typePropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionItemProvider(AdapterFactory adapterFactory) {
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
      // Process InformationPackage.Literals.COLLECTION__TYPE
      if (typePropertyDescriptor != null) {
        Object typeValue = eObject.eGet(InformationPackage.Literals.COLLECTION__TYPE, true);
        if (typeValue != null && typeValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) typeValue)) {
          itemPropertyDescriptors.remove(typePropertyDescriptor);
        } else if (typeValue == null && ExtensionModelManager.getAnyType(eObject, InformationPackage.Literals.COLLECTION__TYPE) != null) {
          itemPropertyDescriptors.remove(typePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(typePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(typePropertyDescriptor);
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

      addOrderedPropertyDescriptor(object);
      addUniquePropertyDescriptor(object);
      addMinInclusivePropertyDescriptor(object);
      addMaxInclusivePropertyDescriptor(object);
      addFinalPropertyDescriptor(object);
      addIsPrimitivePropertyDescriptor(object);
      addVisibilityPropertyDescriptor(object);
      addKindPropertyDescriptor(object);
      addAggregationKindPropertyDescriptor(object);
      addTypePropertyDescriptor(object);
      addIndexPropertyDescriptor(object);
      addContainedOperationsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Ordered feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOrderedPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_MultiplicityElement_ordered_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_ordered_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__ORDERED,
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
   * This adds a property descriptor for the Unique feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addUniquePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_MultiplicityElement_unique_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_unique_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__UNIQUE,
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
         getString("_UI_MultiplicityElement_minInclusive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_minInclusive_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE,
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
         getString("_UI_MultiplicityElement_maxInclusive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_maxInclusive_feature", "_UI_MultiplicityElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE,
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
   * This adds a property descriptor for the Is Primitive feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIsPrimitivePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Collection_isPrimitive_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Collection_isPrimitive_feature", "_UI_Collection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION__IS_PRIMITIVE,
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
         getString("_UI_Collection_visibility_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Collection_visibility_feature", "_UI_Collection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION__VISIBILITY,
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
   * This adds a property descriptor for the Kind feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addKindPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Collection_kind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Collection_kind_feature", "_UI_Collection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION__KIND,
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
   * This adds a property descriptor for the Aggregation Kind feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAggregationKindPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Collection_aggregationKind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Collection_aggregationKind_feature", "_UI_Collection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION__AGGREGATION_KIND,
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
   * This adds a property descriptor for the Type feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTypePropertyDescriptor(Object object) {
    // begin-extension-code
    typePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Collection_type_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Collection_type_feature", "_UI_Collection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION__TYPE,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(typePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Index feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIndexPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Collection_index_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Collection_index_feature", "_UI_Collection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION__INDEX,
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
   * This adds a property descriptor for the Contained Operations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addContainedOperationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Collection_containedOperations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Collection_containedOperations_feature", "_UI_Collection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS,
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
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH);
      childrenFeatures.add(CapellacorePackage.Literals.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS);
      childrenFeatures.add(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES);
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
   * This returns Collection.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Collection")); //$NON-NLS-1$
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
    String label = ((org.polarsys.capella.core.data.information.Collection)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_Collection_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(org.polarsys.capella.core.data.information.Collection.class)) {
      case InformationPackage.COLLECTION__ORDERED:
      case InformationPackage.COLLECTION__UNIQUE:
      case InformationPackage.COLLECTION__MIN_INCLUSIVE:
      case InformationPackage.COLLECTION__MAX_INCLUSIVE:
      case InformationPackage.COLLECTION__FINAL:
      case InformationPackage.COLLECTION__IS_PRIMITIVE:
      case InformationPackage.COLLECTION__VISIBILITY:
      case InformationPackage.COLLECTION__KIND:
      case InformationPackage.COLLECTION__AGGREGATION_KIND:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE:
      case InformationPackage.COLLECTION__OWNED_MIN_VALUE:
      case InformationPackage.COLLECTION__OWNED_MAX_VALUE:
      case InformationPackage.COLLECTION__OWNED_NULL_VALUE:
      case InformationPackage.COLLECTION__OWNED_MIN_CARD:
      case InformationPackage.COLLECTION__OWNED_MIN_LENGTH:
      case InformationPackage.COLLECTION__OWNED_MAX_CARD:
      case InformationPackage.COLLECTION__OWNED_MAX_LENGTH:
      case InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS:
      case InformationPackage.COLLECTION__OWNED_DATA_VALUES:
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
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         InformationFactory.eINSTANCE.createCollectionValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralBooleanValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createBooleanReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationLiteral());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createEnumerationReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralStringValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createStringReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createComplexValueReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createLiteralNumericValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createNumericReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createBinaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH,
                         DatavalueFactory.eINSTANCE.createUnaryExpression());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


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
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE ||
      childFeature == DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD ||
      childFeature == InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH;

    if (qualify) {
      return getString
        ("_UI_CreateChild_text2", //$NON-NLS-1$
         new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner, feature, child, selection);
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
