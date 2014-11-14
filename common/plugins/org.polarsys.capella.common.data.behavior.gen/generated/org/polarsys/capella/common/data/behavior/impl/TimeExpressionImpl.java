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
package org.polarsys.capella.common.data.behavior.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.behavior.TimeExpression;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.data.modellingcore.impl.AbstractTypedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.behavior.impl.TimeExpressionImpl#getObservations <em>Observations</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.behavior.impl.TimeExpressionImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TimeExpressionImpl extends AbstractTypedElementImpl implements TimeExpression {

	/**
	 * The cached value of the '{@link #getObservations() <em>Observations</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObservations()
	 * @generated
	 * @ordered
	 */
	protected AbstractNamedElement observations;





	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected ValueSpecification expression;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeExpressionImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehaviorPackage.Literals.TIME_EXPRESSION;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractNamedElement getObservations() {

		if (observations != null && observations.eIsProxy()) {
			InternalEObject oldObservations = (InternalEObject)observations;
			observations = (AbstractNamedElement)eResolveProxy(oldObservations);
			if (observations != oldObservations) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.TIME_EXPRESSION__OBSERVATIONS, oldObservations, observations));
			}
		}
		return observations;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractNamedElement basicGetObservations() {

		return observations;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setObservations(AbstractNamedElement newObservations) {

		AbstractNamedElement oldObservations = observations;
		observations = newObservations;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.TIME_EXPRESSION__OBSERVATIONS, oldObservations, observations));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ValueSpecification getExpression() {

		if (expression != null && expression.eIsProxy()) {
			InternalEObject oldExpression = (InternalEObject)expression;
			expression = (ValueSpecification)eResolveProxy(oldExpression);
			if (expression != oldExpression) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.TIME_EXPRESSION__EXPRESSION, oldExpression, expression));
			}
		}
		return expression;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ValueSpecification basicGetExpression() {

		return expression;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setExpression(ValueSpecification newExpression) {

		ValueSpecification oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.TIME_EXPRESSION__EXPRESSION, oldExpression, expression));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BehaviorPackage.TIME_EXPRESSION__OBSERVATIONS:
				if (resolve) return getObservations();
				return basicGetObservations();
			case BehaviorPackage.TIME_EXPRESSION__EXPRESSION:
				if (resolve) return getExpression();
				return basicGetExpression();
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
			case BehaviorPackage.TIME_EXPRESSION__OBSERVATIONS:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractNamedElement) {
				// end-extension-code
					setObservations((AbstractNamedElement)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case BehaviorPackage.TIME_EXPRESSION__EXPRESSION:
				// begin-extension-code
				if (newValue == null || newValue instanceof ValueSpecification) {
				// end-extension-code
					setExpression((ValueSpecification)newValue);
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
			case BehaviorPackage.TIME_EXPRESSION__OBSERVATIONS:
				setObservations((AbstractNamedElement)null);
				return;
			case BehaviorPackage.TIME_EXPRESSION__EXPRESSION:
				setExpression((ValueSpecification)null);
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
			case BehaviorPackage.TIME_EXPRESSION__OBSERVATIONS:
				return observations != null;
			case BehaviorPackage.TIME_EXPRESSION__EXPRESSION:
				return expression != null;
		}
		return super.eIsSet(featureID);
	}



} //TimeExpressionImpl