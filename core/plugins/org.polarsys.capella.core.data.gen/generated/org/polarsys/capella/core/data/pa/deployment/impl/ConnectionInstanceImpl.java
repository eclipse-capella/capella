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
package org.polarsys.capella.core.data.pa.deployment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.pa.deployment.ConnectionInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.PortInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.impl.ConnectionInstanceImpl#getConnectionEnds <em>Connection Ends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.impl.ConnectionInstanceImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionInstanceImpl extends AbstractPhysicalInstanceImpl implements ConnectionInstance {

	/**
   * The cached value of the '{@link #getConnectionEnds() <em>Connection Ends</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getConnectionEnds()
   * @generated
   * @ordered
   */
	protected EList<PortInstance> connectionEnds;





	/**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
	protected ComponentExchange type;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ConnectionInstanceImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DeploymentPackage.Literals.CONNECTION_INSTANCE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PortInstance> getConnectionEnds() {

    if (connectionEnds == null) {
      connectionEnds = new EObjectWithInverseResolvingEList.ManyInverse<PortInstance>(PortInstance.class, this, DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS, DeploymentPackage.PORT_INSTANCE__CONNECTIONS);
    }
    return connectionEnds;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentExchange getType() {

    if (type != null && type.eIsProxy()) {
      InternalEObject oldType = (InternalEObject)type;
      type = (ComponentExchange)eResolveProxy(oldType);
      if (type != oldType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.CONNECTION_INSTANCE__TYPE, oldType, type));
      }
    }
    return type;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentExchange basicGetType() {

    return type;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setType(ComponentExchange newType) {

    ComponentExchange oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.CONNECTION_INSTANCE__TYPE, oldType, type));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnectionEnds()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS:
        return ((InternalEList<?>)getConnectionEnds()).basicRemove(otherEnd, msgs);
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
      case DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS:
        return getConnectionEnds();
      case DeploymentPackage.CONNECTION_INSTANCE__TYPE:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS:
        getConnectionEnds().clear();
        getConnectionEnds().addAll((Collection<? extends PortInstance>)newValue);
        return;
      case DeploymentPackage.CONNECTION_INSTANCE__TYPE:
          setType((ComponentExchange)newValue);
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
      case DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS:
        getConnectionEnds().clear();
        return;
      case DeploymentPackage.CONNECTION_INSTANCE__TYPE:
        setType((ComponentExchange)null);
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
      case DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS:
        return connectionEnds != null && !connectionEnds.isEmpty();
      case DeploymentPackage.CONNECTION_INSTANCE__TYPE:
        return type != null;
    }
    return super.eIsSet(featureID);
  }



} //ConnectionInstanceImpl