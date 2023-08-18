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
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operational Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedOperationalActivityPkgs <em>Owned Operational Activity Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getActivityAllocations <em>Activity Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedSwimlanes <em>Owned Swimlanes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedProcess <em>Owned Process</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getAllocatorEntities <em>Allocator Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getRealizingSystemFunctions <em>Realizing System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getAllocatingRoles <em>Allocating Roles</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getContainedOperationalActivities <em>Contained Operational Activities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActivity#getChildrenOperationalActivities <em>Children Operational Activities</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Any process step or function performed, both mental and physical, toward achieving some objective. A task is a \"formal\" activity (see also task).\r\n[source: Sys EM, EIA/IS-731.1]' usage\040guideline='In the first steps of the operational analysis, all activities related to the targeted domain should be identified, regardless of their future allocation to the targeted system or not (e.g. even activities of actors external to the future system being design, should be identified and modelled)\r\n' arcadia_description='An operational Activity is a process step or function performed toward achieving some objective, by actors that could necessitate to use the system for this. Example: listen to radio, select a radio station...' used\040in\040levels='operational' usage\040examples='../img/usage_examples/example_operational_activities.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Activity' explanation='All functions are mapped to (empty) activities' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface OperationalActivity extends AbstractFunction {





	/**
   * Returns the value of the '<em><b>Owned Operational Activity Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalActivityPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Operational Activity Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Operational Activity Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_OwnedOperationalActivityPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-packages of operational activities, contained in this operational activity' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which OperationalActivityPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<OperationalActivityPkg> getOwnedOperationalActivityPkgs();







	/**
   * Returns the value of the '<em><b>Activity Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.ActivityAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.ActivityAllocation#getActivity <em>Activity</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Activity Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_ActivityAllocations()
   * @see org.polarsys.capella.core.data.oa.ActivityAllocation#getActivity
   * @model opposite="activity" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allocation of this operational activity to a given operational role\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ActivityAllocation> getActivityAllocations();







	/**
   * Returns the value of the '<em><b>Owned Swimlanes</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.Swimlane}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Swimlanes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Swimlanes</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_OwnedSwimlanes()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of swimlanes used to partition this operational activity\r\n[source: Capella study]' constraints='none' comment/notes='not used/implemented as of Capella'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='unimplemented' viatra.expression='Nothing in helpers ?'"
   * @generated
   */

	EList<Swimlane> getOwnedSwimlanes();







	/**
   * Returns the value of the '<em><b>Owned Process</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalProcess}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Process</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Process</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_OwnedProcess()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of Processes associated to this Operational Activity\r\n[source: Capella study]' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctionalChains'"
   * @generated
   */

	EList<OperationalProcess> getOwnedProcess();







	/**
   * Returns the value of the '<em><b>Allocator Entities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.Entity}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.Entity#getAllocatedOperationalActivities <em>Allocated Operational Activities</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocator Entities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocator Entities</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_AllocatorEntities()
   * @see org.polarsys.capella.core.data.oa.Entity#getAllocatedOperationalActivities
   * @model opposite="allocatedOperationalActivities" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='OperationalActivity.incomingTraces(self, cfa);\r\nComponentFunctionalAllocation.sourceElement(cfa, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Entity> getAllocatorEntities();







	/**
   * Returns the value of the '<em><b>Realizing System Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getRealizedOperationalActivities <em>Realized Operational Activities</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing System Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing System Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_RealizingSystemFunctions()
   * @see org.polarsys.capella.core.data.ctx.SystemFunction#getRealizedOperationalActivities
   * @model opposite="realizedOperationalActivities" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='inFunctionRealizations.allocatingFunction'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SystemFunction> getRealizingSystemFunctions();







	/**
   * Returns the value of the '<em><b>Allocating Roles</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.Role}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Roles</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_AllocatingRoles()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='activityAllocations.role'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Role> getAllocatingRoles();







	/**
   * Returns the value of the '<em><b>Contained Operational Activities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalActivity}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Operational Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Operational Activities</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_ContainedOperationalActivities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<OperationalActivity> getContainedOperationalActivities();







	/**
   * Returns the value of the '<em><b>Children Operational Activities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalActivity}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children Operational Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Children Operational Activities</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActivity_ChildrenOperationalActivities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='subFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of children operational activities\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<OperationalActivity> getChildrenOperationalActivities();





} // OperationalActivity
