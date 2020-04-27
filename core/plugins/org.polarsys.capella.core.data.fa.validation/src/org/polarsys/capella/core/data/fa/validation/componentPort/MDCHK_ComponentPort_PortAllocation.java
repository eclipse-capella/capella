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
package org.polarsys.capella.core.data.fa.validation.componentPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Checks Ports Allocations consistency.
 */
public class MDCHK_ComponentPort_PortAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    // Precondition.
    if (!(eType == EMFEventType.NULL) || !(eObj instanceof ComponentPort)) {
      return ctx.createSuccessStatus();
    }

    ComponentPort port = (ComponentPort) eObj;
    // Go through PortAllocations.
    for (PortAllocation portAllocation : port.getOutgoingPortAllocations()) {
      TraceableElement sourceElement = portAllocation.getSourceElement();
      TraceableElement targetElement = portAllocation.getTargetElement();
      if ((sourceElement instanceof ComponentPort) && (targetElement instanceof ActivityNode)) {
        // Check target Function is nested in source Component.
        Boolean result = PortExt.isRelatedComponentAllocatingRelatedFunction((ComponentPort) sourceElement, (ActivityNode) targetElement);
        if (Boolean.FALSE.equals(result)) {
          return ctx.createFailureStatus(port.getName());
        }
      } else {
        return ctx.createFailureStatus(port.getName());
      }
    }
    return ctx.createSuccessStatus();
  }

}
