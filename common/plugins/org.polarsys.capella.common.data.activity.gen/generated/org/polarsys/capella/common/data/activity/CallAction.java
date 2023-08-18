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
 * A representation of the model object '<em><b>Call Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.CallAction#getResults <em>Results</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getCallAction()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='CallAction is an abstract class for actions that invoke behavior and receive return values\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='- Only synchronous call actions can have result pins.\r\n- The number and order of argument pins must be the same as the number and order of parameters of the invoked behavior\r\nor behavioral feature. Pins are matched to parameters by order.\r\n- The type, ordering, and multiplicity of an argument pin must be the same as the corresponding parameter of the behavior\r\nor behavioral feature.\r\n[source: UML superstructure v2.2]' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::CallAction' constraints='none'"
 * @generated
 */
public interface CallAction extends InvocationAction {





	/**
   * Returns the value of the '<em><b>Results</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.OutputPin}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Results</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getCallAction_Results()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A list of output pins where the results of performing the invocation are placed\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::CallAction::result' explanation='' constraints=''"
   * @generated
   */

	EList<OutputPin> getResults();





} // CallAction
