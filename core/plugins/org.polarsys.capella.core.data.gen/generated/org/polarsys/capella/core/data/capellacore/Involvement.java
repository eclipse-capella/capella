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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Involvement#getInvolver <em>Involver</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Involvement#getInvolved <em>Involved</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getInvolvement()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link that denotes some involvement relationship of an element that is involved in another one\r\n[Capella study]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Dependency' constraints='none'"
 * @generated
 */
public interface Involvement extends Relationship {





	/**
   * Returns the value of the '<em><b>Involver</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involver</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involver</em>' reference.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getInvolvement_Involver()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='involvedInvolvements'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the element that involves\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	InvolverElement getInvolver();




	/**
   * Returns the value of the '<em><b>Involved</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved</em>' reference.
   * @see #setInvolved(InvolvedElement)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getInvolvement_Involved()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the element that is involved\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	InvolvedElement getInvolved();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.Involvement#getInvolved <em>Involved</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Involved</em>' reference.
   * @see #getInvolved()
   * @generated
   */

	void setInvolved(InvolvedElement value);





} // Involvement
