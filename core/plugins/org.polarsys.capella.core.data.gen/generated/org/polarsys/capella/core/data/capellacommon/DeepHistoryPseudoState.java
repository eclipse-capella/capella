/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.capellacommon;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deep History Pseudo State</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getDeepHistoryPseudoState()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Deep history represents the most recent active configuration of the composite state that directly contains this\r\npseudostate (e.g., the state configuration that was active when the composite state was last exited). A composite state\r\ncan have at most one deep history vertex. At most one transition may originate from the history connector to the default\r\ndeep history state. This transition is taken in case the composite state had never been active before. Entry actions of\r\nstates entered on the implicit direct path from the deep history to the innermost state(s) represented by a deep history\r\nare performed. The entry action is preformed only once for each state in the active state configuration being restored.\r\n[source: UML superstructure v2.4]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Pseudostate' explanation='none' constraints='uml::Pseudostate elements for which kind is deepHistory'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface DeepHistoryPseudoState extends Pseudostate {



} // DeepHistoryPseudoState
