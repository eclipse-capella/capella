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
package org.polarsys.capella.core.data.capellacommon;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getAvailableAbstractFunctions <em>Available Abstract Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getAvailableFunctionalChains <em>Available Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getAvailableAbstractCapabilities <em>Available Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getEntry <em>Entry</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getDoActivity <em>Do Activity</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getExit <em>Exit</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.State#getStateInvariant <em>State Invariant</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A state models a situation during which some (usually implicit) invariant condition holds. \r\n[source: UML superstructure v2.2]\r\n\r\nA condition of a system or element, as defined by some of its properties, which can enable system behaviors and/or structure to occur. Note: The enabled behavior may include no actions, such as associated with a wait state. Also, the condition that defines the state may be dependent on one or more previous states\r\n[source: UML for SE RFP]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='../img/usage_examples/example_statemachine.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::State' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface State extends AbstractState {





	/**
   * Returns the value of the '<em><b>Owned Regions</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.Region}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Regions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Regions</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_OwnedRegions()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The regions owned directly by the state.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::State::region' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Region> getOwnedRegions();







	/**
   * Returns the value of the '<em><b>Owned Connection Points</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.Pseudostate}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Connection Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Connection Points</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_OwnedConnectionPoints()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The entry and exit Pseudostates of a composite State. These can only be entry or exit Pseudostates, and they must have different names. They can only be defined for composite States.\r\n[source:UML v2.5]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::State::connectionPoint' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Pseudostate> getOwnedConnectionPoints();







	/**
   * Returns the value of the '<em><b>Available Abstract Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.AbstractFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Available Abstract Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Available Abstract Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_AvailableAbstractFunctions()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='availableInStates'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractFunction> getAvailableAbstractFunctions();







	/**
   * Returns the value of the '<em><b>Available Functional Chains</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChain}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Available Functional Chains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Available Functional Chains</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_AvailableFunctionalChains()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='availableInStates'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChain> getAvailableFunctionalChains();







	/**
   * Returns the value of the '<em><b>Available Abstract Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Available Abstract Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Available Abstract Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_AvailableAbstractCapabilities()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='availableInStates'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractCapability> getAvailableAbstractCapabilities();







	/**
   * Returns the value of the '<em><b>Entry</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.behavior.AbstractEvent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Entry</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_Entry()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An optional behavior that is executed whenever this state is entered regardless of the transition taken to reach the state. If\r\ndefined, entry actions are always executed to completion prior to any internal behavior or transitions performed within the\r\nstate.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::State::entry' explanation='none' constraints='Multiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractEvent> getEntry();




	/**
   * Returns the value of the '<em><b>Do Activity</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.behavior.AbstractEvent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Do Activity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Do Activity</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_DoActivity()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An optional behavior that is executed while being in the state. The execution starts when this state is entered, and stops\r\neither by itself or when the state is exited whichever comes first.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::State::doActivity' explanation='none' constraints='Multiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractEvent> getDoActivity();




	/**
   * Returns the value of the '<em><b>Exit</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.behavior.AbstractEvent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exit</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_Exit()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An optional behavior that is executed whenever this state is exited regardless of which transition was taken out of the\r\nstate. If defined, exit actions are always executed to completion only after all internal activities and transition actions have\r\ncompleted execution.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::State::exit' explanation='none' constraints='Multiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractEvent> getExit();




	/**
   * Returns the value of the '<em><b>State Invariant</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Invariant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>State Invariant</em>' containment reference.
   * @see #setStateInvariant(AbstractConstraint)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getState_StateInvariant()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies conditions that are always true when this state is the current state. In protocol state machines, state invariants are\r\nadditional conditions to the preconditions of the outgoing transitions, and to the postcondition of the incoming transitions.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::State::stateInvariant' explanation='none' constraints='none'"
   * @generated
   */

	AbstractConstraint getStateInvariant();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.State#getStateInvariant <em>State Invariant</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>State Invariant</em>' containment reference.
   * @see #getStateInvariant()
   * @generated
   */

	void setStateInvariant(AbstractConstraint value);





} // State
