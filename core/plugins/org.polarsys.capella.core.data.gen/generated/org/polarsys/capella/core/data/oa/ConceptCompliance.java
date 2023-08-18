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

import org.polarsys.capella.core.data.capellacore.Relationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concept Compliance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.ConceptCompliance#getComplyWithConcept <em>Comply With Concept</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.ConceptCompliance#getCompliantCapability <em>Compliant Capability</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getConceptCompliance()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='compliance relationship between an operational capability and an operational concept\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface ConceptCompliance extends Relationship {





	/**
   * Returns the value of the '<em><b>Comply With Concept</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comply With Concept</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Comply With Concept</em>' reference.
   * @see #setComplyWithConcept(Concept)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getConceptCompliance_ComplyWithConcept()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Concept involved in this compliance relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	Concept getComplyWithConcept();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.ConceptCompliance#getComplyWithConcept <em>Comply With Concept</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comply With Concept</em>' reference.
   * @see #getComplyWithConcept()
   * @generated
   */

	void setComplyWithConcept(Concept value);







	/**
   * Returns the value of the '<em><b>Compliant Capability</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compliant Capability</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Compliant Capability</em>' reference.
   * @see #setCompliantCapability(OperationalCapability)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getConceptCompliance_CompliantCapability()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capability involved in this compliance relationship\r\n[source: Capella study]' constraints='none' comment/notes='n/a'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	OperationalCapability getCompliantCapability();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.ConceptCompliance#getCompliantCapability <em>Compliant Capability</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Compliant Capability</em>' reference.
   * @see #getCompliantCapability()
   * @generated
   */

	void setCompliantCapability(OperationalCapability value);





} // ConceptCompliance
