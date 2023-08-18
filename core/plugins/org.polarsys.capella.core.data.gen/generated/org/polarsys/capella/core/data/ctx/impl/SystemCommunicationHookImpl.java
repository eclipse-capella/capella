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
package org.polarsys.capella.core.data.ctx.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemCommunication;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Communication Hook</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemCommunicationHookImpl#getCommunication <em>Communication</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemCommunicationHookImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemCommunicationHookImpl extends NamedElementImpl implements SystemCommunicationHook {

	/**
   * The cached value of the '{@link #getCommunication() <em>Communication</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCommunication()
   * @generated
   * @ordered
   */
	protected SystemCommunication communication;





	/**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
	protected Component type;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SystemCommunicationHookImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CtxPackage.Literals.SYSTEM_COMMUNICATION_HOOK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SystemCommunication getCommunication() {

    if (communication != null && communication.eIsProxy()) {
      InternalEObject oldCommunication = (InternalEObject)communication;
      communication = (SystemCommunication)eResolveProxy(oldCommunication);
      if (communication != oldCommunication) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.SYSTEM_COMMUNICATION_HOOK__COMMUNICATION, oldCommunication, communication));
      }
    }
    return communication;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SystemCommunication basicGetCommunication() {

    return communication;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setCommunication(SystemCommunication newCommunication) {

    SystemCommunication oldCommunication = communication;
    communication = newCommunication;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_COMMUNICATION_HOOK__COMMUNICATION, oldCommunication, communication));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Component getType() {

    if (type != null && type.eIsProxy()) {
      InternalEObject oldType = (InternalEObject)type;
      type = (Component)eResolveProxy(oldType);
      if (type != oldType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.SYSTEM_COMMUNICATION_HOOK__TYPE, oldType, type));
      }
    }
    return type;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Component basicGetType() {

    return type;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setType(Component newType) {

    Component oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_COMMUNICATION_HOOK__TYPE, oldType, type));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__COMMUNICATION:
        if (resolve) return getCommunication();
        return basicGetCommunication();
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__TYPE:
        if (resolve) return getType();
        return basicGetType();
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
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__COMMUNICATION:
          setCommunication((SystemCommunication)newValue);
        return;
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__TYPE:
          setType((Component)newValue);
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
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__COMMUNICATION:
        setCommunication((SystemCommunication)null);
        return;
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__TYPE:
        setType((Component)null);
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
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__COMMUNICATION:
        return communication != null;
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK__TYPE:
        return type != null;
    }
    return super.eIsSet(featureID);
  }



} //SystemCommunicationHookImpl