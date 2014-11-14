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
package org.polarsys.capella.core.data.information.datavalue.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class EnumerationLiteralController extends AbstractSimpleEditableSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#writeOpenValue(org.eclipse.emf.ecore.EObject)
   */
  public EObject writeOpenValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p, EObject value) {
    DataValue ref = null;
    if (value instanceof Property) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
    }
    else if (value instanceof NumericValue) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), semanticElement_p);
    }
    else if (value instanceof AbstractBooleanValue) {
      ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), semanticElement_p);
    }
    else if (value instanceof AbstractStringValue) {
      ref = DatavalueFactory.eINSTANCE.createStringReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), semanticElement_p);
    }
    else if (value instanceof EnumerationLiteral) {
      ref = DatavalueFactory.eINSTANCE.createEnumerationReference(defaultName_p);
      ref.eSet(DatavaluePackage.eINSTANCE.getEnumerationReference_ReferencedValue(), value);
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
    if (semanticElement_p != null) {
      DataValue currentValue = (DataValue) semanticElement_p.eGet(semanticFeature_p);
      if (currentValue != null) {
        editValueWizard(currentValue);
      } else {
        EObject owner = semanticElement_p.eContainer();
        if (owner instanceof Enumeration) {
          DataValue newValue = null;
          DataType domainType = ((Enumeration) owner).getDomainType();
          if (domainType instanceof BooleanType) {
            newValue = DatavalueFactory.eINSTANCE.createLiteralBooleanValue(defaultName_p);
          } else if (domainType instanceof StringType) {
            newValue = DatavalueFactory.eINSTANCE.createLiteralStringValue(defaultName_p);
          } else if (domainType instanceof Enumeration) {
            newValue = DatavalueFactory.eINSTANCE.createEnumerationLiteral(defaultName_p);
          } else {
            newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName_p);
          }
          if (null != newValue) {
            semanticElement_p.eSet(semanticFeature_p, newValue);
            if (editValueWizard(newValue)) {
              currentValue = newValue;
            } else {
              throw new EditableSemanticFieldException();
            }
          }
        }
      }

      return currentValue;
    }
    return null;
  }
}
