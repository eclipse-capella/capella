/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.data.activity.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.InputPin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Pin</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.InputPinImpl#isIsControl <em>Is Control</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.InputPinImpl#getInputEvaluationAction <em>Input Evaluation Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class InputPinImpl extends ObjectNodeImpl implements InputPin {

	/**
	 * The default value of the '{@link #isIsControl() <em>Is Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsControl()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CONTROL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsControl() <em>Is Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsControl()
	 * @generated
	 * @ordered
	 */
	protected boolean isControl = IS_CONTROL_EDEFAULT;





	/**
	 * The cached value of the '{@link #getInputEvaluationAction() <em>Input Evaluation Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputEvaluationAction()
	 * @generated
	 * @ordered
	 */
	protected AbstractAction inputEvaluationAction;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputPinImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActivityPackage.Literals.INPUT_PIN;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public boolean isIsControl() {

		return isControl;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setIsControl(boolean newIsControl) {

		boolean oldIsControl = isControl;
		isControl = newIsControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.INPUT_PIN__IS_CONTROL, oldIsControl, isControl));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractAction getInputEvaluationAction() {

		if (inputEvaluationAction != null && inputEvaluationAction.eIsProxy()) {
			InternalEObject oldInputEvaluationAction = (InternalEObject)inputEvaluationAction;
			inputEvaluationAction = (AbstractAction)eResolveProxy(oldInputEvaluationAction);
			if (inputEvaluationAction != oldInputEvaluationAction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.INPUT_PIN__INPUT_EVALUATION_ACTION, oldInputEvaluationAction, inputEvaluationAction));
			}
		}
		return inputEvaluationAction;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractAction basicGetInputEvaluationAction() {

		return inputEvaluationAction;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setInputEvaluationAction(AbstractAction newInputEvaluationAction) {

		AbstractAction oldInputEvaluationAction = inputEvaluationAction;
		inputEvaluationAction = newInputEvaluationAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.INPUT_PIN__INPUT_EVALUATION_ACTION, oldInputEvaluationAction, inputEvaluationAction));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ActivityPackage.INPUT_PIN__IS_CONTROL:
				return isIsControl();
			case ActivityPackage.INPUT_PIN__INPUT_EVALUATION_ACTION:
				if (resolve) return getInputEvaluationAction();
				return basicGetInputEvaluationAction();
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
			case ActivityPackage.INPUT_PIN__IS_CONTROL:
				// begin-extension-code
				if (newValue == null || newValue instanceof Boolean) {
				// end-extension-code
					setIsControl((Boolean)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case ActivityPackage.INPUT_PIN__INPUT_EVALUATION_ACTION:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractAction) {
				// end-extension-code
					setInputEvaluationAction((AbstractAction)newValue);
				// begin-extension-code
				}
				// end-extension-code
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
			case ActivityPackage.INPUT_PIN__IS_CONTROL:
				setIsControl(IS_CONTROL_EDEFAULT);
				return;
			case ActivityPackage.INPUT_PIN__INPUT_EVALUATION_ACTION:
				setInputEvaluationAction((AbstractAction)null);
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
			case ActivityPackage.INPUT_PIN__IS_CONTROL:
				return isControl != IS_CONTROL_EDEFAULT;
			case ActivityPackage.INPUT_PIN__INPUT_EVALUATION_ACTION:
				return inputEvaluationAction != null;
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isControl: "); //$NON-NLS-1$
		result.append(isControl);
		result.append(')');
		return result.toString();
	}


} //InputPinImpl