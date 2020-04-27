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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that the target flow port is used by a functional exchange
 */
public class MDCHK_FunctionPort_functionalExchange extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionInputPort) {
        FunctionInputPort port = (FunctionInputPort) eObj;

        if (port.getIncoming().size() == 0) {
          return ctx.createFailureStatus(new Object[] { port.getName() });
        }
      } else if (eObj instanceof FunctionOutputPort) {
        FunctionOutputPort port = (FunctionOutputPort) eObj;

        if (port.getOutgoing().size() == 0) {
          return ctx.createFailureStatus(new Object[] { port.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
