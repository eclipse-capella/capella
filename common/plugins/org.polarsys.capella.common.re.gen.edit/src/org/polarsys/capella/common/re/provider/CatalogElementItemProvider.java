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
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.common.re.CatalogElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CatalogElementItemProvider extends ReDescriptionElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
    ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor originPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor currentCompliancyPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor defaultReplicaCompliancyPropertyDescriptor;

		/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CatalogElementItemProvider(AdapterFactory adapterFactory) {
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
      // Process RePackage.Literals.CATALOG_ELEMENT__ORIGIN
      if (originPropertyDescriptor != null) {
        Object originValue = eObject.eGet(RePackage.Literals.CATALOG_ELEMENT__ORIGIN, true);
        if (originValue != null && originValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) originValue)) {
          itemPropertyDescriptors.remove(originPropertyDescriptor);
        } else if (originValue == null && ExtensionModelManager.getAnyType(eObject, RePackage.Literals.CATALOG_ELEMENT__ORIGIN) != null) {
          itemPropertyDescriptors.remove(originPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(originPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(originPropertyDescriptor);
        }
      }
      // Process RePackage.Literals.CATALOG_ELEMENT__CURRENT_COMPLIANCY
      if (currentCompliancyPropertyDescriptor != null) {
        Object currentCompliancyValue = eObject.eGet(RePackage.Literals.CATALOG_ELEMENT__CURRENT_COMPLIANCY, true);
        if (currentCompliancyValue != null && currentCompliancyValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) currentCompliancyValue)) {
          itemPropertyDescriptors.remove(currentCompliancyPropertyDescriptor);
        } else if (currentCompliancyValue == null && ExtensionModelManager.getAnyType(eObject, RePackage.Literals.CATALOG_ELEMENT__CURRENT_COMPLIANCY) != null) {
          itemPropertyDescriptors.remove(currentCompliancyPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(currentCompliancyPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(currentCompliancyPropertyDescriptor);
        }
      }
      // Process RePackage.Literals.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY
      if (defaultReplicaCompliancyPropertyDescriptor != null) {
        Object defaultReplicaCompliancyValue = eObject.eGet(RePackage.Literals.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY, true);
        if (defaultReplicaCompliancyValue != null && defaultReplicaCompliancyValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) defaultReplicaCompliancyValue)) {
          itemPropertyDescriptors.remove(defaultReplicaCompliancyPropertyDescriptor);
        } else if (defaultReplicaCompliancyValue == null && ExtensionModelManager.getAnyType(eObject, RePackage.Literals.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY) != null) {
          itemPropertyDescriptors.remove(defaultReplicaCompliancyPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(defaultReplicaCompliancyPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(defaultReplicaCompliancyPropertyDescriptor);
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

      addKindPropertyDescriptor(object);
      addAuthorPropertyDescriptor(object);
      addEnvironmentPropertyDescriptor(object);
      addSuffixPropertyDescriptor(object);
      addPurposePropertyDescriptor(object);
      addReadOnlyPropertyDescriptor(object);
      addVersionPropertyDescriptor(object);
      addTagsPropertyDescriptor(object);
      addOriginPropertyDescriptor(object);
      addCurrentCompliancyPropertyDescriptor(object);
      addDefaultReplicaCompliancyPropertyDescriptor(object);
      addReferencedElementsPropertyDescriptor(object);
      addReplicatedElementsPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
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
         getString("_UI_CatalogElement_kind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_kind_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__KIND,
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
   * This adds a property descriptor for the Author feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAuthorPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_author_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_author_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__AUTHOR,
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
   * This adds a property descriptor for the Environment feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEnvironmentPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_environment_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_environment_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__ENVIRONMENT,
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
   * This adds a property descriptor for the Suffix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuffixPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_suffix_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_suffix_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__SUFFIX,
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
   * This adds a property descriptor for the Purpose feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPurposePropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_purpose_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_purpose_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__PURPOSE,
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
   * This adds a property descriptor for the Read Only feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReadOnlyPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_readOnly_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_readOnly_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__READ_ONLY,
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
   * This adds a property descriptor for the Version feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addVersionPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_version_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_version_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__VERSION,
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
   * This adds a property descriptor for the Tags feature.
   * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
   * @generated
   */
  protected void addTagsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_tags_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_tags_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__TAGS,
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
         getString("_UI_CatalogElement_origin_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_origin_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__ORIGIN,
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
   * This adds a property descriptor for the Current Compliancy feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCurrentCompliancyPropertyDescriptor(Object object) {
    // begin-extension-code
    currentCompliancyPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_currentCompliancy_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_currentCompliancy_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__CURRENT_COMPLIANCY,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(currentCompliancyPropertyDescriptor);
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Default Replica Compliancy feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addDefaultReplicaCompliancyPropertyDescriptor(Object object) {
    // begin-extension-code
    defaultReplicaCompliancyPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_defaultReplicaCompliancy_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_defaultReplicaCompliancy_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(defaultReplicaCompliancyPropertyDescriptor);
    // end-extension-code
  }

  /**
   * This adds a property descriptor for the Referenced Elements feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addReferencedElementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_referencedElements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_referencedElements_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__REFERENCED_ELEMENTS,
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
   * This adds a property descriptor for the Replicated Elements feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addReplicatedElementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CatalogElement_replicatedElements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CatalogElement_replicatedElements_feature", "_UI_CatalogElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         RePackage.Literals.CATALOG_ELEMENT__REPLICATED_ELEMENTS,
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
      childrenFeatures.add(RePackage.Literals.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS);
      childrenFeatures.add(RePackage.Literals.CATALOG_ELEMENT__OWNED_LINKS);
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
   * This returns CatalogElement.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public Object getImage(Object object) {

    if (object instanceof CatalogElement) {
      if (((CatalogElement) object).getKind() == CatalogElementKind.REC) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ReplicableElement")); //$NON-NLS-1$
      } else if (((CatalogElement) object).getKind() == CatalogElementKind.RPL) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Replica")); //$NON-NLS-1$
      } else if (((CatalogElement) object).getKind() == CatalogElementKind.REC_RPL) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ReplicableElementReplica")); //$NON-NLS-1$
      } else if (((CatalogElement) object).getKind() == CatalogElementKind.GROUPING) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/GroupingElement")); //$NON-NLS-1$
      }
    }
    return overlayImage(object, getResourceLocator().getImage("full/obj16/GroupingElement")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getText(Object object) {

    CatalogElement element = (CatalogElement) object;
    // begin-extension-code

    String label = element.getName();
    label = (label == null) || (label.length() == 0) ? "[" + getString("_UI_CatalogElement_type") + "]" : label;

    if (element.getOrigin() != null) {
      String labelOrigin = element.getOrigin().getName();
      label += NLS.bind(" [{0}]", labelOrigin);
    }

    return label;
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

    switch (notification.getFeatureID(CatalogElement.class)) {
      case RePackage.CATALOG_ELEMENT__KIND:
      case RePackage.CATALOG_ELEMENT__AUTHOR:
      case RePackage.CATALOG_ELEMENT__ENVIRONMENT:
      case RePackage.CATALOG_ELEMENT__SUFFIX:
      case RePackage.CATALOG_ELEMENT__PURPOSE:
      case RePackage.CATALOG_ELEMENT__READ_ONLY:
      case RePackage.CATALOG_ELEMENT__VERSION:
      case RePackage.CATALOG_ELEMENT__TAGS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case RePackage.CATALOG_ELEMENT__OWNED_ELEMENTS:
      case RePackage.CATALOG_ELEMENT__OWNED_LINKS:
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
                        (RePackage.Literals.RE_ELEMENT_CONTAINER__OWNED_ELEMENTS,
                         ReFactory.eINSTANCE.createCatalogElement());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (RePackage.Literals.CATALOG_ELEMENT__OWNED_LINKS,
                         ReFactory.eINSTANCE.createCatalogElementLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


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