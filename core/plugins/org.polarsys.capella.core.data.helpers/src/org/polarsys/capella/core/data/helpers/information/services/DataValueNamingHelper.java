/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.information.services;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.UnaryOperator;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 */
public class DataValueNamingHelper {

  /**
   * @param feature feature to be tested
   */
  public static boolean isReferencedValue(EStructuralFeature feature) {
    return DatavaluePackage.Literals.BOOLEAN_REFERENCE__REFERENCED_VALUE.equals(feature)
           || DatavaluePackage.Literals.COMPLEX_VALUE_REFERENCE__REFERENCED_VALUE.equals(feature)
           || DatavaluePackage.Literals.ENUMERATION_REFERENCE__REFERENCED_VALUE.equals(feature)
           || DatavaluePackage.Literals.NUMERIC_REFERENCE__REFERENCED_VALUE.equals(feature)
           || DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_VALUE.equals(feature);
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(DataValue element, EStructuralFeature feature) {
    if (element instanceof AbstractEnumerationValue) {
      return getValue((AbstractEnumerationValue) element, feature);
    } else if (element instanceof AbstractBooleanValue) {
      return getValue((AbstractBooleanValue) element, feature);
    } else if (element instanceof AbstractComplexValue) {
      return getValue((AbstractComplexValue) element, feature);
    } else if (element instanceof NumericValue) {
      return getValue((NumericValue) element, feature);
    } else if (element instanceof AbstractStringValue) {
      return getValue((AbstractStringValue) element, feature);
    } else if (element instanceof CollectionValue) {
      return getValue((CollectionValue) element, feature);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(AbstractBooleanValue element, EStructuralFeature feature) {
    if (element instanceof LiteralBooleanValue) {
      return getValue((LiteralBooleanValue) element, feature);
    } else if (element instanceof BooleanReference) {
      return getValue((BooleanReference) element, feature);
    } else if (element instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element, feature);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(LiteralBooleanValue element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      return name + " = " + (element.isValue() ? "TRUE" : "FALSE"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(BooleanReference element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      AbstractBooleanValue referencedValue = element.getReferencedValue();
      if (referencedValue != null) {
        return name + " -> " + getValue(referencedValue, feature); //$NON-NLS-1$
      }
      Property referencedProperty = element.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(NumericValue element, EStructuralFeature feature) {
    if (element instanceof LiteralNumericValue) {
      return getValue((LiteralNumericValue) element, feature);
    } else if (element instanceof NumericReference) {
      return getValue((NumericReference) element, feature);
    } else if (element instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element, feature);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(AbstractStringValue element, EStructuralFeature feature) {
    if (element instanceof LiteralStringValue) {
      return getValue((LiteralStringValue) element, feature);
    } else if (element instanceof StringReference) {
      return getValue((StringReference) element, feature);
    } else if (element instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element, feature);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(CollectionValue element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if (name == null) {
        return ICommonConstants.EMPTY_STRING;
      }
      return name;
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(LiteralNumericValue element, EStructuralFeature feature) {
    if (element != null) {
      if (isReferencedValue(feature)) {
        String name = element.getName();
        if ((name != null) && !name.equals(ICommonConstants.EMPTY_STRING)) {
          return name;
        }
      }
      String value = element.getValue();
      if (value == null) {
        return ICommonConstants.EMPTY_STRING;
      }
      return value;
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(NumericReference element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      NumericValue referencedValue = element.getReferencedValue();
      if (referencedValue != null) {
        // Reference case: the name of the referenced object is returned instead of its value.
        return name + " -> " + referencedValue.getName(); //$NON-NLS-1$
      }
      Property referencedProperty = element.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(LiteralStringValue element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if ((name != null) && (!name.equals(ICommonConstants.EMPTY_STRING))) {
        return name;
      }

      String value = element.getValue();
      if (null != value) {
        return "\"" + value + "\""; //$NON-NLS-1$//$NON-NLS-2$
      }
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(StringReference element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      AbstractStringValue referencedValue = element.getReferencedValue();
      if (referencedValue != null) {
        return name + " -> " + getValue(referencedValue, feature); //$NON-NLS-1$
      }
      Property referencedProperty = element.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(AbstractEnumerationValue element, EStructuralFeature feature) {
    if (element instanceof EnumerationLiteral) {
      return getValue((EnumerationLiteral) element, feature);
    } else if (element instanceof EnumerationReference) {
      return getValue((EnumerationReference) element, feature);
    } else if (element instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element, feature);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(EnumerationReference element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      AbstractEnumerationValue referencedValue = element.getReferencedValue();
      if (referencedValue != null) {
        AbstractNamedElement eContainer = (AbstractNamedElement) referencedValue.eContainer();
        return name + " -> " + eContainer.getName() + '.' + referencedValue.getName(); //$NON-NLS-1$
      }
      Property referencedProperty = element.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(EnumerationLiteral element, EStructuralFeature feature) {
    if (element != null) {
      return element.getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(AbstractComplexValue element, EStructuralFeature feature) {
    if (element instanceof ComplexValue) {
      return getValue((ComplexValue) element, feature);
    } else if (element instanceof ComplexValueReference) {
      return getValue((ComplexValueReference) element, feature);
    } else if (element instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element, feature);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(ComplexValue element, EStructuralFeature feature) {
    if (element != null) {
      return element.getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(ComplexValueReference element, EStructuralFeature feature) {
    if (element != null) {
      String name = element.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      Property referencedProperty = element.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      AbstractComplexValue referencedValue = element.getReferencedValue();
      if (referencedValue != null) {
        return name + " -> " + getValue(referencedValue, feature); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(AbstractExpressionValue element, EStructuralFeature feature) {
    if (element instanceof BinaryExpression) {
      return getValue((BinaryExpression) element, feature);
    } else if (element instanceof UnaryExpression) {
      return getValue((UnaryExpression) element, feature);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(BinaryExpression element, EStructuralFeature feature) {
    return getValue(element, feature, false);
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   * @param forceExpand force (or not) expression expansion
   */
  public static String getValue(BinaryExpression element, EStructuralFeature feature, boolean forceExpand) {
    if (element != null) {
      String name = element.getName();
      if (((name == null) || (name.equals(ICommonConstants.EMPTY_STRING))) && BinaryOperator.UNSET.equals(element.getOperator())) {
        String exp = element.getUnparsedExpression();
        if (null == exp) {
          return ICommonConstants.EMPTY_STRING;
        }
        return exp;
      } else if (!forceExpand && (name != null) && (!name.equals(ICommonConstants.EMPTY_STRING))) {
        return name;
      }

      String operator = null;
      BinaryOperator op = element.getOperator();
      switch (op) {
        case UNSET:
          operator = Messages.getString("UndefinedValue");break; //$NON-NLS-1$
        case ADD:
          operator = Messages.getString("BinaryOperator_ADD");break; //$NON-NLS-1$
        case DIV:
          operator = Messages.getString("BinaryOperator_DIV");break; //$NON-NLS-1$
        case MAX:
          operator = Messages.getString("BinaryOperator_MAX");break; //$NON-NLS-1$
        case MIN:
          operator = Messages.getString("BinaryOperator_MIN");break; //$NON-NLS-1$
        case MUL:
          operator = Messages.getString("BinaryOperator_MUL");break; //$NON-NLS-1$
        case POW:
          operator = Messages.getString("BinaryOperator_POW");break; //$NON-NLS-1$
        case SUB:
          operator = Messages.getString("BinaryOperator_SUB");break; //$NON-NLS-1$
        case EQU:
          operator = Messages.getString("BinaryOperator_EQU");break; //$NON-NLS-1$
        case IOR:
          operator = Messages.getString("BinaryOperator_IOR");break; //$NON-NLS-1$
        case XOR:
          operator = Messages.getString("BinaryOperator_XOR");break; //$NON-NLS-1$
        case AND:
          operator = Messages.getString("BinaryOperator_AND");break; //$NON-NLS-1$
      }
      if (operator != null) {
        return operator
               + "(" + getOperandValue(element.getOwnedLeftOperand(), feature) + ", " + getOperandValue(element.getOwnedRightOperand(), feature) + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  protected static String getOperandValue(DataValue element, EStructuralFeature feature) {
    String value = null;
    if (element instanceof AbstractExpressionValue) {
      value = getValue((AbstractExpressionValue) element, feature, true);
    } else {
      value = getValue(element, feature);
    }
    return value;
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   * @param forceExpand
   */
  public static String getValue(AbstractExpressionValue element, EStructuralFeature feature, boolean forceExpand) {
    if (element instanceof UnaryExpression) {
      return getValue((UnaryExpression) element, feature, forceExpand);
    } else if (element instanceof BinaryExpression) {
      return getValue((BinaryExpression) element, feature, forceExpand);
    }
    // Should not happen.
    return null;
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   */
  public static String getValue(UnaryExpression element, EStructuralFeature feature) {
    return getValue(element, feature, false);
  }

  /**
   * @param element element whose value is requested
   * @param feature feature referencing given element
   * @param forceExpand force (or not) expression expansion
   */
  public static String getValue(UnaryExpression element, EStructuralFeature feature, boolean forceExpand) {
    if (element != null) {
      String name = element.getName();
      if (((name == null) || (name.equals(ICommonConstants.EMPTY_STRING))) && UnaryOperator.UNSET.equals(element.getOperator())) {
        String exp = element.getUnparsedExpression();
        if (null == exp) {
          return ICommonConstants.EMPTY_STRING;
        }
        return exp;
      } else if (!forceExpand && (name != null) && (!name.equals(ICommonConstants.EMPTY_STRING))) {
        return name;
      }

      String operator = null;
      UnaryOperator op = element.getOperator();
      switch (op) {
        case UNSET:
          operator = Messages.getString("UndefinedValue");break; //$NON-NLS-1$
        case NOT:
          operator = Messages.getString("UnaryOperator_NOT");break; //$NON-NLS-1$
        case POS:
          operator = Messages.getString("UnaryOperator_POS");break; //$NON-NLS-1$
        case PRE:
          operator = Messages.getString("UnaryOperator_PRE");break; //$NON-NLS-1$
        case SUC:
          operator = Messages.getString("UnaryOperator_SUC");break; //$NON-NLS-1$
        case VAL:
          operator = Messages.getString("UnaryOperator_VAL");break; //$NON-NLS-1$
      }
      if (operator != null) {
        return operator + "(" + getValue(element.getOwnedOperand(), feature) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
