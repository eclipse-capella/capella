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
 * A representation of the model object '<em><b>Operational Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalScenario#getContext <em>Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalScenario#getObjective <em>Objective</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalScenario()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Definition of a dynamic behaviour composed of the following information:\r\nContext, objective, pre-conditions, post-conditions, used capabilities, involved roles &amp; actors, operational exchanges &amp; interactions, processes and activities. Ability to be validated. Temporal &amp; performance description.Criticity.\r\nScenarios can be gathered in a set of Use Cases.' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::UseCase' constraints='none'"
 * @generated
 */
public interface OperationalScenario extends NamedElement {





	/**
   * Returns the value of the '<em><b>Context</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Context</em>' attribute.
   * @see #setContext(String)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalScenario_Context()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='description of the context in which this operational scenario takes place\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getContext();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.OperationalScenario#getContext <em>Context</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context</em>' attribute.
   * @see #getContext()
   * @generated
   */

	void setContext(String value);







	/**
   * Returns the value of the '<em><b>Objective</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objective</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Objective</em>' attribute.
   * @see #setObjective(String)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalScenario_Objective()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='description of the objective/output of this operational scenario\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getObjective();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.OperationalScenario#getObjective <em>Objective</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Objective</em>' attribute.
   * @see #getObjective()
   * @generated
   */

	void setObjective(String value);





} // OperationalScenario
