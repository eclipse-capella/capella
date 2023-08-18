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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedLiterals <em>Owned Literals</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getDomainType <em>Domain Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getEnumeration()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Enumeration'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Enumeration' stereotype='eng.Enumeration'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An enumeration is a kind of data type, whose instances may be any of a number of user-defined enumeration literals\r\n[source: UML superstructure v2.2]' usage\040guideline='an enumeration should be created/used whenever all possible values for a parameter are predefined (and the number of values is reasonably limited...)' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Enumeration' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Enumeration extends DataType {





	/**
   * Returns the value of the '<em><b>Owned Literals</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Literals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Literals</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getEnumeration_OwnedLiterals()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedLiteral' featureOwner='Enumeration'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='literals'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the literals that are contained in this enumeration\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Enumeration::ownedLiteral' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<EnumerationLiteral> getOwnedLiterals();







	/**
   * Returns the value of the '<em><b>Owned Default Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Default Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Default Value</em>' containment reference.
   * @see #setOwnedDefaultValue(AbstractEnumerationValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getEnumeration_OwnedDefaultValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='default value among this enumeration\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which EnumerationValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractEnumerationValue getOwnedDefaultValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedDefaultValue <em>Owned Default Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Default Value</em>' containment reference.
   * @see #getOwnedDefaultValue()
   * @generated
   */

	void setOwnedDefaultValue(AbstractEnumerationValue value);







	/**
   * Returns the value of the '<em><b>Owned Null Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Null Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Null Value</em>' containment reference.
   * @see #setOwnedNullValue(AbstractEnumerationValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getEnumeration_OwnedNullValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Null value among this enumeration\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which EnumerationValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractEnumerationValue getOwnedNullValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedNullValue <em>Owned Null Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Null Value</em>' containment reference.
   * @see #getOwnedNullValue()
   * @generated
   */

	void setOwnedNullValue(AbstractEnumerationValue value);







	/**
   * Returns the value of the '<em><b>Owned Min Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Min Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Min Value</em>' containment reference.
   * @see #setOwnedMinValue(AbstractEnumerationValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getEnumeration_OwnedMinValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='minValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specification of the minimum value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' constraints='uml::NamedElement::clientDependency elements on which NumericType stereotype or any stereotype that inherits from it is applied' explanation='_todo_ Treat difference between default, null, min and max values\r\n'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractEnumerationValue getOwnedMinValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMinValue <em>Owned Min Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Min Value</em>' containment reference.
   * @see #getOwnedMinValue()
   * @generated
   */

	void setOwnedMinValue(AbstractEnumerationValue value);







	/**
   * Returns the value of the '<em><b>Owned Max Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Max Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Max Value</em>' containment reference.
   * @see #setOwnedMaxValue(AbstractEnumerationValue)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getEnumeration_OwnedMaxValue()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='maxValue'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specification of the maximum value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' constraints='uml::NamedElement::clientDependency elements on which NumericType stereotype or any stereotype that inherits from it is applied' explanation='_todo_ Treat difference between default, null, min and max values\r\n'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractEnumerationValue getOwnedMaxValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMaxValue <em>Owned Max Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Max Value</em>' containment reference.
   * @see #getOwnedMaxValue()
   * @generated
   */

	void setOwnedMaxValue(AbstractEnumerationValue value);







	/**
   * Returns the value of the '<em><b>Domain Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Domain Type</em>' reference.
   * @see #setDomainType(DataType)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getEnumeration_DomainType()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataType getDomainType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getDomainType <em>Domain Type</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Domain Type</em>' reference.
   * @see #getDomainType()
   * @generated
   */

	void setDomainType(DataType value);





} // Enumeration
