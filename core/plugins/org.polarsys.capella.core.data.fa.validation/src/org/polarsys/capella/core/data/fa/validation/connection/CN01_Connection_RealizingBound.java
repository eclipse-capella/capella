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
   * @param exc
   * @param exch
   * @return
   */
  private boolean isValid(ComponentExchange previous, ComponentExchange current) {
    Port sourcePrevious = ComponentExchangeExt.getSourcePort(previous);
    Port sourceCurrent = ComponentExchangeExt.getSourcePort(current);
    Component sourceComponentPrevious = ComponentExchangeExt.getSourceComponent(previous);
    if (ComponentExchangeExt.getSourcePart(previous) != null) {
      sourceComponentPrevious = null; //disable check for component exchange linked to parts
    }
    Component sourceComponentCurrent = ComponentExchangeExt.getSourceComponent(current);
    if (ComponentExchangeExt.getSourcePart(current) != null) {
      sourceComponentCurrent = null; //disable check for component exchange linked to parts
    }

    Port targetPrevious = ComponentExchangeExt.getTargetPort(previous);
    Port targetCurrent = ComponentExchangeExt.getTargetPort(current);
    Component targetComponentPrevious = ComponentExchangeExt.getTargetComponent(previous);
    if (ComponentExchangeExt.getTargetPart(previous) != null) {
      targetComponentPrevious = null; //disable check for component exchange linked to parts
    }

    Component targetComponentCurrent = ComponentExchangeExt.getTargetComponent(current);
    if (ComponentExchangeExt.getTargetPart(current) != null) {
      targetComponentCurrent = null; //disable check for component exchange linked to parts
    }

    boolean sourceValid = isValid(sourcePrevious, sourceCurrent, sourceComponentPrevious, sourceComponentCurrent);
    boolean targetValid = isValid(targetPrevious, targetCurrent, targetComponentPrevious, targetComponentCurrent);

    return sourceValid && targetValid;
  }

  /**
   * @param sourcePrevious
   * @param sourceCurrent
   * @return
   */
  private boolean isValid(Port portPrevious, Port portCurrent, Component parentPrevious, Component parentCurrent) {
    boolean sourceValid = true;
    if ((portPrevious != null) && (portCurrent != null)) {
      if ((portPrevious instanceof ComponentPort) && (portCurrent instanceof ComponentPort)) {
        sourceValid = PortExt.isTransitionedTo((ComponentPort) portPrevious, (ComponentPort) portCurrent);
      }

    } else if (portCurrent != null) { //for oa checks, if the previous CM is defined between parents of a transitioned Cps related to current CE, it's ok.
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
