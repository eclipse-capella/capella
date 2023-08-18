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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.BinaryExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.BinaryExpressionImpl#getOwnedLeftOperand <em>Owned Left Operand</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.BinaryExpressionImpl#getOwnedRightOperand <em>Owned Right Operand</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BinaryExpressionImpl extends AbstractExpressionValueImpl implements BinaryExpression {

	/**
   * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
	protected static final BinaryOperator OPERATOR_EDEFAULT = BinaryOperator.UNSET;

	/**
   * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
	protected BinaryOperator operator = OPERATOR_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedLeftOperand() <em>Owned Left Operand</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLeftOperand()
   * @generated
   * @ordered
   */
	protected DataValue ownedLeftOperand;





	/**
   * The cached value of the '{@link #getOwnedRightOperand() <em>Owned Right Operand</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedRightOperand()
   * @generated
   * @ordered
   */
	protected DataValue ownedRightOperand;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BinaryExpressionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatavaluePackage.Literals.BINARY_EXPRESSION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public BinaryOperator getOperator() {

    return operator;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOperator(BinaryOperator newOperator) {

    BinaryOperator oldOperator = operator;
    operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.BINARY_EXPRESSION__OPERATOR, oldOperator, operator));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedLeftOperand() {

    if (ownedLeftOperand != null && ownedLeftOperand.eIsProxy()) {
      InternalEObject oldOwnedLeftOperand = (InternalEObject)ownedLeftOperand;
      ownedLeftOperand = (DataValue)eResolveProxy(oldOwnedLeftOperand);
      if (ownedLeftOperand != oldOwnedLeftOperand) {
        InternalEObject newOwnedLeftOperand = (InternalEObject)ownedLeftOperand;
        NotificationChain msgs = oldOwnedLeftOperand.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, null, null);
        if (newOwnedLeftOperand.eInternalContainer() == null) {
          msgs = newOwnedLeftOperand.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, oldOwnedLeftOperand, ownedLeftOperand));
      }
    }
    return ownedLeftOperand;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue basicGetOwnedLeftOperand() {

    return ownedLeftOperand;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedLeftOperand(DataValue newOwnedLeftOperand, NotificationChain msgs) {

    DataValue oldOwnedLeftOperand = ownedLeftOperand;
    ownedLeftOperand = newOwnedLeftOperand;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, oldOwnedLeftOperand, newOwnedLeftOperand);
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
	public void setOwnedLeftOperand(DataValue newOwnedLeftOperand) {

    if (newOwnedLeftOperand != ownedLeftOperand) {
      NotificationChain msgs = null;
      if (ownedLeftOperand != null)
        msgs = ((InternalEObject)ownedLeftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, null, msgs);
      if (newOwnedLeftOperand != null)
        msgs = ((InternalEObject)newOwnedLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, null, msgs);
      msgs = basicSetOwnedLeftOperand(newOwnedLeftOperand, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND, newOwnedLeftOperand, newOwnedLeftOperand));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedRightOperand() {

    if (ownedRightOperand != null && ownedRightOperand.eIsProxy()) {
      InternalEObject oldOwnedRightOperand = (InternalEObject)ownedRightOperand;
      ownedRightOperand = (DataValue)eResolveProxy(oldOwnedRightOperand);
      if (ownedRightOperand != oldOwnedRightOperand) {
        InternalEObject newOwnedRightOperand = (InternalEObject)ownedRightOperand;
        NotificationChain msgs = oldOwnedRightOperand.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, null, null);
        if (newOwnedRightOperand.eInternalContainer() == null) {
          msgs = newOwnedRightOperand.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, oldOwnedRightOperand, ownedRightOperand));
      }
    }
    return ownedRightOperand;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue basicGetOwnedRightOperand() {

    return ownedRightOperand;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedRightOperand(DataValue newOwnedRightOperand, NotificationChain msgs) {

    DataValue oldOwnedRightOperand = ownedRightOperand;
    ownedRightOperand = newOwnedRightOperand;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, oldOwnedRightOperand, newOwnedRightOperand);
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
	public void setOwnedRightOperand(DataValue newOwnedRightOperand) {

    if (newOwnedRightOperand != ownedRightOperand) {
      NotificationChain msgs = null;
      if (ownedRightOperand != null)
        msgs = ((InternalEObject)ownedRightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, null, msgs);
      if (newOwnedRightOperand != null)
        msgs = ((InternalEObject)newOwnedRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, null, msgs);
      msgs = basicSetOwnedRightOperand(newOwnedRightOperand, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND, newOwnedRightOperand, newOwnedRightOperand));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND:
        return basicSetOwnedLeftOperand(null, msgs);
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND:
        return basicSetOwnedRightOperand(null, msgs);
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
      case DatavaluePackage.BINARY_EXPRESSION__OPERATOR:
        return getOperator();
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND:
        if (resolve) return getOwnedLeftOperand();
        return basicGetOwnedLeftOperand();
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND:
        if (resolve) return getOwnedRightOperand();
        return basicGetOwnedRightOperand();
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
      case DatavaluePackage.BINARY_EXPRESSION__OPERATOR:
          setOperator((BinaryOperator)newValue);
        return;
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND:
          setOwnedLeftOperand((DataValue)newValue);
        return;
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND:
          setOwnedRightOperand((DataValue)newValue);
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
      case DatavaluePackage.BINARY_EXPRESSION__OPERATOR:
        setOperator(OPERATOR_EDEFAULT);
        return;
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND:
        setOwnedLeftOperand((DataValue)null);
        return;
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND:
        setOwnedRightOperand((DataValue)null);
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
      case DatavaluePackage.BINARY_EXPRESSION__OPERATOR:
        return operator != OPERATOR_EDEFAULT;
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_LEFT_OPERAND:
        return ownedLeftOperand != null;
      case DatavaluePackage.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND:
        return ownedRightOperand != null;
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
    result.append(" (operator: "); //$NON-NLS-1$
    result.append(operator);
    result.append(')');
    return result.toString();
  }


} //BinaryExpressionImpl