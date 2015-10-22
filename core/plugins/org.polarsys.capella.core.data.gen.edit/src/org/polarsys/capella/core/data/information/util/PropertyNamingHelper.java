/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.util;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

public class PropertyNamingHelper {

  public static String getSymbolIfPropertyIsDerived(Property property) {
    if (null != property) {
      if (property.isIsDerived()) {
        return String.valueOf(ICommonConstants.SLASH_CHARACTER);
      }
    }

    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * convert a multiplicity to a string used in common.odesign and context.odesign
   * @param element
   * @return
   */
  public static String multiplicityToStringDisplay(MultiplicityElement element) {
    NumericValue ownedMinCard = element.getOwnedMinCard();
    NumericValue ownedMaxCard = element.getOwnedMaxCard();
    if ((ownedMinCard == null) && (ownedMaxCard == null)) {
      return "[undefined]"; //$NON-NLS-1$
    }

    String minCard = getCardValue(ownedMinCard);
    String maxCard = getCardValue(ownedMaxCard);

    // rule 1: if min = max display max only (except if min and max equals 1, display void)
    boolean displayNothing = false;
    boolean displayOnlyMax = false;
    if (minCard.equalsIgnoreCase(maxCard) && !minCard.equals(ICommonConstants.EMPTY_STRING) && !maxCard.equals(ICommonConstants.EMPTY_STRING)) {
      if (minCard.equalsIgnoreCase("1")) { //$NON-NLS-1$
        displayNothing = true;
      } else {
        displayOnlyMax = true;
      }
    }

    // rule 2: if min=0 and max=* display only max
    if (minCard.equalsIgnoreCase("0") && maxCard.equalsIgnoreCase("*")) { //$NON-NLS-1$ //$NON-NLS-2$
      displayOnlyMax = true;
    }

    // rule 3: if minCard or maxCard are named, display the names
    String ownedminCardName = ownedMinCard.getName();
    String ownedmaxCardName = ownedMaxCard.getName();
    if ((null != ownedminCardName) && !ownedminCardName.equals(ICommonConstants.EMPTY_STRING)) {
      minCard = ownedminCardName;
      displayNothing = false;
      displayOnlyMax = false;
    }
    if ((null != ownedmaxCardName) && !ownedmaxCardName.equals(ICommonConstants.EMPTY_STRING)) {
      maxCard = ownedmaxCardName;
      displayNothing = false;
      displayOnlyMax = false;
    }

    if (displayNothing) {
      return ICommonConstants.EMPTY_STRING;
    } else if (displayOnlyMax) {
      return "[" //$NON-NLS-1$
             + maxCard + "]"; //$NON-NLS-1$
    }

    return "[" //$NON-NLS-1$
           + minCard + ".." //$NON-NLS-1$
           + maxCard + "]"; //$NON-NLS-1$
  }

  /**
   * Return cardValue or cardName depending on the NumericValue if 'numericValue' Type is LiteralNumericValue - return its value if 'numericValue' Type is
   * AbstractExpression - return its Name if 'numericValue' Type is NumericReference - return its Name [if referencedProperty - calculate cardName as
   * (OwnerClass name :: referencedPropertyName)
   * @param numericValue
   * @return cardValue or cardName depending on the NumericValue
   */
  static public String getCardValue(NumericValue numericValue) {
    String cardValue = ICommonConstants.EMPTY_STRING;
    if (numericValue == null) {
      return ICommonConstants.EMPTY_STRING;
    }

    if (numericValue instanceof LiteralNumericValue) {
      cardValue = getLiteralNumericValue((LiteralNumericValue) numericValue);
    } else if (numericValue instanceof AbstractExpressionValue) {
      cardValue = getAbstractExpressionExp((AbstractExpressionValue) numericValue);
    } else if (numericValue instanceof NumericReference) {
      NumericReference ref = (NumericReference) numericValue;
      Property referencedProperty = ref.getReferencedProperty();
      if (referencedProperty != null) {
        EObject container = referencedProperty.eContainer();
        if (container instanceof Class) {
          cardValue = ((Class) container).getName() + "::" + referencedProperty.getName(); //$NON-NLS-1$
        } else {
          cardValue = referencedProperty.getName();
        }
      } else {
        NumericValue referencedValue = ref.getReferencedValue();
        if (referencedValue != null) {
          if (referencedValue instanceof NumericReference) {
            cardValue = ((NumericReference) referencedValue).getName();
          } else if (referencedValue instanceof LiteralNumericValue) {
            cardValue = getLiteralNumericValue((LiteralNumericValue) referencedValue);
          } else if (referencedValue instanceof AbstractExpressionValue) {
            cardValue = getAbstractExpressionExp((AbstractExpressionValue) referencedValue);
          }
        }
      }
    }

    return cardValue;
  }

  /**
   * @return expression (if null return numericValue name)
   */
  static private String getAbstractExpressionExp(AbstractExpressionValue numValue) {
    String cardValue;
    AbstractExpressionValue expValue = numValue;
    String expression = expValue.getExpression();
    if (expression != null) {
      cardValue = expression;
    } else {
      cardValue = expValue.getName();
    }
    return cardValue;
  }

  /**
   * @return value of literalNumericValue
   */
  static private String getLiteralNumericValue(LiteralNumericValue numValue) {
    String cardValue;
    LiteralNumericValue lnv = numValue;
    String value = lnv.getValue();
    if (value != null) {
      cardValue = value.toString();
    } else {
      cardValue = ICommonConstants.EMPTY_STRING;
    }

    return cardValue;
  }

  /**
   * return prefix of the property label
   * @param context current Property
   * @return prefix as string
   */
  public static String prefixPropertyLabel(EObject context) {
    String str = ICommonConstants.EMPTY_STRING;
    if ((null != context) && (context instanceof Property)) {
      Property pro = (Property) context;
      if (pro.isIsPartOfKey()) {
        str = str + "&" + ICommonConstants.WHITE_SPACE_CHARACTER; //$NON-NLS-1$
      }
      if (pro.isIsStatic()) {
        str = str + "%" + ICommonConstants.WHITE_SPACE_CHARACTER; //$NON-NLS-1$
      }
    }
    return str;
  }
}
