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

import org.polarsys.capella.core.data.capellacore.Allocation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Transition Realization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransitionRealization#getRealizedStateTransition <em>Realized State Transition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.StateTransitionRealization#getRealizingStateTransition <em>Realizing State Transition</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransitionRealization()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a StateTransitionRealization is a specific kind of allocation link between two StateTransitions (typically of different design levels, or of different nature)\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='none' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Realization' explanation='none' constraints='none'"
 * @generated
 */
public interface StateTransitionRealization extends Allocation {





	/**
   * Returns the value of the '<em><b>Realized State Transition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized State Transition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized State Transition</em>' reference.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransitionRealization_RealizedStateTransition()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='destination of the realization link : the state transition that is being realized\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	StateTransition getRealizedStateTransition();







	/**
   * Returns the value of the '<em><b>Realizing State Transition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing State Transition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing State Transition</em>' reference.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateTransitionRealization_RealizingStateTransition()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the source of the realization link : the state transition that is realizing another state transition\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	StateTransition getRealizingStateTransition();





} // StateTransitionRealization
