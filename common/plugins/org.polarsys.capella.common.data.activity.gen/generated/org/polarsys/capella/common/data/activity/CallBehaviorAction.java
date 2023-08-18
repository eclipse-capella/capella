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

import org.polarsys.capella.common.data.behavior.AbstractBehavior;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Behavior Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.CallBehaviorAction#getBehavior <em>Behavior</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getCallBehaviorAction()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='CallBehaviorAction is a call action that invokes a behavior directly rather than invoking a behavioral feature that, in turn,\r\nresults in the invocation of that behavior. The argument values of the action are available to the execution of the invoked\r\nbehavior. For synchronous calls the execution of the call behavior action waits until the execution of the invoked behavior\r\ncompletes and a result is returned on its output pin. The action completes immediately without a result, if the call is\r\nasynchronous.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='- The number of argument pins and the number of parameters of the behavior of type in and in-out must be equal.\r\n- The number of result pins and the number of parameters of the behavior of type return, out, and in-out must be equal.\r\n- The type, ordering, and multiplicity of an argument or result pin is derived from the corresponding parameter of thebehavior.\r\n[source: UML superstructure v2.2]' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::CallBehaviorAction' constraints='none'"
 * @generated
 */
public interface CallBehaviorAction extends CallAction {





	/**
   * Returns the value of the '<em><b>Behavior</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Behavior</em>' reference.
   * @see #setBehavior(AbstractBehavior)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getCallBehaviorAction_Behavior()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The invoked behavior. It must be capable of accepting and returning control\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='cannot map to uml::CallBehaviorAction::behavior since it just does not match (refers to different things)' constraints='none'"
   * @generated
   */

	AbstractBehavior getBehavior();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.CallBehaviorAction#getBehavior <em>Behavior</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Behavior</em>' reference.
   * @see #getBehavior()
   * @generated
   */

	void setBehavior(AbstractBehavior value);





} // CallBehaviorAction
