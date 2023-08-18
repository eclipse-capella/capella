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
 * A representation of the model object '<em><b>Abstract Signal Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.behavior.AbstractSignalEvent#getSignal <em>Signal</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractSignalEvent()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A signal event represents the receipt of an asynchronous signal. A signal event may cause a response, such as a state\r\nmachine transition as specified in the classifier behavior of the classifier that specified the receiver object, if the signal\r\nreferenced by the send request is mentioned in a reception owned or inherited by the classifier that specified the receiver\r\nobject.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::SignalEvent' constraints='none'"
 * @generated
 */
public interface AbstractSignalEvent extends AbstractMessageEvent {





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
   * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractSignalEvent_Signal()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The specific signal that is associated with this event\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::SignalEvent::signal' explanation='none' constraints='none'"
   * @generated
   */

	AbstractSignal getSignal();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.behavior.AbstractSignalEvent#getSignal <em>Signal</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Signal</em>' reference.
   * @see #getSignal()
   * @generated
   */

	void setSignal(AbstractSignal value);





} // AbstractSignalEvent
