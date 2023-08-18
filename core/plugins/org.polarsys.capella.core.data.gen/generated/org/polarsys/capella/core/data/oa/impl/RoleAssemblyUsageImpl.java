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
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAssemblyUsage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Assembly Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.RoleAssemblyUsageImpl#getChild <em>Child</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleAssemblyUsageImpl extends NamedElementImpl implements RoleAssemblyUsage {

	/**
   * The cached value of the '{@link #getChild() <em>Child</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getChild()
   * @generated
   * @ordered
   */
	protected Role child;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RoleAssemblyUsageImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.ROLE_ASSEMBLY_USAGE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Role getChild() {

    if (child != null && child.eIsProxy()) {
      InternalEObject oldChild = (InternalEObject)child;
      child = (Role)eResolveProxy(oldChild);
      if (child != oldChild) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.ROLE_ASSEMBLY_USAGE__CHILD, oldChild, child));
      }
    }
    return child;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Role basicGetChild() {

    return child;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setChild(Role newChild) {

    Role oldChild = child;
    child = newChild;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.ROLE_ASSEMBLY_USAGE__CHILD, oldChild, child));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.ROLE_ASSEMBLY_USAGE__CHILD:
        if (resolve) return getChild();
        return basicGetChild();
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
      case OaPackage.ROLE_ASSEMBLY_USAGE__CHILD:
          setChild((Role)newValue);
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
      case OaPackage.ROLE_ASSEMBLY_USAGE__CHILD:
        setChild((Role)null);
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
      case OaPackage.ROLE_ASSEMBLY_USAGE__CHILD:
        return child != null;
    }
    return super.eIsSet(featureID);
  }



} //RoleAssemblyUsageImpl