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
 * A representation of the model object '<em><b>Organisational Unit Composition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getOrganisationalUnit <em>Organisational Unit</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getParticipatingEntity <em>Participating Entity</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getOrganisationalUnitComposition()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='mediator element to implement the relationship between an organisational unit and the entities it contains\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface OrganisationalUnitComposition extends NamedElement {





	/**
   * Returns the value of the '<em><b>Organisational Unit</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Organisational Unit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Organisational Unit</em>' reference.
   * @see #setOrganisationalUnit(OrganisationalUnit)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOrganisationalUnitComposition_OrganisationalUnit()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the organisational unit involved in the relationship implemented by this mediator element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	OrganisationalUnit getOrganisationalUnit();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getOrganisationalUnit <em>Organisational Unit</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Organisational Unit</em>' reference.
   * @see #getOrganisationalUnit()
   * @generated
   */

	void setOrganisationalUnit(OrganisationalUnit value);







	/**
   * Returns the value of the '<em><b>Participating Entity</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participating Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Participating Entity</em>' reference.
   * @see #setParticipatingEntity(Entity)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOrganisationalUnitComposition_ParticipatingEntity()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the operational entity involved in the relationship implemented by this mediator element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	Entity getParticipatingEntity();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getParticipatingEntity <em>Participating Entity</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Participating Entity</em>' reference.
   * @see #getParticipatingEntity()
   * @generated
   */

	void setParticipatingEntity(Entity value);





} // OrganisationalUnitComposition
