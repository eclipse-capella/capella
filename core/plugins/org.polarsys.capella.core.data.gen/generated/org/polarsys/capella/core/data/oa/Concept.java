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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.Concept#getCompliances <em>Compliances</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.Concept#getCompositeLinks <em>Composite Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getConcept()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Describes how a range of (future and where necessary extant) capabilities is used in an operational context to solve a particular problem or achieve an operational goal according to applicable doctrines.' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Class' explanation='none' constraints='none'"
 * @generated
 */
public interface Concept extends NamedElement {





	/**
   * Returns the value of the '<em><b>Compliances</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.ConceptCompliance}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compliances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Compliances</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getConcept_Compliances()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of Compliances that this operational concept follows\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Dependency::supplier' constraints='Order must be computed'"
   * @generated
   */

	EList<ConceptCompliance> getCompliances();







	/**
   * Returns the value of the '<em><b>Composite Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.ItemInConcept}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Composite Links</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getConcept_CompositeLinks()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='relationships with concept items\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement::clientDependency, keyword::nearestpackage' explanation='none' constraints='uml::NamedElement::clientDependency elements on which ItemInConcept stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ItemInConcept> getCompositeLinks();





} // Concept
