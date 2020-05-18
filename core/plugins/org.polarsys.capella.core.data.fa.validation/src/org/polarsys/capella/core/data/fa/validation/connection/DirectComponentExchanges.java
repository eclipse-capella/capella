/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks realization consistency between ports of functional exchanges.
 */
public class DirectComponentExchanges extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentExchange) {
        ComponentExchange exchange = (ComponentExchange) eObj;
        if (!(TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(exchange)))) {
          if (exchange.getKind() != ComponentExchangeKind.DELEGATION) {
            if (CapellaLayerCheckingExt.isAOrInLogicalLayer(exchange) || CapellaLayerCheckingExt.isAOrInPhysicalLayer(exchange)) {
              Part sourcePart = exchange.getSourcePart();
              Part targetPart = exchange.getTargetPart();
              Port sourcePort = exchange.getSourcePort();
              Port targetPort = exchange.getTargetPort();

              if (sourcePart == null) {
                Component sourceComponent = ComponentExchangeExt.getSourceComponent(exchange);
                if (sourceComponent != null) {
                  List<Part> partitions = sourceComponent.getRepresentingParts();
                  if (!partitions.isEmpty()) {
                	sourcePart = (Part) partitions.get(0);
                  }
                }
              }
              if (targetPart == null) {
                Component targetComponent = ComponentExchangeExt.getTargetComponent(exchange);
                if (targetComponent != null) {
                  List<Part> partitions = targetComponent.getRepresentingParts();
                  if (!partitions.isEmpty()) {
                    targetPart = (Part) partitions.get(0);
                  }
                }
              }

              if ((sourcePart != null) && (targetPart != null) && !ComponentExt.isBrothers(sourcePart, targetPart)) {
                if (!ComponentExt.isComponentExchangeThroughDelegationsExists(sourcePart, targetPart, sourcePort, targetPort)) {
                  return ctx.createFailureStatus(new Object[] { exchange.getName() });
                }
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
