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
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Mission#getOwnedMissionInvolvements <em>Owned Mission Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Mission#getInvolvedSystemComponents <em>Involved System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Mission#getOwnedCapabilityExploitations <em>Owned Capability Exploitations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Mission#getExploitedCapabilities <em>Exploited Capabilities</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getMission()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Mission'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='UseCase' stereotype='eng.Mission'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Operational goal. It must be satisfied by usage of System capabilities.\r\n\r\nA mission can be compared to a UML UseCase : A use case is the specification of a set of actions performed by a system, which yields an observable result that is,\r\ntypically, of value for one or more actors or other stakeholders of the system.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='../img/usage_examples/example_mission.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::UseCase' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Mission extends NamedElement, InvolverElement {





	/**
   * Returns the value of the '<em><b>Owned Mission Involvements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.MissionInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Mission Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Mission Involvements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getMission_OwnedMissionInvolvements()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the links between Mission Involvement links that are owned by this Mission' constraints='none' comment/notes='none'"
   * @generated
   */

	EList<MissionInvolvement> getOwnedMissionInvolvements();







	/**
   * Returns the value of the '<em><b>Involved System Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved System Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved System Components</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getMission_InvolvedSystemComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='System Components that are involved in this Mission' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Mission.ownedMissionInvolvements(self, missionInvolvements);\r\nMissionInvolvement.systemComponent(missionInvolvements, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SystemComponent> getInvolvedSystemComponents();







	/**
   * Returns the value of the '<em><b>Owned Capability Exploitations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.CapabilityExploitation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Capability Exploitations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Capability Exploitations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getMission_OwnedCapabilityExploitations()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='include' featureOwner='UseCase'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='capabilityExploitations'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the capability exploitation links that are assigned to this Mission\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::UseCase::include' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<CapabilityExploitation> getOwnedCapabilityExploitations();







	/**
   * Returns the value of the '<em><b>Exploited Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Capability}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Capability#getPurposeMissions <em>Purpose Missions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exploited Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exploited Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getMission_ExploitedCapabilities()
   * @see org.polarsys.capella.core.data.ctx.Capability#getPurposeMissions
   * @model opposite="purposeMissions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='exploitedCapabilityUseCases'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedCapabilityExploitations.capability'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of Capabilities that this Mission exploits\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Capability> getExploitedCapabilities();





} // Mission
