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
 * A representation of the model object '<em><b>Involved Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.InvolvedElement#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getInvolvedElement()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An involved element is a capella element that is, at least, involved in an involvement relationship with the role of the element that is involved\r\n[source:Meleody light-like study]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface InvolvedElement extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Involving Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Involvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getInvolvedElement_InvolvingInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='involved'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the set of involvement relationships for which the element is involved with the role of the element which involves another one\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and Transient' constraints='none'"
   * @generated
   */

	EList<Involvement> getInvolvingInvolvements();





} // InvolvedElement
