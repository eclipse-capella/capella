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
package org.polarsys.capella.core.data.la;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalFunction#getOwnedLogicalFunctionPkgs <em>Owned Logical Function Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalFunction#getAllocatingLogicalComponents <em>Allocating Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalFunction#getRealizedSystemFunctions <em>Realized System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalFunction#getRealizingPhysicalFunctions <em>Realizing Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalFunction#getContainedLogicalFunctions <em>Contained Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalFunction#getChildrenLogicalFunctions <em>Children Logical Functions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalFunction()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Function at Logical level' usage\040guideline='n/a' used\040in\040levels='logical' usage\040examples='../img/usage_examples/example_logicalfunction.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Activity' explanation='All functions are mapped to (empty) activities' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface LogicalFunction extends AbstractFunction {





	/**
   * Returns the value of the '<em><b>Owned Logical Function Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalFunctionPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Function Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Logical Function Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalFunction_OwnedLogicalFunctionPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Set of subpackages that contain logical function elements' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which LogicalFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<LogicalFunctionPkg> getOwnedLogicalFunctionPkgs();







	/**
   * Returns the value of the '<em><b>Allocating Logical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalComponent}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalComponent#getAllocatedLogicalFunctions <em>Allocated Logical Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Logical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Logical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalFunction_AllocatingLogicalComponents()
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getAllocatedLogicalFunctions
   * @model opposite="allocatedLogicalFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Logical components that allocate this Logical Function' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='LogicalFunction.incomingTraces(self, traces);\r\nComponentFunctionalAllocation.sourceElement(traces, target);'"
   * @generated
   */

	EList<LogicalComponent> getAllocatingLogicalComponents();







	/**
   * Returns the value of the '<em><b>Realized System Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getRealizingLogicalFunctions <em>Realizing Logical Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized System Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized System Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalFunction_RealizedSystemFunctions()
   * @see org.polarsys.capella.core.data.ctx.SystemFunction#getRealizingLogicalFunctions
   * @model opposite="realizingLogicalFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outFunctionRealizations.allocatedFunction'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SystemFunction> getRealizedSystemFunctions();







	/**
   * Returns the value of the '<em><b>Realizing Physical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getRealizedLogicalFunctions <em>Realized Logical Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Physical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Physical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalFunction_RealizingPhysicalFunctions()
   * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getRealizedLogicalFunctions
   * @model opposite="realizedLogicalFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='inFunctionRealizations.allocatingFunction'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalFunction> getRealizingPhysicalFunctions();







	/**
   * Returns the value of the '<em><b>Contained Logical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Logical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Logical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalFunction_ContainedLogicalFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalFunction> getContainedLogicalFunctions();







	/**
   * Returns the value of the '<em><b>Children Logical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children Logical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Children Logical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalFunction_ChildrenLogicalFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='subFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of children logical functions\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalFunction> getChildrenLogicalFunctions();





} // LogicalFunction
