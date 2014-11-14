/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
   * @param feature_p feature to be tested
   */
  public static boolean isReferencedValue(EStructuralFeature feature_p) {
    return DatavaluePackage.Literals.BOOLEAN_REFERENCE__REFERENCED_VALUE.equals(feature_p)
           || DatavaluePackage.Literals.COMPLEX_VALUE_REFERENCE__REFERENCED_VALUE.equals(feature_p)
           || DatavaluePackage.Literals.ENUMERATION_REFERENCE__REFERENCED_VALUE.equals(feature_p)
           || DatavaluePackage.Literals.NUMERIC_REFERENCE__REFERENCED_VALUE.equals(feature_p)
           || DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_VALUE.equals(feature_p);
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(DataValue element_p, EStructuralFeature feature_p) {
    if (element_p instanceof AbstractEnumerationValue) {
      return getValue((AbstractEnumerationValue) element_p, feature_p);
    } else if (element_p instanceof AbstractBooleanValue) {
      return getValue((AbstractBooleanValue) element_p, feature_p);
    } else if (element_p instanceof AbstractComplexValue) {
      return getValue((AbstractComplexValue) element_p, feature_p);
    } else if (element_p instanceof NumericValue) {
      return getValue((NumericValue) element_p, feature_p);
    } else if (element_p instanceof AbstractStringValue) {
      return getValue((AbstractStringValue) element_p, feature_p);
    } else if (element_p instanceof CollectionValue) {
      return getValue((CollectionValue) element_p, feature_p);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(AbstractBooleanValue element_p, EStructuralFeature feature_p) {
    if (element_p instanceof LiteralBooleanValue) {
      return getValue((LiteralBooleanValue) element_p, feature_p);
    } else if (element_p instanceof BooleanReference) {
      return getValue((BooleanReference) element_p, feature_p);
    } else if (element_p instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element_p, feature_p);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(LiteralBooleanValue element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      return name + " = " + (element_p.isValue() ? "TRUE" : "FALSE"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(BooleanReference element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      AbstractBooleanValue referencedValue = element_p.getReferencedValue();
      if (referencedValue != null) {
        return name + " -> " + getValue(referencedValue, feature_p); //$NON-NLS-1$
      }
      Property referencedProperty = element_p.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(NumericValue element_p, EStructuralFeature feature_p) {
    if (element_p instanceof LiteralNumericValue) {
      return getValue((LiteralNumericValue) element_p, feature_p);
    } else if (element_p instanceof NumericReference) {
      return getValue((NumericReference) element_p, feature_p);
    } else if (element_p instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element_p, feature_p);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(AbstractStringValue element_p, EStructuralFeature feature_p) {
    if (element_p instanceof LiteralStringValue) {
      return getValue((LiteralStringValue) element_p, feature_p);
    } else if (element_p instanceof StringReference) {
      return getValue((StringReference) element_p, feature_p);
    } else if (element_p instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element_p, feature_p);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(CollectionValue element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (name == null) {
        return ICommonConstants.EMPTY_STRING;
      }
      return name;
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(LiteralNumericValue element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      if (isReferencedValue(feature_p)) {
        String name = element_p.getName();
        if ((name != null) && !name.equals(ICommonConstants.EMPTY_STRING)) {
          return name;
        }
      }
      String value = element_p.getValue();
      if (value == null) {
        return ICommonConstants.EMPTY_STRING;
      }
      return value;
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(NumericReference element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      NumericValue referencedValue = element_p.getReferencedValue();
      if (referencedValue != null) {
        // Reference case: the name of the referenced object is returned instead of its value.
        return name + " -> " + referencedValue.getName(); //$NON-NLS-1$
      }
      Property referencedProperty = element_p.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(LiteralStringValue element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if ((name != null) && (!name.equals(ICommonConstants.EMPTY_STRING))) {
        return name;
      }

      String value = element_p.getValue();
      if (null != value) {
        return "\"" + value + "\""; //$NON-NLS-1$//$NON-NLS-2$
      }
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(StringReference element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      AbstractStringValue referencedValue = element_p.getReferencedValue();
      if (referencedValue != null) {
        return name + " -> " + getValue(referencedValue, feature_p); //$NON-NLS-1$
      }
      Property referencedProperty = element_p.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(AbstractEnumerationValue element_p, EStructuralFeature feature_p) {
    if (element_p instanceof EnumerationLiteral) {
      return getValue((EnumerationLiteral) element_p, feature_p);
    } else if (element_p instanceof EnumerationReference) {
      return getValue((EnumerationReference) element_p, feature_p);
    } else if (element_p instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element_p, feature_p);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(EnumerationReference element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      AbstractEnumerationValue referencedValue = element_p.getReferencedValue();
      if (referencedValue != null) {
        AbstractNamedElement eContainer = (AbstractNamedElement) referencedValue.eContainer();
        return name + " -> " + eContainer.getName() + '.' + referencedValue.getName(); //$NON-NLS-1$
      }
      Property referencedProperty = element_p.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(EnumerationLiteral element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      return element_p.getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(AbstractComplexValue element_p, EStructuralFeature feature_p) {
    if (element_p instanceof ComplexValue) {
      return getValue((ComplexValue) element_p, feature_p);
    } else if (element_p instanceof ComplexValueReference) {
      return getValue((ComplexValueReference) element_p, feature_p);
    } else if (element_p instanceof AbstractExpressionValue) {
      return getValue((AbstractExpressionValue) element_p, feature_p);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(ComplexValue element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      return element_p.getName();
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(ComplexValueReference element_p, EStructuralFeature feature_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (null == name) {
        name = ICommonConstants.EMPTY_STRING;
      }

      Property referencedProperty = element_p.getReferencedProperty();
      if (referencedProperty != null) {
        return name + " -> " + InformationNamingHelper.getValue(referencedProperty); //$NON-NLS-1$
      }
      AbstractComplexValue referencedValue = element_p.getReferencedValue();
      if (referencedValue != null) {
        return name + " -> " + getValue(referencedValue, feature_p); //$NON-NLS-1$
      }
      return name + " -> " + Messages.getString("UndefinedValue"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(AbstractExpressionValue element_p, EStructuralFeature feature_p) {
    if (element_p instanceof BinaryExpression) {
      return getValue((BinaryExpression) element_p, feature_p);
    } else if (element_p instanceof UnaryExpression) {
      return getValue((UnaryExpression) element_p, feature_p);
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(BinaryExpression element_p, EStructuralFeature feature_p) {
    return getValue(element_p, feature_p, false);
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   * @param forceExpand_p force (or not) expression expansion
   */
  public static String getValue(BinaryExpression element_p, EStructuralFeature feature_p, boolean forceExpand_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (((name == null) || (name.equals(ICommonConstants.EMPTY_STRING))) && BinaryOperator.UNSET.equals(element_p.getOperator())) {
        String exp = element_p.getUnparsedExpression();
        if (null == exp) {
          return ICommonConstants.EMPTY_STRING;
        }
        return exp;
      } else if (!forceExpand_p && (name != null) && (!name.equals(ICommonConstants.EMPTY_STRING))) {
        return name;
      }

      String operator = null;
      BinaryOperator op = element_p.getOperator();
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
               + "(" + getOperandValue(element_p.getOwnedLeftOperand(), feature_p) + ", " + getOperandValue(element_p.getOwnedRightOperand(), feature_p) + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  protected static String getOperandValue(DataValue element_p, EStructuralFeature feature_p) {
    String value = null;
    if (element_p instanceof AbstractExpressionValue) {
      value = getValue((AbstractExpressionValue) element_p, feature_p, true);
    } else {
      value = getValue(element_p, feature_p);
    }
    return value;
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   * @param forceExpand_p
   */
  public static String getValue(AbstractExpressionValue element_p, EStructuralFeature feature_p, boolean forceExpand_p) {
    if (element_p instanceof UnaryExpression) {
      return getValue((UnaryExpression) element_p, feature_p, forceExpand_p);
    } else if (element_p instanceof BinaryExpression) {
      return getValue((BinaryExpression) element_p, feature_p, forceExpand_p);
    }
    // Should not happen.
    return null;
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   */
  public static String getValue(UnaryExpression element_p, EStructuralFeature feature_p) {
    return getValue(element_p, feature_p, false);
  }

  /**
   * @param element_p element whose value is requested
   * @param feature_p feature referencing given element
   * @param forceExpand_p force (or not) expression expansion
   */
  public static String getValue(UnaryExpression element_p, EStructuralFeature feature_p, boolean forceExpand_p) {
    if (element_p != null) {
      String name = element_p.getName();
      if (((name == null) || (name.equals(ICommonConstants.EMPTY_STRING))) && UnaryOperator.UNSET.equals(element_p.getOperator())) {
        String exp = element_p.getUnparsedExpression();
        if (null == exp) {
          return ICommonConstants.EMPTY_STRING;
        }
        return exp;
      } else if (!forceExpand_p && (name != null) && (!name.equals(ICommonConstants.EMPTY_STRING))) {
        return name;
      }

      String operator = null;
      UnaryOperator op = element_p.getOperator();
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
        return operator + "(" + getValue(element_p.getOwnedOperand(), feature_p) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
