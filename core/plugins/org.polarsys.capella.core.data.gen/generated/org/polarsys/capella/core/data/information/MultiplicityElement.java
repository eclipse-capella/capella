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
package org.polarsys.capella.core.data.information;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#isMinInclusive <em>Min Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#isMaxInclusive <em>Max Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinCard <em>Owned Min Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinLength <em>Owned Min Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxCard <em>Owned Max Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxLength <em>Owned Max Length</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A MultiplicityElement is an abstract metaclass that includes optional attributes for defining the bounds of a multiplicity.\r\nA MultiplicityElement also includes specifications of whether the values in an instantiation of this element must be\r\nunique or ordered.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='These constraints must handle situations where the upper bound may be specified by an expression not computable in the\r\nmodel.\r\n- A multiplicity must define at least one valid cardinality that is greater than zero.\r\nupperBound()-&gt;notEmpty() implies upperBound() &gt; 0\r\n- The lower bound must be a non-negative integer literal.\r\nlowerBound()-&gt;notEmpty() implies lowerBound() &gt;= 0\r\n- The upper bound must be greater than or equal to the lower bound.\r\n(upperBound()-&gt;notEmpty() and lowerBound()-&gt;notEmpty()) implies upperBound() &gt;= lowerBound()\r\n- If a non-literal ValueSpecification is used for the lower or upper bound, then evaluating that specification must not have\r\nside effects.\r\nCannot be expressed in OCL.\r\n- If a non-literal ValueSpecification is used for the lower or upper bound, then that specification must be a constant\r\nexpression.\r\nCannot be expressed in OCL.\r\n- The derived lower attribute must equal the lowerBound.\r\nlower = lowerBound()\r\n- The derived upper attribute must equal the upperBound.\r\n[source: UML superstructure v2.2]\r\nupper = upperBound()' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::MultiplicityElement' constraints='none'"
 * @generated
 */
public interface MultiplicityElement extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Ordered</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordered</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Ordered</em>' attribute.
   * @see #setOrdered(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_Ordered()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='For a multivalued multiplicity, this attribute specifies whether the values in an instantiation of this element are\r\nsequentially ordered\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isOrdered();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isOrdered <em>Ordered</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ordered</em>' attribute.
   * @see #isOrdered()
   * @generated
   */

	void setOrdered(boolean value);







	/**
   * Returns the value of the '<em><b>Unique</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Unique</em>' attribute.
   * @see #setUnique(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_Unique()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether or not this element is unique\r\n[source: Capella study]' constraints='none' type='true is element is unique' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isUnique();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isUnique <em>Unique</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unique</em>' attribute.
   * @see #isUnique()
   * @generated
   */

	void setUnique(boolean value);







	/**
   * Returns the value of the '<em><b>Min Inclusive</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Inclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Min Inclusive</em>' attribute.
   * @see #setMinInclusive(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_MinInclusive()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether the min value of the range is included or not\r\n[source: Capella light-light study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isMinInclusive();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isMinInclusive <em>Min Inclusive</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Inclusive</em>' attribute.
   * @see #isMinInclusive()
   * @generated
   */

	void setMinInclusive(boolean value);







	/**
   * Returns the value of the '<em><b>Max Inclusive</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Inclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Max Inclusive</em>' attribute.
   * @see #setMaxInclusive(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_MaxInclusive()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether the max value of the range is included or not\r\n[source: Capella light-light study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isMaxInclusive();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isMaxInclusive <em>Max Inclusive</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Inclusive</em>' attribute.
   * @see #isMaxInclusive()
   * @generated
   */

	void setMaxInclusive(boolean value);







	/**
   * Returns the value of the '<em><b>Owned Default Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Default Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Default Value</em>' containment reference.
   * @see #setOwnedDefaultValue(DataValue)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedDefaultValue()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Default Value'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the value assigned by default to this multiplicity element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which DataValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedDefaultValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedDefaultValue <em>Owned Default Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Default Value</em>' containment reference.
   * @see #getOwnedDefaultValue()
   * @generated
   */

	void setOwnedDefaultValue(DataValue value);







	/**
   * Returns the value of the '<em><b>Owned Min Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Min Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Min Value</em>' containment reference.
   * @see #setOwnedMinValue(DataValue)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedMinValue()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='lowerValue' featureOwner='MultiplicityElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Min Value'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='minimum specified value for this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedMinValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinValue <em>Owned Min Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Min Value</em>' containment reference.
   * @see #getOwnedMinValue()
   * @generated
   */

	void setOwnedMinValue(DataValue value);







	/**
   * Returns the value of the '<em><b>Owned Max Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Max Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Max Value</em>' containment reference.
   * @see #setOwnedMaxValue(DataValue)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedMaxValue()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='upperValue' featureOwner='MultiplicityElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Max Value'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specified max value for this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedMaxValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxValue <em>Owned Max Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Max Value</em>' containment reference.
   * @see #getOwnedMaxValue()
   * @generated
   */

	void setOwnedMaxValue(DataValue value);







	/**
   * Returns the value of the '<em><b>Owned Null Value</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Null Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Null Value</em>' containment reference.
   * @see #setOwnedNullValue(DataValue)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedNullValue()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Null value'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the reference to the null value among the set of values contained in this MultiplicityElement\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which DataValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedNullValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedNullValue <em>Owned Null Value</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Null Value</em>' containment reference.
   * @see #getOwnedNullValue()
   * @generated
   */

	void setOwnedNullValue(DataValue value);







	/**
   * Returns the value of the '<em><b>Owned Min Card</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Min Card</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Min Card</em>' containment reference.
   * @see #setOwnedMinCard(NumericValue)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedMinCard()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='lowerValue' featureOwner='MultiplicityElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Min Card'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the lower bound of the multiplicity interval, if it is expressed as an integer\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMinCard();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinCard <em>Owned Min Card</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Min Card</em>' containment reference.
   * @see #getOwnedMinCard()
   * @generated
   */

	void setOwnedMinCard(NumericValue value);







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
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedMinLength()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='lowerValue' featureOwner='MultiplicityElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Min Length'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specified minimum length for this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which NumericValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMinLength();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinLength <em>Owned Min Length</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Min Length</em>' containment reference.
   * @see #getOwnedMinLength()
   * @generated
   */

	void setOwnedMinLength(NumericValue value);







	/**
   * Returns the value of the '<em><b>Owned Max Card</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Max Card</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Max Card</em>' containment reference.
   * @see #setOwnedMaxCard(NumericValue)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedMaxCard()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='upperValue' featureOwner='MultiplicityElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Max Card'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the upper bound of the multiplicity interval, if it is expressed as an unlimited natural\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMaxCard();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxCard <em>Owned Max Card</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Max Card</em>' containment reference.
   * @see #getOwnedMaxCard()
   * @generated
   */

	void setOwnedMaxCard(NumericValue value);







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
   * @see org.polarsys.capella.core.data.information.InformationPackage#getMultiplicityElement_OwnedMaxLength()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='upperValue' featureOwner='MultiplicityElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Max Length'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specified max length for this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='none' constraints='Elements on which NumericValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	NumericValue getOwnedMaxLength();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxLength <em>Owned Max Length</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Max Length</em>' containment reference.
   * @see #getOwnedMaxLength()
   * @generated
   */

	void setOwnedMaxLength(NumericValue value);





} // MultiplicityElement
