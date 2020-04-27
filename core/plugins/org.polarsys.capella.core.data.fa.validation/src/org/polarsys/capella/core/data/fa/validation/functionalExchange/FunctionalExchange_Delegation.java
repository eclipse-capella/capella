/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalActivity;
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

      if (parentSource != null && parentTarget != null) {
        boolean bDelegatedFE = false;
        if (parentSource.getOwnedFunctions().isEmpty() && parentTarget.getOwnedFunctions().isEmpty()) {
          bDelegatedFE = true;
        }
        if (!bDelegatedFE) {
          return ctx.createFailureStatus(getSourceTargetMessage(fe));
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  private String getMessageNameFor(AbstractFunction af) {
    return EObjectLabelProviderHelper.getMetaclassLabel(af, false);
  }

  private String getMessageNameFor(FunctionalExchange fe) {
    AbstractFunction srcFunc = FunctionalExchangeExt.getSourceFunction(fe);
    if (srcFunc instanceof OperationalActivity) {
      return "Interaction";
    }
    return EObjectLabelProviderHelper.getMetaclassLabel(fe, false);
  }

  private String getSourceTargetMessage(FunctionalExchange fe) {
    String msg = "";
    AbstractFunction srcFunc = FunctionalExchangeExt.getSourceFunction(fe);
    AbstractFunction tarFunc = FunctionalExchangeExt.getTargetFunction(fe);

    if (srcFunc.getOwnedFunctions().size() > 0 && tarFunc.getOwnedFunctions().size() > 0) {
      msg = ("Both source and target of \"" + EObjectLabelProviderHelper.getText(fe) + "\" (" + getMessageNameFor(fe)
          + ") are not delegated to leaf " + getMessageNameFor(srcFunc));
    } else if (srcFunc.getOwnedFunctions().size() > 0) {
      msg = ("The source of \"" + EObjectLabelProviderHelper.getText(fe) + "\" (" + getMessageNameFor(fe)
          + ") is not delegated to a leaf " + getMessageNameFor(srcFunc));
    } else {
      msg = ("The target of \"" + EObjectLabelProviderHelper.getText(fe) + "\" (" + getMessageNameFor(fe)
          + ") is not delegated to a leaf " + getMessageNameFor(srcFunc));
    }
    return msg;
  }
}
