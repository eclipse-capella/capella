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

import org.polarsys.capella.common.data.behavior.AbstractSignal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Send Signal Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.SendSignalAction#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.SendSignalAction#getSignal <em>Signal</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getSendSignalAction()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='SendSignalAction is an action that creates a signal instance from its inputs, and transmits it to the target object, where it\r\nmay cause the firing of a state machine transition or the execution of an activity. The argument values are available to the\r\nexecution of associated behaviors. The requestor continues execution immediately. Any reply message is ignored and is\r\nnot transmitted to the requestor. If the input is already a signal instance, use SendObjectAction\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='- The number and order of argument pins must be the same as the number and order of attributes in the signal.\r\n- The type, ordering, and multiplicity of an argument pin must be the same as the corresponding attribute of the signal.\r\n[source: UML superstructure v2.2]' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::SendSignalAction' constraints='none'"
 * @generated
 */
public interface SendSignalAction extends InvocationAction {





	/**
   * Returns the value of the '<em><b>Target</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' containment reference.
   * @see #setTarget(InputPin)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getSendSignalAction_Target()
   * @model containment="true" resolveProxies="true" required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The target object to which the signal is sent.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::SendSignalAction::target' explanation='none' constraints='none'"
   * @generated
   */

	InputPin getTarget();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.SendSignalAction#getTarget <em>Target</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */

	void setTarget(InputPin value);







	/**
   * Returns the value of the '<em><b>Signal</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Signal</em>' reference.
   * @see #setSignal(AbstractSignal)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getSendSignalAction_Signal()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The type of signal transmitted to the target object.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::SendSignalAction::signal' explanation='none' constraints='none'"
   * @generated
   */

	AbstractSignal getSignal();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.SendSignalAction#getSignal <em>Signal</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Signal</em>' reference.
   * @see #getSignal()
   * @generated
   */

	void setSignal(AbstractSignal value);





} // SendSignalAction
