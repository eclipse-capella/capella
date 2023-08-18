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
package org.polarsys.capella.common.data.behavior;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.behavior.TimeExpression#getObservations <em>Observations</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.behavior.TimeExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getTimeExpression()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A Time Expression defines a value specification that represents a time value\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::TimeExpression' constraints='none'"
 * @generated
 */
public interface TimeExpression extends ValueSpecification {





	/**
   * Returns the value of the '<em><b>Observations</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Observations</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Observations</em>' reference.
   * @see #setObservations(AbstractNamedElement)
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getTimeExpression_Observations()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Refers to the time and duration observations that are involved in expr\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='cardinality of TimeExpression::observations should be changed to [0..*]'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TimeExpression::observation' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	AbstractNamedElement getObservations();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.behavior.TimeExpression#getObservations <em>Observations</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Observations</em>' reference.
   * @see #getObservations()
   * @generated
   */

	void setObservations(AbstractNamedElement value);







	/**
   * Returns the value of the '<em><b>Expression</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' reference.
   * @see #setExpression(ValueSpecification)
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getTimeExpression_Expression()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The value of the time expression\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TimeExpression::expr' explanation='none' constraints='none'"
   * @generated
   */

	ValueSpecification getExpression();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.behavior.TimeExpression#getExpression <em>Expression</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' reference.
   * @see #getExpression()
   * @generated
   */

	void setExpression(ValueSpecification value);





} // TimeExpression
