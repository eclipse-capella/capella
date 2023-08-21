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
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.provider.ModelElementItemProvider;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.information.MultiplicityElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MultiplicityElementItemProvider
	extends ModelElementItemProvider
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
	protected IItemPropertyDescriptor statusPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public MultiplicityElementItemProvider(AdapterFactory adapterFactory) {
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
      // Process CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS
      if (statusPropertyDescriptor != null) {
        Object statusValue = eObject.eGet(CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS, true);
        if (statusValue != null && statusValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) statusValue)) {
          itemPropertyDescriptors.remove(statusPropertyDescriptor);
        } else if (statusValue == null && ExtensionModelManager.getAnyType(eObject, CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS) != null) {
          itemPropertyDescriptors.remove(statusPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(statusPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(statusPropertyDescriptor);
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

      addIncomingTracesPropertyDescriptor(object);
      addOutgoingTracesPropertyDescriptor(object);
      addVisibleInDocPropertyDescriptor(object);
      addVisibleInLMPropertyDescriptor(object);
      addSummaryPropertyDescriptor(object);
      addDescriptionPropertyDescriptor(object);
      addReviewPropertyDescriptor(object);
      addAppliedPropertyValuesPropertyDescriptor(object);
      addAppliedPropertyValueGroupsPropertyDescriptor(object);
      addStatusPropertyDescriptor(object);
      addFeaturesPropertyDescriptor(object);
      addOrderedPropertyDescriptor(object);
      addUniquePropertyDescriptor(object);
      addMinInclusivePropertyDescriptor(object);
      addMaxInclusivePropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Incoming Traces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addIncomingTracesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TraceableElement_incomingTraces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_TraceableElement_incomingTraces_feature", "_UI_TraceableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES,
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
   * This adds a property descriptor for the Outgoing Traces feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOutgoingTracesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TraceableElement_outgoingTraces_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_TraceableElement_outgoingTraces_feature", "_UI_TraceableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
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
   * This adds a property descriptor for the Visible In Doc feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addVisibleInDocPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PublishableElement_visibleInDoc_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PublishableElement_visibleInDoc_feature", "_UI_PublishableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC,
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
   * This adds a property descriptor for the Visible In LM feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addVisibleInLMPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PublishableElement_visibleInLM_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PublishableElement_visibleInLM_feature", "_UI_PublishableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         ModellingcorePackage.Literals.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM,
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
   * This adds a property descriptor for the Summary feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSummaryPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapellaElement_summary_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_summary_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY,
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
   * This adds a property descriptor for the Description feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addDescriptionPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapellaElement_description_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_description_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION,
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
   * This adds a property descriptor for the Review feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReviewPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapellaElement_review_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_review_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CAPELLA_ELEMENT__REVIEW,
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
   * This adds a property descriptor for the Applied Property Values feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAppliedPropertyValuesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapellaElement_appliedPropertyValues_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_appliedPropertyValues_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES,
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
   * This adds a property descriptor for the Applied Property Value Groups feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAppliedPropertyValueGroupsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapellaElement_appliedPropertyValueGroups_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_appliedPropertyValueGroups_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS,
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
   * This adds a property descriptor for the Status feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addStatusPropertyDescriptor(Object object) {
    // begin-extension-code
    statusPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapellaElement_status_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_status_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(statusPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Features feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addFeaturesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapellaElement_features_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapellaElement_features_feature", "_UI_CapellaElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.CAPELLA_ELEMENT__FEATURES,
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
      childrenFeatures.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES);
      childrenFeatures.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES);
      childrenFeatures.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD);
      childrenFeatures.add(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH);
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
        String label = ""; //$NON-NLS-1$
        String targetName = ""; //$NON-NLS-1$
        EObject target = null;

   	if (null != target) {
      if (target instanceof AbstractNamedElement) {
        targetName = ((AbstractNamedElement) target).getName();
      }

      if (null == targetName || "" == targetName) { //$NON-NLS-1$
        targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
   	}
   	label = "[" + getString("_UI_MultiplicityElement_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_MultiplicityElement_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(MultiplicityElement.class)) {
      case InformationPackage.MULTIPLICITY_ELEMENT__VISIBLE_IN_DOC:
      case InformationPackage.MULTIPLICITY_ELEMENT__VISIBLE_IN_LM:
      case InformationPackage.MULTIPLICITY_ELEMENT__SUMMARY:
      case InformationPackage.MULTIPLICITY_ELEMENT__DESCRIPTION:
      case InformationPackage.MULTIPLICITY_ELEMENT__REVIEW:
      case InformationPackage.MULTIPLICITY_ELEMENT__ORDERED:
      case InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE:
      case InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE:
      case InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_PROPERTY_VALUES:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD:
      case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH:
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
                        (ModellingcorePackage.Literals.MODEL_ELEMENT__OWNED_CONSTRAINTS,
                         CapellacoreFactory.eINSTANCE.createConstraint());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createStringPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createIntegerPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createBooleanPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createFloatPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES,
                         CapellacoreFactory.eINSTANCE.createEnumerationPropertyValue());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES,
                         CapellacoreFactory.eINSTANCE.createEnumerationPropertyType());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (CapellacorePackage.Literals.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS,
                         CapellacoreFactory.eINSTANCE.createPropertyValueGroup());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


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
