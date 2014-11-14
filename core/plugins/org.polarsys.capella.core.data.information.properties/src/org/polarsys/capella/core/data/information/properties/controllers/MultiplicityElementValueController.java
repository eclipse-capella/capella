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
package org.polarsys.capella.core.data.information.properties.controllers;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
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
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class MultiplicityElementValueController extends AbstractSimpleEditableSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
   */
  @Override
  public List<EObject> readOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    List<EObject> list = super.readOpenValues(semanticElement_p, semanticFeature_p);

    AbstractType abstractType = ((AbstractTypedElement) semanticElement_p).getAbstractType();
    if (abstractType instanceof Enumeration) {
      Enumeration enumer = (Enumeration) abstractType;
      EList<EnumerationLiteral> ownedLiterals = enumer.getOwnedLiterals();
      for (EnumerationLiteral enumerationLiteral : ownedLiterals) {
        list.add(enumerationLiteral);
      }
    } else {
      IBusinessQuery query =
          BusinessQueriesProvider.getInstance()
              .getContribution(semanticElement_p.eClass(), ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
      if (query != null) {
        List<CapellaElement> availableElements = query.getAvailableElements(semanticElement_p);
        for (CapellaElement capellaElement : availableElements) {
          if (capellaElement instanceof AbstractType) {
            if (capellaElement instanceof Classifier) {
              EList<Feature> ownedFeatures = ((Classifier) capellaElement).getOwnedFeatures();
              for (Feature feature : ownedFeatures) {
                if (feature instanceof Property) {
                  if (semanticElement_p instanceof TypedElement) {
                    AbstractType abstractType2 = ((TypedElement) semanticElement_p).getAbstractType();
                    AbstractType abstractType3 = ((Property) feature).getAbstractType();
                    if (abstractType2 != null && abstractType3 != null) {
                      if (abstractType2.eClass().equals(abstractType3.eClass()) && !feature.equals(semanticElement_p)) {
                        list.add(feature);
                      }
                    }
                  } else {
                    if (!feature.equals(semanticElement_p))
                      list.add(feature);
                  }
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
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#writeOpenValue(org.eclipse.emf.ecore.EObject)
   */
  public EObject writeOpenValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p, EObject value) {
    DataValue ref = null;
    if (value instanceof Property) {
      AbstractType abstractType = ((Property) value).getAbstractType();
      if (abstractType instanceof Class) {
        ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName_p);
        ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof BooleanType) {
        ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName_p);
        ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof NumericType) {
        ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName_p);
        ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof PhysicalQuantity) {
        ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName_p);
        ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      } else if (abstractType instanceof StringType) {
        ref = DatavalueFactory.eINSTANCE.createStringReference(defaultName_p);
        ref.eSet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedProperty(), value);
        ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
      }
    } else if (value instanceof AbstractEnumerationValue) {
      ref = DatavalueFactory.eINSTANCE.createEnumerationReference();
      ref.eSet(DatavaluePackage.eINSTANCE.getEnumerationReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof AbstractBooleanValue) {
      ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof NumericValue) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof AbstractStringValue) {
      ref = DatavalueFactory.eINSTANCE.createStringReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    }

    if (ref != null) {
      semanticElement_p.eSet(semanticFeature_p, ref);
      return ref;
    }

    return value;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#editValue()
   */
  public EObject editValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p) {
    if (semanticElement_p instanceof MultiplicityElement && semanticElement_p instanceof AbstractTypedElement) {
      DataValue currentValue = (DataValue) semanticElement_p.eGet(semanticFeature_p);
      if (currentValue != null) {
        editValueWizard(currentValue);
      } else {
        DataValue newValue = null;
        AbstractType abstractType = ((AbstractTypedElement) semanticElement_p).getAbstractType();
        if (abstractType instanceof StringType) {
          newValue = DatavalueFactory.eINSTANCE.createLiteralStringValue(defaultName_p);
        } else if (abstractType instanceof BooleanType) {
          newValue = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName_p);
        } else if (abstractType instanceof Enumeration) {
          newValue = DatavalueFactory.eINSTANCE.createEnumerationReference(defaultName_p);
        } else if (abstractType instanceof NumericType) {
          newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName_p);
        } else if (abstractType instanceof PhysicalQuantity) {
          newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName_p);
        } else if (abstractType instanceof Union) {
          newValue = DatavalueFactory.eINSTANCE.createComplexValue(defaultName_p);
        }

        if ((newValue != null) && (abstractType != null)) {
          newValue.setAbstractType(abstractType);
          
          semanticElement_p.eSet(semanticFeature_p, newValue);
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
