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
package org.polarsys.capella.core.data.information.datatype.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;

/**
 */
public class NumericTypeController extends AbstractSimpleEditableSemanticFieldController {

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
      NumericValue currentValue = (NumericValue) semanticElement.eGet(semanticFeature);
      if (currentValue != null) {
        editValueWizard(currentValue);
      } else {
        LiteralNumericValue newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName);
        newValue.setAbstractType((NumericType) semanticElement);

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
