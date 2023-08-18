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
package org.polarsys.capella.core.data.interaction.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Receipt Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.EventReceiptOperationImpl#getOperation <em>Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EventReceiptOperationImpl extends EventImpl implements EventReceiptOperation {

	/**
   * The cached value of the '{@link #getOperation() <em>Operation</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperation()
   * @generated
   * @ordered
   */
	protected AbstractEventOperation operation;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected EventReceiptOperationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.EVENT_RECEIPT_OPERATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractEventOperation getOperation() {

    if (operation != null && operation.eIsProxy()) {
      InternalEObject oldOperation = (InternalEObject)operation;
      operation = (AbstractEventOperation)eResolveProxy(oldOperation);
      if (operation != oldOperation) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.EVENT_RECEIPT_OPERATION__OPERATION, oldOperation, operation));
      }
    }
    return operation;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractEventOperation basicGetOperation() {

    return operation;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOperation(AbstractEventOperation newOperation) {

    AbstractEventOperation oldOperation = operation;
    operation = newOperation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.EVENT_RECEIPT_OPERATION__OPERATION, oldOperation, operation));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InteractionPackage.EVENT_RECEIPT_OPERATION__OPERATION:
        if (resolve) return getOperation();
        return basicGetOperation();
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
      case InteractionPackage.EVENT_RECEIPT_OPERATION__OPERATION:
          setOperation((AbstractEventOperation)newValue);
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
      case InteractionPackage.EVENT_RECEIPT_OPERATION__OPERATION:
        setOperation((AbstractEventOperation)null);
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
      case InteractionPackage.EVENT_RECEIPT_OPERATION__OPERATION:
        return operation != null;
    }
    return super.eIsSet(featureID);
  }



} //EventReceiptOperationImpl