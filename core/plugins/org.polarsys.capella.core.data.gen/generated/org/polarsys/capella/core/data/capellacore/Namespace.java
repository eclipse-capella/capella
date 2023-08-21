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
import org.polarsys.capella.core.data.capellacommon.GenericTrace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Namespace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Namespace#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Namespace#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Namespace#getNamingRules <em>Naming Rules</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamespace()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Namespace'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Namespace'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A namespace is an element in a model that contains a set of named elements that can be identified by name.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Namespace' constraints='none'"
 * @generated
 */
public interface Namespace extends NamedElement {





	/**
   * Returns the value of the '<em><b>Owned Traces</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Trace}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Traces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Traces</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamespace_OwnedTraces()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedTraces'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the trace link contained/stored in this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='Some packaged elements of the nearest package on which Trace stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<Trace> getOwnedTraces();







	/**
   * Returns the value of the '<em><b>Contained Generic Traces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.GenericTrace}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Generic Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Generic Traces</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamespace_ContainedGenericTraces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the set of typed elements which eAttribute type value is the owner type.\r\n[source:Capella study]' constraints='None' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedTraces'"
   * @generated
   */

	EList<GenericTrace> getContainedGenericTraces();







	/**
   * Returns the value of the '<em><b>Naming Rules</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.NamingRule}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Naming Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Naming Rules</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamespace_NamingRules()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedComment' featureOwner='Element'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='namingRules'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifications of constraints on the naming of the element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Element::ownedComment' explanation='none' constraints='uml::Element::ownedComment elements on which NamingRule stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<NamingRule> getNamingRules();





} // Namespace
