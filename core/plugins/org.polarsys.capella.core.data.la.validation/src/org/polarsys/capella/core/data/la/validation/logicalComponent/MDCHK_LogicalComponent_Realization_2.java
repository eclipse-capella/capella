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

import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that a Logical Component is not realized by more than one Physical Component.
 */
public class MDCHK_LogicalComponent_Realization_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        LogicalComponent lc = (LogicalComponent) eObj;
        if (lc.getRealizingPhysicalComponents().size() > 1) {
          return ctx.createFailureStatus(lc.getName(),
              lc.getRealizingPhysicalComponents().stream().map(x -> x.getName()).collect(Collectors.joining(", ")),
              NamingHelper.getTitleLabel(lc), "Physical Component");
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
