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
package org.polarsys.capella.core.data.la.validation.logicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that a leaf Logical Component is realized by at least one Physical Component.
 */
public class MDCHK_LogicalComponent_Realization_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        LogicalComponent logCpnt = (LogicalComponent) eObj;
        // The verification is done only if the Logical Component is a leaf or if is an actor
        // Gets the sub owned parts:
        EList<Part> ownedParts = logCpnt.getContainedParts();
        if (ownedParts.size() == 0) {
          if (!logCpnt.getRealizingPhysicalComponents().isEmpty()) {
            return ctx.createSuccessStatus();
          }
          return ctx
              .createFailureStatus(
                  new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(logCpnt), "Physical Component" });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
