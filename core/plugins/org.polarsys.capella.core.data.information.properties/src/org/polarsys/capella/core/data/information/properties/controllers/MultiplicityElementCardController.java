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
package org.polarsys.capella.core.data.information.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
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
public class MultiplicityElementCardController extends AbstractSimpleEditableSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#writeOpenValue(org.eclipse.emf.ecore.EObject)
   */
  public EObject writeOpenValue(CapellaElement semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    DataValue ref = null;
    if (value instanceof Property) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedProperty(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof NumericValue) {
      ref = DatavalueFactory.eINSTANCE.createNumericReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getNumericReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) value).getAbstractType());
    } else if (value instanceof AbstractBooleanValue) {
      ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue(), value);
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
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#editValue()
   */
  public EObject editValue(CapellaElement semanticElement, EStructuralFeature semanticFeature, String defaultName) {
    if (semanticElement != null) {
      NumericValue currentValue = (NumericValue) semanticElement.eGet(semanticFeature);
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
