/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.capellacommon;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Choice Pseudo State</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getChoicePseudoState()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='choice vertices which, when reached, result in the dynamic evaluation of the guards of the triggers of its outgoing\r\ntransitions. This realizes a dynamic conditional branch. It allows splitting of transitions into multiple outgoing paths\r\nsuch that the decision on which path to take may be a function of the results of prior actions performed in the same runto-\r\ncompletion step\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='../img/usage_examples/example_choicepseudostate.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Pseudostate' explanation='none' constraints='uml::Pseudostate elements for which kind is choice'"
 * @generated
 */
public interface ChoicePseudoState extends Pseudostate {



} // ChoicePseudoState
