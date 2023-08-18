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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.information.AbstractInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.Role#getOwnedRoleAssemblyUsages <em>Owned Role Assembly Usages</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.Role#getOwnedActivityAllocations <em>Owned Activity Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.Role#getRoleAllocations <em>Role Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.Role#getActivityAllocations <em>Activity Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.Role#getAllocatingEntities <em>Allocating Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.Role#getAllocatedOperationalActivities <em>Allocated Operational Activities</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getRole()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Role is a set of activities allocated to an actor or a system against another actor or system.' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML ::Blocks ::Block' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Role extends AbstractInstance {





	/**
   * Returns the value of the '<em><b>Owned Role Assembly Usages</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.RoleAssemblyUsage}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Role Assembly Usages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Role Assembly Usages</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRole_OwnedRoleAssemblyUsages()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of mediator elements establishing links between this role and parent/children roles\r\n[source: Capella study]' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Dependency::supplier' constraints='Order must be computed'"
   * @generated
   */

	EList<RoleAssemblyUsage> getOwnedRoleAssemblyUsages();







	/**
   * Returns the value of the '<em><b>Owned Activity Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.ActivityAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Activity Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Activity Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRole_OwnedActivityAllocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of allocations between roles and operational activities, that are stored/owned by this role\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which ActivityAllocation stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<ActivityAllocation> getOwnedActivityAllocations();







	/**
   * Returns the value of the '<em><b>Role Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.RoleAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.RoleAllocation#getRole <em>Role</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Role Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRole_RoleAllocations()
   * @see org.polarsys.capella.core.data.oa.RoleAllocation#getRole
   * @model opposite="role" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of allocations between this operational role, and operational entities\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<RoleAllocation> getRoleAllocations();







	/**
   * Returns the value of the '<em><b>Activity Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.ActivityAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.ActivityAllocation#getRole <em>Role</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Activity Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRole_ActivityAllocations()
   * @see org.polarsys.capella.core.data.oa.ActivityAllocation#getRole
   * @model opposite="role" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of allocations of this role to/from operation activities\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ActivityAllocation> getActivityAllocations();







	/**
   * Returns the value of the '<em><b>Allocating Entities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.Entity}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Entities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Entities</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRole_AllocatingEntities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='roleAllocations.entity'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Entity> getAllocatingEntities();







	/**
   * Returns the value of the '<em><b>Allocated Operational Activities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalActivity}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Operational Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Operational Activities</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getRole_AllocatedOperationalActivities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='activityAllocations.activity'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<OperationalActivity> getAllocatedOperationalActivities();





} // Role
