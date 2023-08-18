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
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getOwnedCapabilityInvolvements <em>Owned Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystemComponents <em>Involved System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getPurposes <em>Purposes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getPurposeMissions <em>Purpose Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getRealizedOperationalCapabilities <em>Realized Operational Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Capability'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.Capability'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Ability of an organisation, system or process to provide a service that supports the achievement of high-level operational goals' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='../img/usage_examples/example_actor_capability.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::UseCase' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Capability extends AbstractCapability {





	/**
   * Returns the value of the '<em><b>Owned Capability Involvements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Capability Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Capability Involvements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_OwnedCapabilityInvolvements()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Capability Involvements owned by this Capability' constraints='none' comment/notes='none'"
   * @generated
   */

	EList<CapabilityInvolvement> getOwnedCapabilityInvolvements();







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
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_InvolvedSystemComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='System Components that are involved in this Capability' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Capability.ownedCapabilityInvolvements(self, involvements);\r\nCapabilityInvolvement.systemComponent(involvements, target);'"
   * @generated
   */

	EList<SystemComponent> getInvolvedSystemComponents();







	/**
   * Returns the value of the '<em><b>Purposes</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.CapabilityExploitation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Purposes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Purposes</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_Purposes()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='addition' umlOppositeReferenceOwner='Include'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='purposes'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the links to the Mission(s) that this Capability supports\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Include::addition' constraints='uml::NamedElement::clientDependency elements on which CapabilityExploitation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='capability'"
   * @generated
   */

	EList<CapabilityExploitation> getPurposes();







	/**
   * Returns the value of the '<em><b>Purpose Missions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Mission}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Mission#getExploitedCapabilities <em>Exploited Capabilities</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Purpose Missions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Purpose Missions</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_PurposeMissions()
   * @see org.polarsys.capella.core.data.ctx.Mission#getExploitedCapabilities
   * @model opposite="exploitedCapabilities" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='purposeMissions'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='purposes.mission'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the Mission(s) that this Capability supports\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<Mission> getPurposeMissions();







	/**
   * Returns the value of the '<em><b>Realized Operational Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalCapability}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.OperationalCapability#getRealizingCapabilities <em>Realizing Capabilities</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Operational Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Operational Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_RealizedOperationalCapabilities()
   * @see org.polarsys.capella.core.data.oa.OperationalCapability#getRealizingCapabilities
   * @model opposite="realizingCapabilities" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractCapabilityRealization.sourceElement(acr, self);\r\nAbstractCapabilityRealization.realizedCapability(acr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<OperationalCapability> getRealizedOperationalCapabilities();







	/**
   * Returns the value of the '<em><b>Realizing Capability Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities <em>Realized Capabilities</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Capability Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_RealizingCapabilityRealizations()
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities
   * @model opposite="realizedCapabilities" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='AbstractCapabilityRealization.targetElement(acr, self);\r\nAbstractCapabilityRealization.realizingCapability(acr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<CapabilityRealization> getRealizingCapabilityRealizations();





} // Capability
