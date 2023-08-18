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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interruptible Activity Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.InterruptibleActivityRegion#getInterruptingEdges <em>Interrupting Edges</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getInterruptibleActivityRegion()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An interruptible region contains activity nodes. When a token leaves an interruptible region via edges designated by the\r\nregion as interrupting edges, all tokens and behaviors in the region are terminated\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented in Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::InterruptibleActivityRegion' constraints='none'"
 * @generated
 */
public interface InterruptibleActivityRegion extends ActivityGroup {





	/**
   * Returns the value of the '<em><b>Interrupting Edges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityEdge}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.activity.ActivityEdge#getInterrupts <em>Interrupts</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interrupting Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interrupting Edges</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getInterruptibleActivityRegion_InterruptingEdges()
   * @see org.polarsys.capella.common.data.activity.ActivityEdge#getInterrupts
   * @model opposite="interrupts"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The edges leaving the region that will abort other tokens flowing in the region\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InterruptibleActivityRegion::interruptingEdge' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ActivityEdge> getInterruptingEdges();





} // InterruptibleActivityRegion
