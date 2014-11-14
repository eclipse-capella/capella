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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class MultiplicityElementLengthController extends AbstractSimpleEditableSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#writeOpenValue(org.eclipse.emf.ecore.EObject)
   */
  public EObject writeOpenValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p, EObject value) {
    DataValue ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName_p);
    if (value instanceof Property) {
        ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
	} else {
		ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue(), value);
	}
    ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    semanticElement_p.eSet(semanticFeature_p, ref);

    return ref;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#editValue()
   */
  public EObject editValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p) {
    if (semanticElement_p != null) {
      NumericValue currentValue = (NumericValue) semanticElement_p.eGet(semanticFeature_p);
      if (currentValue != null) {
        editValueWizard(currentValue);
      } else {
        LiteralNumericValue newValue = DatavalueFactory.eINSTANCE.createLiteralNumericValue(defaultName_p);
        semanticElement_p.eSet(semanticFeature_p, newValue);
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
