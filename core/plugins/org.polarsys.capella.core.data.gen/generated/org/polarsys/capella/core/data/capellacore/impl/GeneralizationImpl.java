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
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generalization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizationImpl#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizationImpl#getSub <em>Sub</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeneralizationImpl extends RelationshipImpl implements Generalization {

	/**
   * The cached value of the '{@link #getSuper() <em>Super</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSuper()
   * @generated
   * @ordered
   */
	protected GeneralizableElement super_;





	/**
   * The cached value of the '{@link #getSub() <em>Sub</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSub()
   * @generated
   * @ordered
   */
	protected GeneralizableElement sub;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected GeneralizationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacorePackage.Literals.GENERALIZATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public GeneralizableElement getSuper() {

    if (super_ != null && super_.eIsProxy()) {
      InternalEObject oldSuper = (InternalEObject)super_;
      super_ = (GeneralizableElement)eResolveProxy(oldSuper);
      if (super_ != oldSuper) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacorePackage.GENERALIZATION__SUPER, oldSuper, super_));
      }
    }
    return super_;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public GeneralizableElement basicGetSuper() {

    return super_;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSuper(GeneralizableElement newSuper) {

    GeneralizableElement oldSuper = super_;
    super_ = newSuper;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.GENERALIZATION__SUPER, oldSuper, super_));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public GeneralizableElement getSub() {

    if (sub != null && sub.eIsProxy()) {
      InternalEObject oldSub = (InternalEObject)sub;
      sub = (GeneralizableElement)eResolveProxy(oldSub);
      if (sub != oldSub) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacorePackage.GENERALIZATION__SUB, oldSub, sub));
      }
    }
    return sub;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public GeneralizableElement basicGetSub() {

    return sub;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSub(GeneralizableElement newSub) {

    GeneralizableElement oldSub = sub;
    sub = newSub;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.GENERALIZATION__SUB, oldSub, sub));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CapellacorePackage.GENERALIZATION__SUPER:
        if (resolve) return getSuper();
        return basicGetSuper();
      case CapellacorePackage.GENERALIZATION__SUB:
        if (resolve) return getSub();
        return basicGetSub();
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
      case CapellacorePackage.GENERALIZATION__SUPER:
          setSuper((GeneralizableElement)newValue);
        return;
      case CapellacorePackage.GENERALIZATION__SUB:
          setSub((GeneralizableElement)newValue);
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
      case CapellacorePackage.GENERALIZATION__SUPER:
        setSuper((GeneralizableElement)null);
        return;
      case CapellacorePackage.GENERALIZATION__SUB:
        setSub((GeneralizableElement)null);
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
      case CapellacorePackage.GENERALIZATION__SUPER:
        return super_ != null;
      case CapellacorePackage.GENERALIZATION__SUB:
        return sub != null;
    }
    return super.eIsSet(featureID);
  }



} //GeneralizationImpl