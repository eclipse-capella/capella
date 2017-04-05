/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.vp.ms.InSituationExpression;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.Situation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>In Situation Expression</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.impl.InSituationExpressionImpl#getSituation <em>Situation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InSituationExpressionImpl extends BooleanExpressionImpl implements InSituationExpression {

  /**
   * The cached value of the '{@link #getSituation() <em>Situation</em>}' reference. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getSituation()
   * @generated
   * @ordered
   */
  protected Situation situation;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected InSituationExpressionImpl() {

    super();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return MsPackage.Literals.IN_SITUATION_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public Situation getSituation() {

    if (situation != null && situation.eIsProxy()) {
      InternalEObject oldSituation = (InternalEObject) situation;
      situation = (Situation) eResolveProxy(oldSituation);
      if (situation != oldSituation) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MsPackage.IN_SITUATION_EXPRESSION__SITUATION,
              oldSituation, situation));
      }
    }
    return situation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public Situation basicGetSituation() {

    return situation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setSituation(Situation newSituation) {

    Situation oldSituation = situation;
    situation = newSituation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MsPackage.IN_SITUATION_EXPRESSION__SITUATION, oldSituation,
          situation));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case MsPackage.IN_SITUATION_EXPRESSION__SITUATION:
      if (resolve)
        return getSituation();
      return basicGetSituation();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case MsPackage.IN_SITUATION_EXPRESSION__SITUATION:
      setSituation((Situation) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case MsPackage.IN_SITUATION_EXPRESSION__SITUATION:
      setSituation((Situation) null);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case MsPackage.IN_SITUATION_EXPRESSION__SITUATION:
      return situation != null;
    }
    return super.eIsSet(featureID);
  }

} // InSituationExpressionImpl