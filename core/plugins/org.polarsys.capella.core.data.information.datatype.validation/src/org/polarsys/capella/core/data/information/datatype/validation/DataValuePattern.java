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
package org.polarsys.capella.core.data.information.datatype.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * This rule make sure value of dataValue(LiteralNumericValue and LiteralStringValue) match the pattern of there dataType. (1)If value is null or void return
 * failure message (2) if pattern is null or void return
 */
public class DataValuePattern extends AbstractValidationRule {
  @Override
  public IStatus validate(IValidationContext ctx) {
    // Get the target
    EObject eObj = ctx.getTarget();
    if ((eObj instanceof LiteralNumericValue) || (eObj instanceof LiteralStringValue)) {
      // Typing the DataValue
      DataValue dataValue = (DataValue) eObj;
      // Get the Value if the DataValue Targeted
      String value = getTargetValue(dataValue);
      // if dataValue is null or void return failure message
      if ((null == value) || value.trim().equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
        return ctx
            .createFailureStatus("value is not set for " + "\"" + CapellaElementExt.getCapellaExplorerLabel(dataValue) + "\"" + " (" + dataValue.eClass().getName() //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                                 + ")"); //$NON-NLS-1$
      }
      // Get the Type of the DataValue Targeted
      AbstractType dataValueType = dataValue.getAbstractType();
      // Store pattern string
      String pattern = null;
      // If the value of the DataValue is not null and the Type is DataType
      if (dataValueType instanceof DataType) {
        // Typing the DataType
        DataType dataType = (DataType) dataValueType;
        // Get the pattern of the DataType
        pattern = dataType.getPattern();
        // Check if the value match with DataType
        if (!checkDataValueFunctionOfDataType(pattern, value, dataType)) {
          if ((null == pattern) || pattern.trim().equals(ICommonConstants.EMPTY_STRING)) {
            if (dataType instanceof NumericType) {
              NumericTypeKind kind = ((NumericType) dataType).getKind();
              if (kind.equals(NumericTypeKind.INTEGER)) {
                pattern = "INTEGER [+-]?[1-9][0-9]*|0"; //$NON-NLS-1$
              } else if (kind.equals(NumericTypeKind.FLOAT)) {
                pattern = "FLOAT (+|-)?([0-9]*[.][0-9]+|[0-9]+[.][0-9]*)(e|E)(+|-)[0-9]+"; //$NON-NLS-1$
              }
            } else {
              pattern = "null"; //$NON-NLS-1$
            }
          }
          // If the value don't match with the type => Validation Failure
          return ctx
              .createFailureStatus(value
                                   + " value of " + "\"" + CapellaElementExt.getCapellaExplorerLabel(dataValue) + "\"" + " (" + dataValue.eClass().getName() + ")" //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                                   + " does not match the patten " + pattern); //$NON-NLS-1$
        }
      } else if ((dataValue instanceof LiteralNumericValue) || (dataValue instanceof LiteralStringValue)) {
        // Assume pattern is equal to Integer by default
        if (!parseInteger(value)) {
          // If the value don't match the default pattern
          pattern = "INTEGER [+-]?[1-9][0-9]*|0"; //$NON-NLS-1$
          return ctx.createFailureStatus(value + " value of " + "\"" + CapellaElementExt.getCapellaExplorerLabel(dataValue) + "\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                           + " (" + dataValue.eClass().getName() + ")" + " does not match the default patten " + pattern); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
      }
    }
    // Validation success
    return ctx.createSuccessStatus();
  }

  /**
   * <b>Check the Value function of its Type</b>
   * <p>
   * Check if the value of the DataValue targeted match with its DataType
   * @param pattern
   * @param value
   * @return
   */
  private boolean checkDataValueFunctionOfDataType(String pattern, String value, DataType dataType) {
    // Check if the Value is not null
    // Check if the pattern is not null or empty
    if ((null != pattern) && !pattern.trim().equals(ICommonConstants.EMPTY_STRING) && (pattern.length() > 1)) {
      // Return if the Value match with the pattern
      return tryToMatchWithPattern(value, pattern);
    }
    // If the Type is a NumericType
    else if ((null != dataType) && (dataType instanceof NumericType)) {
      // Return if the value is match with the Type Kind
      return tryToMatchWithKind(value, dataType);
    }
    // TODO consider other kind of dataType with null pattern
    return false;
  }

  /**
   * <b>Try to match value without the Pattern</b>
   * <p>
   * Try to match value of the DataValue Targeted with the Kind of its DataType
   * @param value
   * @param dataType
   * @return match
   */
  private boolean tryToMatchWithKind(String value, DataType dataType) {
    // Get the Kind of the DataType
    NumericTypeKind kind = ((NumericType) dataType).getKind();
    // Function of the Kind
    switch (kind) {
      // If it's FLOAT
      case FLOAT:
        // Try to parse the value to Float
        try {
          Float.parseFloat(value);
        } catch (NumberFormatException e) {
          // Return false in the case of Parsing Exception
          return false;
        }
      break;
      // If it's INTEGER
      case INTEGER:
        // Try to parse the value to Integer
        return parseInteger(value);
    }
    return true;
  }

  private boolean parseInteger(String value) {
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException e) {
      // Return false in the case of Parsing Exception
      return false;
    }

    return true;
  }

  /**
   * <b>Try to match value with the Pattern</b>
   * <p>
   * Try to match value of the DataValue Targeted with the Pattern of its DataType
   * @param value
   * @param pattern
   * @return match
   */
  private boolean tryToMatchWithPattern(String value, String pattern) {
    try {
      // try to compile the pattern
      Pattern p = Pattern.compile(pattern);
      // Create the Matcher between the value and the pattern
      Matcher m = p.matcher(value);
      // If the value don't respect the pattern
      if (!m.matches()) {
        // Return error
        return false;
      }
      return true;
    } catch (Exception e) {
      // Return false in the case of pattern exception
      return false;
    }
  }

  /**
   * <b>Get The Value of Target</b>
   * <p>
   * Get the Value of the DataValue Targeted or null
   * @return
   */
  private String getTargetValue(DataValue datavalue) {
    // If the value is a LiteralNumericValue
    if (datavalue instanceof LiteralNumericValue) {
      // Return its Value
      return ((LiteralNumericValue) datavalue).getValue();
    }
    // If the value is a LiteralStringValue
    else if (datavalue instanceof LiteralStringValue) {
      // Return its Value
      return ((LiteralStringValue) datavalue).getValue();
    }
    // Else return null
    return null;
  }

}
