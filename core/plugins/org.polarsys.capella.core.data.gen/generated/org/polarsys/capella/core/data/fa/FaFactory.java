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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.fa.FaPackage
 * @generated
 */
public interface FaFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	FaFactory eINSTANCE = org.polarsys.capella.core.data.fa.impl.FaFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Function Specification</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Specification</em>'.
   * @generated
   */
	FunctionSpecification createFunctionSpecification();

	/**
   * Returns a new object of class '<em>Exchange Category</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Category</em>'.
   * @generated
   */
	ExchangeCategory createExchangeCategory();

	/**
   * Returns a new object of class '<em>Exchange Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Link</em>'.
   * @generated
   */
	ExchangeLink createExchangeLink();

	/**
   * Returns a new object of class '<em>Exchange Containment</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Containment</em>'.
   * @generated
   */
	ExchangeContainment createExchangeContainment();

	/**
   * Returns a new object of class '<em>Functional Exchange Specification</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Exchange Specification</em>'.
   * @generated
   */
	FunctionalExchangeSpecification createFunctionalExchangeSpecification();

	/**
   * Returns a new object of class '<em>Functional Chain</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Chain</em>'.
   * @generated
   */
	FunctionalChain createFunctionalChain();

	/**
   * Returns a new object of class '<em>Functional Chain Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Chain Reference</em>'.
   * @generated
   */
	FunctionalChainReference createFunctionalChainReference();

	/**
   * Returns a new object of class '<em>Function Input Port</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Input Port</em>'.
   * @generated
   */
	FunctionInputPort createFunctionInputPort();

	/**
   * Returns a new object of class '<em>Function Output Port</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Output Port</em>'.
   * @generated
   */
	FunctionOutputPort createFunctionOutputPort();

	/**
   * Returns a new object of class '<em>Component Functional Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Functional Allocation</em>'.
   * @generated
   */
	ComponentFunctionalAllocation createComponentFunctionalAllocation();

	/**
   * Returns a new object of class '<em>Functional Chain Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Chain Realization</em>'.
   * @generated
   */
	FunctionalChainRealization createFunctionalChainRealization();

	/**
   * Returns a new object of class '<em>Functional Exchange Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Exchange Realization</em>'.
   * @generated
   */
	FunctionalExchangeRealization createFunctionalExchangeRealization();

	/**
   * Returns a new object of class '<em>Function Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Realization</em>'.
   * @generated
   */
	FunctionRealization createFunctionRealization();

	/**
   * Returns a new object of class '<em>Functional Exchange</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Exchange</em>'.
   * @generated
   */
	FunctionalExchange createFunctionalExchange();

	/**
   * Returns a new object of class '<em>Component Exchange</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Exchange</em>'.
   * @generated
   */
	ComponentExchange createComponentExchange();

	/**
   * Returns a new object of class '<em>Component Exchange Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Exchange Allocation</em>'.
   * @generated
   */
	ComponentExchangeAllocation createComponentExchangeAllocation();

	/**
   * Returns a new object of class '<em>Component Exchange Category</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Exchange Category</em>'.
   * @generated
   */
	ComponentExchangeCategory createComponentExchangeCategory();

	/**
   * Returns a new object of class '<em>Component Exchange End</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Exchange End</em>'.
   * @generated
   */
	ComponentExchangeEnd createComponentExchangeEnd();

	/**
   * Returns a new object of class '<em>Component Exchange Functional Exchange Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Exchange Functional Exchange Allocation</em>'.
   * @generated
   */
	ComponentExchangeFunctionalExchangeAllocation createComponentExchangeFunctionalExchangeAllocation();

	/**
   * Returns a new object of class '<em>Component Exchange Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Exchange Realization</em>'.
   * @generated
   */
	ComponentExchangeRealization createComponentExchangeRealization();

	/**
   * Returns a new object of class '<em>Component Port</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Port</em>'.
   * @generated
   */
	ComponentPort createComponentPort();

	/**
   * Returns a new object of class '<em>Component Port Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Port Allocation</em>'.
   * @generated
   */
	ComponentPortAllocation createComponentPortAllocation();

	/**
   * Returns a new object of class '<em>Component Port Allocation End</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Port Allocation End</em>'.
   * @generated
   */
	ComponentPortAllocationEnd createComponentPortAllocationEnd();

	/**
   * Returns a new object of class '<em>Functional Chain Involvement Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Chain Involvement Link</em>'.
   * @generated
   */
	FunctionalChainInvolvementLink createFunctionalChainInvolvementLink();

	/**
   * Returns a new object of class '<em>Sequence Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Sequence Link</em>'.
   * @generated
   */
	SequenceLink createSequenceLink();

	/**
   * Returns a new object of class '<em>Functional Chain Involvement Function</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Functional Chain Involvement Function</em>'.
   * @generated
   */
	FunctionalChainInvolvementFunction createFunctionalChainInvolvementFunction();

	/**
   * Returns a new object of class '<em>Control Node</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Control Node</em>'.
   * @generated
   */
	ControlNode createControlNode();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	FaPackage getFaPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FunctionSpecification createFunctionSpecification(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExchangeCategory createExchangeCategory(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExchangeLink createExchangeLink(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FunctionalExchangeSpecification createFunctionalExchangeSpecification(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FunctionalChain createFunctionalChain(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FunctionInputPort createFunctionInputPort(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FunctionOutputPort createFunctionOutputPort(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FunctionalExchange createFunctionalExchange(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ComponentExchange createComponentExchange(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ComponentExchangeCategory createComponentExchangeCategory(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ComponentPort createComponentPort(String name_p);

	//begin-capella-code
	//end-capella-code
} //FaFactory
