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
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exception Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getProtectedNode <em>Protected Node</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getHandlerBody <em>Handler Body</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getExceptionInput <em>Exception Input</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getExceptionTypes <em>Exception Types</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getExceptionHandler()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An exception handler is an element that specifies a body to execute in case the specified exception occurs during the\r\nexecution of the protected node\r\n[source: UML superstructure specification v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='Not used/implemented as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ExceptionHandler' constraints='none'"
 * @generated
 */
public interface ExceptionHandler extends ModelElement {





	/**
   * Returns the value of the '<em><b>Protected Node</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.activity.ExecutableNode#getOwnedHandlers <em>Owned Handlers</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Protected Node</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Protected Node</em>' container reference.
   * @see #setProtectedNode(ExecutableNode)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getExceptionHandler_ProtectedNode()
   * @see org.polarsys.capella.common.data.activity.ExecutableNode#getOwnedHandlers
   * @model opposite="ownedHandlers" required="true" transient="false"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The node protected by the handler. The handler is examined if an exception propagates to the outside of the node\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExceptionHandler::protectedNode' explanation='none' constraints='none'"
   * @generated
   */

	ExecutableNode getProtectedNode();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getProtectedNode <em>Protected Node</em>}' container reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Protected Node</em>' container reference.
   * @see #getProtectedNode()
   * @generated
   */

	void setProtectedNode(ExecutableNode value);







	/**
   * Returns the value of the '<em><b>Handler Body</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler Body</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Handler Body</em>' reference.
   * @see #setHandlerBody(ExecutableNode)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getExceptionHandler_HandlerBody()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A node that is executed if the handler satisfies an uncaught exception\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExceptionHandler::handlerBody' explanation='none' constraints='none'"
   * @generated
   */

	ExecutableNode getHandlerBody();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getHandlerBody <em>Handler Body</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Handler Body</em>' reference.
   * @see #getHandlerBody()
   * @generated
   */

	void setHandlerBody(ExecutableNode value);







	/**
   * Returns the value of the '<em><b>Exception Input</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Input</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exception Input</em>' reference.
   * @see #setExceptionInput(ObjectNode)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getExceptionHandler_ExceptionInput()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An object node within the handler body. When the handler catches an exception, the exception token is placed in this\r\nnode, causing the body to execute\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExceptionHandler::exceptionInput' explanation='none' constraints='none'"
   * @generated
   */

	ObjectNode getExceptionInput();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ExceptionHandler#getExceptionInput <em>Exception Input</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exception Input</em>' reference.
   * @see #getExceptionInput()
   * @generated
   */

	void setExceptionInput(ObjectNode value);







	/**
   * Returns the value of the '<em><b>Exception Types</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractType}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exception Types</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getExceptionHandler_ExceptionTypes()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The kind of instances that the handler catches. If an exception occurs whose type is any of the classifiers in the set,\r\nthe handler catches the exception and executes its body\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExceptionHandler::exceptionType' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractType> getExceptionTypes();





} // ExceptionHandler
