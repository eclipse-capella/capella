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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Exchange Functional Exchange Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatedFunctionalExchange <em>Allocated Functional Exchange</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatingComponentExchange <em>Allocating Component Exchange</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeFunctionalExchangeAllocation()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Component Functional Exchange Allocation'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allocation link between a connection and a functional exchange\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Allocations::Allocate' explanation='none' constraints='none'"
 * @generated
 */
public interface ComponentExchangeFunctionalExchangeAllocation extends AbstractFunctionAllocation {





	/**
   * Returns the value of the '<em><b>Allocated Functional Exchange</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingComponentExchangeFunctionalExchangeRealizations <em>Incoming Component Exchange Functional Exchange Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Functional Exchange</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Functional Exchange</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange()
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingComponentExchangeFunctionalExchangeRealizations
   * @model opposite="incomingComponentExchangeFunctionalExchangeRealizations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional exchange involved in this allocation link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	FunctionalExchange getAllocatedFunctionalExchange();







	/**
   * Returns the value of the '<em><b>Allocating Component Exchange</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeFunctionalExchangeAllocations <em>Outgoing Component Exchange Functional Exchange Allocations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Component Exchange</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Component Exchange</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange()
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeFunctionalExchangeAllocations
   * @model opposite="outgoingComponentExchangeFunctionalExchangeAllocations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the connection involved in this allocation relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	ComponentExchange getAllocatingComponentExchange();





} // ComponentExchangeFunctionalExchangeAllocation
