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
package org.polarsys.capella.common.data.activity.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.activity.AcceptEventAction;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.OutputPin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Accept Event Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AcceptEventActionImpl#isIsUnmarshall <em>Is Unmarshall</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AcceptEventActionImpl#getResult <em>Result</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AcceptEventActionImpl extends AbstractActionImpl implements AcceptEventAction {

	/**
   * The default value of the '{@link #isIsUnmarshall() <em>Is Unmarshall</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsUnmarshall()
   * @generated
   * @ordered
   */
	protected static final boolean IS_UNMARSHALL_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsUnmarshall() <em>Is Unmarshall</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsUnmarshall()
   * @generated
   * @ordered
   */
	protected boolean isUnmarshall = IS_UNMARSHALL_EDEFAULT;





	/**
   * The cached value of the '{@link #getResult() <em>Result</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getResult()
   * @generated
   * @ordered
   */
	protected EList<OutputPin> result;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AcceptEventActionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.ACCEPT_EVENT_ACTION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsUnmarshall() {

    return isUnmarshall;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsUnmarshall(boolean newIsUnmarshall) {

    boolean oldIsUnmarshall = isUnmarshall;
    isUnmarshall = newIsUnmarshall;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ACCEPT_EVENT_ACTION__IS_UNMARSHALL, oldIsUnmarshall, isUnmarshall));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OutputPin> getResult() {

    if (result == null) {
      result = new EObjectContainmentEList.Resolving<OutputPin>(OutputPin.class, this, ActivityPackage.ACCEPT_EVENT_ACTION__RESULT);
    }
    return result;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ActivityPackage.ACCEPT_EVENT_ACTION__RESULT:
        return ((InternalEList<?>)getResult()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ActivityPackage.ACCEPT_EVENT_ACTION__IS_UNMARSHALL:
        return isIsUnmarshall();
      case ActivityPackage.ACCEPT_EVENT_ACTION__RESULT:
        return getResult();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ActivityPackage.ACCEPT_EVENT_ACTION__IS_UNMARSHALL:
          setIsUnmarshall((Boolean)newValue);
        return;
      case ActivityPackage.ACCEPT_EVENT_ACTION__RESULT:
        getResult().clear();
        getResult().addAll((Collection<? extends OutputPin>)newValue);
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
      case ActivityPackage.ACCEPT_EVENT_ACTION__IS_UNMARSHALL:
        setIsUnmarshall(IS_UNMARSHALL_EDEFAULT);
        return;
      case ActivityPackage.ACCEPT_EVENT_ACTION__RESULT:
        getResult().clear();
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
      case ActivityPackage.ACCEPT_EVENT_ACTION__IS_UNMARSHALL:
        return isUnmarshall != IS_UNMARSHALL_EDEFAULT;
      case ActivityPackage.ACCEPT_EVENT_ACTION__RESULT:
        return result != null && !result.isEmpty();
    }
    return super.eIsSet(featureID);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (isUnmarshall: "); //$NON-NLS-1$
    result.append(isUnmarshall);
    result.append(')');
    return result.toString();
  }


} //AcceptEventActionImpl