/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.datatype.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Expression Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getUnparsedExpression <em>Unparsed Expression</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getExpressionType <em>Expression Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getAbstractExpressionValue()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Abstract class to support the implementation of Expression specifications\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
 * @generated
 */
public interface AbstractExpressionValue extends AbstractBooleanValue, AbstractComplexValue, AbstractEnumerationValue, NumericValue, AbstractStringValue {





	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getAbstractExpressionValue_Expression()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='textual specification of the expression\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	String getExpression();







	/**
	 * Returns the value of the '<em><b>Unparsed Expression</b></em>' attribute.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unparsed Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unparsed Expression</em>' attribute.
	 * @see #setUnparsedExpression(String)
	 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getAbstractExpressionValue_UnparsedExpression()
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='raw textual specification of the expression\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	String getUnparsedExpression();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getUnparsedExpression <em>Unparsed Expression</em>}' attribute.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unparsed Expression</em>' attribute.
	 * @see #getUnparsedExpression()
	 * @generated
	 */

	void setUnparsedExpression(String value);







	/**
	 * Returns the value of the '<em><b>Expression Type</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression Type</em>' reference.
	 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getAbstractExpressionValue_ExpressionType()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='type' featureOwner='TypedElement'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='type'"
	 *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='abstractType'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the type of the data being valued\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 *        annotation="http://www.polarsys.org/capella/semantic feature='abstractType' excludefrom='xmlpivot'"
	 * @generated
	 */

	DataType getExpressionType();





} // AbstractExpressionValue
