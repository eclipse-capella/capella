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
package org.polarsys.capella.core.model.skeleton.helpers;

import java.util.List;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypeFactory;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 */
public class PredefinedTypesHelper {

  // Predefined string constants for role-dependent value names
  private static final String __MIN_NAME = ""; //$NON-NLS-1$
  private static final String __MAX_NAME = ""; //$NON-NLS-1$
  private static final String __MIN_LENGTH_NAME = ""; //$NON-NLS-1$
  private static final String __MAX_LENGTH_NAME = ""; //$NON-NLS-1$
  // Visibility for all predefined types
  private static final VisibilityKind __PREDEFINED_TYPE_VISIBILITY = VisibilityKind.PUBLIC;

  protected static final String __ZERO_VALUE = "0"; //$NON-NLS-1$
  protected static final String __ONE_VALUE = "1"; //$NON-NLS-1$
  protected static final String __8BITS_MAX_VALUE = "255"; //$NON-NLS-1$

  /**
   * Fills the DataPkg with basic instances of subclasses of DataType
   */
  public static void createPredefinedDataTypes(DataPkg dtRootPkg_p) {

    DataPkg dtRootPkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name);
    dtRootPkg_p.getOwnedDataPkgs().add(dtRootPkg);

    NumericType unsignedShort = newNumericType(NamingConstants.PredefinedTypesCmd_unsignedShort_name, true, NumericTypeKind.INTEGER,
      true, newLiteralNumericValue(__MIN_NAME, __ZERO_VALUE), false, null);

