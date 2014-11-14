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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ValuePin;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Pin</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ValuePinImpl#isIsControl <em>Is Control</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ValuePinImpl#getInputEvaluationAction <em>Input Evaluation Action</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ValuePinImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ValuePinImpl extends ObjectNodeImpl implements ValuePin {

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
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected ValueSpecification value;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValuePinImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActivityPackage.Literals.VALUE_PIN;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.VALUE_PIN__IS_CONTROL, oldIsControl, isControl));

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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.VALUE_PIN__INPUT_EVALUATION_ACTION, oldInputEvaluationAction, inputEvaluationAction));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.VALUE_PIN__INPUT_EVALUATION_ACTION, oldInputEvaluationAction, inputEvaluationAction));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ValueSpecification getValue() {

		if (value != null && value.eIsProxy()) {
			InternalEObject oldValue = (InternalEObject)value;
			value = (ValueSpecification)eResolveProxy(oldValue);
			if (value != oldValue) {
				InternalEObject newValue = (InternalEObject)value;
				NotificationChain msgs = oldValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.VALUE_PIN__VALUE, null, null);
				if (newValue.eInternalContainer() == null) {
					msgs = newValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.VALUE_PIN__VALUE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.VALUE_PIN__VALUE, oldValue, value));
			}
		}
		return value;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ValueSpecification basicGetValue() {

		return value;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetValue(ValueSpecification newValue, NotificationChain msgs) {

		ValueSpecification oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.VALUE_PIN__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setValue(ValueSpecification newValue) {

		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.VALUE_PIN__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.VALUE_PIN__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.VALUE_PIN__VALUE, newValue, newValue));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ActivityPackage.VALUE_PIN__VALUE:
				return basicSetValue(null, msgs);
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
			case ActivityPackage.VALUE_PIN__IS_CONTROL:
				return isIsControl();
			case ActivityPackage.VALUE_PIN__INPUT_EVALUATION_ACTION:
				if (resolve) return getInputEvaluationAction();
				return basicGetInputEvaluationAction();
			case ActivityPackage.VALUE_PIN__VALUE:
				if (resolve) return getValue();
				return basicGetValue();
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
			case ActivityPackage.VALUE_PIN__IS_CONTROL:
				// begin-extension-code
				if (newValue == null || newValue instanceof Boolean) {
				// end-extension-code
					setIsControl((Boolean)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case ActivityPackage.VALUE_PIN__INPUT_EVALUATION_ACTION:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractAction) {
				// end-extension-code
					setInputEvaluationAction((AbstractAction)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case ActivityPackage.VALUE_PIN__VALUE:
				// begin-extension-code
				if (newValue == null || newValue instanceof ValueSpecification) {
				// end-extension-code
					setValue((ValueSpecification)newValue);
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
			case ActivityPackage.VALUE_PIN__IS_CONTROL:
				setIsControl(IS_CONTROL_EDEFAULT);
				return;
			case ActivityPackage.VALUE_PIN__INPUT_EVALUATION_ACTION:
				setInputEvaluationAction((AbstractAction)null);
				return;
			case ActivityPackage.VALUE_PIN__VALUE:
				setValue((ValueSpecification)null);
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
			case ActivityPackage.VALUE_PIN__IS_CONTROL:
				return isControl != IS_CONTROL_EDEFAULT;
			case ActivityPackage.VALUE_PIN__INPUT_EVALUATION_ACTION:
				return inputEvaluationAction != null;
			case ActivityPackage.VALUE_PIN__VALUE:
				return value != null;
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


} //ValuePinImpl