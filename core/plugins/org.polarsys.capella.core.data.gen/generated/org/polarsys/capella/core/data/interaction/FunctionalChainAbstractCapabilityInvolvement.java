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

import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.fa.FunctionalChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Chain Abstract Capability Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement#getCapability <em>Capability</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement#getFunctionalChain <em>Functional Chain</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getFunctionalChainAbstractCapabilityInvolvement()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A functional chain can be involved in capability\r\n[source: MBSD unified approach]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface FunctionalChainAbstractCapabilityInvolvement extends Involvement {





	/**
   * Returns the value of the '<em><b>Capability</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capability</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Capability</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getFunctionalChainAbstractCapabilityInvolvement_Capability()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involver'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	AbstractCapability getCapability();







	/**
   * Returns the value of the '<em><b>Functional Chain</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functional Chain</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Functional Chain</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getFunctionalChainAbstractCapabilityInvolvement_FunctionalChain()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involved'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	FunctionalChain getFunctionalChain();





} // FunctionalChainAbstractCapabilityInvolvement
