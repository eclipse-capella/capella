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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.ConnectionInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.PortInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.impl.PortInstanceImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.impl.PortInstanceImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.impl.PortInstanceImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PortInstanceImpl extends AbstractPhysicalInstanceImpl implements PortInstance {

	/**
   * The cached value of the '{@link #getConnections() <em>Connections</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getConnections()
   * @generated
   * @ordered
   */
	protected EList<ConnectionInstance> connections;









	/**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
	protected ComponentPort type;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PortInstanceImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DeploymentPackage.Literals.PORT_INSTANCE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ConnectionInstance> getConnections() {

    if (connections == null) {
      connections = new EObjectWithInverseResolvingEList.ManyInverse<ConnectionInstance>(ConnectionInstance.class, this, DeploymentPackage.PORT_INSTANCE__CONNECTIONS, DeploymentPackage.CONNECTION_INSTANCE__CONNECTION_ENDS);
    }
    return connections;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentInstance getComponent() {

    ComponentInstance component = basicGetComponent();
    return component != null && component.eIsProxy() ? (ComponentInstance)eResolveProxy((InternalEObject)component) : component;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentInstance basicGetComponent() {


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
    EAnnotation annotation = DeploymentPackage.Literals.PORT_INSTANCE__COMPONENT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, DeploymentPackage.Literals.PORT_INSTANCE__COMPONENT, annotation);
    
    try {
      return (ComponentInstance) result;
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

	public ComponentPort getType() {

    if (type != null && type.eIsProxy()) {
      InternalEObject oldType = (InternalEObject)type;
      type = (ComponentPort)eResolveProxy(oldType);
      if (type != oldType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.PORT_INSTANCE__TYPE, oldType, type));
      }
    }
    return type;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ComponentPort basicGetType() {

    return type;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setType(ComponentPort newType) {

    ComponentPort oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.PORT_INSTANCE__TYPE, oldType, type));

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
      case DeploymentPackage.PORT_INSTANCE__CONNECTIONS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnections()).basicAdd(otherEnd, msgs);
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
      case DeploymentPackage.PORT_INSTANCE__CONNECTIONS:
        return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
      case DeploymentPackage.PORT_INSTANCE__CONNECTIONS:
        return getConnections();
      case DeploymentPackage.PORT_INSTANCE__COMPONENT:
        if (resolve) return getComponent();
        return basicGetComponent();
      case DeploymentPackage.PORT_INSTANCE__TYPE:
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
      case DeploymentPackage.PORT_INSTANCE__CONNECTIONS:
        getConnections().clear();
        getConnections().addAll((Collection<? extends ConnectionInstance>)newValue);
        return;
      case DeploymentPackage.PORT_INSTANCE__TYPE:
          setType((ComponentPort)newValue);
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
      case DeploymentPackage.PORT_INSTANCE__CONNECTIONS:
        getConnections().clear();
        return;
      case DeploymentPackage.PORT_INSTANCE__TYPE:
        setType((ComponentPort)null);
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
      case DeploymentPackage.PORT_INSTANCE__CONNECTIONS:
        return connections != null && !connections.isEmpty();
      case DeploymentPackage.PORT_INSTANCE__COMPONENT:
        return basicGetComponent() != null;
      case DeploymentPackage.PORT_INSTANCE__TYPE:
        return type != null;
    }
    return super.eIsSet(featureID);
  }



} //PortInstanceImpl