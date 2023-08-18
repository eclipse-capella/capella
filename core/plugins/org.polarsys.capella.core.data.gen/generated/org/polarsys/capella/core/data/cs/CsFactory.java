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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.cs.CsPackage
 * @generated
 */
public interface CsFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CsFactory eINSTANCE = org.polarsys.capella.core.data.cs.impl.CsFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Part</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Part</em>'.
   * @generated
   */
	Part createPart();

	/**
   * Returns a new object of class '<em>Component Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Realization</em>'.
   * @generated
   */
	ComponentRealization createComponentRealization();

	/**
   * Returns a new object of class '<em>Interface Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Interface Pkg</em>'.
   * @generated
   */
	InterfacePkg createInterfacePkg();

	/**
   * Returns a new object of class '<em>Interface</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Interface</em>'.
   * @generated
   */
	Interface createInterface();

	/**
   * Returns a new object of class '<em>Interface Implementation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Interface Implementation</em>'.
   * @generated
   */
	InterfaceImplementation createInterfaceImplementation();

	/**
   * Returns a new object of class '<em>Interface Use</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Interface Use</em>'.
   * @generated
   */
	InterfaceUse createInterfaceUse();

	/**
   * Returns a new object of class '<em>Exchange Item Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Item Allocation</em>'.
   * @generated
   */
	ExchangeItemAllocation createExchangeItemAllocation();

	/**
   * Returns a new object of class '<em>Physical Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Link</em>'.
   * @generated
   */
	PhysicalLink createPhysicalLink();

	/**
   * Returns a new object of class '<em>Physical Link Category</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Link Category</em>'.
   * @generated
   */
	PhysicalLinkCategory createPhysicalLinkCategory();

	/**
   * Returns a new object of class '<em>Physical Link End</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Link End</em>'.
   * @generated
   */
	PhysicalLinkEnd createPhysicalLinkEnd();

	/**
   * Returns a new object of class '<em>Physical Link Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Link Realization</em>'.
   * @generated
   */
	PhysicalLinkRealization createPhysicalLinkRealization();

	/**
   * Returns a new object of class '<em>Physical Path</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Path</em>'.
   * @generated
   */
	PhysicalPath createPhysicalPath();

	/**
   * Returns a new object of class '<em>Physical Path Involvement</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Path Involvement</em>'.
   * @generated
   */
	PhysicalPathInvolvement createPhysicalPathInvolvement();

	/**
   * Returns a new object of class '<em>Physical Path Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Path Reference</em>'.
   * @generated
   */
	PhysicalPathReference createPhysicalPathReference();

	/**
   * Returns a new object of class '<em>Physical Path Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Path Realization</em>'.
   * @generated
   */
	PhysicalPathRealization createPhysicalPathRealization();

	/**
   * Returns a new object of class '<em>Physical Port</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Port</em>'.
   * @generated
   */
	PhysicalPort createPhysicalPort();

	/**
   * Returns a new object of class '<em>Physical Port Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Port Realization</em>'.
   * @generated
   */
	PhysicalPortRealization createPhysicalPortRealization();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	CsPackage getCsPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Part createPart(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	InterfacePkg createInterfacePkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Interface createInterface(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExchangeItemAllocation createExchangeItemAllocation(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	PhysicalLink createPhysicalLink(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	PhysicalLinkCategory createPhysicalLinkCategory(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	PhysicalPath createPhysicalPath(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	PhysicalPort createPhysicalPort(String name_p);

	//begin-capella-code
	//end-capella-code
} //CsFactory
