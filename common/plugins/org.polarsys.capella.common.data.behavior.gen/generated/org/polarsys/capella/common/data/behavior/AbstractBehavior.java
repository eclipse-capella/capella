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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.behavior.AbstractBehavior#isIsControlOperator <em>Is Control Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.behavior.AbstractBehavior#getOwnedParameterSet <em>Owned Parameter Set</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.behavior.AbstractBehavior#getOwnedParameter <em>Owned Parameter</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractBehavior()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A Behavior is a specification of how its context classifier changes state over time.\r\nThe concept of behavior is extended to own Parameter Sets.\r\n[source: UML superstructure v2.2]\r\n\r\nDynamic response to an excitation of an engineering thing.\r\n[source: INCOSE AP233 WG, \"System Engineering Concepts - A semantic glossary and model\"]\r\n' usage\040guideline='n/a (abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='This element is a combination of UML2\'s Behavior from BasicBehavior package, and Behavior from CompleteBehavior package.\r\nIt has Parameters, and also has ParameterSets definition (e.g. specific groupings of some of the parameters)' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Behavior' constraints='none'"
 * @generated
 */
public interface AbstractBehavior extends AbstractNamedElement {





	/**
   * Returns the value of the '<em><b>Is Control Operator</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Control Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Control Operator</em>' attribute.
   * @see #setIsControlOperator(boolean)
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractBehavior_IsControlOperator()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Tells whether the type of this behavior node is to be treated as control\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='_todo_ Maybye it can be mapped to uml::ObjectNode::isControlType...' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsControlOperator();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior#isIsControlOperator <em>Is Control Operator</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Control Operator</em>' attribute.
   * @see #isIsControlOperator()
   * @generated
   */

	void setIsControlOperator(boolean value);







	/**
   * Returns the value of the '<em><b>Owned Parameter Set</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractParameterSet}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Parameter Set</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Parameter Set</em>' reference list.
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractBehavior_OwnedParameterSet()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The ParameterSets owned by this Behavior\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Behavior::ownedParameterSet' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractParameterSet> getOwnedParameterSet();







	/**
   * Returns the value of the '<em><b>Owned Parameter</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractParameter}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Parameter</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Parameter</em>' reference list.
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractBehavior_OwnedParameter()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References a list of parameters to the behavior that describes the order and type of arguments that can be given\r\nwhen the behavior is invoked and of the values that will be returned when the behavior completes its execution\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Behavior::ownedParameter' explanation='none' constraints='none'"
   * @generated
   */

	EList<AbstractParameter> getOwnedParameter();





} // AbstractBehavior
