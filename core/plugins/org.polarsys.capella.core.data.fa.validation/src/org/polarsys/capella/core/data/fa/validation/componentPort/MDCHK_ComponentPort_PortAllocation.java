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
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    // Precondition.
    if (!(eType == EMFEventType.NULL) || !(eObj instanceof ComponentPort)) {
      return ctx_p.createSuccessStatus();
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
          return ctx_p.createFailureStatus(port.getName());
        }
      } else {
        return ctx_p.createFailureStatus(port.getName());
      }
    }
    return ctx_p.createSuccessStatus();
  }

}
