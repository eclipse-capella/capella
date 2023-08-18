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
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#isDiscrete <em>Discrete</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#isMinInclusive <em>Min Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#isMaxInclusive <em>Max Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#getNullValue <em>Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#getOwnedInformationRealizations <em>Owned Information Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#getRealizedDataTypes <em>Realized Data Types</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datatype.DataType#getRealizingDataTypes <em>Realizing Data Types</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='DataType'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='DataType' stereotype='eng.DataType'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A data type is a type whose instances are identified only by their value. A DataType may contain attributes to support the\r\nmodeling of structured data types\r\n[source: UML superstructure v2.2]' usage\040guideline='DataTypes should be created for every grouping of data in the system that makes sense from an application standpoint.\r\nIt is especially valuable to avoid redondancy of data structure definitions, and to breakdown the complexity of data structures into manageable bits.' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::DataType' constraints='none'"
 * @generated
 */
public interface DataType extends GeneralizableElement, DataValueContainer, FinalizableElement {





	/**
   * Returns the value of the '<em><b>Discrete</b></em>' attribute.
   * The default value is <code>"true"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Discrete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Discrete</em>' attribute.
   * @see #setDiscrete(boolean)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_Discrete()
   * @model default="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='isDiscreet' featureOwner='eng.DataType' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether or not this data type characterizes a discrete value (versus continuous value)\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isDiscrete();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.DataType#isDiscrete <em>Discrete</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Discrete</em>' attribute.
   * @see #isDiscrete()
   * @generated
   */

	void setDiscrete(boolean value);







	/**
   * Returns the value of the '<em><b>Min Inclusive</b></em>' attribute.
   * The default value is <code>"true"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Inclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Min Inclusive</em>' attribute.
   * @see #setMinInclusive(boolean)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_MinInclusive()
   * @model default="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isMinInclusive();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.DataType#isMinInclusive <em>Min Inclusive</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Inclusive</em>' attribute.
   * @see #isMinInclusive()
   * @generated
   */

	void setMinInclusive(boolean value);







	/**
   * Returns the value of the '<em><b>Max Inclusive</b></em>' attribute.
   * The default value is <code>"true"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Inclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Max Inclusive</em>' attribute.
   * @see #setMaxInclusive(boolean)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_MaxInclusive()
   * @model default="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isMaxInclusive();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.DataType#isMaxInclusive <em>Max Inclusive</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Inclusive</em>' attribute.
   * @see #isMaxInclusive()
   * @generated
   */

	void setMaxInclusive(boolean value);







	/**
   * Returns the value of the '<em><b>Pattern</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern</em>' attribute.
   * @see #setPattern(String)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_Pattern()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='constraint' featureOwner='eng.DataType' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='textual specification of a constraint associated to this data type\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getPattern();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.DataType#getPattern <em>Pattern</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pattern</em>' attribute.
   * @see #getPattern()
   * @generated
   */

	void setPattern(String value);







	/**
   * Returns the value of the '<em><b>Visibility</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.capellacore.VisibilityKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Visibility</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacore.VisibilityKind
   * @see #setVisibility(VisibilityKind)
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_Visibility()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='refer to VisibilityKind description\r\n[source: Capella study]' constraints='none' type='refer to VisibilityKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement:visibility' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	VisibilityKind getVisibility();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datatype.DataType#getVisibility <em>Visibility</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visibility</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacore.VisibilityKind
   * @see #getVisibility()
   * @generated
   */

	void setVisibility(VisibilityKind value);







	/**
   * Returns the value of the '<em><b>Default Value</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Default Value</em>' reference.
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_DefaultValue()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='BooleanType.ownedDefaultValue(self, target);\r\n} or {\r\n\tStringType.ownedDefaultValue(self, target);\r\n} or {\r\n\tEnumeration.ownedDefaultValue(self, target);\r\n} or {\r\n\tNumericType.ownedDefaultValue(self, target);\r\n} or {\r\n\tPhysicalQuantity.ownedDefaultValue(self, target);\r\n\r\n'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allows to specify a default value for this data type\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getDefaultValue();







	/**
   * Returns the value of the '<em><b>Null Value</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Null Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Null Value</em>' reference.
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_NullValue()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='StringType.ownedNullValue(self, target);\r\n} or {\r\n\tEnumeration.ownedNullValue(self, target);\r\n} or {\r\n\tNumericType.ownedNullValue(self, target);\r\n} or {\r\n\tPhysicalQuantity.ownedNullValue(self, target);\r\n\r\n'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allows to specify the nature/value of the \"null\" value for this type of data\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getNullValue();







	/**
   * Returns the value of the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.InformationRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Information Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Information Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_OwnedInformationRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   * @generated
   */

	EList<InformationRealization> getOwnedInformationRealizations();







	/**
   * Returns the value of the '<em><b>Realized Data Types</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.datatype.DataType}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.datatype.DataType#getRealizingDataTypes <em>Realizing Data Types</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Data Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Data Types</em>' reference list.
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_RealizedDataTypes()
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getRealizingDataTypes
   * @model opposite="realizingDataTypes" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='DataType.outgoingTraces(self, ir);\r\nInformationRealization.targetElement(ir, target); '"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='class(es) realized by this class' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<DataType> getRealizedDataTypes();







	/**
   * Returns the value of the '<em><b>Realizing Data Types</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.datatype.DataType}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.datatype.DataType#getRealizedDataTypes <em>Realized Data Types</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Data Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Data Types</em>' reference list.
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#getDataType_RealizingDataTypes()
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getRealizedDataTypes
   * @model opposite="realizedDataTypes" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='DataType.incomingTraces(self, ir);\r\nInformationRealization.sourceElement(ir, target); '"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='class(es) realizing this class' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<DataType> getRealizingDataTypes();





} // DataType
