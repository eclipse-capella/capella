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
package org.polarsys.capella.core.data.capellacore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Involver Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.InvolverElement#getInvolvedInvolvements <em>Involved Involvements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getInvolverElement()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An involver element is a capella element that is, at least, involved in an involvement relationship with the role of the element that involves the other one\r\n[source:Meleody light-like study]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface InvolverElement extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Involved Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Involvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getInvolverElement_InvolvedInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractCapability.ownedAbstractFunctionAbstractCapabilityInvolvements(self, target);\r\n\t} or {\tAbstractCapability.ownedFunctionalChainAbstractCapabilityInvolvements(self, target);\r\n\t} or {\tCapability.ownedActorCapabilityInvolvements(self, target);\r\n\t} or {\tCapability.ownedSystemCapabilityInvolvement(self, target);\r\n\t} or {\tCapabilityRealization.ownedActorCapabilityRealizations(self, target);\r\n\t} or {\tCapabilityRealization.ownedSystemComponentCapabilityRealizations(self, target);\r\n\t} or {\tOperationalCapability.ownedEntityOperationalCapabilityInvolvements(self, target);\r\n\t} or {\tFunctionalChain.ownedFunctionalChainInvolvements(self, target);\r\n\t} or {\tMission.ownedActorMissionInvolvements(self, target);\r\n\t} or {\tMission.ownedSystemMissionInvolvement(self, target);\r\n\t} or {\tPhysicalPath.ownedPhysicalPathInvolvements(self, target);\r\n'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the set of involvement relationships for which the element is involved with the role of the element which is involved\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and Transient' constraints='none'"
   * @generated
   */

	EList<Involvement> getInvolvedInvolvements();





} // InvolverElement
