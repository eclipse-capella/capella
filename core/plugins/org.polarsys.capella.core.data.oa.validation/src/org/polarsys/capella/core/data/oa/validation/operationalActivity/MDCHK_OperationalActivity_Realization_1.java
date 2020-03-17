/*******************************************************************************
 * Copyright (c) 2006, 2014, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.oa.validation.operationalActivity;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_OperationalActivity_Realization_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof OperationalActivity) {
        OperationalActivity act = (OperationalActivity) eObj;
        if (!FunctionExt.isRootFunction(eObj)) {
          EList<SystemFunction> realizedSystemFunctions = act.getRealizingSystemFunctions();
          if (realizedSystemFunctions.isEmpty()) {
            return createFailureStatus(ctx, new Object[] { act.getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
