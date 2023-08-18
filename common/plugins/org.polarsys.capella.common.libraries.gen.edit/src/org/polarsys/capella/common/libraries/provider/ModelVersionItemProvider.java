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

package org.polarsys.capella.common.libraries.provider;

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
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.ModelVersion;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.common.libraries.ModelVersion} object.
 * <!-- begin-user-doc -->
 * @superClass ExtensionItemProviderAdapter
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelVersionItemProvider extends LibraryAbstractElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
    ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelVersionItemProvider(AdapterFactory adapterFactory) {
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

      addMajorVersionNumberPropertyDescriptor(object);
      addMinorVersionNumberPropertyDescriptor(object);
      addLastModifiedFileStampPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Major Version Number feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMajorVersionNumberPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ModelVersion_majorVersionNumber_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ModelVersion_majorVersionNumber_feature", "_UI_ModelVersion_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LibrariesPackage.Literals.MODEL_VERSION__MAJOR_VERSION_NUMBER,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Minor Version Number feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMinorVersionNumberPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ModelVersion_minorVersionNumber_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ModelVersion_minorVersionNumber_feature", "_UI_ModelVersion_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LibrariesPackage.Literals.MODEL_VERSION__MINOR_VERSION_NUMBER,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Last Modified File Stamp feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLastModifiedFileStampPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ModelVersion_lastModifiedFileStamp_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ModelVersion_lastModifiedFileStamp_feature", "_UI_ModelVersion_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LibrariesPackage.Literals.MODEL_VERSION__LAST_MODIFIED_FILE_STAMP,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

  /**
   * This returns ModelVersion.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelVersion")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    ModelVersion modelVersion = (ModelVersion) object;
    String label = "";

    label += modelVersion.getMajorVersionNumber();
    label += ".";
    label += modelVersion.getMinorVersionNumber();
    label += ".";
    label += modelVersion.getLastModifiedFileStamp();

    // begin-extension-code
    return (label == null) || (label.length() == 0) ? "[" + getString("_UI_ModelVersion_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // end-extension-code
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

    switch (notification.getFeatureID(ModelVersion.class)) {
      case LibrariesPackage.MODEL_VERSION__MAJOR_VERSION_NUMBER:
      case LibrariesPackage.MODEL_VERSION__MINOR_VERSION_NUMBER:
      case LibrariesPackage.MODEL_VERSION__LAST_MODIFIED_FILE_STAMP:
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