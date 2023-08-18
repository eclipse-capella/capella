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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.impl.DeploymentConfigurationImpl#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.deployment.impl.DeploymentConfigurationImpl#getOwnedPhysicalInstances <em>Owned Physical Instances</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeploymentConfigurationImpl extends NamedElementImpl implements DeploymentConfiguration {

	/**
   * The cached value of the '{@link #getOwnedDeploymentLinks() <em>Owned Deployment Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDeploymentLinks()
   * @generated
   * @ordered
   */
	protected EList<AbstractDeploymentLink> ownedDeploymentLinks;





	/**
   * The cached value of the '{@link #getOwnedPhysicalInstances() <em>Owned Physical Instances</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalInstances()
   * @generated
   * @ordered
   */
	protected EList<AbstractPhysicalInstance> ownedPhysicalInstances;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected DeploymentConfigurationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DeploymentPackage.Literals.DEPLOYMENT_CONFIGURATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractDeploymentLink> getOwnedDeploymentLinks() {

    if (ownedDeploymentLinks == null) {
      ownedDeploymentLinks = new EObjectContainmentEList.Resolving<AbstractDeploymentLink>(AbstractDeploymentLink.class, this, DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS);
    }
    return ownedDeploymentLinks;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractPhysicalInstance> getOwnedPhysicalInstances() {

    if (ownedPhysicalInstances == null) {
      ownedPhysicalInstances = new EObjectContainmentEList.Resolving<AbstractPhysicalInstance>(AbstractPhysicalInstance.class, this, DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES);
    }
    return ownedPhysicalInstances;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS:
        return ((InternalEList<?>)getOwnedDeploymentLinks()).basicRemove(otherEnd, msgs);
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES:
        return ((InternalEList<?>)getOwnedPhysicalInstances()).basicRemove(otherEnd, msgs);
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
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS:
        return getOwnedDeploymentLinks();
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES:
        return getOwnedPhysicalInstances();
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
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS:
        getOwnedDeploymentLinks().clear();
        getOwnedDeploymentLinks().addAll((Collection<? extends AbstractDeploymentLink>)newValue);
        return;
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES:
        getOwnedPhysicalInstances().clear();
        getOwnedPhysicalInstances().addAll((Collection<? extends AbstractPhysicalInstance>)newValue);
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
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS:
        getOwnedDeploymentLinks().clear();
        return;
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES:
        getOwnedPhysicalInstances().clear();
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
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS:
        return ownedDeploymentLinks != null && !ownedDeploymentLinks.isEmpty();
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES:
        return ownedPhysicalInstances != null && !ownedPhysicalInstances.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //DeploymentConfigurationImpl