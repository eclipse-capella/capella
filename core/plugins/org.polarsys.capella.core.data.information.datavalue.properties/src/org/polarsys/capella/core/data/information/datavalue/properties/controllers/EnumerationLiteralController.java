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
package org.polarsys.capella.core.data.information.datavalue.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
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
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;

/**
 */
public class EnumerationLiteralController extends AbstractSimpleEditableSemanticFieldController {

  /**
   * {@inheritDoc}
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    DataValue ref = null;
    if (value instanceof Property) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
    }
    else if (value instanceof NumericValue) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue(), value);
    }
    else if (value instanceof AbstractBooleanValue) {
      ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue(), value);
    }
    else if (value instanceof AbstractStringValue) {
      ref = DatavalueFactory.eINSTANCE.createStringReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue(), value);
    }
    else if (value instanceof EnumerationLiteral) {
      ref = DatavalueFactory.eINSTANCE.createEnumerationReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getEnumerationReference_ReferencedValue(), value);
    }
    
    if (ref != null && semanticElement instanceof AbstractType && (value instanceof NumericValue
        || value instanceof AbstractBooleanValue || value instanceof AbstractStringValue)) {
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), semanticElement);
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
    if (semanticElement != null) {
      DataValue currentValue = (DataValue) semanticElement.eGet(semanticFeature);
      if (currentValue != null) {
        editValueWizard(currentValue);
      } else {
        EObject owner = semanticElement.eContainer();
        if (owner instanceof Enumeration) {
          DataValue newValue = null;
          DataType domainType = ((Enumeration) owner).getDomainType();
          if (domainType instanceof BooleanType) {
            newValue = DatavalueFactory.eINSTANCE.createLiteralBooleanValue(defaultName);
          } else if (domainType instanceof StringType) {
            newValue = DatavalueFactory.eINSTANCE.createLiteralStringValue(defaultName);
          } else if (domainType instanceof Enumeration) {
            newValue = DatavalueFactory.eINSTANCE.createEnumerationLiteral(defaultName);
          } else {
            newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName);
          }
          if (null != newValue) {
            semanticElement.eSet(semanticFeature, newValue);
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
