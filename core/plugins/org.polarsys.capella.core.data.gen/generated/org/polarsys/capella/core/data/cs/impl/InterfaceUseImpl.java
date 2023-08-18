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

package org.polarsys.capella.core.data.cs.impl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceUse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceUseImpl#getInterfaceUser <em>Interface User</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceUseImpl#getUsedInterface <em>Used Interface</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterfaceUseImpl extends RelationshipImpl implements InterfaceUse {





	/**
   * The cached value of the '{@link #getUsedInterface() <em>Used Interface</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getUsedInterface()
   * @generated
   * @ordered
   */
	protected Interface usedInterface;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InterfaceUseImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.INTERFACE_USE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Component getInterfaceUser() {

    Component interfaceUser = basicGetInterfaceUser();
    return interfaceUser != null && interfaceUser.eIsProxy() ? (Component)eResolveProxy((InternalEObject)interfaceUser) : interfaceUser;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Component basicGetInterfaceUser() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.INTERFACE_USE__INTERFACE_USER.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE_USE__INTERFACE_USER, annotation);
    
    try {
      return (Component) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Interface getUsedInterface() {

    if (usedInterface != null && usedInterface.eIsProxy()) {
      InternalEObject oldUsedInterface = (InternalEObject)usedInterface;
      usedInterface = (Interface)eResolveProxy(oldUsedInterface);
      if (usedInterface != oldUsedInterface) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.INTERFACE_USE__USED_INTERFACE, oldUsedInterface, usedInterface));
      }
    }
    return usedInterface;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Interface basicGetUsedInterface() {

    return usedInterface;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setUsedInterface(Interface newUsedInterface) {

    Interface oldUsedInterface = usedInterface;
    usedInterface = newUsedInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.INTERFACE_USE__USED_INTERFACE, oldUsedInterface, usedInterface));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CsPackage.INTERFACE_USE__INTERFACE_USER:
        if (resolve) return getInterfaceUser();
        return basicGetInterfaceUser();
      case CsPackage.INTERFACE_USE__USED_INTERFACE:
        if (resolve) return getUsedInterface();
        return basicGetUsedInterface();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case CsPackage.INTERFACE_USE__USED_INTERFACE:
          setUsedInterface((Interface)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case CsPackage.INTERFACE_USE__USED_INTERFACE:
        setUsedInterface((Interface)null);
        return;
    }
    super.eUnset(featureID);
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case CsPackage.INTERFACE_USE__INTERFACE_USER:
        return basicGetInterfaceUser() != null;
      case CsPackage.INTERFACE_USE__USED_INTERFACE:
        return usedInterface != null;
    }
    return super.eIsSet(featureID);
  }



} //InterfaceUseImpl