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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Numeric Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getLiteralNumericValue()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='LiteralNumericValue'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Expression' stereotype='eng.LiteralNumericValue'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A literal value expressed as a number (ordered set of digits)\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Expression' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface LiteralNumericValue extends NumericValue {





	/**
   * Returns the value of the '<em><b>Value</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getLiteralNumericValue_Value()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='symbol' featureOwner='Expression'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the number defining this value, expressed as a string\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::LiteralString::value' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue#getValue <em>Value</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */

	void setValue(String value);





} // LiteralNumericValue
