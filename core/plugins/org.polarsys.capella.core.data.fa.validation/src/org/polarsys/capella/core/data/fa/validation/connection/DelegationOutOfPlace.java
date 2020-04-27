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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures correct location for a component exchange
 */
public class DelegationOutOfPlace extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    // Raise a warning if component exchange is not located in the common ancestor between both component exchange bounds
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentExchange) {
        ComponentExchange exchagne = (ComponentExchange) eObj;
        ComponentExchangeKind kind = exchagne.getKind();
        if (kind.equals(ComponentExchangeKind.DELEGATION)) {
          Collection<Part> sourceParts = ComponentExchangeExt.getSourceParts(exchagne);
          Collection<Part> targetParts = ComponentExchangeExt.getTargetParts(exchagne);
          if ((null == sourceParts) || sourceParts.isEmpty() || (null == targetParts) || targetParts.isEmpty()) {
            return ctx.createFailureStatus(exchagne.getName()
                                             + " (Component Exchange) of kind Delegation is not valid (could not find source of target element)."); //$NON-NLS-1$
          }
          boolean flag = false;
          for (Part part : targetParts) {
            Collection<Part> firstPartAncestors = PartExt.getFirstPartAncestors(part);
            for (Part part2 : firstPartAncestors) {
              if (sourceParts.contains(part2)) {
                flag = true;
                break;
              }
            }
            if (flag) {
              break;
            }
          }
          // if containment relationship not found
          if (!flag) {
            return ctx.createFailureStatus(exchagne.getName() + " (Component Exchange) of kind Delegation is out of place and useless."); //$NON-NLS-1$
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
