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
package org.polarsys.capella.core.data.information.communication.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.information.communication.SignalInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.SignalImpl#getSignalInstances <em>Signal Instances</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SignalImpl extends CommunicationItemImpl implements Signal {

	/**
   * The cached value of the '{@link #getSignalInstances() <em>Signal Instances</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSignalInstances()
   * @generated
   * @ordered
   */
	protected EList<SignalInstance> signalInstances;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SignalImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CommunicationPackage.Literals.SIGNAL;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SignalInstance> getSignalInstances() {

    if (signalInstances == null) {
      signalInstances = new EObjectContainmentEList.Resolving<SignalInstance>(SignalInstance.class, this, CommunicationPackage.SIGNAL__SIGNAL_INSTANCES);
    }
    return signalInstances;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CommunicationPackage.SIGNAL__SIGNAL_INSTANCES:
        return ((InternalEList<?>)getSignalInstances()).basicRemove(otherEnd, msgs);
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
      case CommunicationPackage.SIGNAL__SIGNAL_INSTANCES:
        return getSignalInstances();
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
      case CommunicationPackage.SIGNAL__SIGNAL_INSTANCES:
        getSignalInstances().clear();
        getSignalInstances().addAll((Collection<? extends SignalInstance>)newValue);
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
      case CommunicationPackage.SIGNAL__SIGNAL_INSTANCES:
        getSignalInstances().clear();
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
      case CommunicationPackage.SIGNAL__SIGNAL_INSTANCES:
        return signalInstances != null && !signalInstances.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //SignalImpl