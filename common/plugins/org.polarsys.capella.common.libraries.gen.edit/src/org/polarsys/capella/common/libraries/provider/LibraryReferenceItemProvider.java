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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.common.libraries.LibraryReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LibraryReferenceItemProvider extends LibraryAbstractElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
    ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor libraryPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor versionPropertyDescriptor;


		/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibraryReferenceItemProvider(AdapterFactory adapterFactory) {
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
      // Process LibrariesPackage.Literals.LIBRARY_REFERENCE__LIBRARY
      if (libraryPropertyDescriptor != null) {
        Object libraryValue = eObject.eGet(LibrariesPackage.Literals.LIBRARY_REFERENCE__LIBRARY, true);
        if (libraryValue != null && libraryValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) libraryValue)) {
          itemPropertyDescriptors.remove(libraryPropertyDescriptor);
        } else if (libraryValue == null && ExtensionModelManager.getAnyType(eObject, LibrariesPackage.Literals.LIBRARY_REFERENCE__LIBRARY) != null) {
          itemPropertyDescriptors.remove(libraryPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(libraryPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(libraryPropertyDescriptor);
        }
      }
      // Process LibrariesPackage.Literals.LIBRARY_REFERENCE__VERSION
      if (versionPropertyDescriptor != null) {
        Object versionValue = eObject.eGet(LibrariesPackage.Literals.LIBRARY_REFERENCE__VERSION, true);
        if (versionValue != null && versionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) versionValue)) {
          itemPropertyDescriptors.remove(versionPropertyDescriptor);
        } else if (versionValue == null && ExtensionModelManager.getAnyType(eObject, LibrariesPackage.Literals.LIBRARY_REFERENCE__VERSION) != null) {
          itemPropertyDescriptors.remove(versionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(versionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(versionPropertyDescriptor);
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

      addLibraryPropertyDescriptor(object);
      addAccessPolicyPropertyDescriptor(object);
      addVersionPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Access Policy feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAccessPolicyPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LibraryReference_accessPolicy_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LibraryReference_accessPolicy_feature", "_UI_LibraryReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LibrariesPackage.Literals.LIBRARY_REFERENCE__ACCESS_POLICY,
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
   * This adds a property descriptor for the Library feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLibraryPropertyDescriptor(Object object) {
    // begin-extension-code
    libraryPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LibraryReference_library_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LibraryReference_library_feature", "_UI_LibraryReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LibrariesPackage.Literals.LIBRARY_REFERENCE__LIBRARY,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(libraryPropertyDescriptor);
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Version feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addVersionPropertyDescriptor(Object object) {
    // begin-extension-code
    versionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_LibraryReference_version_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_LibraryReference_version_feature", "_UI_LibraryReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         LibrariesPackage.Literals.LIBRARY_REFERENCE__VERSION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(versionPropertyDescriptor);
    // end-extension-code
  }

  /**
   * This returns LibraryReference.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public Object getImage(Object object) {
    LibraryReference element = (LibraryReference) object;
    if (element != null) {

      ModelInformation info = element.getLibrary();

      if ((info != null) && !info.eIsProxy()) {
        EObject root = EcoreUtil.getRootContainer(info);
        if (root != null) {
          IItemLabelProvider labelProvider =
              (IItemLabelProvider) (((ComposeableAdapterFactory) getAdapterFactory()).getRootAdapterFactory()).adapt(root, IItemLabelProvider.class);
          return labelProvider.getImage(root);
        }
      }
    }

    return overlayImage(object, getResourceLocator().getImage("full/obj16/LibraryReference")); //$NON-NLS-1$

  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getText(Object object) {
    LibraryReference element = (LibraryReference) object;
    String label = "";
    String library = "Unknown";
    String policy = "";
    if (element == null) {

    }
    AccessPolicy labelValue = ((LibraryReference) object).getAccessPolicy();
    if (labelValue != null) {
      policy = " [" + labelValue.toString() + "]";
    }

    ModelInformation info = element.getLibrary();
    if (info != null) {
      if (info.eIsProxy()) {
        library = ((InternalEObject) (info)).eProxyURI().lastSegment();

      } else {
        EObject root = EcoreUtil.getRootContainer(info);
        if (root != null) {
          IItemLabelProvider labelProvider =
              (IItemLabelProvider) (((ComposeableAdapterFactory) getAdapterFactory()).getRootAdapterFactory()).adapt(root, IItemLabelProvider.class);
          library = labelProvider.getText(root);
        }
      }
    }

    label += NLS.bind("{0}{1}", library, policy);

    // begin-extension-code
    return (label == null) || (label.length() == 0) ? "[" + getString("_UI_LibraryReference_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(LibraryReference.class)) {
      case LibrariesPackage.LIBRARY_REFERENCE__ACCESS_POLICY:
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