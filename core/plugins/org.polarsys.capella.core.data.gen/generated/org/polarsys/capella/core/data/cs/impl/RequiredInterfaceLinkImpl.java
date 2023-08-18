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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.RequiredInterfaceLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Required Interface Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.RequiredInterfaceLinkImpl#getInterface <em>Interface</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class RequiredInterfaceLinkImpl extends RelationshipImpl implements RequiredInterfaceLink {

	/**
   * The cached value of the '{@link #getInterface() <em>Interface</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInterface()
   * @generated
   * @ordered
   */
	protected Interface interface_;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RequiredInterfaceLinkImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.REQUIRED_INTERFACE_LINK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Interface getInterface() {

    if (interface_ != null && interface_.eIsProxy()) {
      InternalEObject oldInterface = (InternalEObject)interface_;
      interface_ = (Interface)eResolveProxy(oldInterface);
      if (interface_ != oldInterface) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.REQUIRED_INTERFACE_LINK__INTERFACE, oldInterface, interface_));
      }
    }
    return interface_;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Interface basicGetInterface() {

    return interface_;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setInterface(Interface newInterface) {

    Interface oldInterface = interface_;
    interface_ = newInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.REQUIRED_INTERFACE_LINK__INTERFACE, oldInterface, interface_));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CsPackage.REQUIRED_INTERFACE_LINK__INTERFACE:
        if (resolve) return getInterface();
        return basicGetInterface();
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
      case CsPackage.REQUIRED_INTERFACE_LINK__INTERFACE:
          setInterface((Interface)newValue);
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
      case CsPackage.REQUIRED_INTERFACE_LINK__INTERFACE:
        setInterface((Interface)null);
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
      case CsPackage.REQUIRED_INTERFACE_LINK__INTERFACE:
        return interface_ != null;
    }
    return super.eIsSet(featureID);
  }



} //RequiredInterfaceLinkImpl