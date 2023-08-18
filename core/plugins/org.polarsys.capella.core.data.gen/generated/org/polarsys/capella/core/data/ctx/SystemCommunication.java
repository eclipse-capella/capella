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
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.Relationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Communication</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemCommunication#getEnds <em>Ends</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemCommunication()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='SystemCommunication'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Association' stereotype='eng.SystemCommunication'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a communication relationship between the System (seen as a black box) and some external entities (typically Actors)\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Association' explanation='none' constraints='none'"
 * @generated
 */
public interface SystemCommunication extends Relationship {





	/**
   * Returns the value of the '<em><b>Ends</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemCommunicationHook}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ends</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Ends</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemCommunication_Ends()
   * @model containment="true" resolveProxies="true" lower="2" upper="2"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='navigableOwnedEnd' featureOwner='Association'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='system'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the endpoints of this relationship link (there can be an arbitrary number of them for a given link)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Association::ownedEnd' explanation='none' constraints='none'"
   * @generated
   */

	EList<SystemCommunicationHook> getEnds();





} // SystemCommunication
