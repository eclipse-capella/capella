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
package org.polarsys.capella.core.data.la.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacommon.impl.AbstractCapabilityPkgImpl;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability Realization Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationPkgImpl#getOwnedCapabilityRealizations <em>Owned Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationPkgImpl#getOwnedCapabilityRealizationPkgs <em>Owned Capability Realization Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CapabilityRealizationPkgImpl extends AbstractCapabilityPkgImpl implements CapabilityRealizationPkg {

	/**
   * The cached value of the '{@link #getOwnedCapabilityRealizations() <em>Owned Capability Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCapabilityRealizations()
   * @generated
   * @ordered
   */
	protected EList<CapabilityRealization> ownedCapabilityRealizations;





	/**
   * The cached value of the '{@link #getOwnedCapabilityRealizationPkgs() <em>Owned Capability Realization Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCapabilityRealizationPkgs()
   * @generated
   * @ordered
   */
	protected EList<CapabilityRealizationPkg> ownedCapabilityRealizationPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CapabilityRealizationPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LaPackage.Literals.CAPABILITY_REALIZATION_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityRealization> getOwnedCapabilityRealizations() {

    if (ownedCapabilityRealizations == null) {
      ownedCapabilityRealizations = new EObjectContainmentEList.Resolving<CapabilityRealization>(CapabilityRealization.class, this, LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS);
    }
    return ownedCapabilityRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityRealizationPkg> getOwnedCapabilityRealizationPkgs() {

    if (ownedCapabilityRealizationPkgs == null) {
      ownedCapabilityRealizationPkgs = new EObjectContainmentEList.Resolving<CapabilityRealizationPkg>(CapabilityRealizationPkg.class, this, LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS);
    }
    return ownedCapabilityRealizationPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS:
        return ((InternalEList<?>)getOwnedCapabilityRealizations()).basicRemove(otherEnd, msgs);
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS:
        return ((InternalEList<?>)getOwnedCapabilityRealizationPkgs()).basicRemove(otherEnd, msgs);
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
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS:
        return getOwnedCapabilityRealizations();
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS:
        return getOwnedCapabilityRealizationPkgs();
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
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS:
        getOwnedCapabilityRealizations().clear();
        getOwnedCapabilityRealizations().addAll((Collection<? extends CapabilityRealization>)newValue);
        return;
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS:
        getOwnedCapabilityRealizationPkgs().clear();
        getOwnedCapabilityRealizationPkgs().addAll((Collection<? extends CapabilityRealizationPkg>)newValue);
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
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS:
        getOwnedCapabilityRealizations().clear();
        return;
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS:
        getOwnedCapabilityRealizationPkgs().clear();
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
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS:
        return ownedCapabilityRealizations != null && !ownedCapabilityRealizations.isEmpty();
      case LaPackage.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS:
        return ownedCapabilityRealizationPkgs != null && !ownedCapabilityRealizationPkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //CapabilityRealizationPkgImpl