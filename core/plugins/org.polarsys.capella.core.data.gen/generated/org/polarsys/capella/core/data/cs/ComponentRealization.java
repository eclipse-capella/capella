/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.cs;

import org.polarsys.capella.core.data.capellacore.Allocation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Realization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.ComponentRealization#getRealizedComponent <em>Realized Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.ComponentRealization#getRealizingComponent <em>Realizing Component</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponentRealization()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Mediator class between Component elements, representing the realization link between these elements\r\n[source: Capella light-light study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 * @generated
 */

public interface ComponentRealization extends Allocation {





	/**
   * Returns the value of the '<em><b>Realized Component</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Component</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponentRealization_RealizedComponent()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the allocated component\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Component getRealizedComponent();







	/**
   * Returns the value of the '<em><b>Realizing Component</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Component</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponentRealization_RealizingComponent()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the allocating component\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Component getRealizingComponent();





} // ComponentRealization
