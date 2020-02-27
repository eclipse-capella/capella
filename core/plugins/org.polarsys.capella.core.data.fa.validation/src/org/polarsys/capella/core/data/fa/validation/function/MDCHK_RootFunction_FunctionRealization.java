/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.function;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * TC_DF_15 - This rule ensures the realization consistency between Root Functions.
 */
public class MDCHK_RootFunction_FunctionRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractFunction) {
        AbstractFunction function = (AbstractFunction) eObj;
        String currentFunctionName = "Root \"" + function.getName() + "\" (" + function.eClass().getName() + ") ";
        String targetFunctionName = "";
        BlockArchitecture currentLevelArchitecture = BlockArchitectureExt.getRootBlockArchitecture(function);
        BlockArchitecture previousArchitectures = BlockArchitectureExt
            .getPreviousBlockArchitecture(currentLevelArchitecture);
        if (previousArchitectures != null) {
          AbstractFunction targetFunction = BlockArchitectureExt.getRootFunction(previousArchitectures);
          if (targetFunction != null) {
            targetFunctionName = "Root \"" + targetFunction.getName() + "\" (" + targetFunction.eClass().getName()
                + ")";
          }
        }

        if (function
            .equals(BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(function)))) {
          if (function instanceof SystemFunction) {
            if (((SystemFunction) function).getRealizedOperationalActivities().isEmpty()) {
              if (previousArchitectures == null) {
                return createFailureStatus(ctx, new Object[] { currentFunctionName + "does not realize the "
                    + "Operational Analysis (Not Found), Please create Operational Analysis" });
              }
              targetFunctionName = (targetFunctionName.isEmpty()) ? "Root Operational Activity (Not Found)"
                  : targetFunctionName;
              return createFailureStatus(ctx,
                  new Object[] { currentFunctionName + "does not realize the " + targetFunctionName });
            }
          } else if (function instanceof LogicalFunction) {
            if (((LogicalFunction) function).getRealizedSystemFunctions().isEmpty()) {
              targetFunctionName = (targetFunctionName.isEmpty()) ? "Root System Function (Not Found)"
                  : targetFunctionName;
              return createFailureStatus(ctx,
                  new Object[] { currentFunctionName + "does not realize the " + targetFunctionName });
            }
          } else if (function instanceof PhysicalFunction) {
            if (((PhysicalFunction) function).getRealizedLogicalFunctions().isEmpty()) {
              targetFunctionName = (targetFunctionName.isEmpty()) ? "Root Logical Function (Not Found)"
                  : targetFunctionName;
              return createFailureStatus(ctx,
                  new Object[] { currentFunctionName + "does not realize the " + targetFunctionName });
            }
          }

          for (AbstractTrace trace : function.getIncomingTraces()) {
            TraceableElement sourceElement = trace.getSourceElement();

            if (trace instanceof FunctionRealization) {
              if (!((function instanceof LogicalFunction && sourceElement instanceof PhysicalFunction)
                  || (function instanceof SystemFunction && sourceElement instanceof LogicalFunction)
                  || (function instanceof OperationalActivity && sourceElement instanceof SystemFunction))) {
                return createFailureStatus(ctx,
                    new Object[] { currentFunctionName + "does not realize by accurate Root Function" });
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
