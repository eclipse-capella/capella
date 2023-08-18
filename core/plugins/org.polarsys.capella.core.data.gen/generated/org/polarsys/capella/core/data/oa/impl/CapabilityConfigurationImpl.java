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
package org.polarsys.capella.core.data.oa.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.oa.CapabilityConfiguration;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.CapabilityConfigurationImpl#getConfiguredCapability <em>Configured Capability</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CapabilityConfigurationImpl extends AbstractConceptItemImpl implements CapabilityConfiguration {

	/**
   * The cached value of the '{@link #getConfiguredCapability() <em>Configured Capability</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getConfiguredCapability()
   * @generated
   * @ordered
   */
	protected OperationalCapability configuredCapability;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CapabilityConfigurationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.CAPABILITY_CONFIGURATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalCapability getConfiguredCapability() {

    if (configuredCapability != null && configuredCapability.eIsProxy()) {
      InternalEObject oldConfiguredCapability = (InternalEObject)configuredCapability;
      configuredCapability = (OperationalCapability)eResolveProxy(oldConfiguredCapability);
      if (configuredCapability != oldConfiguredCapability) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY, oldConfiguredCapability, configuredCapability));
      }
    }
    return configuredCapability;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalCapability basicGetConfiguredCapability() {

    return configuredCapability;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setConfiguredCapability(OperationalCapability newConfiguredCapability) {

    OperationalCapability oldConfiguredCapability = configuredCapability;
    configuredCapability = newConfiguredCapability;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY, oldConfiguredCapability, configuredCapability));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY:
        if (resolve) return getConfiguredCapability();
        return basicGetConfiguredCapability();
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
      case OaPackage.CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY:
          setConfiguredCapability((OperationalCapability)newValue);
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
      case OaPackage.CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY:
        setConfiguredCapability((OperationalCapability)null);
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
      case OaPackage.CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY:
        return configuredCapability != null;
    }
    return super.eIsSet(featureID);
  }



} //CapabilityConfigurationImpl