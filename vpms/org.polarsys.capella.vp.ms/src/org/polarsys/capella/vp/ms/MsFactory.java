/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *    Altran - Compare Configurations
 *******************************************************************************/

package org.polarsys.capella.vp.ms;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.vp.ms.MsPackage
 * @generated
 */
public interface MsFactory extends EFactory {
  /**
   * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  MsFactory eINSTANCE = org.polarsys.capella.vp.ms.impl.MsFactoryImpl.init();

  /**
   * Returns a new object of class '<em>CS Configuration</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>CS Configuration</em>'.
   * @generated
   */
  CSConfiguration createCSConfiguration();

  /**
   * Returns a new object of class '<em>FSM Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>FSM Type</em>'.
   * @generated
   */
  FSMType createFSMType();

  /**
   * Returns a new object of class '<em>Situation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Situation</em>'.
   * @generated
   */
  Situation createSituation();

  /**
   * Returns a new object of class '<em>In State Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>In State Expression</em>'.
   * @generated
   */
  InStateExpression createInStateExpression();

  /**
   * Returns a new object of class '<em>In Situation Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>In Situation Expression</em>'.
   * @generated
   */
  InSituationExpression createInSituationExpression();

  /**
   * Returns a new object of class '<em>And Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>And Operation</em>'.
   * @generated
   */
  AndOperation createAndOperation();

  /**
   * Returns a new object of class '<em>Or Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Or Operation</em>'.
   * @generated
   */
  OrOperation createOrOperation();

  /**
   * Returns a new object of class '<em>Not Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Not Operation</em>'.
   * @generated
   */
  NotOperation createNotOperation();

  /**
   * Returns a new object of class '<em>Comparison</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Comparison</em>'.
   * @generated
   */
  Comparison createComparison();

  /**
   * Returns a new object of class '<em>Result</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Result</em>'.
   * @generated
   */
  Result createResult();

  /**
   * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the package supported by this factory.
   * @generated
   */
  MsPackage getMsPackage();

} // MsFactory
