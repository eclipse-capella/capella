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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Time Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent#isIsRelative <em>Is Relative</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent#getWhen <em>When</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractTimeEvent()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A time event specifies a point in time by an expression. The expression might be absolute or might be relative to some\r\nother point in time.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::TimeEvent' constraints='none'"
 * @generated
 */
public interface AbstractTimeEvent extends AbstractEvent {





	/**
   * Returns the value of the '<em><b>Is Relative</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Relative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Relative</em>' attribute.
   * @see #setIsRelative(boolean)
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractTimeEvent_IsRelative()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies whether it is relative or absolute time\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TimeEvent::isRelative' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsRelative();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent#isIsRelative <em>Is Relative</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Relative</em>' attribute.
   * @see #isIsRelative()
   * @generated
   */

	void setIsRelative(boolean value);







	/**
   * Returns the value of the '<em><b>When</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>When</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>When</em>' reference.
   * @see #setWhen(TimeExpression)
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractTimeEvent_When()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the corresponding time deadline\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TimeEvent::when' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	TimeExpression getWhen();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent#getWhen <em>When</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>When</em>' reference.
   * @see #getWhen()
   * @generated
   */

	void setWhen(TimeExpression value);





} // AbstractTimeEvent
