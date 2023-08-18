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
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.TimeLapse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Lapse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.TimeLapseImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.TimeLapseImpl#getFinish <em>Finish</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TimeLapseImpl extends NamedElementImpl implements TimeLapse {

	/**
   * The cached value of the '{@link #getStart() <em>Start</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getStart()
   * @generated
   * @ordered
   */
	protected InteractionFragment start;





	/**
   * The cached value of the '{@link #getFinish() <em>Finish</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getFinish()
   * @generated
   * @ordered
   */
	protected InteractionFragment finish;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TimeLapseImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.TIME_LAPSE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InteractionFragment getStart() {

    if (start != null && start.eIsProxy()) {
      InternalEObject oldStart = (InternalEObject)start;
      start = (InteractionFragment)eResolveProxy(oldStart);
      if (start != oldStart) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.TIME_LAPSE__START, oldStart, start));
      }
    }
    return start;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InteractionFragment basicGetStart() {

    return start;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setStart(InteractionFragment newStart) {

    InteractionFragment oldStart = start;
    start = newStart;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.TIME_LAPSE__START, oldStart, start));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InteractionFragment getFinish() {

    if (finish != null && finish.eIsProxy()) {
      InternalEObject oldFinish = (InternalEObject)finish;
      finish = (InteractionFragment)eResolveProxy(oldFinish);
      if (finish != oldFinish) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.TIME_LAPSE__FINISH, oldFinish, finish));
      }
    }
    return finish;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InteractionFragment basicGetFinish() {

    return finish;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setFinish(InteractionFragment newFinish) {

    InteractionFragment oldFinish = finish;
    finish = newFinish;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.TIME_LAPSE__FINISH, oldFinish, finish));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InteractionPackage.TIME_LAPSE__START:
        if (resolve) return getStart();
        return basicGetStart();
      case InteractionPackage.TIME_LAPSE__FINISH:
        if (resolve) return getFinish();
        return basicGetFinish();
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
      case InteractionPackage.TIME_LAPSE__START:
          setStart((InteractionFragment)newValue);
        return;
      case InteractionPackage.TIME_LAPSE__FINISH:
          setFinish((InteractionFragment)newValue);
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
      case InteractionPackage.TIME_LAPSE__START:
        setStart((InteractionFragment)null);
        return;
      case InteractionPackage.TIME_LAPSE__FINISH:
        setFinish((InteractionFragment)null);
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
      case InteractionPackage.TIME_LAPSE__START:
        return start != null;
      case InteractionPackage.TIME_LAPSE__FINISH:
        return finish != null;
    }
    return super.eIsSet(featureID);
  }



} //TimeLapseImpl