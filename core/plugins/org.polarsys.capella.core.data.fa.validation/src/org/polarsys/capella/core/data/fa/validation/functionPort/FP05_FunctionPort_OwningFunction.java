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
package org.polarsys.capella.core.data.fa.validation.functionPort;

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPortExt;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class FP05_FunctionPort_OwningFunction extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionPort) {
        AbstractFunction owner = (AbstractFunction) eObj.eContainer();
        for (Port fip : FunctionPortExt.getRealizingPorts((FunctionPort) eObj)) {
          AbstractFunction fct = (AbstractFunction) fip.eContainer();
          if (!FunctionExt.getRealizedFunctions(fct).contains(owner)) {
            return ConstraintStatus.createStatus(ctx, fct, Arrays.asList(fct, owner), getMessagePattern(),
              new Object[]{owner.getName(), fct.getName()});
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   *
   */
  protected String getMessagePattern() {
    return "{0} (Function} shall be realized by {1} (Function} : both contain a function port having a realization link between them"; //$NON-NLS-1$
  }
}
