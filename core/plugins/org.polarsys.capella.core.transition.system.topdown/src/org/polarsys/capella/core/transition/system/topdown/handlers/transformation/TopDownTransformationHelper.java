/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.transformation;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.ScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityTraceHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.rules.IRuleTransformation;
import org.polarsys.capella.core.transition.system.handlers.transformation.CapellaTransformationHandler;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 *
 */
public class TopDownTransformationHelper extends CapellaTransformationHandler {

  /**
   * @param context_p
   * @return
   */
  public static TopDownTransformationHelper getInstance(IContext context_p) {
    return new TopDownTransformationHelper();
  }

  @Override
  public IStatus isOrWillBeTransformed(EObject source_p, IContext context_p) {

    IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);

    ITraceabilityHandler handler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER);
    boolean result = handler.isTraced(source_p, context_p) || isTracedInTarget(source_p, context_p);

    if (!result) {
      // Element must be in the scope of the transformation, otherwise, even if the rule tells so, it will not be transformed :)
      if (ScopeHandlerHelper.getInstance(context_p).isInScope(source_p, context_p)) {
        try {
          MappingPossibility mapping = ruleHandler.getApplicablePossibility(source_p);
          if (mapping != null) {
            IRule<?> rule = ruleHandler.getApplicablePossibility(source_p).getCompleteRule();
            if ((rule != null) && (rule instanceof IRuleTransformation)) {
              IRuleTransformation deeperRule = (IRuleTransformation) rule;
              result = deeperRule.applyRequired(source_p, context_p).isOK() && deeperRule.transformRequired(source_p, context_p).isOK();
            }
          }
        } catch (MappingPossibilityResolutionException exception_p) {
          // Nothing to report
        }
      }
    }
    if (result) {
      return Status.OK_STATUS;
    }

    return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("Element ''{0}'' will not be transitioned.",
        LogHelper.getInstance().getText(source_p)));
  }

  public boolean isTracedInTarget(EObject element_p, IContext context_p) {

    // To know if an element already exist into target, we use the TraceabilityHandler of target scope while transformation.
    // We need to replace the current handler to avoid transformation in target model instead of transformation in transformation model.
    IHandler handler = TraceabilityHandlerHelper.getInstance(context_p);
    boolean result = false;
    try {
      ITraceabilityHandler targetHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TARGET_HANDLER);
      if (!targetHandler.retrieveTracedElements(element_p, context_p).isEmpty()) {
        result = true;
      }
    } finally {
      context_p.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);

    }
    return result;
  }

  public Collection<EObject> retrieveTargetTracedElements(EObject element_p, IContext context_p) {

    Collection<EObject> objects = Collections.emptyList();
    IHandler handler = TraceabilityHandlerHelper.getInstance(context_p);
    try {
      ITraceabilityHandler targetHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TARGET_HANDLER);
      objects = targetHandler.retrieveTracedElements(element_p, context_p);

    } finally {
      context_p.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);

    }
    return objects;
  }

  public boolean isTrace(EObject element_p, IContext context_p) {
    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER);
    if (sourceHandler instanceof ITraceabilityTraceHandler) {
      ITraceabilityTraceHandler tH = (ITraceabilityTraceHandler) sourceHandler;
      if (tH.isTrace(element_p, context_p)) {
        return true;
      }
    }
    return false;
  }

}
