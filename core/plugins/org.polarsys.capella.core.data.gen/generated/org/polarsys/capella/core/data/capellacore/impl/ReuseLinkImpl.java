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
package org.polarsys.capella.core.data.capellacore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.ReuseableStructure;
import org.polarsys.capella.core.data.capellacore.ReuserStructure;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reuse Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.ReuseLinkImpl#getReused <em>Reused</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.ReuseLinkImpl#getReuser <em>Reuser</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReuseLinkImpl extends RelationshipImpl implements ReuseLink {

	/**
   * The cached value of the '{@link #getReused() <em>Reused</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReused()
   * @generated
   * @ordered
   */
	protected ReuseableStructure reused;





	/**
   * The cached value of the '{@link #getReuser() <em>Reuser</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReuser()
   * @generated
   * @ordered
   */
	protected ReuserStructure reuser;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ReuseLinkImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacorePackage.Literals.REUSE_LINK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ReuseableStructure getReused() {

    return reused;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReused(ReuseableStructure newReused) {

    ReuseableStructure oldReused = reused;
    reused = newReused;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.REUSE_LINK__REUSED, oldReused, reused));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ReuserStructure getReuser() {

    if (reuser != null && reuser.eIsProxy()) {
      InternalEObject oldReuser = (InternalEObject)reuser;
      reuser = (ReuserStructure)eResolveProxy(oldReuser);
      if (reuser != oldReuser) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacorePackage.REUSE_LINK__REUSER, oldReuser, reuser));
      }
    }
    return reuser;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ReuserStructure basicGetReuser() {

    return reuser;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReuser(ReuserStructure newReuser) {

    ReuserStructure oldReuser = reuser;
    reuser = newReuser;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.REUSE_LINK__REUSER, oldReuser, reuser));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CapellacorePackage.REUSE_LINK__REUSED:
        return getReused();
      case CapellacorePackage.REUSE_LINK__REUSER:
        if (resolve) return getReuser();
        return basicGetReuser();
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
      case CapellacorePackage.REUSE_LINK__REUSED:
          setReused((ReuseableStructure)newValue);
        return;
      case CapellacorePackage.REUSE_LINK__REUSER:
          setReuser((ReuserStructure)newValue);
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
      case CapellacorePackage.REUSE_LINK__REUSED:
        setReused((ReuseableStructure)null);
        return;
      case CapellacorePackage.REUSE_LINK__REUSER:
        setReuser((ReuserStructure)null);
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
      case CapellacorePackage.REUSE_LINK__REUSED:
        return reused != null;
      case CapellacorePackage.REUSE_LINK__REUSER:
        return reuser != null;
    }
    return super.eIsSet(featureID);
  }



} //ReuseLinkImpl