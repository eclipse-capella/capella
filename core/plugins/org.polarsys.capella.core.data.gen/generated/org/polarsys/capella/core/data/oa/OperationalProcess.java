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
package org.polarsys.capella.core.data.oa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.FunctionalChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operational Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalProcess#getInvolvingOperationalCapabilities <em>Involving Operational Capabilities</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalProcess()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An Operational Process is a logical organization of activities to fulfill an operational capability.' usage\040guideline='defining an Operational Process is similar to defining a functional chain at System Analysis level : it is composed of an ordered set of operational activities.\r\n[source: Capella study]' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Activity' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface OperationalProcess extends FunctionalChain {





	/**
   * Returns the value of the '<em><b>Involving Operational Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Operational Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Operational Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalProcess_InvolvingOperationalCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='OperationalProcess.involvingInvolvements(self, fcaci);\r\nFunctionalChainAbstractCapabilityInvolvement.capability(fcaci, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<OperationalCapability> getInvolvingOperationalCapabilities();





} // OperationalProcess
