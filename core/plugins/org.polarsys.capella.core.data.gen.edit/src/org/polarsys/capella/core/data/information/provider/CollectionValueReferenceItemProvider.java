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
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.information.AbstractCollectionValue;
import org.polarsys.capella.core.data.information.CollectionValueReference;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.util.DataValueNaminghelper;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.information.CollectionValueReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CollectionValueReferenceItemProvider extends AbstractCollectionValueItemProvider implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor referencedValuePropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor referencedPropertyPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionValueReferenceItemProvider(AdapterFactory adapterFactory) {
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
      // Process InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE
      if (referencedValuePropertyDescriptor != null) {
        Object referencedValueValue = eObject.eGet(InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE, true);
        if (referencedValueValue != null && referencedValueValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) referencedValueValue)) {
          itemPropertyDescriptors.remove(referencedValuePropertyDescriptor);
        } else if (referencedValueValue == null && ExtensionModelManager.getAnyType(eObject, InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE) != null) {
          itemPropertyDescriptors.remove(referencedValuePropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(referencedValuePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(referencedValuePropertyDescriptor);
        }
      }
      // Process InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY
      if (referencedPropertyPropertyDescriptor != null) {
        Object referencedPropertyValue = eObject.eGet(InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY, true);
        if (referencedPropertyValue != null && referencedPropertyValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) referencedPropertyValue)) {
          itemPropertyDescriptors.remove(referencedPropertyPropertyDescriptor);
        } else if (referencedPropertyValue == null && ExtensionModelManager.getAnyType(eObject, InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY) != null) {
          itemPropertyDescriptors.remove(referencedPropertyPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(referencedPropertyPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(referencedPropertyPropertyDescriptor);
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

      addReferencedValuePropertyDescriptor(object);
      addReferencedPropertyPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Referenced Value feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReferencedValuePropertyDescriptor(Object object) {
    // begin-extension-code
    referencedValuePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CollectionValueReference_referencedValue_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CollectionValueReference_referencedValue_feature", "_UI_CollectionValueReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(referencedValuePropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Referenced Property feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addReferencedPropertyPropertyDescriptor(Object object) {
    // begin-extension-code
    referencedPropertyPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CollectionValueReference_referencedProperty_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CollectionValueReference_referencedProperty_feature", "_UI_CollectionValueReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(referencedPropertyPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns CollectionValueReference.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/CollectionValueReference")); //$NON-NLS-1$
  }

		/**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTextGen(Object object) {
   String[] result = new String[] { null };

    	//begin-capella-code
    String label = ((CollectionValueReference)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_CollectionValueReference_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      //end-capella-code

    return result[0];

  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  @Override
  public String getText(Object object) {
    CollectionValueReference ref = (CollectionValueReference) object;
    String label = ref.getName();
    String valuePrefix = DataValueNaminghelper.getReferencePrefix(ref);
    String valueName = ""; //$NON-NLS-1$

    Property property = ref.getReferencedProperty();
    AbstractCollectionValue value = ref.getReferencedValue();
    if (null != value) {
      valueName = value.getName();
      if (null == valueName || "" == valueName) { //$NON-NLS-1$
        valueName = "[" + value.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    else if (null != property) {
      valueName = ((AbstractNamedElement) property.eContainer()).getName() + "." + property.getName(); //$NON-NLS-1$
      if (null == valueName || "" == valueName) { //$NON-NLS-1$
        valueName = "[" + property.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    else {
      valueName = "<undefined>"; //$NON-NLS-1$
    }

    if (label == null)
      label = ""; //$NON-NLS-1$

    label = valuePrefix + label;
    label += " -> " + valueName; //$NON-NLS-1$

    return label;
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
