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
import org.polarsys.capella.core.data.cs.impl.BlockArchitecturePkgImpl;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Architecture Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitecturePkgImpl#getOwnedLogicalArchitectures <em>Owned Logical Architectures</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogicalArchitecturePkgImpl extends BlockArchitecturePkgImpl implements LogicalArchitecturePkg {

	/**
   * The cached value of the '{@link #getOwnedLogicalArchitectures() <em>Owned Logical Architectures</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalArchitectures()
   * @generated
   * @ordered
   */
	protected EList<LogicalArchitecture> ownedLogicalArchitectures;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LogicalArchitecturePkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LaPackage.Literals.LOGICAL_ARCHITECTURE_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalArchitecture> getOwnedLogicalArchitectures() {

    if (ownedLogicalArchitectures == null) {
      ownedLogicalArchitectures = new EObjectContainmentEList.Resolving<LogicalArchitecture>(LogicalArchitecture.class, this, LaPackage.LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES);
    }
    return ownedLogicalArchitectures;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LaPackage.LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES:
        return ((InternalEList<?>)getOwnedLogicalArchitectures()).basicRemove(otherEnd, msgs);
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
      case LaPackage.LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES:
        return getOwnedLogicalArchitectures();
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
      case LaPackage.LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES:
        getOwnedLogicalArchitectures().clear();
        getOwnedLogicalArchitectures().addAll((Collection<? extends LogicalArchitecture>)newValue);
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
      case LaPackage.LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES:
        getOwnedLogicalArchitectures().clear();
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
      case LaPackage.LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES:
        return ownedLogicalArchitectures != null && !ownedLogicalArchitectures.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //LogicalArchitecturePkgImpl