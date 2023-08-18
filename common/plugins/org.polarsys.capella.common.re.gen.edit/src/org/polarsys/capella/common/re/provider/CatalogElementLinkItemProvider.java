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

package org.polarsys.capella.common.re.provider;

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
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.common.re.CatalogElementLink} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CatalogElementLinkItemProvider extends
		ReAbstractElementItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor sourcePropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor targetPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor originPropertyDescriptor;


	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CatalogElementLinkItemProvider(AdapterFactory adapterFactory) {
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
      // Process RePackage.Literals.CATALOG_ELEMENT_LINK__SOURCE
      if (sourcePropertyDescriptor != null) {
        Object sourceValue = eObject.eGet(RePackage.Literals.CATALOG_ELEMENT_LINK__SOURCE, true);
        if (sourceValue != null && sourceValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourceValue)) {
          itemPropertyDescriptors.remove(sourcePropertyDescriptor);
        } else if (sourceValue == null && ExtensionModelManager.getAnyType(eObject, RePackage.Literals.CATALOG_ELEMENT_LINK__SOURCE) != null) {
          itemPropertyDescriptors.remove(sourcePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(sourcePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(sourcePropertyDescriptor);
        }
      }
      // Process RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET
      if (targetPropertyDescriptor != null) {
        Object targetValue = eObject.eGet(RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET, true);
        if (targetValue != null && targetValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetValue)) {
          itemPropertyDescriptors.remove(targetPropertyDescriptor);
        } else if (targetValue == null && ExtensionModelManager.getAnyType(eObject, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET) != null) {
          itemPropertyDescriptors.remove(targetPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(targetPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(targetPropertyDescriptor);
        }
      }
      // Process RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN
      if (originPropertyDescriptor != null) {
        Object originValue = eObject.eGet(RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN, true);
        if (originValue != null && originValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) originValue)) {
          itemPropertyDescriptors.remove(originPropertyDescriptor);
        } else if (originValue == null && ExtensionModelManager.getAnyType(eObject, RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN) != null) {
          itemPropertyDescriptors.remove(originPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(originPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(originPropertyDescriptor);
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

      addSourcePropertyDescriptor(object);
      addTargetPropertyDescriptor(object);
      addOriginPropertyDescriptor(object);
      addUnsynchronizedFeaturesPropertyDescriptor(object);
      addSuffixedPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Source feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSourcePropertyDescriptor(Object object) {
    // begin-extension-code
    sourcePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElementLink_source_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElementLink_source_feature", "_UI_CatalogElementLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT_LINK__SOURCE,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(sourcePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Target feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTargetPropertyDescriptor(Object object) {
    // begin-extension-code
    targetPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElementLink_target_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElementLink_target_feature", "_UI_CatalogElementLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(targetPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Origin feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addOriginPropertyDescriptor(Object object) {
    // begin-extension-code
    originPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElementLink_origin_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElementLink_origin_feature", "_UI_CatalogElementLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(originPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Unsynchronized Features feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addUnsynchronizedFeaturesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElementLink_unsynchronizedFeatures_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElementLink_unsynchronizedFeatures_feature", "_UI_CatalogElementLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES,
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
   * This adds a property descriptor for the Suffixed feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSuffixedPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElementLink_suffixed_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElementLink_suffixed_feature", "_UI_CatalogElementLink_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT_LINK__SUFFIXED,
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
   * This returns CatalogElementLink.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/CatalogElementLink")); //$NON-NLS-1$
  }

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		CatalogElementLink element = (CatalogElementLink) object;
		String label = "";

		if (element.getTarget() != null) {
			IItemLabelProvider labelProvider = (IItemLabelProvider) (((ComposeableAdapterFactory) getAdapterFactory())
					.getRootAdapterFactory()).adapt(element.getTarget(),
					IItemLabelProvider.class);
			label += NLS.bind("Link to ''{0}''",
					labelProvider.getText(element.getTarget()));
		}

		// begin-extension-code
		return (label == null) || (label.length() == 0) ? "[" + getString("_UI_CatalogElementLink_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

    switch (notification.getFeatureID(CatalogElementLink.class)) {
      case RePackage.CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES:
      case RePackage.CATALOG_ELEMENT_LINK__SUFFIXED:
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
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