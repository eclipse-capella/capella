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

import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Numeric Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.NumericType#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getNumericType()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='NumericType'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='DataType' stereotype='eng.NumericType'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Characterizes a value that can be expressed by a sequence of numbers\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::DataType' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface NumericType extends DataType {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The default value is <code>"INTEGER"</code>.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.datatype.NumericTypeKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.datatype.NumericTypeKind
   * @see #setKind(NumericTypeKind)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getNumericType_Kind()
   * @model default="INTEGER"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the kind of this numeric type\r\n[source: Capella study]' constraints='none' type='refer to NumericTypeKind' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericTypeKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.datatype.NumericTypeKind
   * @see #getKind()
   * @generated
   */

	void setKind(NumericTypeKind value);







	/**
   * Returns the value of the '<em><b>Owned Default Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Default Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Default Value</em>' containment reference.
   * @see #setOwnedDefaultValue(NumericValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getNumericType_OwnedDefaultValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='defaultValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the default value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which NumericValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedDefaultValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedDefaultValue <em>Owned Default Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Default Value</em>' containment reference.
   * @see #getOwnedDefaultValue()
   * @generated
   */

	void setOwnedDefaultValue(NumericValue value);







	/**
   * Returns the value of the '<em><b>Owned Null Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Null Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Null Value</em>' containment reference.
   * @see #setOwnedNullValue(NumericValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getNumericType_OwnedNullValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='nullValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the neutral value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which NumericValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedNullValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedNullValue <em>Owned Null Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Null Value</em>' containment reference.
   * @see #getOwnedNullValue()
   * @generated
   */

	void setOwnedNullValue(NumericValue value);







	/**
   * Returns the value of the '<em><b>Owned Min Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Min Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Min Value</em>' containment reference.
   * @see #setOwnedMinValue(NumericValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getNumericType_OwnedMinValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='minValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specification of the minimum value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which NumericValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMinValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMinValue <em>Owned Min Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Min Value</em>' containment reference.
   * @see #getOwnedMinValue()
   * @generated
   */

	void setOwnedMinValue(NumericValue value);







	/**
   * Returns the value of the '<em><b>Owned Max Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Max Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Max Value</em>' containment reference.
   * @see #setOwnedMaxValue(NumericValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getNumericType_OwnedMaxValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='maxValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specification of the maximum value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which NumericValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMaxValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMaxValue <em>Owned Max Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Max Value</em>' containment reference.
   * @see #getOwnedMaxValue()
   * @generated
   */

	void setOwnedMaxValue(NumericValue value);





} // NumericType
