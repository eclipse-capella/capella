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
 * A representation of the model object '<em><b>Executable Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ExecutableNode#getOwnedHandlers <em>Owned Handlers</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getExecutableNode()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An executable node is an abstract class for activity nodes that may be executed. It is used as an attachment point for\r\nexception handlers.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='should be on uml::ExecutableNode, but Activity' constraints='none'"
 * @generated
 */
public interface ExecutableNode extends ActivityNode {





	/**
   * Returns the value of the '<em><b>Owned Handlers</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ExceptionHandler}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getProtectedNode <em>Protected Node</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Handlers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Handlers</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getExecutableNode_OwnedHandlers()
   * @see org.polarsys.capella.common.data.activity.ExceptionHandler#getProtectedNode
   * @model opposite="protectedNode" containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A set of exception handlers that are examined if an uncaught exception propagates to the outer level of the executable\r\nnode.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExecutableNode::handler' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ExceptionHandler> getOwnedHandlers();





} // ExecutableNode
