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

package org.polarsys.capella.core.data.interaction.provider;

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
import org.polarsys.capella.core.data.capellacore.provider.RelationshipItemProvider;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractCapabilityExtendItemProvider extends RelationshipItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
    ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor extendedPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor extensionPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor extensionLocationPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AbstractCapabilityExtendItemProvider(AdapterFactory adapterFactory) {
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
      // Process InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED
      if (extendedPropertyDescriptor != null) {
        Object extendedValue = eObject.eGet(InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED, true);
        if (extendedValue != null && extendedValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) extendedValue)) {
          itemPropertyDescriptors.remove(extendedPropertyDescriptor);
        } else if (extendedValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED) != null) {
          itemPropertyDescriptors.remove(extendedPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(extendedPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(extendedPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION
      if (extensionPropertyDescriptor != null) {
        Object extensionValue = eObject.eGet(InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION, true);
        if (extensionValue != null && extensionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) extensionValue)) {
          itemPropertyDescriptors.remove(extensionPropertyDescriptor);
        } else if (extensionValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION) != null) {
          itemPropertyDescriptors.remove(extensionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(extensionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(extensionPropertyDescriptor);
        }
      }
      // Process InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION
      if (extensionLocationPropertyDescriptor != null) {
        Object extensionLocationValue = eObject.eGet(InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION, true);
        if (extensionLocationValue != null && extensionLocationValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) extensionLocationValue)) {
          itemPropertyDescriptors.remove(extensionLocationPropertyDescriptor);
        } else if (extensionLocationValue == null && ExtensionModelManager.getAnyType(eObject, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION) != null) {
          itemPropertyDescriptors.remove(extensionLocationPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(extensionLocationPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(extensionLocationPropertyDescriptor);
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

      addExtendedPropertyDescriptor(object);
      addExtensionPropertyDescriptor(object);
      addExtensionLocationPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Extended feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExtendedPropertyDescriptor(Object object) {
    // begin-extension-code
    extendedPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractCapabilityExtend_extended_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractCapabilityExtend_extended_feature", "_UI_AbstractCapabilityExtend_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(extendedPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Extension feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExtensionPropertyDescriptor(Object object) {
    // begin-extension-code
    extensionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractCapabilityExtend_extension_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractCapabilityExtend_extension_feature", "_UI_AbstractCapabilityExtend_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(extensionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Extension Location feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addExtensionLocationPropertyDescriptor(Object object) {
    // begin-extension-code
    extensionLocationPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_AbstractCapabilityExtend_extensionLocation_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_AbstractCapabilityExtend_extensionLocation_feature", "_UI_AbstractCapabilityExtend_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(extensionLocationPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns AbstractCapabilityExtend.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/AbstractCapabilityExtend")); //$NON-NLS-1$
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
   	label = "[" + getString("_UI_AbstractCapabilityExtend_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_AbstractCapabilityExtend_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
    String targetName = ""; //$NON-NLS-1$

    EObject target = ((AbstractCapabilityExtend) object).getExtended();
    if (null != target) {
      if (target instanceof AbstractNamedElement) {
        targetName = ((AbstractNamedElement) target).getName();
      }
      if (null == targetName || "" == targetName) { //$NON-NLS-1$
        targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    return "[" + getString("_UI_AbstractCapabilityExtend_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
