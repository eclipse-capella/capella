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
import org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Component Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentPkgImpl#getOwnedLogicalComponents <em>Owned Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentPkgImpl#getOwnedLogicalComponentPkgs <em>Owned Logical Component Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogicalComponentPkgImpl extends ComponentPkgImpl implements LogicalComponentPkg {

	/**
   * The cached value of the '{@link #getOwnedLogicalComponents() <em>Owned Logical Components</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalComponents()
   * @generated
   * @ordered
   */
	protected EList<LogicalComponent> ownedLogicalComponents;





	/**
   * The cached value of the '{@link #getOwnedLogicalComponentPkgs() <em>Owned Logical Component Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalComponentPkgs()
   * @generated
   * @ordered
   */
	protected EList<LogicalComponentPkg> ownedLogicalComponentPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LogicalComponentPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LaPackage.Literals.LOGICAL_COMPONENT_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalComponent> getOwnedLogicalComponents() {

    if (ownedLogicalComponents == null) {
      ownedLogicalComponents = new EObjectContainmentEList.Resolving<LogicalComponent>(LogicalComponent.class, this, LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS);
    }
    return ownedLogicalComponents;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalComponentPkg> getOwnedLogicalComponentPkgs() {

    if (ownedLogicalComponentPkgs == null) {
      ownedLogicalComponentPkgs = new EObjectContainmentEList.Resolving<LogicalComponentPkg>(LogicalComponentPkg.class, this, LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS);
    }
    return ownedLogicalComponentPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS:
        return ((InternalEList<?>)getOwnedLogicalComponents()).basicRemove(otherEnd, msgs);
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS:
        return ((InternalEList<?>)getOwnedLogicalComponentPkgs()).basicRemove(otherEnd, msgs);
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
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS:
        return getOwnedLogicalComponents();
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS:
        return getOwnedLogicalComponentPkgs();
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
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS:
        getOwnedLogicalComponents().clear();
        getOwnedLogicalComponents().addAll((Collection<? extends LogicalComponent>)newValue);
        return;
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS:
        getOwnedLogicalComponentPkgs().clear();
        getOwnedLogicalComponentPkgs().addAll((Collection<? extends LogicalComponentPkg>)newValue);
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
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS:
        getOwnedLogicalComponents().clear();
        return;
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS:
        getOwnedLogicalComponentPkgs().clear();
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
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS:
        return ownedLogicalComponents != null && !ownedLogicalComponents.isEmpty();
      case LaPackage.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS:
        return ownedLogicalComponentPkgs != null && !ownedLogicalComponentPkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //LogicalComponentPkgImpl