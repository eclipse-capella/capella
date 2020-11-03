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

package org.polarsys.capella.core.data.information.properties.controllers;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;

/**
 */
public class MultiplicityElementValueController extends AbstractSimpleEditableSemanticFieldController {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> list = super.readOpenValues(semanticElement, semanticFeature);

    AbstractType abstractType = ((AbstractTypedElement) semanticElement).getAbstractType();
    if (abstractType instanceof Enumeration) {
      Enumeration enumer = (Enumeration) abstractType;
      EList<EnumerationLiteral> ownedLiterals = enumer.getOwnedLiterals();
      for (EnumerationLiteral enumerationLiteral : ownedLiterals) {
        list.add(enumerationLiteral);
      }
    } else {
      IBusinessQuery query =
          BusinessQueriesProvider.getInstance()
              .getContribution(semanticElement.eClass(), ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
      if (query != null) {
        List<EObject> availableElements = query.getAvailableElements(semanticElement);
        for (EObject capellaElement : availableElements) {
          if (capellaElement instanceof AbstractType && capellaElement instanceof Classifier) {
              EList<Feature> ownedFeatures = ((Classifier) capellaElement).getOwnedFeatures();
              for (Feature feature : ownedFeatures) {
                if (feature instanceof Property) {
                  if (semanticElement instanceof TypedElement) {
                    AbstractType abstractType2 = ((TypedElement) semanticElement).getAbstractType();
                    AbstractType abstractType3 = ((Property) feature).getAbstractType();
                    if (abstractType2 != null && abstractType3 != null && abstractType2.eClass().equals(abstractType3.eClass()) && !feature.equals(semanticElement)) {
                      list.add(feature);
                    }
                  } else {
                    if (!feature.equals(semanticElement))
                      list.add(feature);
                  }
                }
              }
          }
        }
      }
    }

    return list;
  }

  /**
   * {@inheritDoc}
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    DataValue ref = null;
    if (value instanceof Property) {
      AbstractType abstractType = ((Property) value).getAbstractType();
      if (abstractType instanceof Class) {
        ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
        ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof BooleanType) {
        ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
        ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof NumericType) {
        ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
        ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof PhysicalQuantity) {
        ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
        ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof StringType) {
        ref = DatavalueFactory.eINSTANCE.createStringReference(defaultName);
        ref.eSet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      }
    } else if (value instanceof AbstractEnumerationValue) {
      ref = DatavalueFactory.eINSTANCE.createEnumerationReference();
      ref.eSet(DatavaluePackage.eINSTANCE.getEnumerationReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof AbstractBooleanValue) {
      ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof NumericValue) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof AbstractStringValue) {
      ref = DatavalueFactory.eINSTANCE.createStringReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    }

    if (ref != null) {
      semanticElement.eSet(semanticFeature, ref);
      return ref;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName) {
    if (semanticElement instanceof MultiplicityElement && semanticElement instanceof AbstractTypedElement) {
      DataValue currentValue = (DataValue) semanticElement.eGet(semanticFeature);
      if (currentValue != null) {
        editValueWizard(currentValue);
      } else {
        DataValue newValue = null;
        AbstractType abstractType = ((AbstractTypedElement) semanticElement).getAbstractType();
        if (abstractType instanceof StringType) {
          newValue = DatavalueFactory.eINSTANCE.createLiteralStringValue(defaultName);
        } else if (abstractType instanceof BooleanType) {
          newValue = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
        } else if (abstractType instanceof Enumeration) {
          newValue = DatavalueFactory.eINSTANCE.createEnumerationReference(defaultName);
        } else if (abstractType instanceof NumericType) {
          newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName);
        } else if (abstractType instanceof PhysicalQuantity) {
          newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName);
        } else if (abstractType instanceof Union) {
          newValue = DatavalueFactory.eINSTANCE.createComplexValue(defaultName);
        }

        if (newValue != null) {
          newValue.setAbstractType(abstractType);
          semanticElement.eSet(semanticFeature, newValue);
          if (editValueWizard(newValue)) {
            currentValue = newValue;
          } else {
            throw new EditableSemanticFieldException();
          }
        }
      }

      return currentValue;
    }
    return null;
  }
}
