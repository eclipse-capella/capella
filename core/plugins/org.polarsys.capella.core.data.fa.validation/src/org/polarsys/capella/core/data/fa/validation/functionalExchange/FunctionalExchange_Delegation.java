/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class FunctionalExchange_Delegation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionalExchange) {
        FunctionalExchange fe = (FunctionalExchange) eObj;
        AbstractFunction parentSource = FunctionalExchangeExt.getSourceFunction(fe);
        AbstractFunction parentTarget = FunctionalExchangeExt.getTargetFunction(fe);

        boolean bDelegatedFE = false;
        if (parentSource.getOwnedFunctions().size() == 0 && parentTarget.getOwnedFunctions().size() == 0) {
          bDelegatedFE = true;
        }
        if (!bDelegatedFE)
          return createFailureStatus(ctx_p, new Object[] { getSourceTargetMessage(fe) });
      }
    }
    return ctx_p.createSuccessStatus();
  }

  private String getMessaeNameFor(AbstractFunction af_p) {
    if (af_p instanceof OperationalActivity) {
      return "Operational Activity";
    } else if (af_p instanceof SystemFunction) {
      return "System Function";
    } else if (af_p instanceof LogicalFunction) {
      return "Logical Function";
    } else if (af_p instanceof PhysicalFunction) {
      return "Physical Function";
    }
    return "";
  }

  private String getMessaeNameFor(FunctionalExchange fe_p) {
    AbstractFunction srcFunc = FunctionalExchangeExt.getSourceFunction((FunctionalExchange) fe_p);
    if (srcFunc instanceof OperationalActivity) {
      return "Interaction";
    }
    return "Functional Exchange";
  }

  private String getSourceTargetMessage(FunctionalExchange fe_p) {
    String msg = "";
    AbstractFunction srcFunc = FunctionalExchangeExt.getSourceFunction((FunctionalExchange) fe_p);
    AbstractFunction tarFunc = FunctionalExchangeExt.getTargetFunction((FunctionalExchange) fe_p);

    if (srcFunc.getOwnedFunctions().size() > 0 && tarFunc.getOwnedFunctions().size() > 0)
      msg += ("Both source and target of \"" + fe_p.getName() + "\" (" + getMessaeNameFor(fe_p)
          + ") are not delegated to leaf " + getMessaeNameFor(srcFunc));
    else if (srcFunc.getOwnedFunctions().size() > 0)
      msg += ("The source of \"" + fe_p.getName() + "\" (" + getMessaeNameFor(fe_p) + ") is not delegated to a leaf " + getMessaeNameFor(srcFunc));
    else
      msg += ("The target of \"" + fe_p.getName() + "\" (" + getMessaeNameFor(fe_p) + ") is not delegated to a leaf " + getMessaeNameFor(srcFunc));
    return msg;
  }
}
