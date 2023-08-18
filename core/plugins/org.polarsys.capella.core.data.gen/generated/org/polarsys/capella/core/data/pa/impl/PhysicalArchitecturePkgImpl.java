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
package org.polarsys.capella.core.data.pa.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.cs.impl.BlockArchitecturePkgImpl;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Architecture Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitecturePkgImpl#getOwnedPhysicalArchitecturePkgs <em>Owned Physical Architecture Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitecturePkgImpl#getOwnedPhysicalArchitectures <em>Owned Physical Architectures</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalArchitecturePkgImpl extends BlockArchitecturePkgImpl implements PhysicalArchitecturePkg {

	/**
   * The cached value of the '{@link #getOwnedPhysicalArchitecturePkgs() <em>Owned Physical Architecture Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalArchitecturePkgs()
   * @generated
   * @ordered
   */
	protected EList<PhysicalArchitecturePkg> ownedPhysicalArchitecturePkgs;





	/**
   * The cached value of the '{@link #getOwnedPhysicalArchitectures() <em>Owned Physical Architectures</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalArchitectures()
   * @generated
   * @ordered
   */
	protected EList<PhysicalArchitecture> ownedPhysicalArchitectures;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalArchitecturePkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return PaPackage.Literals.PHYSICAL_ARCHITECTURE_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalArchitecturePkg> getOwnedPhysicalArchitecturePkgs() {

    if (ownedPhysicalArchitecturePkgs == null) {
      ownedPhysicalArchitecturePkgs = new EObjectContainmentEList.Resolving<PhysicalArchitecturePkg>(PhysicalArchitecturePkg.class, this, PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS);
    }
    return ownedPhysicalArchitecturePkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalArchitecture> getOwnedPhysicalArchitectures() {

    if (ownedPhysicalArchitectures == null) {
      ownedPhysicalArchitectures = new EObjectContainmentEList.Resolving<PhysicalArchitecture>(PhysicalArchitecture.class, this, PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES);
    }
    return ownedPhysicalArchitectures;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS:
        return ((InternalEList<?>)getOwnedPhysicalArchitecturePkgs()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES:
        return ((InternalEList<?>)getOwnedPhysicalArchitectures()).basicRemove(otherEnd, msgs);
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
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS:
        return getOwnedPhysicalArchitecturePkgs();
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES:
        return getOwnedPhysicalArchitectures();
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
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS:
        getOwnedPhysicalArchitecturePkgs().clear();
        getOwnedPhysicalArchitecturePkgs().addAll((Collection<? extends PhysicalArchitecturePkg>)newValue);
        return;
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES:
        getOwnedPhysicalArchitectures().clear();
        getOwnedPhysicalArchitectures().addAll((Collection<? extends PhysicalArchitecture>)newValue);
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
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS:
        getOwnedPhysicalArchitecturePkgs().clear();
        return;
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES:
        getOwnedPhysicalArchitectures().clear();
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
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS:
        return ownedPhysicalArchitecturePkgs != null && !ownedPhysicalArchitecturePkgs.isEmpty();
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES:
        return ownedPhysicalArchitectures != null && !ownedPhysicalArchitectures.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //PhysicalArchitecturePkgImpl