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
package org.polarsys.capella.core.data.interaction.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;

/**
 */
public abstract class SequenceMessageController extends AbstractSimpleEditableSemanticFieldController {
  /**
   * @param semanticElement_p
   * @param semanticFeature_p
   * 
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.ISimpleEditableSemanticFieldController#loadValue(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public EObject loadValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    if (semanticElement_p instanceof SequenceMessage) {
      return SequenceMessageExt.getOperation((SequenceMessage) semanticElement_p);
    }
    return null;
  }

  /**
   * @param semanticElement_p
   */
  public static void resetValue(CapellaElement semanticElement_p) {
    if (semanticElement_p instanceof SequenceMessage) {
      SequenceMessageExt.resetMessage((SequenceMessage) semanticElement_p);
      SequenceMessage reply = SequenceMessageExt.getOppositeSequenceMessage((SequenceMessage) semanticElement_p);
      if (reply != null) {
        SequenceMessageExt.resetMessage(reply);
      }
    }
  }
}
