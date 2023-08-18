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
package org.polarsys.capella.core.data.information.datavalue.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.information.datavalue.*;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.UnaryOperator;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DatavalueFactoryImpl extends EFactoryImpl implements DatavalueFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static DatavalueFactory init() {
    try {
      DatavalueFactory theDatavalueFactory = (DatavalueFactory)EPackage.Registry.INSTANCE.getEFactory(DatavaluePackage.eNS_URI);
      if (theDatavalueFactory != null) {
        return theDatavalueFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DatavalueFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DatavalueFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case DatavaluePackage.LITERAL_BOOLEAN_VALUE: return createLiteralBooleanValue();
      case DatavaluePackage.BOOLEAN_REFERENCE: return createBooleanReference();
      case DatavaluePackage.ENUMERATION_LITERAL: return createEnumerationLiteral();
      case DatavaluePackage.ENUMERATION_REFERENCE: return createEnumerationReference();
      case DatavaluePackage.LITERAL_STRING_VALUE: return createLiteralStringValue();
      case DatavaluePackage.STRING_REFERENCE: return createStringReference();
      case DatavaluePackage.LITERAL_NUMERIC_VALUE: return createLiteralNumericValue();
      case DatavaluePackage.NUMERIC_REFERENCE: return createNumericReference();
      case DatavaluePackage.COMPLEX_VALUE: return createComplexValue();
      case DatavaluePackage.COMPLEX_VALUE_REFERENCE: return createComplexValueReference();
      case DatavaluePackage.VALUE_PART: return createValuePart();
      case DatavaluePackage.BINARY_EXPRESSION: return createBinaryExpression();
      case DatavaluePackage.UNARY_EXPRESSION: return createUnaryExpression();
      case DatavaluePackage.OPAQUE_EXPRESSION: return createOpaqueExpression();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case DatavaluePackage.BINARY_OPERATOR:
        return createBinaryOperatorFromString(eDataType, initialValue);
      case DatavaluePackage.UNARY_OPERATOR:
        return createUnaryOperatorFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case DatavaluePackage.BINARY_OPERATOR:
        return convertBinaryOperatorToString(eDataType, instanceValue);
      case DatavaluePackage.UNARY_OPERATOR:
        return convertUnaryOperatorToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public LiteralBooleanValue createLiteralBooleanValue() {
    LiteralBooleanValueImpl literalBooleanValue = new LiteralBooleanValueImpl();
    //begin-capella-code
    literalBooleanValue.setId(IdGenerator.createId());
    //end-capella-code
    return literalBooleanValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public BooleanReference createBooleanReference() {
    BooleanReferenceImpl booleanReference = new BooleanReferenceImpl();
    //begin-capella-code
    booleanReference.setId(IdGenerator.createId());
    //end-capella-code
    return booleanReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EnumerationLiteral createEnumerationLiteral() {
    EnumerationLiteralImpl enumerationLiteral = new EnumerationLiteralImpl();
    //begin-capella-code
    enumerationLiteral.setId(IdGenerator.createId());
    //end-capella-code
    return enumerationLiteral;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EnumerationReference createEnumerationReference() {
    EnumerationReferenceImpl enumerationReference = new EnumerationReferenceImpl();
    //begin-capella-code
    enumerationReference.setId(IdGenerator.createId());
    //end-capella-code
    return enumerationReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public LiteralStringValue createLiteralStringValue() {
    LiteralStringValueImpl literalStringValue = new LiteralStringValueImpl();
    //begin-capella-code
    literalStringValue.setId(IdGenerator.createId());
    //end-capella-code
    return literalStringValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StringReference createStringReference() {
    StringReferenceImpl stringReference = new StringReferenceImpl();
    //begin-capella-code
    stringReference.setId(IdGenerator.createId());
    //end-capella-code
    return stringReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public LiteralNumericValue createLiteralNumericValue() {
    LiteralNumericValueImpl literalNumericValue = new LiteralNumericValueImpl();
    //begin-capella-code
    literalNumericValue.setId(IdGenerator.createId());
    //end-capella-code
    return literalNumericValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NumericReference createNumericReference() {
    NumericReferenceImpl numericReference = new NumericReferenceImpl();
    //begin-capella-code
    numericReference.setId(IdGenerator.createId());
    //end-capella-code
    return numericReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ComplexValue createComplexValue() {
    ComplexValueImpl complexValue = new ComplexValueImpl();
    //begin-capella-code
    complexValue.setId(IdGenerator.createId());
    //end-capella-code
    return complexValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ComplexValueReference createComplexValueReference() {
    ComplexValueReferenceImpl complexValueReference = new ComplexValueReferenceImpl();
    //begin-capella-code
    complexValueReference.setId(IdGenerator.createId());
    //end-capella-code
    return complexValueReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ValuePart createValuePart() {
    ValuePartImpl valuePart = new ValuePartImpl();
    //begin-capella-code
    valuePart.setId(IdGenerator.createId());
    //end-capella-code
    return valuePart;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public BinaryExpression createBinaryExpression() {
    BinaryExpressionImpl binaryExpression = new BinaryExpressionImpl();
    //begin-capella-code
    binaryExpression.setId(IdGenerator.createId());
    //end-capella-code
    return binaryExpression;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public UnaryExpression createUnaryExpression() {
    UnaryExpressionImpl unaryExpression = new UnaryExpressionImpl();
    //begin-capella-code
    unaryExpression.setId(IdGenerator.createId());
    //end-capella-code
    return unaryExpression;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OpaqueExpression createOpaqueExpression() {
    OpaqueExpressionImpl opaqueExpression = new OpaqueExpressionImpl();
    //begin-capella-code
    opaqueExpression.setId(IdGenerator.createId());
    //end-capella-code
    return opaqueExpression;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public BinaryOperator createBinaryOperatorFromString(EDataType eDataType, String initialValue) {
    BinaryOperator result = BinaryOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertBinaryOperatorToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public UnaryOperator createUnaryOperatorFromString(EDataType eDataType, String initialValue) {
    UnaryOperator result = UnaryOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertUnaryOperatorToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DatavaluePackage getDatavaluePackage() {
    return (DatavaluePackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static DatavaluePackage getPackage() {
    return DatavaluePackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LiteralBooleanValue createLiteralBooleanValue(String name_p) {
    LiteralBooleanValue literalBooleanValue = createLiteralBooleanValue();
    literalBooleanValue.setName(name_p);	  
    return literalBooleanValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public BooleanReference createBooleanReference(String name_p) {
    BooleanReference booleanReference = createBooleanReference();
    booleanReference.setName(name_p);	  
    return booleanReference;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EnumerationLiteral createEnumerationLiteral(String name_p) {
    EnumerationLiteral enumerationLiteral = createEnumerationLiteral();
    enumerationLiteral.setName(name_p);	  
    return enumerationLiteral;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EnumerationReference createEnumerationReference(String name_p) {
    EnumerationReference enumerationReference = createEnumerationReference();
    enumerationReference.setName(name_p);	  
    return enumerationReference;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LiteralStringValue createLiteralStringValue(String name_p) {
    LiteralStringValue literalStringValue = createLiteralStringValue();
    literalStringValue.setName(name_p);	  
    return literalStringValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public StringReference createStringReference(String name_p) {
    StringReference stringReference = createStringReference();
    stringReference.setName(name_p);	  
    return stringReference;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LiteralNumericValue createLiteralNumericValue(String name_p) {
    LiteralNumericValue literalNumericValue = createLiteralNumericValue();
    literalNumericValue.setName(name_p);	  
    return literalNumericValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public NumericReference createNumericReference(String name_p) {
    NumericReference numericReference = createNumericReference();
    numericReference.setName(name_p);	  
    return numericReference;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ComplexValue createComplexValue(String name_p) {
    ComplexValue complexValue = createComplexValue();
    complexValue.setName(name_p);	  
    return complexValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ComplexValueReference createComplexValueReference(String name_p) {
    ComplexValueReference complexValueReference = createComplexValueReference();
    complexValueReference.setName(name_p);	  
    return complexValueReference;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public BinaryExpression createBinaryExpression(String name_p) {
    BinaryExpression binaryExpression = createBinaryExpression();
    binaryExpression.setName(name_p);	  
    return binaryExpression;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public UnaryExpression createUnaryExpression(String name_p) {
    UnaryExpression unaryExpression = createUnaryExpression();
    unaryExpression.setName(name_p);	  
    return unaryExpression;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OpaqueExpression createOpaqueExpression(String name_p) {
    OpaqueExpression opaqueExpression = createOpaqueExpression();
    opaqueExpression.setName(name_p);	  
    return opaqueExpression;
  }

	//begin-capella-code

	//end-capella-code
} //DatavalueFactoryImpl
