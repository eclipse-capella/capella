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
package org.polarsys.capella.core.data.information.datavalue.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.information.datavalue.*;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage
 * @generated
 */
public class DatavalueSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static DatavaluePackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DatavalueSwitch() {
    if (modelPackage == null) {
      modelPackage = DatavaluePackage.eINSTANCE;
    }
  }

	/**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case DatavaluePackage.DATA_VALUE: {
        DataValue dataValue = (DataValue)theEObject;
        T result = caseDataValue(dataValue);
        if (result == null) result = caseNamedElement(dataValue);
        if (result == null) result = caseValueSpecification(dataValue);
        if (result == null) result = caseCapellaElement(dataValue);
        if (result == null) result = caseAbstractTypedElement(dataValue);
        if (result == null) result = caseAbstractNamedElement(dataValue);
        if (result == null) result = caseTraceableElement(dataValue);
        if (result == null) result = casePublishableElement(dataValue);
        if (result == null) result = caseModelElement(dataValue);
        if (result == null) result = caseExtensibleElement(dataValue);
        if (result == null) result = caseElement(dataValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.DATA_VALUE_CONTAINER: {
        DataValueContainer dataValueContainer = (DataValueContainer)theEObject;
        T result = caseDataValueContainer(dataValueContainer);
        if (result == null) result = caseStructure(dataValueContainer);
        if (result == null) result = caseNamespace(dataValueContainer);
        if (result == null) result = caseNamedElement(dataValueContainer);
        if (result == null) result = caseAbstractNamedElement(dataValueContainer);
        if (result == null) result = caseCapellaElement(dataValueContainer);
        if (result == null) result = caseTraceableElement(dataValueContainer);
        if (result == null) result = casePublishableElement(dataValueContainer);
        if (result == null) result = caseModelElement(dataValueContainer);
        if (result == null) result = caseExtensibleElement(dataValueContainer);
        if (result == null) result = caseElement(dataValueContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.ABSTRACT_BOOLEAN_VALUE: {
        AbstractBooleanValue abstractBooleanValue = (AbstractBooleanValue)theEObject;
        T result = caseAbstractBooleanValue(abstractBooleanValue);
        if (result == null) result = caseDataValue(abstractBooleanValue);
        if (result == null) result = caseNamedElement(abstractBooleanValue);
        if (result == null) result = caseValueSpecification(abstractBooleanValue);
        if (result == null) result = caseCapellaElement(abstractBooleanValue);
        if (result == null) result = caseAbstractTypedElement(abstractBooleanValue);
        if (result == null) result = caseAbstractNamedElement(abstractBooleanValue);
        if (result == null) result = caseTraceableElement(abstractBooleanValue);
        if (result == null) result = casePublishableElement(abstractBooleanValue);
        if (result == null) result = caseModelElement(abstractBooleanValue);
        if (result == null) result = caseExtensibleElement(abstractBooleanValue);
        if (result == null) result = caseElement(abstractBooleanValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.LITERAL_BOOLEAN_VALUE: {
        LiteralBooleanValue literalBooleanValue = (LiteralBooleanValue)theEObject;
        T result = caseLiteralBooleanValue(literalBooleanValue);
        if (result == null) result = caseAbstractBooleanValue(literalBooleanValue);
        if (result == null) result = caseDataValue(literalBooleanValue);
        if (result == null) result = caseNamedElement(literalBooleanValue);
        if (result == null) result = caseValueSpecification(literalBooleanValue);
        if (result == null) result = caseCapellaElement(literalBooleanValue);
        if (result == null) result = caseAbstractTypedElement(literalBooleanValue);
        if (result == null) result = caseAbstractNamedElement(literalBooleanValue);
        if (result == null) result = caseTraceableElement(literalBooleanValue);
        if (result == null) result = casePublishableElement(literalBooleanValue);
        if (result == null) result = caseModelElement(literalBooleanValue);
        if (result == null) result = caseExtensibleElement(literalBooleanValue);
        if (result == null) result = caseElement(literalBooleanValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.BOOLEAN_REFERENCE: {
        BooleanReference booleanReference = (BooleanReference)theEObject;
        T result = caseBooleanReference(booleanReference);
        if (result == null) result = caseAbstractBooleanValue(booleanReference);
        if (result == null) result = caseDataValue(booleanReference);
        if (result == null) result = caseNamedElement(booleanReference);
        if (result == null) result = caseValueSpecification(booleanReference);
        if (result == null) result = caseCapellaElement(booleanReference);
        if (result == null) result = caseAbstractTypedElement(booleanReference);
        if (result == null) result = caseAbstractNamedElement(booleanReference);
        if (result == null) result = caseTraceableElement(booleanReference);
        if (result == null) result = casePublishableElement(booleanReference);
        if (result == null) result = caseModelElement(booleanReference);
        if (result == null) result = caseExtensibleElement(booleanReference);
        if (result == null) result = caseElement(booleanReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.ABSTRACT_ENUMERATION_VALUE: {
        AbstractEnumerationValue abstractEnumerationValue = (AbstractEnumerationValue)theEObject;
        T result = caseAbstractEnumerationValue(abstractEnumerationValue);
        if (result == null) result = caseDataValue(abstractEnumerationValue);
        if (result == null) result = caseNamedElement(abstractEnumerationValue);
        if (result == null) result = caseValueSpecification(abstractEnumerationValue);
        if (result == null) result = caseCapellaElement(abstractEnumerationValue);
        if (result == null) result = caseAbstractTypedElement(abstractEnumerationValue);
        if (result == null) result = caseAbstractNamedElement(abstractEnumerationValue);
        if (result == null) result = caseTraceableElement(abstractEnumerationValue);
        if (result == null) result = casePublishableElement(abstractEnumerationValue);
        if (result == null) result = caseModelElement(abstractEnumerationValue);
        if (result == null) result = caseExtensibleElement(abstractEnumerationValue);
        if (result == null) result = caseElement(abstractEnumerationValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.ENUMERATION_LITERAL: {
        EnumerationLiteral enumerationLiteral = (EnumerationLiteral)theEObject;
        T result = caseEnumerationLiteral(enumerationLiteral);
        if (result == null) result = caseAbstractEnumerationValue(enumerationLiteral);
        if (result == null) result = caseDataValue(enumerationLiteral);
        if (result == null) result = caseNamedElement(enumerationLiteral);
        if (result == null) result = caseValueSpecification(enumerationLiteral);
        if (result == null) result = caseCapellaElement(enumerationLiteral);
        if (result == null) result = caseAbstractTypedElement(enumerationLiteral);
        if (result == null) result = caseAbstractNamedElement(enumerationLiteral);
        if (result == null) result = caseTraceableElement(enumerationLiteral);
        if (result == null) result = casePublishableElement(enumerationLiteral);
        if (result == null) result = caseModelElement(enumerationLiteral);
        if (result == null) result = caseExtensibleElement(enumerationLiteral);
        if (result == null) result = caseElement(enumerationLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.ENUMERATION_REFERENCE: {
        EnumerationReference enumerationReference = (EnumerationReference)theEObject;
        T result = caseEnumerationReference(enumerationReference);
        if (result == null) result = caseAbstractEnumerationValue(enumerationReference);
        if (result == null) result = caseDataValue(enumerationReference);
        if (result == null) result = caseNamedElement(enumerationReference);
        if (result == null) result = caseValueSpecification(enumerationReference);
        if (result == null) result = caseCapellaElement(enumerationReference);
        if (result == null) result = caseAbstractTypedElement(enumerationReference);
        if (result == null) result = caseAbstractNamedElement(enumerationReference);
        if (result == null) result = caseTraceableElement(enumerationReference);
        if (result == null) result = casePublishableElement(enumerationReference);
        if (result == null) result = caseModelElement(enumerationReference);
        if (result == null) result = caseExtensibleElement(enumerationReference);
        if (result == null) result = caseElement(enumerationReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.ABSTRACT_STRING_VALUE: {
        AbstractStringValue abstractStringValue = (AbstractStringValue)theEObject;
        T result = caseAbstractStringValue(abstractStringValue);
        if (result == null) result = caseDataValue(abstractStringValue);
        if (result == null) result = caseNamedElement(abstractStringValue);
        if (result == null) result = caseValueSpecification(abstractStringValue);
        if (result == null) result = caseCapellaElement(abstractStringValue);
        if (result == null) result = caseAbstractTypedElement(abstractStringValue);
        if (result == null) result = caseAbstractNamedElement(abstractStringValue);
        if (result == null) result = caseTraceableElement(abstractStringValue);
        if (result == null) result = casePublishableElement(abstractStringValue);
        if (result == null) result = caseModelElement(abstractStringValue);
        if (result == null) result = caseExtensibleElement(abstractStringValue);
        if (result == null) result = caseElement(abstractStringValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.LITERAL_STRING_VALUE: {
        LiteralStringValue literalStringValue = (LiteralStringValue)theEObject;
        T result = caseLiteralStringValue(literalStringValue);
        if (result == null) result = caseAbstractStringValue(literalStringValue);
        if (result == null) result = caseDataValue(literalStringValue);
        if (result == null) result = caseNamedElement(literalStringValue);
        if (result == null) result = caseValueSpecification(literalStringValue);
        if (result == null) result = caseCapellaElement(literalStringValue);
        if (result == null) result = caseAbstractTypedElement(literalStringValue);
        if (result == null) result = caseAbstractNamedElement(literalStringValue);
        if (result == null) result = caseTraceableElement(literalStringValue);
        if (result == null) result = casePublishableElement(literalStringValue);
        if (result == null) result = caseModelElement(literalStringValue);
        if (result == null) result = caseExtensibleElement(literalStringValue);
        if (result == null) result = caseElement(literalStringValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.STRING_REFERENCE: {
        StringReference stringReference = (StringReference)theEObject;
        T result = caseStringReference(stringReference);
        if (result == null) result = caseAbstractStringValue(stringReference);
        if (result == null) result = caseDataValue(stringReference);
        if (result == null) result = caseNamedElement(stringReference);
        if (result == null) result = caseValueSpecification(stringReference);
        if (result == null) result = caseCapellaElement(stringReference);
        if (result == null) result = caseAbstractTypedElement(stringReference);
        if (result == null) result = caseAbstractNamedElement(stringReference);
        if (result == null) result = caseTraceableElement(stringReference);
        if (result == null) result = casePublishableElement(stringReference);
        if (result == null) result = caseModelElement(stringReference);
        if (result == null) result = caseExtensibleElement(stringReference);
        if (result == null) result = caseElement(stringReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.NUMERIC_VALUE: {
        NumericValue numericValue = (NumericValue)theEObject;
        T result = caseNumericValue(numericValue);
        if (result == null) result = caseDataValue(numericValue);
        if (result == null) result = caseNamedElement(numericValue);
        if (result == null) result = caseValueSpecification(numericValue);
        if (result == null) result = caseCapellaElement(numericValue);
        if (result == null) result = caseAbstractTypedElement(numericValue);
        if (result == null) result = caseAbstractNamedElement(numericValue);
        if (result == null) result = caseTraceableElement(numericValue);
        if (result == null) result = casePublishableElement(numericValue);
        if (result == null) result = caseModelElement(numericValue);
        if (result == null) result = caseExtensibleElement(numericValue);
        if (result == null) result = caseElement(numericValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.LITERAL_NUMERIC_VALUE: {
        LiteralNumericValue literalNumericValue = (LiteralNumericValue)theEObject;
        T result = caseLiteralNumericValue(literalNumericValue);
        if (result == null) result = caseNumericValue(literalNumericValue);
        if (result == null) result = caseDataValue(literalNumericValue);
        if (result == null) result = caseNamedElement(literalNumericValue);
        if (result == null) result = caseValueSpecification(literalNumericValue);
        if (result == null) result = caseCapellaElement(literalNumericValue);
        if (result == null) result = caseAbstractTypedElement(literalNumericValue);
        if (result == null) result = caseAbstractNamedElement(literalNumericValue);
        if (result == null) result = caseTraceableElement(literalNumericValue);
        if (result == null) result = casePublishableElement(literalNumericValue);
        if (result == null) result = caseModelElement(literalNumericValue);
        if (result == null) result = caseExtensibleElement(literalNumericValue);
        if (result == null) result = caseElement(literalNumericValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.NUMERIC_REFERENCE: {
        NumericReference numericReference = (NumericReference)theEObject;
        T result = caseNumericReference(numericReference);
        if (result == null) result = caseNumericValue(numericReference);
        if (result == null) result = caseDataValue(numericReference);
        if (result == null) result = caseNamedElement(numericReference);
        if (result == null) result = caseValueSpecification(numericReference);
        if (result == null) result = caseCapellaElement(numericReference);
        if (result == null) result = caseAbstractTypedElement(numericReference);
        if (result == null) result = caseAbstractNamedElement(numericReference);
        if (result == null) result = caseTraceableElement(numericReference);
        if (result == null) result = casePublishableElement(numericReference);
        if (result == null) result = caseModelElement(numericReference);
        if (result == null) result = caseExtensibleElement(numericReference);
        if (result == null) result = caseElement(numericReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.ABSTRACT_COMPLEX_VALUE: {
        AbstractComplexValue abstractComplexValue = (AbstractComplexValue)theEObject;
        T result = caseAbstractComplexValue(abstractComplexValue);
        if (result == null) result = caseDataValue(abstractComplexValue);
        if (result == null) result = caseNamedElement(abstractComplexValue);
        if (result == null) result = caseValueSpecification(abstractComplexValue);
        if (result == null) result = caseCapellaElement(abstractComplexValue);
        if (result == null) result = caseAbstractTypedElement(abstractComplexValue);
        if (result == null) result = caseAbstractNamedElement(abstractComplexValue);
        if (result == null) result = caseTraceableElement(abstractComplexValue);
        if (result == null) result = casePublishableElement(abstractComplexValue);
        if (result == null) result = caseModelElement(abstractComplexValue);
        if (result == null) result = caseExtensibleElement(abstractComplexValue);
        if (result == null) result = caseElement(abstractComplexValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.COMPLEX_VALUE: {
        ComplexValue complexValue = (ComplexValue)theEObject;
        T result = caseComplexValue(complexValue);
        if (result == null) result = caseAbstractComplexValue(complexValue);
        if (result == null) result = caseDataValue(complexValue);
        if (result == null) result = caseNamedElement(complexValue);
        if (result == null) result = caseValueSpecification(complexValue);
        if (result == null) result = caseCapellaElement(complexValue);
        if (result == null) result = caseAbstractTypedElement(complexValue);
        if (result == null) result = caseAbstractNamedElement(complexValue);
        if (result == null) result = caseTraceableElement(complexValue);
        if (result == null) result = casePublishableElement(complexValue);
        if (result == null) result = caseModelElement(complexValue);
        if (result == null) result = caseExtensibleElement(complexValue);
        if (result == null) result = caseElement(complexValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.COMPLEX_VALUE_REFERENCE: {
        ComplexValueReference complexValueReference = (ComplexValueReference)theEObject;
        T result = caseComplexValueReference(complexValueReference);
        if (result == null) result = caseAbstractComplexValue(complexValueReference);
        if (result == null) result = caseDataValue(complexValueReference);
        if (result == null) result = caseNamedElement(complexValueReference);
        if (result == null) result = caseValueSpecification(complexValueReference);
        if (result == null) result = caseCapellaElement(complexValueReference);
        if (result == null) result = caseAbstractTypedElement(complexValueReference);
        if (result == null) result = caseAbstractNamedElement(complexValueReference);
        if (result == null) result = caseTraceableElement(complexValueReference);
        if (result == null) result = casePublishableElement(complexValueReference);
        if (result == null) result = caseModelElement(complexValueReference);
        if (result == null) result = caseExtensibleElement(complexValueReference);
        if (result == null) result = caseElement(complexValueReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.VALUE_PART: {
        ValuePart valuePart = (ValuePart)theEObject;
        T result = caseValuePart(valuePart);
        if (result == null) result = caseCapellaElement(valuePart);
        if (result == null) result = caseTraceableElement(valuePart);
        if (result == null) result = casePublishableElement(valuePart);
        if (result == null) result = caseModelElement(valuePart);
        if (result == null) result = caseExtensibleElement(valuePart);
        if (result == null) result = caseElement(valuePart);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE: {
        AbstractExpressionValue abstractExpressionValue = (AbstractExpressionValue)theEObject;
        T result = caseAbstractExpressionValue(abstractExpressionValue);
        if (result == null) result = caseAbstractBooleanValue(abstractExpressionValue);
        if (result == null) result = caseAbstractComplexValue(abstractExpressionValue);
        if (result == null) result = caseAbstractEnumerationValue(abstractExpressionValue);
        if (result == null) result = caseNumericValue(abstractExpressionValue);
        if (result == null) result = caseAbstractStringValue(abstractExpressionValue);
        if (result == null) result = caseDataValue(abstractExpressionValue);
        if (result == null) result = caseNamedElement(abstractExpressionValue);
        if (result == null) result = caseValueSpecification(abstractExpressionValue);
        if (result == null) result = caseCapellaElement(abstractExpressionValue);
        if (result == null) result = caseAbstractTypedElement(abstractExpressionValue);
        if (result == null) result = caseAbstractNamedElement(abstractExpressionValue);
        if (result == null) result = caseTraceableElement(abstractExpressionValue);
        if (result == null) result = casePublishableElement(abstractExpressionValue);
        if (result == null) result = caseModelElement(abstractExpressionValue);
        if (result == null) result = caseExtensibleElement(abstractExpressionValue);
        if (result == null) result = caseElement(abstractExpressionValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.BINARY_EXPRESSION: {
        BinaryExpression binaryExpression = (BinaryExpression)theEObject;
        T result = caseBinaryExpression(binaryExpression);
        if (result == null) result = caseAbstractExpressionValue(binaryExpression);
        if (result == null) result = caseAbstractBooleanValue(binaryExpression);
        if (result == null) result = caseAbstractComplexValue(binaryExpression);
        if (result == null) result = caseAbstractEnumerationValue(binaryExpression);
        if (result == null) result = caseNumericValue(binaryExpression);
        if (result == null) result = caseAbstractStringValue(binaryExpression);
        if (result == null) result = caseDataValue(binaryExpression);
        if (result == null) result = caseNamedElement(binaryExpression);
        if (result == null) result = caseValueSpecification(binaryExpression);
        if (result == null) result = caseCapellaElement(binaryExpression);
        if (result == null) result = caseAbstractTypedElement(binaryExpression);
        if (result == null) result = caseAbstractNamedElement(binaryExpression);
        if (result == null) result = caseTraceableElement(binaryExpression);
        if (result == null) result = casePublishableElement(binaryExpression);
        if (result == null) result = caseModelElement(binaryExpression);
        if (result == null) result = caseExtensibleElement(binaryExpression);
        if (result == null) result = caseElement(binaryExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.UNARY_EXPRESSION: {
        UnaryExpression unaryExpression = (UnaryExpression)theEObject;
        T result = caseUnaryExpression(unaryExpression);
        if (result == null) result = caseAbstractExpressionValue(unaryExpression);
        if (result == null) result = caseAbstractBooleanValue(unaryExpression);
        if (result == null) result = caseAbstractComplexValue(unaryExpression);
        if (result == null) result = caseAbstractEnumerationValue(unaryExpression);
        if (result == null) result = caseNumericValue(unaryExpression);
        if (result == null) result = caseAbstractStringValue(unaryExpression);
        if (result == null) result = caseDataValue(unaryExpression);
        if (result == null) result = caseNamedElement(unaryExpression);
        if (result == null) result = caseValueSpecification(unaryExpression);
        if (result == null) result = caseCapellaElement(unaryExpression);
        if (result == null) result = caseAbstractTypedElement(unaryExpression);
        if (result == null) result = caseAbstractNamedElement(unaryExpression);
        if (result == null) result = caseTraceableElement(unaryExpression);
        if (result == null) result = casePublishableElement(unaryExpression);
        if (result == null) result = caseModelElement(unaryExpression);
        if (result == null) result = caseExtensibleElement(unaryExpression);
        if (result == null) result = caseElement(unaryExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatavaluePackage.OPAQUE_EXPRESSION: {
        OpaqueExpression opaqueExpression = (OpaqueExpression)theEObject;
        T result = caseOpaqueExpression(opaqueExpression);
        if (result == null) result = caseCapellaElement(opaqueExpression);
        if (result == null) result = caseValueSpecification(opaqueExpression);
        if (result == null) result = caseTraceableElement(opaqueExpression);
        if (result == null) result = casePublishableElement(opaqueExpression);
        if (result == null) result = caseAbstractTypedElement(opaqueExpression);
        if (result == null) result = caseAbstractNamedElement(opaqueExpression);
        if (result == null) result = caseModelElement(opaqueExpression);
        if (result == null) result = caseExtensibleElement(opaqueExpression);
        if (result == null) result = caseElement(opaqueExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Data Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDataValue(DataValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Data Value Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Value Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDataValueContainer(DataValueContainer object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Boolean Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Boolean Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractBooleanValue(AbstractBooleanValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Literal Boolean Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal Boolean Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLiteralBooleanValue(LiteralBooleanValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Reference</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBooleanReference(BooleanReference object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Enumeration Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Enumeration Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractEnumerationValue(AbstractEnumerationValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEnumerationLiteral(EnumerationLiteral object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration Reference</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEnumerationReference(EnumerationReference object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract String Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract String Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractStringValue(AbstractStringValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Literal String Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal String Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLiteralStringValue(LiteralStringValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>String Reference</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStringReference(StringReference object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Numeric Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Numeric Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNumericValue(NumericValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Literal Numeric Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal Numeric Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLiteralNumericValue(LiteralNumericValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Numeric Reference</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Numeric Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNumericReference(NumericReference object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Complex Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Complex Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractComplexValue(AbstractComplexValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Complex Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Complex Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComplexValue(ComplexValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Complex Value Reference</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Complex Value Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComplexValueReference(ComplexValueReference object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Value Part</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Value Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseValuePart(ValuePart object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Expression Value</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Expression Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractExpressionValue(AbstractExpressionValue object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Binary Expression</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Binary Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBinaryExpression(BinaryExpression object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseUnaryExpression(UnaryExpression object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Opaque Expression</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Opaque Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOpaqueExpression(OpaqueExpression object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseElement(Element object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExtensibleElement(ExtensibleElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModelElement(ModelElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractNamedElement(AbstractNamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTraceableElement(TraceableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePublishableElement(PublishableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapellaElement(CapellaElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedElement(NamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Typed Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Typed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractTypedElement(AbstractTypedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Value Specification</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Value Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseValueSpecification(ValueSpecification object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamespace(Namespace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStructure(Structure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
	@Override
	public T defaultCase(EObject object) {
    return null;
  }

} //DatavalueSwitch
