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
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityNode#getInActivityPartition <em>In Activity Partition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityNode#getInInterruptibleRegion <em>In Interruptible Region</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityNode#getInStructuredNode <em>In Structured Node</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityNode#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityNode#getIncoming <em>Incoming</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityNode()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An activity node is an abstract class for the steps of an activity. It covers executable nodes, control nodes, and object\r\nnodes.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='Activity nodes can only be owned by activities or groups.\r\nActivity nodes may be owned by at most one structured node.\r\n[source: UML superstructure v2.2]' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ActivityNode' constraints='none'"
 * @generated
 */
public interface ActivityNode extends AbstractNamedElement {





	/**
   * Returns the value of the '<em><b>In Activity Partition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Activity Partition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In Activity Partition</em>' reference.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityNode_InActivityPartition()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedNodes'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Partitions containing the node\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	ActivityPartition getInActivityPartition();







	/**
   * Returns the value of the '<em><b>In Interruptible Region</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Interruptible Region</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In Interruptible Region</em>' reference.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityNode_InInterruptibleRegion()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedNodes'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Interruptible regions containing the node\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	InterruptibleActivityRegion getInInterruptibleRegion();







	/**
   * Returns the value of the '<em><b>In Structured Node</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Structured Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In Structured Node</em>' reference.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityNode_InStructuredNode()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedNodes'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Structured activity node containing the node\r\n[source: UML superstructure v2.2]' constraints='node' comment/notes='node'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	InterruptibleActivityRegion getInStructuredNode();







	/**
   * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityEdge}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityNode_Outgoing()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Edges that have the node as source\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern ActivityNode__outgoing(self : ActivityNode, target : ActivityEdge) {\n\tActivityEdge.source(target, self);\n} or {\n\tAbstractAction(self);\n\tAbstractAction.outputs(self, OutputPort);\n\tActivityNode.outgoing(OutputPort, target);\n}'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ActivityEdge> getOutgoing();







	/**
   * Returns the value of the '<em><b>Incoming</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityEdge}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityNode_Incoming()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Edges that have the node as target.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern ActivityNode__incoming(self : ActivityNode, target : ActivityEdge) {\n\tActivityEdge.target(target, self);\n} or {\n\tAbstractAction(self);\n\tAbstractAction.inputs(self, InputPort);\n\tActivityNode.incoming(InputPort, target);\n}'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ActivityEdge> getIncoming();





} // ActivityNode
