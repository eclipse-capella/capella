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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Accept Event Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.AcceptEventAction#isIsUnmarshall <em>Is Unmarshall</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AcceptEventAction#getResult <em>Result</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAcceptEventAction()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='AcceptEventAction is an action that waits for the occurrence of an event meeting specified condition\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='- AcceptEventActions may have no input pins.\r\n- There are no output pins if the trigger events are only ChangeEvents, or if they are only CallEvents when this action is an\r\ninstance of AcceptEventAction and not an instance of a descendant of AcceptEventAction (such as AcceptCallAction).\r\n- If the trigger events are all TimeEvents, there is exactly one output pin.\r\nUML superstructure Specification, v2.2 235\r\n- If isUnmarshalled is true, there must be exactly one trigger for events of type SignalEvent. The number of result output\r\npins must be the same as the number of attributes of the signal. The type and ordering of each result output pin must be the\r\nsame as the corresponding attribute of the signal. The multiplicity of each result output pin must be compatible with the\r\nmultiplicity of the corresponding attribute\r\n[source: UML superstructure v2.2]' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::AcceptEventAction' constraints='none'"
 * @generated
 */
public interface AcceptEventAction extends AbstractAction {





	/**
   * Returns the value of the '<em><b>Is Unmarshall</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Unmarshall</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Unmarshall</em>' attribute.
   * @see #setIsUnmarshall(boolean)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAcceptEventAction_IsUnmarshall()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Indicates whether there is a single output pin for the event, or multiple output pins for attributes of the event\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::AcceptEventAction::isUnmarshall' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsUnmarshall();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.AcceptEventAction#isIsUnmarshall <em>Is Unmarshall</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Unmarshall</em>' attribute.
   * @see #isIsUnmarshall()
   * @generated
   */

	void setIsUnmarshall(boolean value);







	/**
   * Returns the value of the '<em><b>Result</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.OutputPin}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Result</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAcceptEventAction_Result()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Pins holding the received event objects or their attributes. Event objects may be copied in transmission, so identity\r\nmight not be preserved\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::AcceptEventAction::result' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<OutputPin> getResult();





} // AcceptEventAction
