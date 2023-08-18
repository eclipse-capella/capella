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

package org.polarsys.capella.core.data.cs.provider;

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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.PhysicalLinkEnd} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalLinkEndItemProvider
	extends AbstractPhysicalLinkEndItemProvider
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
	protected IItemPropertyDescriptor portPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor partPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PhysicalLinkEndItemProvider(AdapterFactory adapterFactory) {
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
      // Process CsPackage.Literals.PHYSICAL_LINK_END__PORT
      if (portPropertyDescriptor != null) {
        Object portValue = eObject.eGet(CsPackage.Literals.PHYSICAL_LINK_END__PORT, true);
        if (portValue != null && portValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) portValue)) {
          itemPropertyDescriptors.remove(portPropertyDescriptor);
        } else if (portValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.PHYSICAL_LINK_END__PORT) != null) {
          itemPropertyDescriptors.remove(portPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(portPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(portPropertyDescriptor);
        }
      }
      // Process CsPackage.Literals.PHYSICAL_LINK_END__PART
      if (partPropertyDescriptor != null) {
        Object partValue = eObject.eGet(CsPackage.Literals.PHYSICAL_LINK_END__PART, true);
        if (partValue != null && partValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) partValue)) {
          itemPropertyDescriptors.remove(partPropertyDescriptor);
        } else if (partValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.PHYSICAL_LINK_END__PART) != null) {
          itemPropertyDescriptors.remove(partPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(partPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(partPropertyDescriptor);
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

      addPortPropertyDescriptor(object);
      addPartPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Port feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addPortPropertyDescriptor(Object object) {
    // begin-extension-code
    portPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalLinkEnd_port_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLinkEnd_port_feature", "_UI_PhysicalLinkEnd_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_LINK_END__PORT,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(portPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Part feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addPartPropertyDescriptor(Object object) {
    // begin-extension-code
    partPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_PhysicalLinkEnd_part_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_PhysicalLinkEnd_part_feature", "_UI_PhysicalLinkEnd_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CsPackage.Literals.PHYSICAL_LINK_END__PART,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(partPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This returns PhysicalLinkEnd.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalLinkEnd")); //$NON-NLS-1$
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
   	label = "[" + getString("_UI_PhysicalLinkEnd_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_PhysicalLinkEnd_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
   String[] result = new String[] { null };

    //begin-capella-code
    
   String label = ""; //$NON-NLS-1$
   String portName = ""; //$NON-NLS-1$
   EObject port = ((PhysicalLinkEnd)object).getPort();

   String partName = ""; //$NON-NLS-1$
   EObject part = ((PhysicalLinkEnd)object).getPart();

    
    if (null != port) {
      if (port instanceof AbstractNamedElement) {
        portName = ((AbstractNamedElement)port).getName();
      }

      if (null == portName || "" == portName) { //$NON-NLS-1$
        portName = "[" + port.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    
    if (null != part) {
      if (part instanceof AbstractNamedElement) {
        partName = "[" + ((AbstractNamedElement) part).getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }

      if (null == partName || "" == partName) { //$NON-NLS-1$
        partName = "[" + part.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    
    label = "[" + getString("_UI_PhysicalLinkEnd_type") + "] to " + portName + " " + partName ; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
    //end-capella-code
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_PhysicalLinkEnd_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
