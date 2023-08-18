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

import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Duration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getStart <em>Start</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getFinish <em>Finish</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getConstraintDuration()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ConstraintDuration extends NamedElement {





	/**
   * Returns the value of the '<em><b>Duration</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Duration</em>' attribute.
   * @see #setDuration(String)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getConstraintDuration_Duration()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getDuration();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getDuration <em>Duration</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Duration</em>' attribute.
   * @see #getDuration()
   * @generated
   */

	void setDuration(String value);







	/**
   * Returns the value of the '<em><b>Start</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Start</em>' reference.
   * @see #setStart(InteractionFragment)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getConstraintDuration_Start()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' constraints='none' comment/notes='none'"
   * @generated
   */

	InteractionFragment getStart();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getStart <em>Start</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start</em>' reference.
   * @see #getStart()
   * @generated
   */

	void setStart(InteractionFragment value);







	/**
   * Returns the value of the '<em><b>Finish</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Finish</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Finish</em>' reference.
   * @see #setFinish(InteractionFragment)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getConstraintDuration_Finish()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' constraints='none' comment/notes='none'"
   * @generated
   */

	InteractionFragment getFinish();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getFinish <em>Finish</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Finish</em>' reference.
   * @see #getFinish()
   * @generated
   */

	void setFinish(InteractionFragment value);





} // ConstraintDuration
