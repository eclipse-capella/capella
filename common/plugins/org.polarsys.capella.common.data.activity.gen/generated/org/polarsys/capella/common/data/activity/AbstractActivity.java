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
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractActivity#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractActivity#isIsSingleExecution <em>Is Single Execution</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractActivity#getOwnedNodes <em>Owned Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractActivity#getOwnedEdges <em>Owned Edges</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractActivity#getOwnedGroups <em>Owned Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractActivity#getOwnedStructuredNodes <em>Owned Structured Nodes</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractActivity()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An activity specifies the coordination of executions of subordinate behaviors, using a control and data flow model\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Activity' constraints='none'"
 * @generated
 */
public interface AbstractActivity extends AbstractBehavior, TraceableElement {





	/**
   * Returns the value of the '<em><b>Is Read Only</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Read Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Read Only</em>' attribute.
   * @see #setIsReadOnly(boolean)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractActivity_IsReadOnly()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='If true, this activity must not make any changes to variables outside the activity or to objects. (This is an assertion, not\r\nan executable property. It may be used by an execution engine to optimize model execution. If the assertion is\r\nviolated by the action, then the model is ill formed.)\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::isReadOnly' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsReadOnly();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.AbstractActivity#isIsReadOnly <em>Is Read Only</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Read Only</em>' attribute.
   * @see #isIsReadOnly()
   * @generated
   */

	void setIsReadOnly(boolean value);







	/**
   * Returns the value of the '<em><b>Is Single Execution</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Single Execution</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Single Execution</em>' attribute.
   * @see #setIsSingleExecution(boolean)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractActivity_IsSingleExecution()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='If true, all invocations of the activity are handled by the same execution\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::isSingleExecution' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsSingleExecution();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.AbstractActivity#isIsSingleExecution <em>Is Single Execution</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Single Execution</em>' attribute.
   * @see #isIsSingleExecution()
   * @generated
   */

	void setIsSingleExecution(boolean value);







	/**
   * Returns the value of the '<em><b>Owned Nodes</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityNode}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Nodes</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractActivity_OwnedNodes()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Nodes coordinated by the activity.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::node' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ActivityNode> getOwnedNodes();







	/**
   * Returns the value of the '<em><b>Owned Edges</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityEdge}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Edges</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractActivity_OwnedEdges()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Edges expressing flow between nodes of the activity.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::edge' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ActivityEdge> getOwnedEdges();







	/**
   * Returns the value of the '<em><b>Owned Groups</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityGroup}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Groups</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractActivity_OwnedGroups()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Top-level groups in the activity\r\n[source: UML superstructure v2.2]' constraints='The groups of an activity have no supergroups\r\n[source: UML superstructure v2.2]' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::group' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ActivityGroup> getOwnedGroups();







	/**
   * Returns the value of the '<em><b>Owned Structured Nodes</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.StructuredActivityNode}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Structured Nodes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Structured Nodes</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractActivity_OwnedStructuredNodes()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedGroups'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Nodes coordinated by the activity\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<StructuredActivityNode> getOwnedStructuredNodes();





} // AbstractActivity
