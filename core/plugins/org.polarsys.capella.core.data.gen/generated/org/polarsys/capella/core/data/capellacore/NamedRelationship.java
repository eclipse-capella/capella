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
 * A representation of the model object '<em><b>Named Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.NamedRelationship#getNamingRules <em>Naming Rules</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamedRelationship()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='NamedRelationship'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Relationship'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A named relationship is a relationship that has a name\r\n[source:Capella study]\r\n\r\nA named relationship can be compared to an UML Association :\r\nAn association specifies a semantic relationship that can occur between typed instances. It has at least two ends\r\nrepresented by properties, each of which is connected to the type of the end. More than one end of the association may\r\nhave the same type.\r\nAn end property of an association that is owned by an end class or that is a navigable owned end of the association\r\nindicates that the association is navigable from the opposite ends; otherwise, the association is not navigable from the\r\nopposite ends.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Relationship' constraints='none'"
 * @generated
 */
public interface NamedRelationship extends Relationship, NamedElement {





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
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamedRelationship_NamingRules()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedComment' featureOwner='Element'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='namingRules'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifications of constraints applying to the naming of the relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Element::ownedComment' explanation='none' constraints='uml::Element::ownedComment elements on which NamingRule stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<NamingRule> getNamingRules();





} // NamedRelationship
