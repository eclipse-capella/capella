/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.dataValue;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class EnumerationLiteralDomainValueCheck extends AbstractValidationRule {

  enum TypeRelationship {
    FIRST_UNTYPED, SUBTYPE
  }

  static final String EMPTY_STRING = ""; //$NON-NLS-1$

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof Enumeration) {
      Enumeration enumeration = (Enumeration) eObj;
      EList<EnumerationLiteral> ownedLiterals = enumeration.getOwnedLiterals();
      for (EnumerationLiteral enumerationLiteral : ownedLiterals) {

        if (!this.isSatisfiedBy(enumerationLiteral, enumeration)) {

          return ctx.createFailureStatus(eObj, typingState(enumeration), computeDiagnosticMessage(enumeration));
        }

      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * The rule is the following:</br> If the enumeration domain type is T, then the literal domain values are </br> - All defined and typed by T or a subtype of
   * T</br> - All defined and not typed</br> If the enumeration domain is untyped, then the literal domain values are</br> - All defined and are all numeric
   * values</br> - All undefined</br>
   * @param enumerationLiteral
   * @param enumeration
   * @return
   */
  public boolean isSatisfiedBy(EnumerationLiteral enumerationLiteral, Enumeration enumeration) {
    DataType domainType = enumeration.getDomainType();
    EList<EnumerationLiteral> ownedLiterals = enumeration.getOwnedLiterals();

    if (ownedLiterals.isEmpty()) {
      return true;
    }

    if (domainType != null) {
      if (checkDefined(ownedLiterals) && ownedLiteralTypesAreConcordantWithEnumDomainType(ownedLiterals, domainType)) {
        return true;
      }
    } else {
      if (checkDefined(ownedLiterals) && areNumericValues(ownedLiterals)) {
        return true;
      }
      if (checkUndefined(ownedLiterals)) {
        return true;
      }
    }
    return false;

  }

  /**
   * Checks that ownedLiterals are all defined (expected=true) or all undefined (expected==false)
   * @param ownedLiterals
   * @param expected
   * @return
   */
  private boolean checkDefined(EList<EnumerationLiteral> ownedLiterals) {
    for (EnumerationLiteral literal : ownedLiterals) { // expected=true => all defined
      DataValue domainValue = literal.getDomainValue();
      if (null == domainValue) {
        return false;
      }
      DataValue value = getReferencedLiteralValue(domainValue);

      if (value instanceof LiteralBooleanValue) {
        return true;

      } else if (value instanceof LiteralNumericValue) {
        String valueLiteral = ((LiteralNumericValue) value).getValue();
        if (isNull(valueLiteral, true) || EMPTY_STRING.equals(valueLiteral)) {
          return false;
        }
      } else if (value instanceof LiteralStringValue) {
        String valueLiteral = ((LiteralStringValue) value).getValue();

        if (isNull(valueLiteral, true) || EMPTY_STRING.equals(valueLiteral)) {
          return false;
        }
      } else {
        continue;
      }
    }
    return true;
  }

  private boolean checkUndefined(EList<EnumerationLiteral> ownedLiterals) {
    for (EnumerationLiteral literal : ownedLiterals) {
      DataValue domainValue = literal.getDomainValue();
      // prod00118670:START
      if (null == domainValue) {
        continue;
      } // prod00118670:END
      DataValue value = getReferencedLiteralValue(domainValue);
      if (value instanceof LiteralBooleanValue) {
        return false;

      } else if (value instanceof LiteralNumericValue) {
        String valueLiteral = ((LiteralNumericValue) value).getValue();
        if (isNull(valueLiteral, false) && !EMPTY_STRING.equals(valueLiteral)) {
          return false;
        }
      } else if (value instanceof LiteralStringValue) {
        String valueLiteral = ((LiteralStringValue) value).getValue();
        if (isNull(valueLiteral, false) && !EMPTY_STRING.equals(valueLiteral)) {
          return false;
        }
      } else {
        continue;
      }
    }
    return true;
  }

  /**
   * @param ownedLiterals
   * @return
   */
  private boolean areNumericValues(EList<EnumerationLiteral> ownedLiterals) {
    for (EnumerationLiteral literal : ownedLiterals) {
      DataValue domainValue = literal.getDomainValue();

      DataValue value = getReferencedLiteralValue(domainValue);

      if (value instanceof NumericValue) {
        continue;
      }
      return false;
    }
    return true;
  }

  /**
   * @param firstLiteral
   * @param domainType
   */
  private TypeRelationship getRelationShip(EnumerationLiteral firstLiteral, DataType domainType) {
    if (ownedLiteralTypeIsConcordantWithEnumDomainType(firstLiteral, domainType, TypeRelationship.FIRST_UNTYPED)) {
      return TypeRelationship.FIRST_UNTYPED;
    }
    if (ownedLiteralTypeIsConcordantWithEnumDomainType(firstLiteral, domainType, TypeRelationship.SUBTYPE)) {
      return TypeRelationship.SUBTYPE;
    }
    return null;

  }

  /**
   * Checks that owned literals are eighter all untyped or all typed by domainType or a sub type
   * @param ownedLiterals
   * @param domainType
   */
  private boolean ownedLiteralTypesAreConcordantWithEnumDomainType(EList<EnumerationLiteral> ownedLiterals, DataType domainType) {
    if (ownedLiterals.isEmpty()) {
      return true;
    }

    EnumerationLiteral firstLiteral = ownedLiterals.get(0);
    // check if the relationship of the first literal with the domain type of the enumration
    TypeRelationship firstLiteralRelationShip = getRelationShip(firstLiteral, domainType);
    if (firstLiteralRelationShip == null) { // first literal is not in concordance with Enum domain type
      return false;
    }
    List<EnumerationLiteral> ownedLiteralsTail = ownedLiterals.subList(1, ownedLiterals.size());
    for (EnumerationLiteral literal : ownedLiteralsTail) {

      if (!ownedLiteralTypeIsConcordantWithEnumDomainType(literal, domainType, firstLiteralRelationShip)) {
        return false;
      }

    }
    return true;
  }

  /**
   * Checks that literal domain type and domainType have relationship as relationship
   * @param literal
   * @param domainType
   * @param relationship
   */
  private boolean ownedLiteralTypeIsConcordantWithEnumDomainType(EnumerationLiteral literal, DataType domainType, TypeRelationship relationship) {
    DataValue domainValue = literal.getDomainValue();
    DataValue value = getReferencedLiteralValue(domainValue);

    if ((value instanceof LiteralBooleanValue) || (value instanceof LiteralNumericValue) || (value instanceof LiteralStringValue)) {
      AbstractType type = value.getAbstractType();
      if (areConcordantTypes(type, domainType, relationship)) {
        return true;
      }
    }
    return false;

  }

  /**
   * Check that the relationship btw type and domainType is the one specified by relationshipCheck
   * @param type
   * @param domainType
   * @param relationshipCheck
   * @return
   */
  private boolean areConcordantTypes(AbstractType type, DataType domainType, TypeRelationship relationshipCheck) {
    switch (relationshipCheck) {
      case FIRST_UNTYPED:
        return null == type;
      case SUBTYPE:
        return isEqualOrSubtypeOf(type, domainType);
      default:
        return false;
    }

  }

  /**
   * @param domainValue
   * @return
   */
  private DataValue getReferencedLiteralValue(DataValue domainValue) {
    if (domainValue instanceof BooleanReference) {
      return getReferencedLiteralBooleanValue(domainValue);
    }
    if (domainValue instanceof NumericReference) {
      return getReferencedLiteralNumericValue(domainValue);
    }
    if (domainValue instanceof StringReference) {
      return getReferencedLiteralStringValue(domainValue);
    }
    // datavalue is not a reference => return the datavalue
    return domainValue;
  }

  /**
   * Recursively navigate through references to the referenced Boolean literal value
   * @param domainValue
   * @return
   */
  private LiteralBooleanValue getReferencedLiteralBooleanValue(DataValue domainValue) {
    Object ref = domainValue.eGet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue());
    while ((ref instanceof BooleanReference) && (((BooleanReference) ref).eGet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue()) != null)) {
      ref = ((BooleanReference) ref).eGet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue());
    }
    if (ref instanceof LiteralBooleanValue) {
      return (LiteralBooleanValue) ref;
    }
    return null;
  }

  /**
   * Recursively navigate through references to the referenced String literal value
   * @param domainValue
   * @return
   */
  private LiteralStringValue getReferencedLiteralStringValue(DataValue domainValue) {
    Object ref = domainValue.eGet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue());
    while ((ref instanceof StringReference) && (((StringReference) ref).eGet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue()) != null)) {
      ref = ((StringReference) ref).eGet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue());
    }
    if (ref instanceof LiteralStringValue) {
      return (LiteralStringValue) ref;
    }
    return null;
  }

  /**
   * Recursively navigate through references to the referenced Numeric literal value
   * @param domainValue
   * @return
   */
  private LiteralNumericValue getReferencedLiteralNumericValue(DataValue domainValue) {
    Object ref = domainValue.eGet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue());
    while ((ref instanceof NumericReference) && (((NumericReference) ref).eGet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue()) != null)) {
      ref = ((NumericReference) ref).eGet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue());
    }
    if (ref instanceof LiteralNumericValue) {
      return (LiteralNumericValue) ref;
    }
    return null;
  }

  /**
   * @param enumeration
   * @return
   */
  private String computeDiagnosticMessage(Enumeration enumeration) {
    if (isTyped(enumeration)) {
      return Messages.EnumerationLiteralDomainValueCheck_typedEnum_diagnostic;
    }
    return Messages.EnumerationLiteralDomainValueCheck_unTypedEnum_diagnostic;
  }

  /**
   * @param enumeration
   * @return
   */
  private String typingState(Enumeration enumeration) {

    return isTyped(enumeration) ? "typed" : "not typed"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @param enumeration
   * @return
   */
  private boolean isTyped(Enumeration enumeration) {
    return null != enumeration.getDomainType();
  }

  private boolean isNull(Object elt, boolean expected) {
    if (elt == null) {
      return expected;
    }
    return !expected;
  }

  private boolean isEqualOrSubtypeOf(AbstractType type, DataType domainType) {
    if ((null == domainType) || (null == type)) {
      return false;
    }
    List<GeneralizableElement> subTypes = GeneralizableElementExt.getAllSubGeneralizableElements(domainType);
    if (subTypes.contains(type)) {
      return true;
    } else if (type.equals(domainType)) {
      return true;
    }
    return false;
  }

}
