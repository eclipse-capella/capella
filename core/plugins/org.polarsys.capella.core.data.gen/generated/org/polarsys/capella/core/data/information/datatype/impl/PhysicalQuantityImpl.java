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
package org.polarsys.capella.core.data.information.datatype.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Quantity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.PhysicalQuantityImpl#getUnit <em>Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalQuantityImpl extends NumericTypeImpl implements PhysicalQuantity {

	/**
   * The cached value of the '{@link #getUnit() <em>Unit</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getUnit()
   * @generated
   * @ordered
   */
	protected Unit unit;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalQuantityImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatatypePackage.Literals.PHYSICAL_QUANTITY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Unit getUnit() {

    if (unit != null && unit.eIsProxy()) {
      InternalEObject oldUnit = (InternalEObject)unit;
      unit = (Unit)eResolveProxy(oldUnit);
      if (unit != oldUnit) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatatypePackage.PHYSICAL_QUANTITY__UNIT, oldUnit, unit));
      }
    }
    return unit;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Unit basicGetUnit() {

    return unit;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setUnit(Unit newUnit) {

    Unit oldUnit = unit;
    unit = newUnit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatatypePackage.PHYSICAL_QUANTITY__UNIT, oldUnit, unit));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case DatatypePackage.PHYSICAL_QUANTITY__UNIT:
        if (resolve) return getUnit();
        return basicGetUnit();
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
      case DatatypePackage.PHYSICAL_QUANTITY__UNIT:
          setUnit((Unit)newValue);
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
      case DatatypePackage.PHYSICAL_QUANTITY__UNIT:
        setUnit((Unit)null);
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
      case DatatypePackage.PHYSICAL_QUANTITY__UNIT:
        return unit != null;
    }
    return super.eIsSet(featureID);
  }



} //PhysicalQuantityImpl