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
package org.polarsys.capella.core.data.interaction.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 *
 */
public class DataFlowController extends SequenceMessageController {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> list = new ArrayList<EObject>();
    for (FunctionalExchange exchange : DataFlowHelper.getAvailableFonctionalExchanges((SequenceMessage) semanticElement)) {
      list.add(exchange);
    }
    return list;
  }

  /**
   * {@inheritDoc}
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    DataFlowHelper.affectDataflowToMessageAndOpposite((SequenceMessage) semanticElement, (FunctionalExchange) value);
    return value;
  }

  /**
   * {@inheritDoc}
   */
  public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName) {
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
   * {@inheritDoc}
   */
  @Override
  public EObject loadValue(EObject semanticElement, EStructuralFeature semanticFeature) {
    if (semanticElement instanceof SequenceMessage) {
      return SequenceMessageExt.getFunctionalExchange((SequenceMessage) semanticElement);
    }
    return null;
  }
}
