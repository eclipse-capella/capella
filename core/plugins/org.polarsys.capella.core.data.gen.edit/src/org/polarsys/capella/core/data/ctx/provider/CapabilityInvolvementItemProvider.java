/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.ctx.provider;

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
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.provider.RelationshipItemProvider;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CapabilityInvolvementItemProvider
	extends RelationshipItemProvider
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
	protected IItemPropertyDescriptor involverPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor involvedPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor systemComponentPropertyDescriptor;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor capabilityPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapabilityInvolvementItemProvider(AdapterFactory adapterFactory) {
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
      // Process CapellacorePackage.Literals.INVOLVEMENT__INVOLVER
      if (involverPropertyDescriptor != null) {
        Object involverValue = eObject.eGet(CapellacorePackage.Literals.INVOLVEMENT__INVOLVER, true);
        if (involverValue != null && involverValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) involverValue)) {
          itemPropertyDescriptors.remove(involverPropertyDescriptor);
        } else if (involverValue == null && ExtensionModelManager.getAnyType(eObject, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER) != null) {
          itemPropertyDescriptors.remove(involverPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(involverPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(involverPropertyDescriptor);
        }
      }
      // Process CapellacorePackage.Literals.INVOLVEMENT__INVOLVED
      if (involvedPropertyDescriptor != null) {
        Object involvedValue = eObject.eGet(CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, true);
        if (involvedValue != null && involvedValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) involvedValue)) {
          itemPropertyDescriptors.remove(involvedPropertyDescriptor);
        } else if (involvedValue == null && ExtensionModelManager.getAnyType(eObject, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED) != null) {
          itemPropertyDescriptors.remove(involvedPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(involvedPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(involvedPropertyDescriptor);
        }
      }
      // Process CtxPackage.Literals.CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT
      if (systemComponentPropertyDescriptor != null) {
        Object systemComponentValue = eObject.eGet(CtxPackage.Literals.CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT, true);
        if (systemComponentValue != null && systemComponentValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) systemComponentValue)) {
          itemPropertyDescriptors.remove(systemComponentPropertyDescriptor);
        } else if (systemComponentValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT) != null) {
          itemPropertyDescriptors.remove(systemComponentPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(systemComponentPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(systemComponentPropertyDescriptor);
        }
      }
      // Process CtxPackage.Literals.CAPABILITY_INVOLVEMENT__CAPABILITY
      if (capabilityPropertyDescriptor != null) {
        Object capabilityValue = eObject.eGet(CtxPackage.Literals.CAPABILITY_INVOLVEMENT__CAPABILITY, true);
        if (capabilityValue != null && capabilityValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) capabilityValue)) {
          itemPropertyDescriptors.remove(capabilityPropertyDescriptor);
        } else if (capabilityValue == null && ExtensionModelManager.getAnyType(eObject, CtxPackage.Literals.CAPABILITY_INVOLVEMENT__CAPABILITY) != null) {
          itemPropertyDescriptors.remove(capabilityPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(capabilityPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(capabilityPropertyDescriptor);
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

      addInvolverPropertyDescriptor(object);
      addInvolvedPropertyDescriptor(object);
      addSystemComponentPropertyDescriptor(object);
      addCapabilityPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Involver feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolverPropertyDescriptor(Object object) {
    // begin-extension-code
    involverPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Involvement_involver_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Involvement_involver_feature", "_UI_Involvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.INVOLVEMENT__INVOLVER,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(involverPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involved feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedPropertyDescriptor(Object object) {
    // begin-extension-code
    involvedPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Involvement_involved_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Involvement_involved_feature", "_UI_Involvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.INVOLVEMENT__INVOLVED,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(involvedPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the System Component feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSystemComponentPropertyDescriptor(Object object) {
    // begin-extension-code
    systemComponentPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapabilityInvolvement_systemComponent_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapabilityInvolvement_systemComponent_feature", "_UI_CapabilityInvolvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(systemComponentPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Capability feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addCapabilityPropertyDescriptor(Object object) {
    // begin-extension-code
    capabilityPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_CapabilityInvolvement_capability_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_CapabilityInvolvement_capability_feature", "_UI_CapabilityInvolvement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CtxPackage.Literals.CAPABILITY_INVOLVEMENT__CAPABILITY,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(capabilityPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns CapabilityInvolvement.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/CapabilityInvolvement")); //$NON-NLS-1$
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

 		target = ((Involvement) object).getInvolved();
  
   	if (null != target) {
      if (target instanceof AbstractNamedElement) {
        targetName = ((AbstractNamedElement) target).getName();
      }

      if (null == targetName || "" == targetName) { //$NON-NLS-1$
        targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
   	}
   	label = "[" + getString("_UI_CapabilityInvolvement_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_CapabilityInvolvement_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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