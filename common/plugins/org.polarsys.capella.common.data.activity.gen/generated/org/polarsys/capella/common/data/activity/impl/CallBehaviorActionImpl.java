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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.CallBehaviorAction;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Behavior Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.CallBehaviorActionImpl#getBehavior <em>Behavior</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CallBehaviorActionImpl extends CallActionImpl implements CallBehaviorAction {

	/**
   * The cached value of the '{@link #getBehavior() <em>Behavior</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getBehavior()
   * @generated
   * @ordered
   */
	protected AbstractBehavior behavior;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CallBehaviorActionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.CALL_BEHAVIOR_ACTION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBehavior getBehavior() {

    if (behavior != null && behavior.eIsProxy()) {
      InternalEObject oldBehavior = (InternalEObject)behavior;
      behavior = (AbstractBehavior)eResolveProxy(oldBehavior);
      if (behavior != oldBehavior) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR, oldBehavior, behavior));
      }
    }
    return behavior;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBehavior basicGetBehavior() {

    return behavior;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setBehavior(AbstractBehavior newBehavior) {

    AbstractBehavior oldBehavior = behavior;
    behavior = newBehavior;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR, oldBehavior, behavior));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ActivityPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR:
        if (resolve) return getBehavior();
        return basicGetBehavior();
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
      case ActivityPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR:
          setBehavior((AbstractBehavior)newValue);
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
      case ActivityPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR:
        setBehavior((AbstractBehavior)null);
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
      case ActivityPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR:
        return behavior != null;
    }
    return super.eIsSet(featureID);
  }



} //CallBehaviorActionImpl