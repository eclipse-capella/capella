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
package org.polarsys.capella.core.data.oa;

import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Community Of Interest Composition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getCommunityOfInterest <em>Community Of Interest</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getInterestedOrganisationUnit <em>Interested Organisation Unit</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getCommunityOfInterestComposition()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Relationship between a community of interest and the organisational units\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface CommunityOfInterestComposition extends NamedElement {





	/**
   * Returns the value of the '<em><b>Community Of Interest</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Community Of Interest</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Community Of Interest</em>' reference.
   * @see #setCommunityOfInterest(CommunityOfInterest)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getCommunityOfInterestComposition_CommunityOfInterest()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The community of interest involved in the relationship implemented by this mediator element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	CommunityOfInterest getCommunityOfInterest();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getCommunityOfInterest <em>Community Of Interest</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Community Of Interest</em>' reference.
   * @see #getCommunityOfInterest()
   * @generated
   */

	void setCommunityOfInterest(CommunityOfInterest value);







	/**
   * Returns the value of the '<em><b>Interested Organisation Unit</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interested Organisation Unit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interested Organisation Unit</em>' reference.
   * @see #setInterestedOrganisationUnit(OrganisationalUnit)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getCommunityOfInterestComposition_InterestedOrganisationUnit()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The organisational unit involved in the relationship implemented by this mediator element.\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	OrganisationalUnit getInterestedOrganisationUnit();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getInterestedOrganisationUnit <em>Interested Organisation Unit</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interested Organisation Unit</em>' reference.
   * @see #getInterestedOrganisationUnit()
   * @generated
   */

	void setInterestedOrganisationUnit(OrganisationalUnit value);





} // CommunityOfInterestComposition
