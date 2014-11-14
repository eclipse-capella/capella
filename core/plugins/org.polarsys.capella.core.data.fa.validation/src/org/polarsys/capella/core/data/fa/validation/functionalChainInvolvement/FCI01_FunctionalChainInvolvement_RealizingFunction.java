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
package org.polarsys.capella.core.data.fa.validation.functionalChainInvolvement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Checks realization consistency between functional exchanges.
 */
public class FCI01_FunctionalChainInvolvement_RealizingFunction extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      boolean isFunction= false;
      
      if (eObj instanceof FunctionalChainInvolvement) {
        FunctionalChainInvolvement fci = (FunctionalChainInvolvement)eObj;
        if (fci.getInvolved() instanceof AbstractFunction) {
          isFunction = true;
        }
        for (AbstractTrace trace : fci.getOutgoingTraces()) {
          if (trace.getTargetElement() instanceof FunctionalChainInvolvement) {
            FunctionalChainInvolvement exc = (FunctionalChainInvolvement)trace.getTargetElement();
            if (exc.getInvolved() instanceof AbstractFunction) {
              
              for (AbstractTrace traceFunction : ((AbstractFunction)exc.getInvolved()).getIncomingTraces()) {
                if (traceFunction.getSourceElement() instanceof AbstractFunction) {
                  AbstractFunction targetFunction = (AbstractFunction)traceFunction.getSourceElement();
                  if (EcoreUtil2.isOrIsContainedBy(fci.getInvolved(), targetFunction)) {
                    return ctx.createSuccessStatus();
                  }
                }
              }
              
            }
          }
        }
        
        if (fci.getOutgoingTraces().size()!=0 && isFunction) {
          return createFailureStatus(ctx, new Object[] { CapellaElementExt.getName(fci) });
        }
        
      }
    }
    return ctx.createSuccessStatus();
    
  }

}
