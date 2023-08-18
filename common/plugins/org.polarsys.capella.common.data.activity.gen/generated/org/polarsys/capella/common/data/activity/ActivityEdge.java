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

import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.RateKind;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getKindOfRate <em>Kind Of Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getInActivityPartition <em>In Activity Partition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getInInterruptibleRegion <em>In Interruptible Region</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getInStructuredNode <em>In Structured Node</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getRate <em>Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getGuard <em>Guard</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityEdge#getInterrupts <em>Interrupts</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='ActivityEdge is an abstract class for the connections along which tokens flow between activity nodes. It covers control\r\nand data flow edges. Activity edges can control token flow\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ActivityEdge' constraints='none'"
 * @generated
 */
public interface ActivityEdge extends AbstractRelationship {





	/**
   * Returns the value of the '<em><b>Kind Of Rate</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.common.data.modellingcore.RateKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind Of Rate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind Of Rate</em>' attribute.
   * @see org.polarsys.capella.common.data.modellingcore.RateKind
   * @see #setKindOfRate(RateKind)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_KindOfRate()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='characterizes the rate : typically, discrete versus continuous.\r\n[source: Capella study]' constraints='none' type='typically, \"discrete\" or \"continuous\"' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='Application of stereotypes that inherits from the SysML::Activities::Rate stereotype.\r\nIt can be either SysML::Activities::Continuous or SysML::Activities::Discrete.' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	RateKind getKindOfRate();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getKindOfRate <em>Kind Of Rate</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind Of Rate</em>' attribute.
   * @see org.polarsys.capella.common.data.modellingcore.RateKind
   * @see #getKindOfRate()
   * @generated
   */

	void setKindOfRate(RateKind value);







	/**
   * Returns the value of the '<em><b>In Activity Partition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Activity Partition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In Activity Partition</em>' reference.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_InActivityPartition()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedEdges'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Partitions containing the edge\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
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
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_InInterruptibleRegion()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedEdges'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) Region containing this edge\r\n[source: Capella study]' constraints='none' comment/notes='none'"
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
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_InStructuredNode()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedEdges'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Structured activity node containing the edge\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	StructuredActivityNode getInStructuredNode();







	/**
   * Returns the value of the '<em><b>Rate</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Rate</em>' containment reference.
   * @see #setRate(ValueSpecification)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_Rate()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='It specifies the expected value of the number of objects and\r\nvalues that traverse the edge per time interval, that is, the expected value rate at which they leave the source node and\r\narrive at the target node. It does not refer to the rate at which a value changes over time\r\n[source: SysML specification v1.1]' constraints='The rate of a parameter must be less than or equal to rates on edges that come into or go out from pins and parameters nodes corresponding to the parameter.\r\n[source: SysML specification v1.1]' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='Application of the SysML::Activities::Rate stereotype.\r\nSysML::Activities::Rate::rate' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getRate();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getRate <em>Rate</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rate</em>' containment reference.
   * @see #getRate()
   * @generated
   */

	void setRate(ValueSpecification value);







	/**
   * Returns the value of the '<em><b>Probability</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Probability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Probability</em>' containment reference.
   * @see #setProbability(ValueSpecification)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_Probability()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Likelihood that a value leaving the decision node or object node will traverse the edge.\r\n[source: SysML specification v1.1]' constraints='Shall be between 0 and 1' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='Application of the SysML::Activities::Probability stereotype.\r\nSysML::Activities::Probability::probability' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getProbability();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getProbability <em>Probability</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Probability</em>' containment reference.
   * @see #getProbability()
   * @generated
   */

	void setProbability(ValueSpecification value);







	/**
   * Returns the value of the '<em><b>Target</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(ActivityNode)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_Target()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Node to which tokens are put when they traverse the edge\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityEdge::target' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ActivityNode getTarget();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getTarget <em>Target</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */

	void setTarget(ActivityNode value);







	/**
   * Returns the value of the '<em><b>Source</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(ActivityNode)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_Source()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Node from which tokens are taken when they traverse the edge\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityEdge::source' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ActivityNode getSource();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getSource <em>Source</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */

	void setSource(ActivityNode value);







	/**
   * Returns the value of the '<em><b>Guard</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Guard</em>' containment reference.
   * @see #setGuard(ValueSpecification)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_Guard()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specification evaluated at runtime to determine if the edge can be traversed\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityEdge::guard' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getGuard();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getGuard <em>Guard</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard</em>' containment reference.
   * @see #getGuard()
   * @generated
   */

	void setGuard(ValueSpecification value);







	/**
   * Returns the value of the '<em><b>Weight</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Weight</em>' containment reference.
   * @see #setWeight(ValueSpecification)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_Weight()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The minimum number of tokens that must traverse the edge at the same time\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityEdge::weight' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getWeight();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getWeight <em>Weight</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Weight</em>' containment reference.
   * @see #getWeight()
   * @generated
   */

	void setWeight(ValueSpecification value);







	/**
   * Returns the value of the '<em><b>Interrupts</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.activity.InterruptibleActivityRegion#getInterruptingEdges <em>Interrupting Edges</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interrupts</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interrupts</em>' reference.
   * @see #setInterrupts(InterruptibleActivityRegion)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityEdge_Interrupts()
   * @see org.polarsys.capella.common.data.activity.InterruptibleActivityRegion#getInterruptingEdges
   * @model opposite="interruptingEdges"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Region that the edge can interrupt\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityEdge::interrupts' explanation='none' constraints='none'"
   * @generated
   */

	InterruptibleActivityRegion getInterrupts();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getInterrupts <em>Interrupts</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interrupts</em>' reference.
   * @see #getInterrupts()
   * @generated
   */

	void setInterrupts(InterruptibleActivityRegion value);





} // ActivityEdge
