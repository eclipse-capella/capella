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
package org.polarsys.capella.core.data.capellacommon;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.la.CapabilityRealization;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Realization Involved Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement#getCapabilityRealizationInvolvements <em>Capability Realization Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getCapabilityRealizationInvolvedElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a model element involved in the realization of a Capability\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface CapabilityRealizationInvolvedElement extends InvolvedElement {





	/**
   * Returns the value of the '<em><b>Capability Realization Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capability Realization Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Capability Realization Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getCapabilityRealizationInvolvedElement_CapabilityRealizationInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the capability realization involvement relationships in which this element is referenced\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involvingInvolvements'"
   * @generated
   */

	EList<CapabilityRealizationInvolvement> getCapabilityRealizationInvolvements();

	/**
   * Returns the value of the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Capability Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getCapabilityRealizationInvolvedElement_InvolvingCapabilityRealizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Capability realizations that involve this element' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CapabilityRealizationInvolvedElement.capabilityRealizationInvolvements(self, involvements);\r\nCapabilityRealizationInvolvement.involver(involvements, target);'"
   * @generated
   */

	EList<CapabilityRealization> getInvolvingCapabilityRealizations();





} // CapabilityRealizationInvolvedElement
