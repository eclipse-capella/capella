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
package org.polarsys.capella.core.data.information.datatype;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage
 * @generated
 */
public interface DatatypeFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	DatatypeFactory eINSTANCE = org.polarsys.capella.core.data.information.datatype.impl.DatatypeFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Boolean Type</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Type</em>'.
   * @generated
   */
	BooleanType createBooleanType();

	/**
   * Returns a new object of class '<em>Enumeration</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration</em>'.
   * @generated
   */
	Enumeration createEnumeration();

	/**
   * Returns a new object of class '<em>String Type</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>String Type</em>'.
   * @generated
   */
	StringType createStringType();

	/**
   * Returns a new object of class '<em>Numeric Type</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Numeric Type</em>'.
   * @generated
   */
	NumericType createNumericType();

	/**
   * Returns a new object of class '<em>Physical Quantity</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Physical Quantity</em>'.
   * @generated
   */
	PhysicalQuantity createPhysicalQuantity();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	DatatypePackage getDatatypePackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	BooleanType createBooleanType(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Enumeration createEnumeration(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	StringType createStringType(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	NumericType createNumericType(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	PhysicalQuantity createPhysicalQuantity(String name_p);

	//begin-capella-code
	//end-capella-code
} //DatatypeFactory
