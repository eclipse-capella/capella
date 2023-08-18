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
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.StateMachine#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.StateMachine#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateMachine()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='State machines can be used to express the behavior of part of a system. Behavior is modeled as a traversal of a graph of\r\nstate nodes interconnected by one or more joined transition arcs that are triggered by the dispatching of series of (event)\r\noccurrences. During this traversal, the state machine executes a series of activities associated with various elements of the\r\nstate machine.\r\n[source: UML superstructure v2.2]\r\n' usage\040guideline='a state machine is created (usually through the creation of a state or mode diagram, declaring states, modes, and transitions between them) as a support to specify the dynamic behavior of an entity' used\040in\040levels='operational, system, logical, physical' usage\040examples='../img/usage_examples/example_statemachine.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::StateMachine' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface StateMachine extends CapellaElement, AbstractBehavior {





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
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateMachine_OwnedRegions()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The regions owned directly by the state machine.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::StateMachine::region' explanation='none' constraints='Order must be computed'"
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
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateMachine_OwnedConnectionPoints()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The entry and exit Pseudostates of a composite State. These can only be entry or exit Pseudostates, and they must have different names. They can only be defined for composite States.\r\n[source:UML v2.5]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::StateMachine::connectionPoint' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Pseudostate> getOwnedConnectionPoints();





} // StateMachine
