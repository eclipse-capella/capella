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
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.StateFragment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.StateFragmentImpl#getRelatedAbstractState <em>Related Abstract State</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.StateFragmentImpl#getRelatedAbstractFunction <em>Related Abstract Function</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateFragmentImpl extends TimeLapseImpl implements StateFragment {

	/**
   * The cached value of the '{@link #getRelatedAbstractState() <em>Related Abstract State</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRelatedAbstractState()
   * @generated
   * @ordered
   */
	protected AbstractState relatedAbstractState;





	/**
   * The cached value of the '{@link #getRelatedAbstractFunction() <em>Related Abstract Function</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRelatedAbstractFunction()
   * @generated
   * @ordered
   */
	protected AbstractFunction relatedAbstractFunction;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StateFragmentImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.STATE_FRAGMENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractState getRelatedAbstractState() {

    if (relatedAbstractState != null && relatedAbstractState.eIsProxy()) {
      InternalEObject oldRelatedAbstractState = (InternalEObject)relatedAbstractState;
      relatedAbstractState = (AbstractState)eResolveProxy(oldRelatedAbstractState);
      if (relatedAbstractState != oldRelatedAbstractState) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_STATE, oldRelatedAbstractState, relatedAbstractState));
      }
    }
    return relatedAbstractState;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractState basicGetRelatedAbstractState() {

    return relatedAbstractState;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setRelatedAbstractState(AbstractState newRelatedAbstractState) {

    AbstractState oldRelatedAbstractState = relatedAbstractState;
    relatedAbstractState = newRelatedAbstractState;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_STATE, oldRelatedAbstractState, relatedAbstractState));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractFunction getRelatedAbstractFunction() {

    if (relatedAbstractFunction != null && relatedAbstractFunction.eIsProxy()) {
      InternalEObject oldRelatedAbstractFunction = (InternalEObject)relatedAbstractFunction;
      relatedAbstractFunction = (AbstractFunction)eResolveProxy(oldRelatedAbstractFunction);
      if (relatedAbstractFunction != oldRelatedAbstractFunction) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION, oldRelatedAbstractFunction, relatedAbstractFunction));
      }
    }
    return relatedAbstractFunction;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractFunction basicGetRelatedAbstractFunction() {

    return relatedAbstractFunction;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setRelatedAbstractFunction(AbstractFunction newRelatedAbstractFunction) {

    AbstractFunction oldRelatedAbstractFunction = relatedAbstractFunction;
    relatedAbstractFunction = newRelatedAbstractFunction;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION, oldRelatedAbstractFunction, relatedAbstractFunction));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_STATE:
        if (resolve) return getRelatedAbstractState();
        return basicGetRelatedAbstractState();
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION:
        if (resolve) return getRelatedAbstractFunction();
        return basicGetRelatedAbstractFunction();
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
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_STATE:
          setRelatedAbstractState((AbstractState)newValue);
        return;
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION:
          setRelatedAbstractFunction((AbstractFunction)newValue);
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
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_STATE:
        setRelatedAbstractState((AbstractState)null);
        return;
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION:
        setRelatedAbstractFunction((AbstractFunction)null);
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
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_STATE:
        return relatedAbstractState != null;
      case InteractionPackage.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION:
        return relatedAbstractFunction != null;
    }
    return super.eIsSet(featureID);
  }



} //StateFragmentImpl