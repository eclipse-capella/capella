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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Initial Pseudo State</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getInitialPseudoState()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An initial pseudostate represents a default vertex that is the source for a single transition to the default state of a composite state. There can be at most one initial vertex in a region. The outgoing transition from the initial vertex may\r\nhave a behavior, but not a trigger or guard.\r\n[source: UML superstructure v2.2]' usage\040guideline='this pseudo state should be used to declare the entry point of the state machine' used\040in\040levels='operational, system, logical, physical' usage\040examples='../img/usage_examples/example_statemachine.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Pseudostate' explanation='none' constraints='uml::Pseudostate elements for which kind is initial'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface InitialPseudoState extends Pseudostate {



} // InitialPseudoState
