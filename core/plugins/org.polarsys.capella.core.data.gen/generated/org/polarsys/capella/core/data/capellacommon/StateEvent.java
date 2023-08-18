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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.NamedElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.StateEvent#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.StateEvent#getOwnedStateEventRealizations <em>Owned State Event Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateEvent()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An event used in statemachine definition which occurs at a given condition. \r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='../img/usage_examples/example_statemachine.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='none' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface StateEvent extends NamedElement, AbstractEvent {





	/**
   * Returns the value of the '<em><b>Expression</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' reference.
   * @see #setExpression(Constraint)
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateEvent_Expression()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ChangeEvent::changeExpression if current element is a ChangeEvent\r\numl::TimeEvent::when if current element is a TimeEvent\r\n' base\040metaclass\040in\040UML/SysML\040profile\040='uml::ChangeEvent and uml::TimeEvent\r\n' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getExpression();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacommon.StateEvent#getExpression <em>Expression</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' reference.
   * @see #getExpression()
   * @generated
   */

	void setExpression(Constraint value);




	/**
   * Returns the value of the '<em><b>Owned State Event Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateEventRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned State Event Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned State Event Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getStateEvent_OwnedStateEventRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the realization links that are owned/contained in this StateEvent\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which StateEventRealization stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<StateEventRealization> getOwnedStateEventRealizations();





} // StateEvent
