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
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.Situation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Comparison</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.impl.ComparisonImpl#getConfiguration1 <em>Configuration1</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.ComparisonImpl#getSituation <em>Situation</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.impl.ComparisonImpl#getConfiguration2 <em>Configuration2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComparisonImpl extends NamedElementImpl implements Comparison {

  /**
   * The cached value of the '{@link #getConfiguration1() <em>Configuration1</em>}' reference list. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #getConfiguration1()
   * @generated
   * @ordered
   */
  protected EList<CSConfiguration> configuration1;

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
   * The cached value of the '{@link #getConfiguration2() <em>Configuration2</em>}' reference list. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #getConfiguration2()
   * @generated
   * @ordered
   */
  protected EList<CSConfiguration> configuration2;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ComparisonImpl() {

    super();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return MsPackage.Literals.COMPARISON;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<CSConfiguration> getConfiguration1() {

    if (configuration1 == null) {
      configuration1 = new EObjectResolvingEList<CSConfiguration>(CSConfiguration.class, this,
          MsPackage.COMPARISON__CONFIGURATION1);
    }
    return configuration1;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<Situation> getSituation() {

    if (situation == null) {
      situation = new EObjectResolvingEList<Situation>(Situation.class, this, MsPackage.COMPARISON__SITUATION);
    }
    return situation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */

  public EList<CSConfiguration> getConfiguration2() {

    if (configuration2 == null) {
      configuration2 = new EObjectResolvingEList<CSConfiguration>(CSConfiguration.class, this,
          MsPackage.COMPARISON__CONFIGURATION2);
    }
    return configuration2;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case MsPackage.COMPARISON__CONFIGURATION1:
      return getConfiguration1();
    case MsPackage.COMPARISON__SITUATION:
      return getSituation();
    case MsPackage.COMPARISON__CONFIGURATION2:
      return getConfiguration2();
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
    case MsPackage.COMPARISON__CONFIGURATION1:
      getConfiguration1().clear();
      getConfiguration1().addAll((Collection<? extends CSConfiguration>) newValue);
      return;
    case MsPackage.COMPARISON__SITUATION:
      getSituation().clear();
      getSituation().addAll((Collection<? extends Situation>) newValue);
      return;
    case MsPackage.COMPARISON__CONFIGURATION2:
      getConfiguration2().clear();
      getConfiguration2().addAll((Collection<? extends CSConfiguration>) newValue);
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
    case MsPackage.COMPARISON__CONFIGURATION1:
      getConfiguration1().clear();
      return;
    case MsPackage.COMPARISON__SITUATION:
      getSituation().clear();
      return;
    case MsPackage.COMPARISON__CONFIGURATION2:
      getConfiguration2().clear();
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
    case MsPackage.COMPARISON__CONFIGURATION1:
      return configuration1 != null && !configuration1.isEmpty();
    case MsPackage.COMPARISON__SITUATION:
      return situation != null && !situation.isEmpty();
    case MsPackage.COMPARISON__CONFIGURATION2:
      return configuration2 != null && !configuration2.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} // ComparisonImpl