/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
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
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) eObj;
      AbstractFunction parentSource = FunctionalExchangeExt.getSourceFunction(fe);
      AbstractFunction parentTarget = FunctionalExchangeExt.getTargetFunction(fe);

      boolean bDelegatedFE = false;
      if (parentSource.getOwnedFunctions().size() == 0 && parentTarget.getOwnedFunctions().size() == 0) {
        bDelegatedFE = true;
      }
      if (!bDelegatedFE) {
        return ctx.createFailureStatus(getSourceTargetMessage(fe));
      }
    }
    return ctx.createSuccessStatus();
  }

  private String getMessageNameFor(AbstractFunction af) {
    if (af instanceof OperationalActivity) {
      return "Operational Activity";
    } else if (af instanceof SystemFunction) {
      return "System Function";
    } else if (af instanceof LogicalFunction) {
      return "Logical Function";
    } else if (af instanceof PhysicalFunction) {
      return "Physical Function";
    }
    return "";
  }

  private String getMessageNameFor(FunctionalExchange fe) {
    AbstractFunction srcFunc = FunctionalExchangeExt.getSourceFunction(fe);
    if (srcFunc instanceof OperationalActivity) {
      return "Interaction";
    }
    return "Functional Exchange";
  }

  private String getSourceTargetMessage(FunctionalExchange fe) {
    String msg = "";
    AbstractFunction srcFunc = FunctionalExchangeExt.getSourceFunction(fe);
    AbstractFunction tarFunc = FunctionalExchangeExt.getTargetFunction(fe);

    if (srcFunc.getOwnedFunctions().size() > 0 && tarFunc.getOwnedFunctions().size() > 0) {
      msg = ("Both source and target of \"" + EObjectLabelProviderHelper.getText(fe) + "\" (" + getMessageNameFor(fe)
          + ") are not delegated to leaf " + getMessageNameFor(srcFunc));
    }
    else if (srcFunc.getOwnedFunctions().size() > 0) {
      msg = ("The source of \"" +  EObjectLabelProviderHelper.getText(fe) + "\" (" + getMessageNameFor(fe) + ") is not delegated to a leaf " + getMessageNameFor(srcFunc));
    }
    else {
      msg = ("The target of \"" +  EObjectLabelProviderHelper.getText(fe) + "\" (" + getMessageNameFor(fe) + ") is not delegated to a leaf " + getMessageNameFor(srcFunc));
    }
    return msg;
  }
}
