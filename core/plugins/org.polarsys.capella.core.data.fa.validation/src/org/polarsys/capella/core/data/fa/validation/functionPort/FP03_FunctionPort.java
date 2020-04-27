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

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public abstract class FP03_FunctionPort extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionInputPort) {
        FunctionInputPort fip = (FunctionInputPort) eObj;
        AbstractFunction fct_fip = (AbstractFunction) fip.eContainer();

        for (ExchangeItem item : fip.getIncomingExchangeItems()) {
          for (EObject ref : EObjectExt.getReferencers(item, FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS)) {
            if (!ref.equals(fip) && (ref instanceof FunctionInputPort)) {
              AbstractFunction fct_ref = (AbstractFunction) ref.eContainer();
              if (!validate(ctx, fct_fip, fip, fct_ref, (FunctionPort) ref)) {
                return ConstraintStatus.createStatus(ctx, fip, Arrays.asList(fip, ref), getMessagePattern(),
                  new Object[]{fip.getName(), fct_fip.getName(), ((FunctionPort) ref).getName(), fct_ref.getName()});
              }
            }
          }
        }
      } else if (eObj instanceof FunctionOutputPort) {
        FunctionOutputPort fop = (FunctionOutputPort) eObj;
        AbstractFunction fct_fop = (AbstractFunction) fop.eContainer();

        for (ExchangeItem item : fop.getOutgoingExchangeItems()) {
          for (EObject ref : EObjectExt.getReferencers(item, FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS)) {
            if (!ref.equals(fop) && (ref instanceof FunctionOutputPort)) {
              AbstractFunction fct_ref = (AbstractFunction) ref.eContainer();
              if (!validate(ctx, fct_fop, fop, fct_ref, (FunctionPort) ref)) {
                return ConstraintStatus.createStatus(ctx, fop, Arrays.asList(fop, ref), getMessagePattern(),
                  new Object[]{fop.getName(), fct_fop.getName(), ((FunctionPort) ref).getName(), fct_ref.getName()});
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  protected abstract String getMessagePattern();

  protected abstract boolean validate(IValidationContext ctx, AbstractFunction fct1, FunctionPort fp1, AbstractFunction fct2, FunctionPort fp2);
}
