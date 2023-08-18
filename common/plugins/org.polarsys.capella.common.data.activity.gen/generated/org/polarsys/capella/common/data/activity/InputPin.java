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
package org.polarsys.capella.common.data.activity;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Pin</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.InputPin#getInputEvaluationAction <em>Input Evaluation Action</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getInputPin()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An input pin is a pin that holds input values to be consumed by an action\r\n[source: UML superstructure v2.2]\r\n\r\nAn action input pin is a kind of pin that executes an action to determine the values to input to another.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::InputPin' constraints='none'"
 * @generated
 */
public interface InputPin extends Pin {





	/**
   * Returns the value of the '<em><b>Input Evaluation Action</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Evaluation Action</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Input Evaluation Action</em>' reference.
   * @see #setInputEvaluationAction(AbstractAction)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getInputPin_InputEvaluationAction()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The action used to provide values\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='This association can be filled only if extended metaclass is ActionInputPin'"
   * @generated
   */

	AbstractAction getInputEvaluationAction();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.InputPin#getInputEvaluationAction <em>Input Evaluation Action</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Input Evaluation Action</em>' reference.
   * @see #getInputEvaluationAction()
   * @generated
   */

	void setInputEvaluationAction(AbstractAction value);





} // InputPin
