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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.BooleanTypeImpl#getOwnedLiterals <em>Owned Literals</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.impl.BooleanTypeImpl#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BooleanTypeImpl extends DataTypeImpl implements BooleanType {

	/**
   * The cached value of the '{@link #getOwnedLiterals() <em>Owned Literals</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLiterals()
   * @generated
   * @ordered
   */
	protected EList<LiteralBooleanValue> ownedLiterals;





	/**
   * The cached value of the '{@link #getOwnedDefaultValue() <em>Owned Default Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDefaultValue()
   * @generated
   * @ordered
   */
	protected AbstractBooleanValue ownedDefaultValue;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BooleanTypeImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatatypePackage.Literals.BOOLEAN_TYPE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LiteralBooleanValue> getOwnedLiterals() {

    if (ownedLiterals == null) {
      ownedLiterals = new EObjectContainmentEList<LiteralBooleanValue>(LiteralBooleanValue.class, this, DatatypePackage.BOOLEAN_TYPE__OWNED_LITERALS);
    }
    return ownedLiterals;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBooleanValue getOwnedDefaultValue() {

    if (ownedDefaultValue != null && ownedDefaultValue.eIsProxy()) {
      InternalEObject oldOwnedDefaultValue = (InternalEObject)ownedDefaultValue;
      ownedDefaultValue = (AbstractBooleanValue)eResolveProxy(oldOwnedDefaultValue);
      if (ownedDefaultValue != oldOwnedDefaultValue) {
        InternalEObject newOwnedDefaultValue = (InternalEObject)ownedDefaultValue;
        NotificationChain msgs = oldOwnedDefaultValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE, null, null);
        if (newOwnedDefaultValue.eInternalContainer() == null) {
          msgs = newOwnedDefaultValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, ownedDefaultValue));
      }
    }
    return ownedDefaultValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBooleanValue basicGetOwnedDefaultValue() {

    return ownedDefaultValue;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedDefaultValue(AbstractBooleanValue newOwnedDefaultValue, NotificationChain msgs) {

    AbstractBooleanValue oldOwnedDefaultValue = ownedDefaultValue;
    ownedDefaultValue = newOwnedDefaultValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, newOwnedDefaultValue);
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
	public void setOwnedDefaultValue(AbstractBooleanValue newOwnedDefaultValue) {

    if (newOwnedDefaultValue != ownedDefaultValue) {
      NotificationChain msgs = null;
      if (ownedDefaultValue != null)
        msgs = ((InternalEObject)ownedDefaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE, null, msgs);
      if (newOwnedDefaultValue != null)
        msgs = ((InternalEObject)newOwnedDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE, null, msgs);
      msgs = basicSetOwnedDefaultValue(newOwnedDefaultValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE, newOwnedDefaultValue, newOwnedDefaultValue));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DatatypePackage.BOOLEAN_TYPE__OWNED_LITERALS:
        return ((InternalEList<?>)getOwnedLiterals()).basicRemove(otherEnd, msgs);
      case DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE:
        return basicSetOwnedDefaultValue(null, msgs);
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
      case DatatypePackage.BOOLEAN_TYPE__OWNED_LITERALS:
        return getOwnedLiterals();
      case DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE:
        if (resolve) return getOwnedDefaultValue();
        return basicGetOwnedDefaultValue();
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
      case DatatypePackage.BOOLEAN_TYPE__OWNED_LITERALS:
        getOwnedLiterals().clear();
        getOwnedLiterals().addAll((Collection<? extends LiteralBooleanValue>)newValue);
        return;
      case DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE:
          setOwnedDefaultValue((AbstractBooleanValue)newValue);
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
      case DatatypePackage.BOOLEAN_TYPE__OWNED_LITERALS:
        getOwnedLiterals().clear();
        return;
      case DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE:
        setOwnedDefaultValue((AbstractBooleanValue)null);
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
      case DatatypePackage.BOOLEAN_TYPE__OWNED_LITERALS:
        return ownedLiterals != null && !ownedLiterals.isEmpty();
      case DatatypePackage.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE:
        return ownedDefaultValue != null;
    }
    return super.eIsSet(featureID);
  }



} //BooleanTypeImpl