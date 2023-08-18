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
package org.polarsys.capella.core.data.la;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Realization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getOwnedCapabilityRealizationInvolvements <em>Owned Capability Realization Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getInvolvedComponents <em>Involved Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities <em>Realized Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations <em>Realized Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='CapabilityRealization'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.CapabilityRealization'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a realization of a capability of the above abstraction level\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='logical' usage\040examples='../img/usage_examples/example_capabilityrealizationlogical.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::UseCase' explanation='needs to be mapped to UseCase since its parent is mapped to UseCase...and has many references mapped to UseCase\'s references' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CapabilityRealization extends AbstractCapability {





	/**
   * Returns the value of the '<em><b>Owned Capability Realization Involvements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Capability Realization Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Capability Realization Involvements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_OwnedCapabilityRealizationInvolvements()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the capability realization links that are contained in this CapabilityRealization\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   * @generated
   */

	EList<CapabilityRealizationInvolvement> getOwnedCapabilityRealizationInvolvements();







	/**
   * Returns the value of the '<em><b>Involved Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Components</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_InvolvedComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Components that are involved in this Capability Realization' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CapabilityRealization.ownedCapabilityRealizationInvolvements(self, involvements);\r\nCapabilityRealizationInvolvement.involvedCapabilityRealizationInvolvedElement(involvements, target);'"
   * @generated
   */

	EList<CapabilityRealizationInvolvedElement> getInvolvedComponents();







	/**
   * Returns the value of the '<em><b>Realized Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Capability}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_RealizedCapabilities()
   * @see org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations
   * @model opposite="realizingCapabilityRealizations" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CapabilityRealization.outgoingTraces(self, acr);\r\nAbstractCapabilityRealization.realizedCapability(acr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Capability> getRealizedCapabilities();







	/**
   * Returns the value of the '<em><b>Realized Capability Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Capability Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_RealizedCapabilityRealizations()
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations
   * @model opposite="realizingCapabilityRealizations" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CapabilityRealization.outgoingTraces(self, acr);\r\nAbstractCapabilityRealization.realizedCapability(acr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<CapabilityRealization> getRealizedCapabilityRealizations();







	/**
   * Returns the value of the '<em><b>Realizing Capability Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations <em>Realized Capability Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Capability Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_RealizingCapabilityRealizations()
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations
   * @model opposite="realizedCapabilityRealizations" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CapabilityRealization.incomingTraces(self, acr);\r\nAbstractCapabilityRealization.realizingCapability(acr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<CapabilityRealization> getRealizingCapabilityRealizations();





} // CapabilityRealization
