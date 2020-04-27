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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures ComonentExchange allocates valid functionalExchanges
 */
public class ComponentExchangeFEAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // This rule does not applies to physical links
      if ((eObj instanceof ComponentExchange)) {
        List<IStatus> statuses = new ArrayList<IStatus>();
        ComponentExchange componentExchange = (ComponentExchange) eObj;
        EList<FunctionalExchange> allocatedExchanges = componentExchange.getAllocatedFunctionalExchanges();
        // if no allocated exchanges found, no further check needed
        if (allocatedExchanges.size() < 1) {
          return ctx.createSuccessStatus();
        }
        List<CapellaElement> availableExhcnage = ComponentExchangeExt.getValidFEAvailableForAllocation(componentExchange);
        if ((null != availableExhcnage) && (null != allocatedExchanges)) {
          for (FunctionalExchange allocatedExchange : allocatedExchanges) {
            if (!availableExhcnage.contains(allocatedExchange)) {
              statuses.add(ctx.createFailureStatus(componentExchange.getName()
                                                     + " (" + componentExchange.eClass().getName() + ") should not allocate " //$NON-NLS-1$ //$NON-NLS-2$
                                                     + allocatedExchange.getName()
                                                     + " (" + allocatedExchange.eClass().getName() + ") regarding ports directions.")); //$NON-NLS-1$ //$NON-NLS-2$
            }
          }
        }

        // return list of failure status message if any
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
