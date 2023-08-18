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

import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMinLength <em>Owned Min Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMaxLength <em>Owned Max Length</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getStringType()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='StringType'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='DataType' stereotype='eng.StringType'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A string is a sequence of characters in some suitable character set used to display information about the model\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::DataType' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface StringType extends DataType {





	/**
   * Returns the value of the '<em><b>Owned Default Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Default Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Default Value</em>' containment reference.
   * @see #setOwnedDefaultValue(AbstractStringValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getStringType_OwnedDefaultValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='defaultValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the default value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which StringValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractStringValue getOwnedDefaultValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedDefaultValue <em>Owned Default Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Default Value</em>' containment reference.
   * @see #getOwnedDefaultValue()
   * @generated
   */

	void setOwnedDefaultValue(AbstractStringValue value);







	/**
   * Returns the value of the '<em><b>Owned Null Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Null Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Null Value</em>' containment reference.
   * @see #setOwnedNullValue(AbstractStringValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getStringType_OwnedNullValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='nullValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the neutral value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which StringValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractStringValue getOwnedNullValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedNullValue <em>Owned Null Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Null Value</em>' containment reference.
   * @see #getOwnedNullValue()
   * @generated
   */

	void setOwnedNullValue(AbstractStringValue value);







	/**
   * Returns the value of the '<em><b>Owned Min Length</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Min Length</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Min Length</em>' containment reference.
   * @see #setOwnedMinLength(NumericValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getStringType_OwnedMinLength()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='minLength'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specification of the minimum length of the string\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which FunctionRealization stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMinLength();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMinLength <em>Owned Min Length</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Min Length</em>' containment reference.
   * @see #getOwnedMinLength()
   * @generated
   */

	void setOwnedMinLength(NumericValue value);







	/**
   * Returns the value of the '<em><b>Owned Max Length</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Max Length</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Max Length</em>' containment reference.
   * @see #setOwnedMaxLength(NumericValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getStringType_OwnedMaxLength()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='maxLength'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specification of the maximum length of the string\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which UnlimitedNaturalValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMaxLength();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMaxLength <em>Owned Max Length</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Max Length</em>' containment reference.
   * @see #getOwnedMaxLength()
   * @generated
   */

	void setOwnedMaxLength(NumericValue value);





} // StringType
