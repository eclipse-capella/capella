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
package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.information.Port;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort <em>Represented Component Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPort#getAllocatorComponentPorts <em>Allocator Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPort#getRealizedFunctionPorts <em>Realized Function Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPort#getRealizingFunctionPorts <em>Realizing Function Ports</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPort()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Function Port'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A port is an interaction point between a block or part and its environment that is connected with other ports via connectors\r\n[source: SysML specification v1.1]\r\n\r\nBase abstract class for actual port implementations\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
 * @generated
 */
public interface FunctionPort extends Port, TypedElement, AbstractEvent {





	/**
   * Returns the value of the '<em><b>Represented Component Port</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @deprecated : 'representedComponentPort' shall not be used anymore
   * <!-- end-model-doc -->
   * @return the value of the '<em>Represented Component Port</em>' reference.
   * @see #setRepresentedComponentPort(ComponentPort)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPort_RepresentedComponentPort()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the ComponentPort that this function port represents\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	@Deprecated
	ComponentPort getRepresentedComponentPort();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort <em>Represented Component Port</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Represented Component Port</em>' reference.
   * @see #getRepresentedComponentPort()
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   */

	@Deprecated
	void setRepresentedComponentPort(ComponentPort value);







	/**
   * Returns the value of the '<em><b>Allocator Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentPort#getAllocatedFunctionPorts <em>Allocated Function Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocator Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocator Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPort_AllocatorComponentPorts()
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getAllocatedFunctionPorts
   * @model opposite="allocatedFunctionPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingPortAllocations.allocatingPort'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getAllocatorComponentPorts();







	/**
   * Returns the value of the '<em><b>Realized Function Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionPort}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Function Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Function Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPort_RealizedFunctionPorts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingPortRealizations.realizedPort'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionPort> getRealizedFunctionPorts();







	/**
   * Returns the value of the '<em><b>Realizing Function Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionPort}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Function Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Function Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPort_RealizingFunctionPorts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingPortRealizations.realizingPort'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionPort> getRealizingFunctionPorts();





} // FunctionPort
