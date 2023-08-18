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

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.ValuePart#getReferencedProperty <em>Referenced Property</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.ValuePart#getOwnedValue <em>Owned Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getValuePart()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used in the decomposition of complex values into smaller unitary elements\r\n[source: Capella light-light study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Expression' explanation='' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ValuePart extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Referenced Property</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Property</em>' reference.
   * @see #setReferencedProperty(Property)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getValuePart_ReferencedProperty()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Property getReferencedProperty();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.ValuePart#getReferencedProperty <em>Referenced Property</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Referenced Property</em>' reference.
   * @see #getReferencedProperty()
   * @generated
   */

	void setReferencedProperty(Property value);







	/**
   * Returns the value of the '<em><b>Owned Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Value</em>' containment reference.
   * @see #setOwnedValue(DataValue)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getValuePart_OwnedValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='****** elements on which DataValue stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.ValuePart#getOwnedValue <em>Owned Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Value</em>' containment reference.
   * @see #getOwnedValue()
   * @generated
   */

	void setOwnedValue(DataValue value);





} // ValuePart
