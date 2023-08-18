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
 * A representation of the model object '<em><b>Activity Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.ActivityAllocation#getRole <em>Role</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.ActivityAllocation#getActivity <em>Activity</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getActivityAllocation()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allocation relationship between an operational role and an operational activity\r\n[source: Capella study]' usage\040guideline='In Capella, these allocations are created using the \"Operational Role Blank\" diagram' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Allocations::Allocate' explanation='none' constraints='none'"
 * @generated
 */
public interface ActivityAllocation extends Allocation {





	/**
   * Returns the value of the '<em><b>Role</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.Role#getActivityAllocations <em>Activity Allocations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Role</em>' reference.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getActivityAllocation_Role()
   * @see org.polarsys.capella.core.data.oa.Role#getActivityAllocations
   * @model opposite="activityAllocations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Operational role involved in this allocation relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Role getRole();







	/**
   * Returns the value of the '<em><b>Activity</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getActivityAllocations <em>Activity Allocations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Activity</em>' reference.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getActivityAllocation_Activity()
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getActivityAllocations
   * @model opposite="activityAllocations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Operational activity involved in this allocation relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	OperationalActivity getActivity();





} // ActivityAllocation
