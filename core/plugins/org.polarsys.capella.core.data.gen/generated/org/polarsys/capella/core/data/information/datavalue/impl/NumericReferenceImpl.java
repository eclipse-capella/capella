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
package org.polarsys.capella.core.data.information.datavalue.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Numeric Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.NumericReferenceImpl#getReferencedValue <em>Referenced Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.NumericReferenceImpl#getReferencedProperty <em>Referenced Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NumericReferenceImpl extends NumericValueImpl implements NumericReference {

	/**
   * The cached value of the '{@link #getReferencedValue() <em>Referenced Value</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferencedValue()
   * @generated
   * @ordered
   */
	protected NumericValue referencedValue;





	/**
   * The cached value of the '{@link #getReferencedProperty() <em>Referenced Property</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferencedProperty()
   * @generated
   * @ordered
   */
	protected Property referencedProperty;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected NumericReferenceImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatavaluePackage.Literals.NUMERIC_REFERENCE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getReferencedValue() {

    if (referencedValue != null && referencedValue.eIsProxy()) {
      InternalEObject oldReferencedValue = (InternalEObject)referencedValue;
      referencedValue = (NumericValue)eResolveProxy(oldReferencedValue);
      if (referencedValue != oldReferencedValue) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_VALUE, oldReferencedValue, referencedValue));
      }
    }
    return referencedValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue basicGetReferencedValue() {

    return referencedValue;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReferencedValue(NumericValue newReferencedValue) {

    NumericValue oldReferencedValue = referencedValue;
    referencedValue = newReferencedValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_VALUE, oldReferencedValue, referencedValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Property getReferencedProperty() {

    if (referencedProperty != null && referencedProperty.eIsProxy()) {
      InternalEObject oldReferencedProperty = (InternalEObject)referencedProperty;
      referencedProperty = (Property)eResolveProxy(oldReferencedProperty);
      if (referencedProperty != oldReferencedProperty) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_PROPERTY, oldReferencedProperty, referencedProperty));
      }
    }
    return referencedProperty;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Property basicGetReferencedProperty() {

    return referencedProperty;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReferencedProperty(Property newReferencedProperty) {

    Property oldReferencedProperty = referencedProperty;
    referencedProperty = newReferencedProperty;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_PROPERTY, oldReferencedProperty, referencedProperty));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_VALUE:
        if (resolve) return getReferencedValue();
        return basicGetReferencedValue();
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_PROPERTY:
        if (resolve) return getReferencedProperty();
        return basicGetReferencedProperty();
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
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_VALUE:
          setReferencedValue((NumericValue)newValue);
        return;
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_PROPERTY:
          setReferencedProperty((Property)newValue);
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
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_VALUE:
        setReferencedValue((NumericValue)null);
        return;
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_PROPERTY:
        setReferencedProperty((Property)null);
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
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_VALUE:
        return referencedValue != null;
      case DatavaluePackage.NUMERIC_REFERENCE__REFERENCED_PROPERTY:
        return referencedProperty != null;
    }
    return super.eIsSet(featureID);
  }



} //NumericReferenceImpl