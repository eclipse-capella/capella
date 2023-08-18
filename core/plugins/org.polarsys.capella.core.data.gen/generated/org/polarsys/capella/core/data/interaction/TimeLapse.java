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
 * A representation of the model object '<em><b>Time Lapse</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.TimeLapse#getStart <em>Start</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.TimeLapse#getFinish <em>Finish</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getTimeLapse()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 * @generated
 */
public interface TimeLapse extends NamedElement {





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
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getTimeLapse_Start()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='start' featureOwner='ExecutionSpecification'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='start'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the starting point of this Execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExecutionSpecification::start' explanation='none' constraints='none'"
   * @generated
   */

	InteractionFragment getStart();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.TimeLapse#getStart <em>Start</em>}' reference.

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
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getTimeLapse_Finish()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='finish' featureOwner='ExecutionSpecification'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='finish'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the ending point of this Execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExecutionSpecification::finish' explanation='none' constraints='none'"
   * @generated
   */

	InteractionFragment getFinish();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.TimeLapse#getFinish <em>Finish</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Finish</em>' reference.
   * @see #getFinish()
   * @generated
   */

	void setFinish(InteractionFragment value);





} // TimeLapse
