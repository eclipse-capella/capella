/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.vp.ms.ms.InStateExpression} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InStateExpressionItemProvider extends BooleanExpressionItemProvider implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected IItemPropertyDescriptor statePropertyDescriptor;

  /**
   * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public InStateExpressionItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void checkChildCreationExtender(Object object) {
    super.checkChildCreationExtender(object);
    if (object instanceof EObject) {
      EObject eObject = (EObject) object;
      // Process MsPackage.Literals.IN_STATE_EXPRESSION__STATE
      if (statePropertyDescriptor != null) {
        Object stateValue = eObject.eGet(MsPackage.Literals.IN_STATE_EXPRESSION__STATE, true);
        if (stateValue != null && stateValue instanceof EObject
            && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) stateValue)) {
          itemPropertyDescriptors.remove(statePropertyDescriptor);
        } else if (stateValue == null
            && ExtensionModelManager.getAnyType(eObject, MsPackage.Literals.IN_STATE_EXPRESSION__STATE) != null) {
          itemPropertyDescriptors.remove(statePropertyDescriptor);
        } else if (itemPropertyDescriptors.contains(statePropertyDescriptor) == false) {
          itemPropertyDescriptors.add(statePropertyDescriptor);
        }
      }
    }
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

      addStatePropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the State feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addStatePropertyDescriptor(Object object) {
    // begin-extension-code
    statePropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code
    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_InStateExpression_state_feature"), //$NON-NLS-1$
        getString("_UI_PropertyDescriptor_description", "_UI_InStateExpression_state_feature", //$NON-NLS-1$ //$NON-NLS-2$
            "_UI_InStateExpression_type"), //$NON-NLS-1$
        MsPackage.Literals.IN_STATE_EXPRESSION__STATE, true, false, true, null, null,
        // begin-extension-code
        null);
    itemPropertyDescriptors.add(statePropertyDescriptor);
    // end-extension-code
  }

  /**
   * This returns InStateExpression.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @Override
  public Object getImage(Object object) {
    InStateExpression expr = (InStateExpression) object;
    if (expr.getState() != null) {
      IItemLabelProvider provider = (IItemLabelProvider) ((ComposeableAdapterFactory) getAdapterFactory())
          .getRootAdapterFactory().adapt(expr.getState(), IItemLabelProvider.class);
      if (provider != null) {
        return provider.getImage(expr.getState());
      }
    }
    return overlayImage(object, getResourceLocator().getImage("full/obj16/InStateExpression")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @Override
  public String getText(Object object) {

    InStateExpression expr = (InStateExpression) object;
    if (expr.getState() != null) {
      IItemLabelProvider provider = (IItemLabelProvider) ((ComposeableAdapterFactory) getAdapterFactory())
          .getRootAdapterFactory().adapt(expr.getState(), IItemLabelProvider.class);
      if (provider != null) {
        return provider.getText(expr.getState());
      }
    }

    String label = ((InStateExpression) object).getId();
    // begin-extension-code
    return label == null || label.length() == 0 ? "[" + getString("_UI_InStateExpression_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
  }

}