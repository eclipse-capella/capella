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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.SendSignalAction;
import org.polarsys.capella.common.data.behavior.AbstractSignal;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Send Signal Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.SendSignalActionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.SendSignalActionImpl#getSignal <em>Signal</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class SendSignalActionImpl extends InvocationActionImpl implements SendSignalAction {

	/**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
	protected InputPin target;





	/**
   * The cached value of the '{@link #getSignal() <em>Signal</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSignal()
   * @generated
   * @ordered
   */
	protected AbstractSignal signal;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SendSignalActionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.SEND_SIGNAL_ACTION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InputPin getTarget() {

    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (InputPin)eResolveProxy(oldTarget);
      if (target != oldTarget) {
        InternalEObject newTarget = (InternalEObject)target;
        NotificationChain msgs = oldTarget.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.SEND_SIGNAL_ACTION__TARGET, null, null);
        if (newTarget.eInternalContainer() == null) {
          msgs = newTarget.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.SEND_SIGNAL_ACTION__TARGET, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.SEND_SIGNAL_ACTION__TARGET, oldTarget, target));
      }
    }
    return target;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InputPin basicGetTarget() {

    return target;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetTarget(InputPin newTarget, NotificationChain msgs) {

    InputPin oldTarget = target;
    target = newTarget;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.SEND_SIGNAL_ACTION__TARGET, oldTarget, newTarget);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setTarget(InputPin newTarget) {

    if (newTarget != target) {
      NotificationChain msgs = null;
      if (target != null)
        msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.SEND_SIGNAL_ACTION__TARGET, null, msgs);
      if (newTarget != null)
        msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.SEND_SIGNAL_ACTION__TARGET, null, msgs);
      msgs = basicSetTarget(newTarget, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.SEND_SIGNAL_ACTION__TARGET, newTarget, newTarget));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractSignal getSignal() {

    if (signal != null && signal.eIsProxy()) {
      InternalEObject oldSignal = (InternalEObject)signal;
      signal = (AbstractSignal)eResolveProxy(oldSignal);
      if (signal != oldSignal) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.SEND_SIGNAL_ACTION__SIGNAL, oldSignal, signal));
      }
    }
    return signal;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractSignal basicGetSignal() {

    return signal;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSignal(AbstractSignal newSignal) {

    AbstractSignal oldSignal = signal;
    signal = newSignal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.SEND_SIGNAL_ACTION__SIGNAL, oldSignal, signal));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ActivityPackage.SEND_SIGNAL_ACTION__TARGET:
        return basicSetTarget(null, msgs);
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
      case ActivityPackage.SEND_SIGNAL_ACTION__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case ActivityPackage.SEND_SIGNAL_ACTION__SIGNAL:
        if (resolve) return getSignal();
        return basicGetSignal();
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
      case ActivityPackage.SEND_SIGNAL_ACTION__TARGET:
          setTarget((InputPin)newValue);
        return;
      case ActivityPackage.SEND_SIGNAL_ACTION__SIGNAL:
          setSignal((AbstractSignal)newValue);
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
      case ActivityPackage.SEND_SIGNAL_ACTION__TARGET:
        setTarget((InputPin)null);
        return;
      case ActivityPackage.SEND_SIGNAL_ACTION__SIGNAL:
        setSignal((AbstractSignal)null);
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
      case ActivityPackage.SEND_SIGNAL_ACTION__TARGET:
        return target != null;
      case ActivityPackage.SEND_SIGNAL_ACTION__SIGNAL:
        return signal != null;
    }
    return super.eIsSet(featureID);
  }



} //SendSignalActionImpl