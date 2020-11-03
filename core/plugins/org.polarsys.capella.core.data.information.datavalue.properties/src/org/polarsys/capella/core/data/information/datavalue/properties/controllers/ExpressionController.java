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
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;

/**
 */
public class ExpressionController extends AbstractSimpleEditableSemanticFieldController {
  /**
   * {@inheritDoc}
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    DataValue newValue = null;
    if (value instanceof AbstractBooleanValue) {
      newValue = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
      newValue.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue(), value);
    } else if (value instanceof AbstractComplexValue) {
      newValue = DatavalueFactory.eINSTANCE.createComplexValueReference(defaultName);
      newValue.eSet(DatavaluePackage.eINSTANCE.getComplexValueReference_ReferencedValue(), value);
    } else if (value instanceof AbstractEnumerationValue) {
      newValue = DatavalueFactory.eINSTANCE.createEnumerationReference(defaultName);
      newValue.eSet(DatavaluePackage.eINSTANCE.getEnumerationReference_ReferencedValue(), value);
    } else if (value instanceof NumericValue) {
      newValue = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
      newValue.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue(), value);
    } else if (value instanceof AbstractStringValue) {
      newValue = DatavalueFactory.eINSTANCE.createStringReference(defaultName);
      newValue.eSet(DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue(), value);
    } else if (value instanceof Unit) {
      semanticElement.eSet(semanticFeature, value);
    }

    if (newValue != null) {
      semanticElement.eSet(semanticFeature, newValue);
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
        LiteralNumericValue newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName);

        semanticElement.eSet(semanticFeature, newValue);
        if (editValueWizard(newValue)) {
          currentValue = newValue;
        } else {
          throw new EditableSemanticFieldException();
        }
      }

      return currentValue;
    }
    return null;
  }
}
