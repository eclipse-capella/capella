/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Result</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.impl.ResultImpl#getSituation <em>Situation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultImpl extends NamedElementImpl implements Result {

  /**
   * The cached value of the '{@link #getSituation() <em>Situation</em>}' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getSituation()
   * @generated
   * @ordered
   */
  protected EList<Situation> situation;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ResultImpl() {

    super();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return MsPackage.Literals.RESULT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<Situation> getSituation() {

    if (situation == null) {
      situation = new EObjectResolvingEList<Situation>(Situation.class, this, MsPackage.RESULT__SITUATION);
    }
    return situation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case MsPackage.RESULT__SITUATION:
      return getSituation();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case MsPackage.RESULT__SITUATION:
      getSituation().clear();
      getSituation().addAll((Collection<? extends Situation>) newValue);
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
    case MsPackage.RESULT__SITUATION:
      getSituation().clear();
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
    case MsPackage.RESULT__SITUATION:
      return situation != null && !situation.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} // ResultImpl