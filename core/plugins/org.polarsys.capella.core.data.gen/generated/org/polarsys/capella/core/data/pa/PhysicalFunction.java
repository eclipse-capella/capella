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
package org.polarsys.capella.core.data.pa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.la.LogicalFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getOwnedPhysicalFunctionPkgs <em>Owned Physical Function Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatingPhysicalComponents <em>Allocating Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getRealizedLogicalFunctions <em>Realized Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getContainedPhysicalFunctions <em>Contained Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getChildrenPhysicalFunctions <em>Children Physical Functions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunction()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Function applied at physical level\r\n[source: Capella study]' usage\040guideline='this element is used in the \"functional approach\" usage, as the result of the flow down/refinement of the functions at the logical architecture level\r\n[source: Capella study]' used\040in\040levels='physical' usage\040examples='../img/usage_examples/example_physical_functions.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Activity' explanation='All functions are mapped to (empty) activities' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalFunction extends AbstractFunction {





	/**
   * Returns the value of the '<em><b>Owned Physical Function Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunctionPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Function Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Function Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunction_OwnedPhysicalFunctionPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the sub-(physical function) packages contained in this physical function' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which PhysicalFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalFunctionPkg> getOwnedPhysicalFunctionPkgs();







	/**
   * Returns the value of the '<em><b>Allocating Physical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponent}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Physical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Physical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunction_AllocatingPhysicalComponents()
   * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getAllocatedPhysicalFunctions
   * @model opposite="allocatedPhysicalFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalFunction.incomingTraces(self, incomingTraces);\r\nComponentFunctionalAllocation.sourceElement(incomingTraces, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalComponent> getAllocatingPhysicalComponents();







	/**
   * Returns the value of the '<em><b>Realized Logical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalFunction#getRealizingPhysicalFunctions <em>Realizing Physical Functions</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Logical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Logical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunction_RealizedLogicalFunctions()
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getRealizingPhysicalFunctions
   * @model opposite="realizingPhysicalFunctions" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outFunctionRealizations.allocatedFunction'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalFunction> getRealizedLogicalFunctions();







	/**
   * Returns the value of the '<em><b>Contained Physical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Physical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Physical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunction_ContainedPhysicalFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFunctions'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalFunction> getContainedPhysicalFunctions();







	/**
   * Returns the value of the '<em><b>Children Physical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children Physical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Children Physical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunction_ChildrenPhysicalFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='subFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of children physical functions\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalFunction> getChildrenPhysicalFunctions();





} // PhysicalFunction
