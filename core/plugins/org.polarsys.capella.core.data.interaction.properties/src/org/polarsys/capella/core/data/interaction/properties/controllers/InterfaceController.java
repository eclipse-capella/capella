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

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class InterfaceController extends SequenceMessageController {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> list = new ArrayList<EObject>();
    SequenceMessage message = (SequenceMessage) semanticElement;
    InstanceRole sourceIR = message.getSendingEnd().getCovered();
    InstanceRole targetIR = message.getReceivingEnd().getCovered();
    for (CapellaElement op : InterfaceHelper.getInstance().getAvailableExchangeItemsFromInterfaces(sourceIR, targetIR, message.getKind() == MessageKind.SYNCHRONOUS_CALL)) {
      list.add(op);
    }
    return list;
  }

  /**
   * {@inheritDoc}
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    InterfaceHelper.affectExchangeItem((SequenceMessage) semanticElement, (AbstractEventOperation)((ExchangeItemAllocation) value).getAllocatedItem());
    return value;
  }

  /**
   * {@inheritDoc}
   */
  public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName) {
    if (semanticElement instanceof SequenceMessage) {
      AbstractEventOperation currentValue = SequenceMessageExt.getOperation((SequenceMessage) semanticElement);
      if (currentValue != null) {
        editValueWizard(currentValue);
      }
      return currentValue;
    }
    return null;
  }
}