    // Predefined data types
    List<DataType> predefinedDataTypes = dtRootPkg.getOwnedDataTypes();
    predefinedDataTypes.add(newPredefinedBoolean());
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_byte_name, true, NumericTypeKind.INTEGER,
      true, newLiteralNumericValue(__MIN_NAME, __ZERO_VALUE), true, newLiteralNumericValue(__MAX_NAME, __8BITS_MAX_VALUE)));
    predefinedDataTypes.add(newPredefinedChar(unsignedShort));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_double_name, false, NumericTypeKind.FLOAT));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_float_name, false, NumericTypeKind.FLOAT));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_hexadecimal_name, true, NumericTypeKind.INTEGER,
      true, newLiteralNumericValue(__MIN_NAME, __ZERO_VALUE), true, newHexadecimalMaxValue()));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_integer_name, true, NumericTypeKind.INTEGER));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_long_name, true, NumericTypeKind.INTEGER));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_longLong_name, true, NumericTypeKind.INTEGER));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_short_name, true, NumericTypeKind.INTEGER));
    predefinedDataTypes.add(newPredefinedString(NamingConstants.PredefinedTypesCmd_string_name));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_unsignedInteger_name, true, NumericTypeKind.INTEGER,
      true, newLiteralNumericValue(__MIN_NAME, __ZERO_VALUE), false, null));
    predefinedDataTypes.add(unsignedShort);
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_unsignedLong_name, true, NumericTypeKind.INTEGER,
      true, newLiteralNumericValue(__MIN_NAME, __ZERO_VALUE), false, null));
    predefinedDataTypes.add(newNumericType(NamingConstants.PredefinedTypesCmd_unsignedLongLong_name, true, NumericTypeKind.INTEGER,
      true, newLiteralNumericValue(__MIN_NAME, __ZERO_VALUE), false, null));
  }

  private static BooleanType newPredefinedBoolean() {
    // Boolean
    BooleanType result = DatatypeFactory.eINSTANCE.createBooleanType();
    result.setVisibility(__PREDEFINED_TYPE_VISIBILITY);
    result.setName(NamingConstants.PredefinedTypesCmd_boolean_name);
    result.setDiscrete(true);
    // True
    LiteralBooleanValue tValue = DatavalueFactory.eINSTANCE.createLiteralBooleanValue();
    tValue.setName(NamingConstants.PredefinedTypesCmd_trueValue_name);
    tValue.setValue(true);
    tValue.setAbstractType(result);
    result.getOwnedLiterals().add(tValue);
    // False
    LiteralBooleanValue fValue = DatavalueFactory.eINSTANCE.createLiteralBooleanValue();
    fValue.setName(NamingConstants.PredefinedTypesCmd_falseValue_name);
    fValue.setValue(false);
    fValue.setAbstractType(result);
    result.getOwnedLiterals().add(fValue);
    return result;
  }

  /**
   * 
   */
  private static NumericType newNumericType(String name_p, boolean discrete_p, NumericTypeKind kind_p, boolean minInclusive_p, LiteralNumericValue minValue_p, boolean maxInclusive_p, NumericValue maxValue_p) {
    NumericType result = newNumericType(name_p, discrete_p, kind_p);
    if (null != minValue_p) {
      minValue_p.setAbstractType(result);
      result.setOwnedMinValue(minValue_p);
    }
    if (null != maxValue_p) {
      maxValue_p.setAbstractType(result);
      result.setOwnedMaxValue(maxValue_p);
    }
    result.setMinInclusive(minInclusive_p);
    result.setMaxInclusive(maxInclusive_p);
    return result;
  }

  /**
   * 
   */
  private static NumericType newNumericType(String name_p, boolean discrete_p, NumericTypeKind kind_p) {
    NumericType result = DatatypeFactory.eINSTANCE.createNumericType();
    result.setVisibility(__PREDEFINED_TYPE_VISIBILITY);
    result.setName(name_p);
    result.setDiscrete(discrete_p);
    result.setKind(kind_p);
    return result;
  }
  
  /**
   * Returns a new StringType representing the usual Char type
   * @param cartType_p the NumericType used for defining lengths and cards
   */
  private static StringType newPredefinedChar(NumericType cardType_p) {
    StringType result = newPredefinedString(NamingConstants.PredefinedTypesCmd_char_name);
    result.setOwnedMinLength(newLiteralNumericValue(__MIN_LENGTH_NAME, __ONE_VALUE, cardType_p));
    result.setOwnedMaxLength(newLiteralNumericValue(__MAX_LENGTH_NAME, __ONE_VALUE, cardType_p));
    return result;
  }

  /**
   * 
   */
  private static StringType newPredefinedString(String name_p) {
    StringType result = DatatypeFactory.eINSTANCE.createStringType();
    result.setVisibility(__PREDEFINED_TYPE_VISIBILITY);
    result.setName(name_p);
    result.setDiscrete(true);
    return result;
  }

  /**
   * Helper method that creates a Numeric Value with the given name, value and type.
   */
  private static LiteralNumericValue newLiteralNumericValue(final String name_p, final String value_p, NumericType type_p) {
    LiteralNumericValue result = newLiteralNumericValue(name_p, value_p);
    result.setAbstractType(type_p);
    return result;
  }

  /**
   * Helper method that creates a Numeric Value with the given name and value.
   */
  private static LiteralNumericValue newLiteralNumericValue(final String name_p, final String value_p) {
    LiteralNumericValue result = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
    result.setName(name_p);
    result.setValue(value_p);
    return result;
  }

  /**
   *
   */
  private static BinaryExpression newHexadecimalMaxValue() {
    BinaryExpression subExp = DatavalueFactory.eINSTANCE.createBinaryExpression();
    subExp.setOperator(BinaryOperator.SUB);
    BinaryExpression powExp = DatavalueFactory.eINSTANCE.createBinaryExpression();
    powExp.setOperator(BinaryOperator.POW);
    LiteralNumericValue subExp_rightExp = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
    subExp_rightExp.setValue("1"); //$NON-NLS-1$
    LiteralNumericValue powExp_leftExp = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
    powExp_leftExp.setValue("2"); //$NON-NLS-1$
    LiteralNumericValue powExp_rightExp = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
    powExp_rightExp.setValue("64"); //$NON-NLS-1$

    subExp.setOwnedLeftOperand(powExp);
    subExp.setOwnedRightOperand(subExp_rightExp);
    powExp.setOwnedLeftOperand(powExp_leftExp);
    powExp.setOwnedRightOperand(powExp_rightExp);
    
    return subExp;
  }
}
