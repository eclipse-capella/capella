/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.capellacommon;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Realization Involved Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement#getInvolvingCapabilityRealizationInvolvements <em>Involving Capability Realization Involvements</em>}</li>
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
	 * Returns the value of the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Capability Realization Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Involving Capability Realization Involvements</em>' reference list.
	 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getCapabilityRealizationInvolvedElement_InvolvingCapabilityRealizationInvolvements()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the capability realization involvement relationships in which this element is referenced\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<CapabilityRealizationInvolvement> getInvolvingCapabilityRealizationInvolvements();





} // CapabilityRealizationInvolvedElement
