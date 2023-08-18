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
import org.polarsys.capella.core.data.fa.impl.FunctionPkgImpl;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Function Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionPkgImpl#getOwnedLogicalFunctions <em>Owned Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionPkgImpl#getOwnedLogicalFunctionPkgs <em>Owned Logical Function Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogicalFunctionPkgImpl extends FunctionPkgImpl implements LogicalFunctionPkg {

	/**
   * The cached value of the '{@link #getOwnedLogicalFunctions() <em>Owned Logical Functions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalFunctions()
   * @generated
   * @ordered
   */
	protected EList<LogicalFunction> ownedLogicalFunctions;





	/**
   * The cached value of the '{@link #getOwnedLogicalFunctionPkgs() <em>Owned Logical Function Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalFunctionPkgs()
   * @generated
   * @ordered
   */
	protected EList<LogicalFunctionPkg> ownedLogicalFunctionPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LogicalFunctionPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LaPackage.Literals.LOGICAL_FUNCTION_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalFunction> getOwnedLogicalFunctions() {

    if (ownedLogicalFunctions == null) {
      ownedLogicalFunctions = new EObjectContainmentEList.Resolving<LogicalFunction>(LogicalFunction.class, this, LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS);
    }
    return ownedLogicalFunctions;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalFunctionPkg> getOwnedLogicalFunctionPkgs() {

    if (ownedLogicalFunctionPkgs == null) {
      ownedLogicalFunctionPkgs = new EObjectContainmentEList.Resolving<LogicalFunctionPkg>(LogicalFunctionPkg.class, this, LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS);
    }
    return ownedLogicalFunctionPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS:
        return ((InternalEList<?>)getOwnedLogicalFunctions()).basicRemove(otherEnd, msgs);
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS:
        return ((InternalEList<?>)getOwnedLogicalFunctionPkgs()).basicRemove(otherEnd, msgs);
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
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS:
        return getOwnedLogicalFunctions();
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS:
        return getOwnedLogicalFunctionPkgs();
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
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS:
        getOwnedLogicalFunctions().clear();
        getOwnedLogicalFunctions().addAll((Collection<? extends LogicalFunction>)newValue);
        return;
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS:
        getOwnedLogicalFunctionPkgs().clear();
        getOwnedLogicalFunctionPkgs().addAll((Collection<? extends LogicalFunctionPkg>)newValue);
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
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS:
        getOwnedLogicalFunctions().clear();
        return;
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS:
        getOwnedLogicalFunctionPkgs().clear();
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
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS:
        return ownedLogicalFunctions != null && !ownedLogicalFunctions.isEmpty();
      case LaPackage.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS:
        return ownedLogicalFunctionPkgs != null && !ownedLogicalFunctionPkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //LogicalFunctionPkgImpl