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
 * A representation of the model object '<em><b>Role Assembly Usage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.RoleAssemblyUsage#getChild <em>Child</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getRoleAssemblyUsage()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='mediator class supporting the relationship between two roles having a hierarchical dependence\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Usage' explanation='none' constraints='none'"
 * @generated
 */
public interface RoleAssemblyUsage extends NamedElement {





	/**
   * Returns the value of the '<em><b>Child</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Child</em>' reference.
   * @see #setChild(Role)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRoleAssemblyUsage_Child()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='child Role involved in this relationship mediator element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	Role getChild();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.RoleAssemblyUsage#getChild <em>Child</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Child</em>' reference.
   * @see #getChild()
   * @generated
   */

	void setChild(Role value);





} // RoleAssemblyUsage
