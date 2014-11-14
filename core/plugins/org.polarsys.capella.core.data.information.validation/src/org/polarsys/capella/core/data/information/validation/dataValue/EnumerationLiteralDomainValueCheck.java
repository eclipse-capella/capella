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
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();

    if (eObj instanceof Enumeration) {
      Enumeration enumeration = (Enumeration) eObj;
      EList<EnumerationLiteral> ownedLiterals = enumeration.getOwnedLiterals();
      for (EnumerationLiteral enumerationLiteral : ownedLiterals) {

        if (!this.isSatisfiedBy(enumerationLiteral, enumeration)) {

          return ctx_p.createFailureStatus(eObj, typingState(enumeration), computeDiagnosticMessage(enumeration));
        }

      }
    }
    return ctx_p.createSuccessStatus();
  }

  /**
   * The rule is the following:</br> If the enumeration domain type is T, then the literal domain values are </br> - All defined and typed by T or a subtype of
   * T</br> - All defined and not typed</br> If the enumeration domain is untyped, then the literal domain values are</br> - All defined and are all numeric
   * values</br> - All undefined</br>
   * @param enumerationLiteral_p
   * @param enum_p
   * @return
   */
  public boolean isSatisfiedBy(EnumerationLiteral enumerationLiteral_p, Enumeration enum_p) {
    DataType domainType = enum_p.getDomainType();
    EList<EnumerationLiteral> ownedLiterals = enum_p.getOwnedLiterals();

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
   * Checks that ownedLiterals_p are all defined (expected_p=true) or all undefined (expected_p==false)
   * @param ownedLiterals_p
   * @param expected_p
   * @return
   */
  private boolean checkDefined(EList<EnumerationLiteral> ownedLiterals_p) {
    for (EnumerationLiteral literal : ownedLiterals_p) { // expected=true => all defined
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

  private boolean checkUndefined(EList<EnumerationLiteral> ownedLiterals_p) {
    for (EnumerationLiteral literal : ownedLiterals_p) {
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
   * @param ownedLiterals_p
   * @return
   */
  private boolean areNumericValues(EList<EnumerationLiteral> ownedLiterals_p) {
    for (EnumerationLiteral literal : ownedLiterals_p) {
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
   * @param firstLiteral_p
   * @param domainType_p
   */
  private TypeRelationship getRelationShip(EnumerationLiteral firstLiteral_p, DataType domainType_p) {
    if (ownedLiteralTypeIsConcordantWithEnumDomainType(firstLiteral_p, domainType_p, TypeRelationship.FIRST_UNTYPED)) {
      return TypeRelationship.FIRST_UNTYPED;
    }
    if (ownedLiteralTypeIsConcordantWithEnumDomainType(firstLiteral_p, domainType_p, TypeRelationship.SUBTYPE)) {
      return TypeRelationship.SUBTYPE;
    }
    return null;

  }

  /**
   * Checks that owned literals are eighter all untyped or all typed by domainType_p or a sub type
   * @param ownedLiterals_p
   * @param domainType_p
   */
  private boolean ownedLiteralTypesAreConcordantWithEnumDomainType(EList<EnumerationLiteral> ownedLiterals_p, DataType domainType_p) {
    if (ownedLiterals_p.isEmpty()) {
      return true;
    }

    EnumerationLiteral firstLiteral = ownedLiterals_p.get(0);
    // check if the relationship of the first literal with the domain type of the enumration
    TypeRelationship firstLiteralRelationShip = getRelationShip(firstLiteral, domainType_p);
    if (firstLiteralRelationShip == null) { // first literal is not in concordance with Enum domain type
      return false;
    }
    List<EnumerationLiteral> ownedLiteralsTail = ownedLiterals_p.subList(1, ownedLiterals_p.size());
    for (EnumerationLiteral literal : ownedLiteralsTail) {

      if (!ownedLiteralTypeIsConcordantWithEnumDomainType(literal, domainType_p, firstLiteralRelationShip)) {
        return false;
      }

    }
    return true;
  }

  /**
   * Checks that literal domain type and domainType_p have relationship_p as relationship
   * @param literal_p
   * @param domainType_p
   * @param relationship_p
   */
  private boolean ownedLiteralTypeIsConcordantWithEnumDomainType(EnumerationLiteral literal_p, DataType domainType_p, TypeRelationship relationship_p) {
    DataValue domainValue = literal_p.getDomainValue();
    DataValue value = getReferencedLiteralValue(domainValue);

    if ((value instanceof LiteralBooleanValue) || (value instanceof LiteralNumericValue) || (value instanceof LiteralStringValue)) {
      AbstractType type = value.getAbstractType();
      if (areConcordantTypes(type, domainType_p, relationship_p)) {
        return true;
      }
    }
    return false;

  }

  /**
   * Check that the relationship btw type_p and domainType_p is the one specified by relationshipCheck_p
   * @param type_p
   * @param domainType_p
   * @param relationshipCheck_p
   * @return
   */
  private boolean areConcordantTypes(AbstractType type_p, DataType domainType_p, TypeRelationship relationshipCheck_p) {
    switch (relationshipCheck_p) {
      case FIRST_UNTYPED:
        return null == type_p;
      case SUBTYPE:
        return isEqualOrSubtypeOf(type_p, domainType_p);
      default:
        return false;
    }

  }

  /**
   * @param domainValue_p
   * @return
   */
  private DataValue getReferencedLiteralValue(DataValue domainValue_p) {
    if (domainValue_p instanceof BooleanReference) {
      return getReferencedLiteralBooleanValue(domainValue_p);
    }
    if (domainValue_p instanceof NumericReference) {
      return getReferencedLiteralNumericValue(domainValue_p);
    }
    if (domainValue_p instanceof StringReference) {
      return getReferencedLiteralStringValue(domainValue_p);
    }
    // datavalue is not a reference => return the datavalue
    return domainValue_p;
  }

  /**
   * Recursively navigate through references to the referenced Boolean literal value
   * @param domainValue_p
   * @return
   */
  private LiteralBooleanValue getReferencedLiteralBooleanValue(DataValue domainValue_p) {
    Object ref = domainValue_p.eGet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue());
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
   * @param domainValue_p
   * @return
   */
  private LiteralStringValue getReferencedLiteralStringValue(DataValue domainValue_p) {
    Object ref = domainValue_p.eGet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue());
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
   * @param domainValue_p
   * @return
   */
  private LiteralNumericValue getReferencedLiteralNumericValue(DataValue domainValue_p) {
    Object ref = domainValue_p.eGet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue());
    while ((ref instanceof NumericReference) && (((NumericReference) ref).eGet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue()) != null)) {
      ref = ((NumericReference) ref).eGet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue());
    }
    if (ref instanceof LiteralNumericValue) {
      return (LiteralNumericValue) ref;
    }
    return null;
  }

  /**
   * @param enum_p
   * @return
   */
  private String computeDiagnosticMessage(Enumeration enum_p) {
    if (isTyped(enum_p)) {
      return Messages.EnumerationLiteralDomainValueCheck_typedEnum_diagnostic;
    }
    return Messages.EnumerationLiteralDomainValueCheck_unTypedEnum_diagnostic;
  }

  /**
   * @param enum_p
   * @return
   */
  private String typingState(Enumeration enum_p) {

    return isTyped(enum_p) ? "typed" : "not typed"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @param enum_p
   * @return
   */
  private boolean isTyped(Enumeration enum_p) {
    return null != enum_p.getDomainType();
  }

  private boolean isNull(Object elt_p, boolean expected_p) {
    if (elt_p == null) {
      return expected_p;
    }
    return !expected_p;
  }

  private boolean isEqualOrSubtypeOf(AbstractType type_p, DataType domainType_p) {
    if ((null == domainType_p) || (null == type_p)) {
      return false;
    }
    List<GeneralizableElement> subTypes = GeneralizableElementExt.getAllSubGeneralizableElements(domainType_p);
    if (subTypes.contains(type_p)) {
      return true;
    } else if (type_p.equals(domainType_p)) {
      return true;
    }
    return false;
  }

}
