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
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exchange</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityExchange#getRealizingActivityFlows <em>Realizing Activity Flows</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityExchange()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Interactions between Ports to support Functions, (e.g. Exchanges can be composed of system data, events, analogic signals...).\r\nExchanges are a refinement of the interfaces requirements identified through behavioral modeling, and expressed through Functions.\r\nHence any Function may identify a series of  Exchanges to fully transfer the required element ' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface ActivityExchange extends AbstractInformationFlow {





	/**
   * Returns the value of the '<em><b>Realizing Activity Flows</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityEdge}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Activity Flows</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Activity Flows</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityExchange_RealizingActivityFlows()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='realizations'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Determines which ActivityEdges will realize the specified flow.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ActivityEdge> getRealizingActivityFlows();





} // ActivityExchange
