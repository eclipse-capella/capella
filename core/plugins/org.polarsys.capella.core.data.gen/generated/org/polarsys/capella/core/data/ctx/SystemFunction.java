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
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunction#getOwnedSystemFunctionPkgs <em>Owned System Function Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatingSystemComponents <em>Allocating System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunction#getRealizedOperationalActivities <em>Realized Operational Activities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunction#getRealizingLogicalFunctions <em>Realizing Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunction#getContainedSystemFunctions <em>Contained System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunction#getChildrenSystemFunctions <em>Children System Functions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunction()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Function at System level\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='../img/usage_examples/example_systemfunction.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Activity' explanation='All functions are mapped to Activities. Parent activities refer to children activities via CallBehaviorActions' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface SystemFunction extends AbstractFunction {





	/**
   * Returns the value of the '<em><b>Owned System Function Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunctionPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Function Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned System Function Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunction_OwnedSystemFunctionPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub (function) package under this function' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='' constraints='uml::Package::nestedPackage elements on which SystemFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SystemFunctionPkg> getOwnedSystemFunctionPkgs();







	/**
   * Returns the value of the '<em><b>Allocating System Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating System Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating System Components</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunction_AllocatingSystemComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Components that allocate this System Function.' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='SystemFunction.incomingTraces(self, traces);\r\nComponentFunctionalAllocation.sourceElement(traces, target);'"
   * @generated
   */

	EList<SystemComponent> getAllocatingSystemComponents();







	/**
   * Returns the value of the '<em><b>Realized Operational Activities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalActivity}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getRealizingSystemFunctions <em>Realizing System Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Operational Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Operational Activities</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunction_RealizedOperationalActivities()
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getRealizingSystemFunctions
   * @model opposite="realizingSystemFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outFunctionRealizations.allocatedFunction'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<OperationalActivity> getRealizedOperationalActivities();







	/**
   * Returns the value of the '<em><b>Realizing Logical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalFunction#getRealizedSystemFunctions <em>Realized System Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Logical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Logical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunction_RealizingLogicalFunctions()
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getRealizedSystemFunctions
   * @model opposite="realizedSystemFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='inFunctionRealizations.allocatingFunction'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalFunction> getRealizingLogicalFunctions();







	/**
   * Returns the value of the '<em><b>Contained System Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained System Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained System Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunction_ContainedSystemFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SystemFunction> getContainedSystemFunctions();







	/**
   * Returns the value of the '<em><b>Children System Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children System Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Children System Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunction_ChildrenSystemFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='subFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of children system functions\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SystemFunction> getChildrenSystemFunctions();





} // SystemFunction
