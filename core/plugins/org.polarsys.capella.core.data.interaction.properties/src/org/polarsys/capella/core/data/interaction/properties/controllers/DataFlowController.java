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
package org.polarsys.capella.core.data.interaction.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class DataFlowController extends SequenceMessageController {

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
   */
  @Override
  public List<EObject> readOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> list = new ArrayList<EObject>();
    for (FunctionalExchange exchange : DataFlowHelper.getAvailableFonctionalExchanges((SequenceMessage) semanticElement)) {
      list.add(exchange);
    }
    return list;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#writeOpenValue(org.eclipse.emf.ecore.EObject)
   */
  public EObject writeOpenValue(CapellaElement semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    DataFlowHelper.affectDataflowToMessageAndOpposite((SequenceMessage) semanticElement, (FunctionalExchange) value);
    return value;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#editValue()
   */
  public EObject editValue(CapellaElement semanticElement, EStructuralFeature semanticFeature, String defaultName) {
    if (semanticElement instanceof SequenceMessage) {
      AbstractEventOperation currentValue = SequenceMessageExt.getFunctionalExchange((SequenceMessage) semanticElement);
      if (currentValue != null) {
        editValueWizard(currentValue);
        // to synchronize names
        DataFlowHelper.affectDataflowToMessageAndOpposite((SequenceMessage) semanticElement, currentValue);
        return currentValue;
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.ISimpleEditableSemanticFieldController#loadValue(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public EObject loadValue(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    if (semanticElement instanceof SequenceMessage) {
      return SequenceMessageExt.getFunctionalExchange((SequenceMessage) semanticElement);
    }
    return null;
  }
}
