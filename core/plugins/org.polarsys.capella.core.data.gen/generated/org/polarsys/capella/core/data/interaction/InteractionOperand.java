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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.Constraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.InteractionOperand#getReferencedInteractionFragments <em>Referenced Interaction Fragments</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.InteractionOperand#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionOperand()
 * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 * @generated
 */
public interface InteractionOperand extends InteractionFragment {





	/**
   * Returns the value of the '<em><b>Guard</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Guard</em>' reference.
   * @see #setGuard(Constraint)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionOperand_Guard()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getGuard();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.InteractionOperand#getGuard <em>Guard</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard</em>' reference.
   * @see #getGuard()
   * @generated
   */

	void setGuard(Constraint value);




	/**
   * Returns the value of the '<em><b>Referenced Interaction Fragments</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.InteractionFragment}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Interaction Fragments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Interaction Fragments</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionOperand_ReferencedInteractionFragments()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   */

	EList<InteractionFragment> getReferencedInteractionFragments();





} // InteractionOperand
