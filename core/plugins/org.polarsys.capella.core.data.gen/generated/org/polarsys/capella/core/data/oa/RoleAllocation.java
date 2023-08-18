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

import org.polarsys.capella.core.data.capellacore.Allocation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.RoleAllocation#getRole <em>Role</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.RoleAllocation#getEntity <em>Entity</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getRoleAllocation()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Allocation link between an operational role and an operational entity\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Allocations::Allocate' explanation='none' constraints='none'"
 * @generated
 */
public interface RoleAllocation extends Allocation {





	/**
   * Returns the value of the '<em><b>Role</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.Role#getRoleAllocations <em>Role Allocations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Role</em>' reference.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRoleAllocation_Role()
   * @see org.polarsys.capella.core.data.oa.Role#getRoleAllocations
   * @model opposite="roleAllocations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the operational role involved in this allocation link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Role getRole();







	/**
   * Returns the value of the '<em><b>Entity</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.Entity#getRoleAllocations <em>Role Allocations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Entity</em>' reference.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRoleAllocation_Entity()
   * @see org.polarsys.capella.core.data.oa.Entity#getRoleAllocations
   * @model opposite="roleAllocations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the operational entity involved in this allocation link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Entity getEntity();





} // RoleAllocation
