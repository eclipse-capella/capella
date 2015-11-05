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
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
   */
  @Override
  public List<EObject> readOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
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
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#writeOpenValue(org.eclipse.emf.ecore.EObject)
   */
  public EObject writeOpenValue(CapellaElement semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    InterfaceHelper.affectExchangeItem((SequenceMessage) semanticElement, (AbstractEventOperation)((ExchangeItemAllocation) value).getAllocatedItem());
    return value;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#editValue()
   */
  public EObject editValue(CapellaElement semanticElement, EStructuralFeature semanticFeature, String defaultName) {
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
