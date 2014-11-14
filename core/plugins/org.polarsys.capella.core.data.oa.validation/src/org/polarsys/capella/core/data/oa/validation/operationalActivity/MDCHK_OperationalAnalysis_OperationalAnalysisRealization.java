/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Ensures that Operational Analysis Realization links targeting Operational Analysis instances have System Analysis instances as source.
 */
public class MDCHK_OperationalAnalysis_OperationalAnalysisRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof OperationalAnalysis) {
        OperationalAnalysis oa = (OperationalAnalysis) eObj;

        for (AbstractTrace trace : oa.getIncomingTraces()) {
          TraceableElement sourceElement = trace.getSourceElement();
          if ((trace instanceof OperationalAnalysisRealization) && !(sourceElement instanceof SystemAnalysis)) {
            return createFailureStatus(ctx_p, new Object[] { oa.getName() });
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
