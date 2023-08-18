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
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.UnaryOperator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unary Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.UnaryExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.UnaryExpressionImpl#getOwnedOperand <em>Owned Operand</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnaryExpressionImpl extends AbstractExpressionValueImpl implements UnaryExpression {

	/**
   * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
	protected static final UnaryOperator OPERATOR_EDEFAULT = UnaryOperator.UNSET;

	/**
   * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
	protected UnaryOperator operator = OPERATOR_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedOperand() <em>Owned Operand</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedOperand()
   * @generated
   * @ordered
   */
	protected DataValue ownedOperand;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected UnaryExpressionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatavaluePackage.Literals.UNARY_EXPRESSION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public UnaryOperator getOperator() {

    return operator;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOperator(UnaryOperator newOperator) {

    UnaryOperator oldOperator = operator;
    operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.UNARY_EXPRESSION__OPERATOR, oldOperator, operator));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedOperand() {

    if (ownedOperand != null && ownedOperand.eIsProxy()) {
      InternalEObject oldOwnedOperand = (InternalEObject)ownedOperand;
      ownedOperand = (DataValue)eResolveProxy(oldOwnedOperand);
      if (ownedOperand != oldOwnedOperand) {
        InternalEObject newOwnedOperand = (InternalEObject)ownedOperand;
        NotificationChain msgs = oldOwnedOperand.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND, null, null);
        if (newOwnedOperand.eInternalContainer() == null) {
          msgs = newOwnedOperand.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND, oldOwnedOperand, ownedOperand));
      }
    }
    return ownedOperand;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue basicGetOwnedOperand() {

    return ownedOperand;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedOperand(DataValue newOwnedOperand, NotificationChain msgs) {

    DataValue oldOwnedOperand = ownedOperand;
    ownedOperand = newOwnedOperand;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND, oldOwnedOperand, newOwnedOperand);
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
	public void setOwnedOperand(DataValue newOwnedOperand) {

    if (newOwnedOperand != ownedOperand) {
      NotificationChain msgs = null;
      if (ownedOperand != null)
        msgs = ((InternalEObject)ownedOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND, null, msgs);
      if (newOwnedOperand != null)
        msgs = ((InternalEObject)newOwnedOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND, null, msgs);
      msgs = basicSetOwnedOperand(newOwnedOperand, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND, newOwnedOperand, newOwnedOperand));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND:
        return basicSetOwnedOperand(null, msgs);
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
      case DatavaluePackage.UNARY_EXPRESSION__OPERATOR:
        return getOperator();
      case DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND:
        if (resolve) return getOwnedOperand();
        return basicGetOwnedOperand();
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
      case DatavaluePackage.UNARY_EXPRESSION__OPERATOR:
          setOperator((UnaryOperator)newValue);
        return;
      case DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND:
          setOwnedOperand((DataValue)newValue);
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
      case DatavaluePackage.UNARY_EXPRESSION__OPERATOR:
        setOperator(OPERATOR_EDEFAULT);
        return;
      case DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND:
        setOwnedOperand((DataValue)null);
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
      case DatavaluePackage.UNARY_EXPRESSION__OPERATOR:
        return operator != OPERATOR_EDEFAULT;
      case DatavaluePackage.UNARY_EXPRESSION__OWNED_OPERAND:
        return ownedOperand != null;
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


} //UnaryExpressionImpl