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
package org.polarsys.capella.core.data.capellacore;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage
 * @generated
 */
public interface CapellacoreFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CapellacoreFactory eINSTANCE = org.polarsys.capella.core.data.capellacore.impl.CapellacoreFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Naming Rule</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Naming Rule</em>'.
   * @generated
   */
	NamingRule createNamingRule();

	/**
   * Returns a new object of class '<em>Constraint</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Constraint</em>'.
   * @generated
   */
	Constraint createConstraint();

	/**
   * Returns a new object of class '<em>Key Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Key Value</em>'.
   * @generated
   */
	KeyValue createKeyValue();

	/**
   * Returns a new object of class '<em>Reuse Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Reuse Link</em>'.
   * @generated
   */
	ReuseLink createReuseLink();

	/**
   * Returns a new object of class '<em>Generalization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Generalization</em>'.
   * @generated
   */
	Generalization createGeneralization();

	/**
   * Returns a new object of class '<em>String Property Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>String Property Value</em>'.
   * @generated
   */
	StringPropertyValue createStringPropertyValue();

	/**
   * Returns a new object of class '<em>Integer Property Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer Property Value</em>'.
   * @generated
   */
	IntegerPropertyValue createIntegerPropertyValue();

	/**
   * Returns a new object of class '<em>Boolean Property Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Property Value</em>'.
   * @generated
   */
	BooleanPropertyValue createBooleanPropertyValue();

	/**
   * Returns a new object of class '<em>Float Property Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Float Property Value</em>'.
   * @generated
   */
	FloatPropertyValue createFloatPropertyValue();

	/**
   * Returns a new object of class '<em>Enumeration Property Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration Property Value</em>'.
   * @generated
   */
	EnumerationPropertyValue createEnumerationPropertyValue();

	/**
   * Returns a new object of class '<em>Enumeration Property Type</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration Property Type</em>'.
   * @generated
   */
	EnumerationPropertyType createEnumerationPropertyType();

	/**
   * Returns a new object of class '<em>Enumeration Property Literal</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration Property Literal</em>'.
   * @generated
   */
	EnumerationPropertyLiteral createEnumerationPropertyLiteral();

	/**
   * Returns a new object of class '<em>Property Value Group</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Value Group</em>'.
   * @generated
   */
	PropertyValueGroup createPropertyValueGroup();

	/**
   * Returns a new object of class '<em>Property Value Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Value Pkg</em>'.
   * @generated
   */
	PropertyValuePkg createPropertyValuePkg();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	CapellacorePackage getCapellacorePackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Constraint createConstraint(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	StringPropertyValue createStringPropertyValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	IntegerPropertyValue createIntegerPropertyValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	BooleanPropertyValue createBooleanPropertyValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	FloatPropertyValue createFloatPropertyValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EnumerationPropertyValue createEnumerationPropertyValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EnumerationPropertyType createEnumerationPropertyType(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EnumerationPropertyLiteral createEnumerationPropertyLiteral(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	PropertyValueGroup createPropertyValueGroup(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	PropertyValuePkg createPropertyValuePkg(String name_p);

	//begin-capella-code
	//end-capella-code
} //CapellacoreFactory
