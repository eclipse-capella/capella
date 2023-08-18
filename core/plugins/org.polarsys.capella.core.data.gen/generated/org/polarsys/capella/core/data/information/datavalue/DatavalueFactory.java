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
package org.polarsys.capella.core.data.information.datavalue;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage
 * @generated
 */
public interface DatavalueFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	DatavalueFactory eINSTANCE = org.polarsys.capella.core.data.information.datavalue.impl.DatavalueFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Literal Boolean Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal Boolean Value</em>'.
   * @generated
   */
	LiteralBooleanValue createLiteralBooleanValue();

	/**
   * Returns a new object of class '<em>Boolean Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Reference</em>'.
   * @generated
   */
	BooleanReference createBooleanReference();

	/**
   * Returns a new object of class '<em>Enumeration Literal</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration Literal</em>'.
   * @generated
   */
	EnumerationLiteral createEnumerationLiteral();

	/**
   * Returns a new object of class '<em>Enumeration Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration Reference</em>'.
   * @generated
   */
	EnumerationReference createEnumerationReference();

	/**
   * Returns a new object of class '<em>Literal String Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal String Value</em>'.
   * @generated
   */
	LiteralStringValue createLiteralStringValue();

	/**
   * Returns a new object of class '<em>String Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>String Reference</em>'.
   * @generated
   */
	StringReference createStringReference();

	/**
   * Returns a new object of class '<em>Literal Numeric Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal Numeric Value</em>'.
   * @generated
   */
	LiteralNumericValue createLiteralNumericValue();

	/**
   * Returns a new object of class '<em>Numeric Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Numeric Reference</em>'.
   * @generated
   */
	NumericReference createNumericReference();

	/**
   * Returns a new object of class '<em>Complex Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Complex Value</em>'.
   * @generated
   */
	ComplexValue createComplexValue();

	/**
   * Returns a new object of class '<em>Complex Value Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Complex Value Reference</em>'.
   * @generated
   */
	ComplexValueReference createComplexValueReference();

	/**
   * Returns a new object of class '<em>Value Part</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Value Part</em>'.
   * @generated
   */
	ValuePart createValuePart();

	/**
   * Returns a new object of class '<em>Binary Expression</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Binary Expression</em>'.
   * @generated
   */
	BinaryExpression createBinaryExpression();

	/**
   * Returns a new object of class '<em>Unary Expression</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Unary Expression</em>'.
   * @generated
   */
	UnaryExpression createUnaryExpression();

	/**
   * Returns a new object of class '<em>Opaque Expression</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Opaque Expression</em>'.
   * @generated
   */
	OpaqueExpression createOpaqueExpression();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	DatavaluePackage getDatavaluePackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LiteralBooleanValue createLiteralBooleanValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	BooleanReference createBooleanReference(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EnumerationLiteral createEnumerationLiteral(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EnumerationReference createEnumerationReference(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LiteralStringValue createLiteralStringValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	StringReference createStringReference(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	LiteralNumericValue createLiteralNumericValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	NumericReference createNumericReference(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ComplexValue createComplexValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ComplexValueReference createComplexValueReference(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	BinaryExpression createBinaryExpression(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	UnaryExpression createUnaryExpression(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OpaqueExpression createOpaqueExpression(String name_p);

	//begin-capella-code
	//end-capella-code
} //DatavalueFactory
