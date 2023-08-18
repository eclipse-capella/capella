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
package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Functional Chain Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer#getOwnedFunctionalChains <em>Owned Functional Chains</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalChainContainer()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Base class for possible containers of functional chains (may be both functional or use case containers)\r\n[source: MBSD unified approach]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
 * @generated
 */
public interface AbstractFunctionalChainContainer extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChain}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Chains</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Chains</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalChainContainer_OwnedFunctionalChains()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedFunctionalChains'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional chains associated to this function, e.g. functional chains that involve only sub-functions of this function\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='uml::Class::nestedClassifier elements on which FunctionalChain stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChain> getOwnedFunctionalChains();





} // AbstractFunctionalChainContainer
