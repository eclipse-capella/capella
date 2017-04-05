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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.vp.ms.BooleanExpression;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.Situation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Situation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.impl.SituationImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SituationImpl extends NamedElementImpl implements Situation {

  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected BooleanExpression expression;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected SituationImpl() {

    super();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return MsPackage.Literals.SITUATION;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public BooleanExpression getExpression() {

    return expression;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public NotificationChain basicSetExpression(BooleanExpression newExpression, NotificationChain msgs) {

    BooleanExpression oldExpression = expression;
    expression = newExpression;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsPackage.SITUATION__EXPRESSION,
          oldExpression, newExpression);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }

    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public void setExpression(BooleanExpression newExpression) {

    if (newExpression != expression) {
      NotificationChain msgs = null;
      if (expression != null)
        msgs = ((InternalEObject) expression).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - MsPackage.SITUATION__EXPRESSION, null, msgs);
      if (newExpression != null)
        msgs = ((InternalEObject) newExpression).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - MsPackage.SITUATION__EXPRESSION, null, msgs);
      msgs = basicSetExpression(newExpression, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(this, Notification.SET, MsPackage.SITUATION__EXPRESSION, newExpression, newExpression));

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
    case MsPackage.SITUATION__EXPRESSION:
      return basicSetExpression(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case MsPackage.SITUATION__EXPRESSION:
      return getExpression();
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
    case MsPackage.SITUATION__EXPRESSION:
      setExpression((BooleanExpression) newValue);
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
    case MsPackage.SITUATION__EXPRESSION:
      setExpression((BooleanExpression) null);
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
    case MsPackage.SITUATION__EXPRESSION:
      return expression != null;
    }
    return super.eIsSet(featureID);
  }

} // SituationImpl