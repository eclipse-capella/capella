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
package org.polarsys.capella.core.data.interaction;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.InteractionFragment#getCoveredInstanceRoles <em>Covered Instance Roles</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionFragment()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 * @generated
 */
public interface InteractionFragment extends NamedElement {





	/**
   * Returns the value of the '<em><b>Covered Instance Roles</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.InstanceRole}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Covered Instance Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Covered Instance Roles</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionFragment_CoveredInstanceRoles()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='covers'"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='covered' featureOwner='InteractionFragment'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the instance role that performs this Execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InteractionFragment::covered' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	EList<InstanceRole> getCoveredInstanceRoles();





} // InteractionFragment
