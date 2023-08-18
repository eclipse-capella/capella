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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Numeric Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NumericTypeImpl extends DataTypeImpl implements NumericType {

	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final NumericTypeKind KIND_EDEFAULT = NumericTypeKind.INTEGER;

	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected NumericTypeKind kind = KIND_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedDefaultValue() <em>Owned Default Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDefaultValue()
   * @generated
   * @ordered
   */
	protected NumericValue ownedDefaultValue;





	/**
   * The cached value of the '{@link #getOwnedNullValue() <em>Owned Null Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedNullValue()
   * @generated
   * @ordered
   */
	protected NumericValue ownedNullValue;





	/**
   * The cached value of the '{@link #getOwnedMinValue() <em>Owned Min Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMinValue()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMinValue;





	/**
   * The cached value of the '{@link #getOwnedMaxValue() <em>Owned Max Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMaxValue()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMaxValue;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected NumericTypeImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatatypePackage.Literals.NUMERIC_TYPE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericTypeKind getKind() {

    return kind;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(NumericTypeKind newKind) {

    NumericTypeKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__KIND, oldKind, kind));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedDefaultValue() {

    if (ownedDefaultValue != null && ownedDefaultValue.eIsProxy()) {
      InternalEObject oldOwnedDefaultValue = (InternalEObject)ownedDefaultValue;
      ownedDefaultValue = (NumericValue)eResolveProxy(oldOwnedDefaultValue);
      if (ownedDefaultValue != oldOwnedDefaultValue) {
        InternalEObject newOwnedDefaultValue = (InternalEObject)ownedDefaultValue;
        NotificationChain msgs = oldOwnedDefaultValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE, null, null);
        if (newOwnedDefaultValue.eInternalContainer() == null) {
          msgs = newOwnedDefaultValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, ownedDefaultValue));
      }
    }
    return ownedDefaultValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue basicGetOwnedDefaultValue() {

    return ownedDefaultValue;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedDefaultValue(NumericValue newOwnedDefaultValue, NotificationChain msgs) {

    NumericValue oldOwnedDefaultValue = ownedDefaultValue;
    ownedDefaultValue = newOwnedDefaultValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, newOwnedDefaultValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedDefaultValue(NumericValue newOwnedDefaultValue) {

    if (newOwnedDefaultValue != ownedDefaultValue) {
      NotificationChain msgs = null;
      if (ownedDefaultValue != null)
        msgs = ((InternalEObject)ownedDefaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE, null, msgs);
      if (newOwnedDefaultValue != null)
        msgs = ((InternalEObject)newOwnedDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE, null, msgs);
      msgs = basicSetOwnedDefaultValue(newOwnedDefaultValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE, newOwnedDefaultValue, newOwnedDefaultValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedNullValue() {

    if (ownedNullValue != null && ownedNullValue.eIsProxy()) {
      InternalEObject oldOwnedNullValue = (InternalEObject)ownedNullValue;
      ownedNullValue = (NumericValue)eResolveProxy(oldOwnedNullValue);
      if (ownedNullValue != oldOwnedNullValue) {
        InternalEObject newOwnedNullValue = (InternalEObject)ownedNullValue;
        NotificationChain msgs = oldOwnedNullValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE, null, null);
        if (newOwnedNullValue.eInternalContainer() == null) {
          msgs = newOwnedNullValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE, oldOwnedNullValue, ownedNullValue));
      }
    }
    return ownedNullValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue basicGetOwnedNullValue() {

    return ownedNullValue;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedNullValue(NumericValue newOwnedNullValue, NotificationChain msgs) {

    NumericValue oldOwnedNullValue = ownedNullValue;
    ownedNullValue = newOwnedNullValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE, oldOwnedNullValue, newOwnedNullValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedNullValue(NumericValue newOwnedNullValue) {

    if (newOwnedNullValue != ownedNullValue) {
      NotificationChain msgs = null;
      if (ownedNullValue != null)
        msgs = ((InternalEObject)ownedNullValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE, null, msgs);
      if (newOwnedNullValue != null)
        msgs = ((InternalEObject)newOwnedNullValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE, null, msgs);
      msgs = basicSetOwnedNullValue(newOwnedNullValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE, newOwnedNullValue, newOwnedNullValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMinValue() {

    if (ownedMinValue != null && ownedMinValue.eIsProxy()) {
      InternalEObject oldOwnedMinValue = (InternalEObject)ownedMinValue;
      ownedMinValue = (NumericValue)eResolveProxy(oldOwnedMinValue);
      if (ownedMinValue != oldOwnedMinValue) {
        InternalEObject newOwnedMinValue = (InternalEObject)ownedMinValue;
        NotificationChain msgs = oldOwnedMinValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE, null, null);
        if (newOwnedMinValue.eInternalContainer() == null) {
          msgs = newOwnedMinValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE, oldOwnedMinValue, ownedMinValue));
      }
    }
    return ownedMinValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue basicGetOwnedMinValue() {

    return ownedMinValue;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMinValue(NumericValue newOwnedMinValue, NotificationChain msgs) {

    NumericValue oldOwnedMinValue = ownedMinValue;
    ownedMinValue = newOwnedMinValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE, oldOwnedMinValue, newOwnedMinValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedMinValue(NumericValue newOwnedMinValue) {

    if (newOwnedMinValue != ownedMinValue) {
      NotificationChain msgs = null;
      if (ownedMinValue != null)
        msgs = ((InternalEObject)ownedMinValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE, null, msgs);
      if (newOwnedMinValue != null)
        msgs = ((InternalEObject)newOwnedMinValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE, null, msgs);
      msgs = basicSetOwnedMinValue(newOwnedMinValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE, newOwnedMinValue, newOwnedMinValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMaxValue() {

    if (ownedMaxValue != null && ownedMaxValue.eIsProxy()) {
      InternalEObject oldOwnedMaxValue = (InternalEObject)ownedMaxValue;
      ownedMaxValue = (NumericValue)eResolveProxy(oldOwnedMaxValue);
      if (ownedMaxValue != oldOwnedMaxValue) {
        InternalEObject newOwnedMaxValue = (InternalEObject)ownedMaxValue;
        NotificationChain msgs = oldOwnedMaxValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE, null, null);
        if (newOwnedMaxValue.eInternalContainer() == null) {
          msgs = newOwnedMaxValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE, oldOwnedMaxValue, ownedMaxValue));
      }
    }
    return ownedMaxValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue basicGetOwnedMaxValue() {

    return ownedMaxValue;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMaxValue(NumericValue newOwnedMaxValue, NotificationChain msgs) {

    NumericValue oldOwnedMaxValue = ownedMaxValue;
    ownedMaxValue = newOwnedMaxValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE, oldOwnedMaxValue, newOwnedMaxValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedMaxValue(NumericValue newOwnedMaxValue) {

    if (newOwnedMaxValue != ownedMaxValue) {
      NotificationChain msgs = null;
      if (ownedMaxValue != null)
        msgs = ((InternalEObject)ownedMaxValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE, null, msgs);
      if (newOwnedMaxValue != null)
        msgs = ((InternalEObject)newOwnedMaxValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE, null, msgs);
      msgs = basicSetOwnedMaxValue(newOwnedMaxValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE, newOwnedMaxValue, newOwnedMaxValue));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE:
        return basicSetOwnedDefaultValue(null, msgs);
      case DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE:
        return basicSetOwnedNullValue(null, msgs);
      case DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE:
        return basicSetOwnedMinValue(null, msgs);
      case DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE:
        return basicSetOwnedMaxValue(null, msgs);
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
      case DatatypePackage.NUMERIC_TYPE__KIND:
        return getKind();
      case DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE:
        if (resolve) return getOwnedDefaultValue();
        return basicGetOwnedDefaultValue();
      case DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE:
        if (resolve) return getOwnedNullValue();
        return basicGetOwnedNullValue();
      case DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE:
        if (resolve) return getOwnedMinValue();
        return basicGetOwnedMinValue();
      case DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE:
        if (resolve) return getOwnedMaxValue();
        return basicGetOwnedMaxValue();
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
      case DatatypePackage.NUMERIC_TYPE__KIND:
          setKind((NumericTypeKind)newValue);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE:
          setOwnedDefaultValue((NumericValue)newValue);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE:
          setOwnedNullValue((NumericValue)newValue);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE:
          setOwnedMinValue((NumericValue)newValue);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE:
          setOwnedMaxValue((NumericValue)newValue);
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
      case DatatypePackage.NUMERIC_TYPE__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE:
        setOwnedDefaultValue((NumericValue)null);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE:
        setOwnedNullValue((NumericValue)null);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE:
        setOwnedMinValue((NumericValue)null);
        return;
      case DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE:
        setOwnedMaxValue((NumericValue)null);
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
      case DatatypePackage.NUMERIC_TYPE__KIND:
        return kind != KIND_EDEFAULT;
      case DatatypePackage.NUMERIC_TYPE__OWNED_DEFAULT_VALUE:
        return ownedDefaultValue != null;
      case DatatypePackage.NUMERIC_TYPE__OWNED_NULL_VALUE:
        return ownedNullValue != null;
      case DatatypePackage.NUMERIC_TYPE__OWNED_MIN_VALUE:
        return ownedMinValue != null;
      case DatatypePackage.NUMERIC_TYPE__OWNED_MAX_VALUE:
        return ownedMaxValue != null;
    }
    return super.eIsSet(featureID);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(')');
    return result.toString();
  }


} //NumericTypeImpl