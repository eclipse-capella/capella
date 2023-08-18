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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.fa.impl.FunctionPkgImpl;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operational Activity Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityPkgImpl#getOwnedOperationalActivities <em>Owned Operational Activities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityPkgImpl#getOwnedOperationalActivityPkgs <em>Owned Operational Activity Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationalActivityPkgImpl extends FunctionPkgImpl implements OperationalActivityPkg {

	/**
   * The cached value of the '{@link #getOwnedOperationalActivities() <em>Owned Operational Activities</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedOperationalActivities()
   * @generated
   * @ordered
   */
	protected EList<OperationalActivity> ownedOperationalActivities;





	/**
   * The cached value of the '{@link #getOwnedOperationalActivityPkgs() <em>Owned Operational Activity Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedOperationalActivityPkgs()
   * @generated
   * @ordered
   */
	protected EList<OperationalActivityPkg> ownedOperationalActivityPkgs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationalActivityPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalActivity> getOwnedOperationalActivities() {

    if (ownedOperationalActivities == null) {
      ownedOperationalActivities = new EObjectContainmentEList.Resolving<OperationalActivity>(OperationalActivity.class, this, OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES);
    }
    return ownedOperationalActivities;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalActivityPkg> getOwnedOperationalActivityPkgs() {

    if (ownedOperationalActivityPkgs == null) {
      ownedOperationalActivityPkgs = new EObjectContainmentEList.Resolving<OperationalActivityPkg>(OperationalActivityPkg.class, this, OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS);
    }
    return ownedOperationalActivityPkgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES:
        return ((InternalEList<?>)getOwnedOperationalActivities()).basicRemove(otherEnd, msgs);
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        return ((InternalEList<?>)getOwnedOperationalActivityPkgs()).basicRemove(otherEnd, msgs);
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
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES:
        return getOwnedOperationalActivities();
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        return getOwnedOperationalActivityPkgs();
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
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES:
        getOwnedOperationalActivities().clear();
        getOwnedOperationalActivities().addAll((Collection<? extends OperationalActivity>)newValue);
        return;
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        getOwnedOperationalActivityPkgs().clear();
        getOwnedOperationalActivityPkgs().addAll((Collection<? extends OperationalActivityPkg>)newValue);
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
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES:
        getOwnedOperationalActivities().clear();
        return;
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        getOwnedOperationalActivityPkgs().clear();
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
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES:
        return ownedOperationalActivities != null && !ownedOperationalActivities.isEmpty();
      case OaPackage.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS:
        return ownedOperationalActivityPkgs != null && !ownedOperationalActivityPkgs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //OperationalActivityPkgImpl