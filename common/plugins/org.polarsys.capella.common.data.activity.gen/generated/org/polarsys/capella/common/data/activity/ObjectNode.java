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
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ObjectNode#isIsControlType <em>Is Control Type</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ObjectNode#getKindOfNode <em>Kind Of Node</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ObjectNode#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ObjectNode#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ObjectNode#getInState <em>In State</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ObjectNode#getSelection <em>Selection</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNode()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An object node is an activity node that indicates an instance of a particular classifier, possibly in a particular state, may\r\nbe available at a particular point in the activity\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='- All edges coming into or going out of object nodes must be object flow edges.\r\n- Object nodes are not unique typed elements.\r\nisUnique = false\r\n- If an object node has a selection behavior, then the ordering of the object node is ordered and vice versa.\r\n- A selection behavior has one input parameter and one output parameter. The input parameter must be a bag of elements of\r\nthe same type as the object node or a supertype of the type of object node. The output parameter must be the same or a\r\nsubtype of the type of object node. The behavior cannot have side effects.\r\n[source: UML superstructure v2.2]' comment/notes='for the SysML profile, it will make sense to use the SysML stereotypes \"no buffer\" and \"overwrite\" over a UML ObjectNode (p99 and 100 of SysML spec)' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ObjectNode' constraints='none'"
 * @generated
 */
public interface ObjectNode extends ActivityNode, AbstractTypedElement {





	/**
   * Returns the value of the '<em><b>Is Control Type</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Control Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Control Type</em>' attribute.
   * @see #setIsControlType(boolean)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNode_IsControlType()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Tells whether the type of the object node is to be treated as control\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNode::isControlType' explanation='none' constraints='Cardinality of uml::ObjectNode::isControlType is [1..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsControlType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ObjectNode#isIsControlType <em>Is Control Type</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Control Type</em>' attribute.
   * @see #isIsControlType()
   * @generated
   */

	void setIsControlType(boolean value);







	/**
   * Returns the value of the '<em><b>Kind Of Node</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.common.data.activity.ObjectNodeKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind Of Node</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind Of Node</em>' attribute.
   * @see org.polarsys.capella.common.data.activity.ObjectNodeKind
   * @see #setKindOfNode(ObjectNodeKind)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNode_KindOfNode()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='characterizes the node' constraints='none' type='refer to ObjectNodeKind enumeration' comment/notes='this field does not exist in UML but the related notion exists in SysML'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ObjectNodeKind getKindOfNode();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ObjectNode#getKindOfNode <em>Kind Of Node</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind Of Node</em>' attribute.
   * @see org.polarsys.capella.common.data.activity.ObjectNodeKind
   * @see #getKindOfNode()
   * @generated
   */

	void setKindOfNode(ObjectNodeKind value);







	/**
   * Returns the value of the '<em><b>Ordering</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.common.data.activity.ObjectNodeOrderingKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Ordering</em>' attribute.
   * @see org.polarsys.capella.common.data.activity.ObjectNodeOrderingKind
   * @see #setOrdering(ObjectNodeOrderingKind)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNode_Ordering()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Tells whether and how the tokens in the object node are ordered for selection to traverse edges outgoing from the\r\nobject node\r\n[source: UML superstructure v2.2]' constraints='none' type='Refer to ObjectNodeOrderingKind enumeration' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNode::ordering' explanation='none' constraints='Cardinality of uml::ObjectNode::ordering is [1..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ObjectNodeOrderingKind getOrdering();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ObjectNode#getOrdering <em>Ordering</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ordering</em>' attribute.
   * @see org.polarsys.capella.common.data.activity.ObjectNodeOrderingKind
   * @see #getOrdering()
   * @generated
   */

	void setOrdering(ObjectNodeOrderingKind value);







	/**
   * Returns the value of the '<em><b>Upper Bound</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Bound</em>' containment reference.
   * @see #setUpperBound(ValueSpecification)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNode_UpperBound()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The maximum number of tokens allowed in the node. Objects cannot flow into the node if the upper bound is\r\nreached.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNode::upperBound' explanation='none' constraints='Cardinality of uml::ObjectNode::upperBound is [1..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getUpperBound();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ObjectNode#getUpperBound <em>Upper Bound</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Bound</em>' containment reference.
   * @see #getUpperBound()
   * @generated
   */

	void setUpperBound(ValueSpecification value);







	/**
   * Returns the value of the '<em><b>In State</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.IState}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In State</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In State</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNode_InState()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The required states of the object available at this point in the activity\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNode::inState' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<IState> getInState();







	/**
   * Returns the value of the '<em><b>Selection</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selection</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Selection</em>' reference.
   * @see #setSelection(AbstractBehavior)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNode_Selection()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Selects tokens for outgoing edges.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNode::selection' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractBehavior getSelection();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ObjectNode#getSelection <em>Selection</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Selection</em>' reference.
   * @see #getSelection()
   * @generated
   */

	void setSelection(AbstractBehavior value);





} // ObjectNode
