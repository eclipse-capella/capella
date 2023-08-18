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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Property Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getType <em>Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getEnumerationPropertyValue()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='It is a way to define extension properties for any capella elements\r\nA property value is a named element that has a value. This value has no specific format, it is described as a string.\r\n[Capella study]\r\n' usage\040guideline='none' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Comment' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface EnumerationPropertyValue extends AbstractPropertyValue {





	/**
   * Returns the value of the '<em><b>Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(EnumerationPropertyType)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getEnumerationPropertyValue_Type()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Type of this property\r\n[source:Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EnumerationPropertyType getType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getType <em>Type</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */

	void setType(EnumerationPropertyType value);







	/**
   * Returns the value of the '<em><b>Value</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' reference.
   * @see #setValue(EnumerationPropertyLiteral)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getEnumerationPropertyValue_Value()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Value of this property, described in string format\r\n[source:Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='value will be stored as a stereotype-specific property, as a reference to an EnumerationPropertyLiteral' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EnumerationPropertyLiteral getValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getValue <em>Value</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' reference.
   * @see #getValue()
   * @generated
   */

	void setValue(EnumerationPropertyLiteral value);





} // EnumerationPropertyValue
