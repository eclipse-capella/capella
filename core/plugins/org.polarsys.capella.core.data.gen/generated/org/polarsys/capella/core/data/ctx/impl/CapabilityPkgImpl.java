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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacommon.impl.AbstractCapabilityPkgImpl;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityPkgImpl#getOwnedCapabilities <em>Owned Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityPkgImpl#getOwnedCapabilityPkgs <em>Owned Capability Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CapabilityPkgImpl extends AbstractCapabilityPkgImpl implements CapabilityPkg {

	/**
   * The cached value of the '{@link #getOwnedCapabilities() <em>Owned Capabilities</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCapabilities()
   * @generated
   * @ordered
   */
	protected EList<Capability> ownedCapabilities;





	/**
   * The cached value of the '{@link #getOwnedCapabilityPkgs() <em>Owned Capability Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCapabilityPkgs()
   * @generated
   * @ordered
   */
	protected EList<CapabilityPkg> ownedCapabilityPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CapabilityPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CtxPackage.Literals.CAPABILITY_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Capability> getOwnedCapabilities() {

    if (ownedCapabilities == null) {
      ownedCapabilities = new EObjectContainmentEList.Resolving<Capability>(Capability.class, this, CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITIES);
    }
    return ownedCapabilities;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityPkg> getOwnedCapabilityPkgs() {

    if (ownedCapabilityPkgs == null) {
      ownedCapabilityPkgs = new EObjectContainmentEList.Resolving<CapabilityPkg>(CapabilityPkg.class, this, CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS);
    }
    return ownedCapabilityPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITIES:
        return ((InternalEList<?>)getOwnedCapabilities()).basicRemove(otherEnd, msgs);
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS:
        return ((InternalEList<?>)getOwnedCapabilityPkgs()).basicRemove(otherEnd, msgs);
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
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITIES:
        return getOwnedCapabilities();
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS:
        return getOwnedCapabilityPkgs();
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
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITIES:
        getOwnedCapabilities().clear();
        getOwnedCapabilities().addAll((Collection<? extends Capability>)newValue);
        return;
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS:
        getOwnedCapabilityPkgs().clear();
        getOwnedCapabilityPkgs().addAll((Collection<? extends CapabilityPkg>)newValue);
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
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITIES:
        getOwnedCapabilities().clear();
        return;
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS:
        getOwnedCapabilityPkgs().clear();
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
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITIES:
        return ownedCapabilities != null && !ownedCapabilities.isEmpty();
      case CtxPackage.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS:
        return ownedCapabilityPkgs != null && !ownedCapabilityPkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //CapabilityPkgImpl