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
package org.polarsys.capella.core.data.fa.validation.connection;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks realization consistency between ports of functional exchanges.
 */
public class CN01_Connection_RealizingBound extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentExchange) {
        ComponentExchange exch = (ComponentExchange) eObj;
        List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements((CapellaElement) eObj, FaPackage.Literals.COMPONENT_EXCHANGE);

        for (CapellaElement element : previousPhaseElements) {
          ComponentExchange exc = (ComponentExchange) element;
          if (isValid(exc, exch)) {
            return ctx.createSuccessStatus();
          }
        }
        if (previousPhaseElements.size() != 0) {
          return createFailureStatus(ctx, new Object[] { exch.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * @param exc_p
   * @param exch_p
   * @return
   */
  private boolean isValid(ComponentExchange previous_p, ComponentExchange current_p) {
    Port sourcePrevious = ComponentExchangeExt.getSourcePort(previous_p);
    Port sourceCurrent = ComponentExchangeExt.getSourcePort(current_p);
    Component sourceComponentPrevious = ComponentExchangeExt.getSourceComponent(previous_p);
    if (ComponentExchangeExt.getSourcePart(previous_p) != null) {
      sourceComponentPrevious = null; //disable check for component exchange linked to parts
    }
    Component sourceComponentCurrent = ComponentExchangeExt.getSourceComponent(current_p);
    if (ComponentExchangeExt.getSourcePart(current_p) != null) {
      sourceComponentCurrent = null; //disable check for component exchange linked to parts
    }

    Port targetPrevious = ComponentExchangeExt.getTargetPort(previous_p);
    Port targetCurrent = ComponentExchangeExt.getTargetPort(current_p);
    Component targetComponentPrevious = ComponentExchangeExt.getTargetComponent(previous_p);
    if (ComponentExchangeExt.getTargetPart(previous_p) != null) {
      targetComponentPrevious = null; //disable check for component exchange linked to parts
    }

    Component targetComponentCurrent = ComponentExchangeExt.getTargetComponent(current_p);
    if (ComponentExchangeExt.getTargetPart(current_p) != null) {
      targetComponentCurrent = null; //disable check for component exchange linked to parts
    }

    boolean sourceValid = isValid(sourcePrevious, sourceCurrent, sourceComponentPrevious, sourceComponentCurrent);
    boolean targetValid = isValid(targetPrevious, targetCurrent, targetComponentPrevious, targetComponentCurrent);

    return sourceValid && targetValid;
  }

  /**
   * @param sourcePrevious_p
   * @param sourceCurrent_p
   * @return
   */
  private boolean isValid(Port portPrevious_p, Port portCurrent_p, Component parentPrevious, Component parentCurrent) {
    boolean sourceValid = true;
    if ((portPrevious_p != null) && (portCurrent_p != null)) {
      if ((portPrevious_p instanceof ComponentPort) && (portCurrent_p instanceof ComponentPort)) {
        sourceValid = PortExt.isTransitionedTo((ComponentPort) portPrevious_p, (ComponentPort) portCurrent_p);
      }

    } else if (portCurrent_p != null) { //for oa checks, if the previous CM is defined between parents of a transitioned Cps related to current CE, it's ok.
      if ((parentPrevious != null) && (parentCurrent != null)) {
        Collection<Component> parentsPrevious = ComponentExt.getAllSubUsedComponents(parentPrevious);
        parentsPrevious.addAll(ComponentExt.getComponentAncestors(parentPrevious));
        parentsPrevious.add(parentPrevious);
        sourceValid = false;
        for (Component previous : parentsPrevious) {
          if (RefinementLinkExt.isLinkedTo(parentCurrent, previous)) {
            sourceValid = true;
          }
        }
      }
    }
    return sourceValid;
  }

}
