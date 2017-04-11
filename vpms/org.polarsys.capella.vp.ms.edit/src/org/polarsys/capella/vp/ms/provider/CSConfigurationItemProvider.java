/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *    Altran - Compare Configurations
 *******************************************************************************/

package org.polarsys.capella.vp.ms.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.MsFactory;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.kitalpha.emde.model.EmdePackage;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.vp.ms.ms.CSConfiguration} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CSConfigurationItemProvider extends NamedElementItemProvider implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
  /**
   * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public CSConfigurationItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
    if (itemPropertyDescriptors == null) {
      super.getPropertyDescriptors(object);

      addSupportedModesPropertyDescriptor(object);
      addElementsPropertyDescriptor(object);
      addDeploymentLinksPropertyDescriptor(object);
      addFunctionsPropertyDescriptor(object);
      addFunctionalChainsPropertyDescriptor(object);
      addComponentsPropertyDescriptor(object);
      addPortsPropertyDescriptor(object);
      addChildConfigurationsPropertyDescriptor(object);
      addKindPropertyDescriptor(object);
      addAccessPropertyDescriptor(object);
      addSelectorPropertyDescriptor(object);
      addContextPropertyDescriptor(object);
      addCompareToPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Supported Modes feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addSupportedModesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_supportedModes_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_supportedModes_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__SUPPORTED_MODES, true, false, true, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Elements feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addElementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_elements_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_elements_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__ELEMENTS, true, false, true, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Deployment Links feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addDeploymentLinksPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_deploymentLinks_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_deploymentLinks_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__DEPLOYMENT_LINKS, false, false, false, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Functions feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_functions_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_functions_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__FUNCTIONS, false, false, false, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Functional Chains feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addFunctionalChainsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_functionalChains_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_functionalChains_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__FUNCTIONAL_CHAINS, false, false, false, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Components feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addComponentsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_components_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_components_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__COMPONENTS, false, false, false, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Ports feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addPortsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_ports_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_ports_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__PORTS, false, false, false, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Child Configurations feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addChildConfigurationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_childConfigurations_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_childConfigurations_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__CHILD_CONFIGURATIONS, true, false, true, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Kind feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addKindPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_kind_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_kind_feature", "_UI_CSConfiguration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        MsPackage.Literals.CS_CONFIGURATION__KIND, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Access feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addAccessPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_access_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_access_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__ACCESS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Selector feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addSelectorPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_selector_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_selector_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__SELECTOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Context feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addContextPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_context_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_context_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__CONTEXT, true, false, true, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Compare To feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addCompareToPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add(createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_CSConfiguration_compareTo_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_CSConfiguration_compareTo_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_CSConfiguration_type"), //$NON-NLS-1$
        MsPackage.Literals.CS_CONFIGURATION__COMPARE_TO, true, false, true, null, null,
        // begin-extension-code
        null));
    // end-extension-code
  }

  /**
   * This returns CSConfiguration.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/CSConfiguration")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getText(Object object) {

    String label = ((CSConfiguration) object).getName();
    // begin-extension-code
    return label == null || label.length() == 0 ? "[" + getString("_UI_CSConfiguration_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // end-extension-code
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating a
   * viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(CSConfiguration.class)) {
    case MsPackage.CS_CONFIGURATION__KIND:
    case MsPackage.CS_CONFIGURATION__ACCESS:
    case MsPackage.CS_CONFIGURATION__SELECTOR:
      fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
      return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created under
   * this object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
    super.collectNewChildDescriptors(newChildDescriptors, object);
    // begin-extension-code
    {
      CommandParameter commandParameter = createChildParameter(
          EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS, MsFactory.eINSTANCE.createCSConfiguration());
      if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
        newChildDescriptors.add(commandParameter);
      }
    }
    // end-extension-code

    // begin-extension-code
    {
      CommandParameter commandParameter = createChildParameter(
          EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS, MsFactory.eINSTANCE.createFSMType());
      if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
        newChildDescriptors.add(commandParameter);
      }
    }
    // end-extension-code

    // begin-extension-code
    {
      CommandParameter commandParameter = createChildParameter(
          EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS, MsFactory.eINSTANCE.createSituation());
      if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
        newChildDescriptors.add(commandParameter);
      }
    }
    // end-extension-code

    // begin-extension-code
    {
      CommandParameter commandParameter = createChildParameter(
          EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS, MsFactory.eINSTANCE.createComparison());
      if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
        newChildDescriptors.add(commandParameter);
      }
    }
    // end-extension-code

    // begin-extension-code
    {
      CommandParameter commandParameter = createChildParameter(
          EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS, MsFactory.eINSTANCE.createResult());
      if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
        newChildDescriptors.add(commandParameter);
      }
    }
    // end-extension-code

  }

  /**
   * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createItemPropertyDescriptor(org.eclipse.emf.common.notify.AdapterFactory,
   *      org.eclipse.emf.common.util.ResourceLocator, java.lang.String, java.lang.String,
   *      org.eclipse.emf.ecore.EStructuralFeature, boolean, boolean, boolean, java.lang.Object, java.lang.String,
   *      java.lang.String[])
   */
  @Override
  protected ItemPropertyDescriptor createItemPropertyDescriptor(AdapterFactory adapterFactory_p,
      ResourceLocator resourceLocator_p, String displayName_p, String description_p, EStructuralFeature feature_p,
      boolean isSettable_p, boolean multiLine_p, boolean sortChoices_p, Object staticImage_p, String category_p,
      String[] filterFlags_p) {

    if (feature_p == MsPackage.Literals.CS_CONFIGURATION__ELEMENTS
        || feature_p == MsPackage.Literals.CS_CONFIGURATION__COMPONENTS
        || feature_p == MsPackage.Literals.CS_CONFIGURATION__DEPLOYMENT_LINKS
        || feature_p == MsPackage.Literals.CS_CONFIGURATION__FUNCTIONAL_CHAINS
        || feature_p == MsPackage.Literals.CS_CONFIGURATION__FUNCTIONS
        || feature_p == MsPackage.Literals.CS_CONFIGURATION__PORTS) {

      return new CSConfigurationItemPropertyDescriptor(adapterFactory_p, resourceLocator_p, displayName_p,
          description_p, feature_p, isSettable_p, multiLine_p, sortChoices_p, staticImage_p, category_p, filterFlags_p);

    } else if (feature_p == MsPackage.Literals.CS_CONFIGURATION__CHILD_CONFIGURATIONS) {

      return new ChildConfigurationsPropertyDescriptor(adapterFactory_p, resourceLocator_p, displayName_p,
          description_p, feature_p, isSettable_p, multiLine_p, sortChoices_p, staticImage_p, category_p, filterFlags_p);

    } else {

      return super.createItemPropertyDescriptor(adapterFactory_p, resourceLocator_p, displayName_p, description_p,
          feature_p, isSettable_p, multiLine_p, sortChoices_p, staticImage_p, category_p, filterFlags_p);

    }

  }

}