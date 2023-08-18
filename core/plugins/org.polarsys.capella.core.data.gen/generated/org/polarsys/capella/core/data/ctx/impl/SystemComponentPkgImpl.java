/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.ctx.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Component Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentPkgImpl#getOwnedSystemComponents <em>Owned System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentPkgImpl#getOwnedSystemComponentPkgs <em>Owned System Component Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemComponentPkgImpl extends ComponentPkgImpl implements SystemComponentPkg {

	/**
   * The cached value of the '{@link #getOwnedSystemComponents() <em>Owned System Components</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSystemComponents()
   * @generated
   * @ordered
   */
	protected EList<SystemComponent> ownedSystemComponents;





	/**
   * The cached value of the '{@link #getOwnedSystemComponentPkgs() <em>Owned System Component Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSystemComponentPkgs()
   * @generated
   * @ordered
   */
	protected EList<SystemComponentPkg> ownedSystemComponentPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SystemComponentPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CtxPackage.Literals.SYSTEM_COMPONENT_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemComponent> getOwnedSystemComponents() {

    if (ownedSystemComponents == null) {
      ownedSystemComponents = new EObjectContainmentEList.Resolving<SystemComponent>(SystemComponent.class, this, CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS);
    }
    return ownedSystemComponents;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemComponentPkg> getOwnedSystemComponentPkgs() {

    if (ownedSystemComponentPkgs == null) {
      ownedSystemComponentPkgs = new EObjectContainmentEList.Resolving<SystemComponentPkg>(SystemComponentPkg.class, this, CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS);
    }
    return ownedSystemComponentPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS:
        return ((InternalEList<?>)getOwnedSystemComponents()).basicRemove(otherEnd, msgs);
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS:
        return ((InternalEList<?>)getOwnedSystemComponentPkgs()).basicRemove(otherEnd, msgs);
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
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS:
        return getOwnedSystemComponents();
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS:
        return getOwnedSystemComponentPkgs();
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
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS:
        getOwnedSystemComponents().clear();
        getOwnedSystemComponents().addAll((Collection<? extends SystemComponent>)newValue);
        return;
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS:
        getOwnedSystemComponentPkgs().clear();
        getOwnedSystemComponentPkgs().addAll((Collection<? extends SystemComponentPkg>)newValue);
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
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS:
        getOwnedSystemComponents().clear();
        return;
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS:
        getOwnedSystemComponentPkgs().clear();
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
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS:
        return ownedSystemComponents != null && !ownedSystemComponents.isEmpty();
      case CtxPackage.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS:
        return ownedSystemComponentPkgs != null && !ownedSystemComponentPkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //SystemComponentPkgImpl