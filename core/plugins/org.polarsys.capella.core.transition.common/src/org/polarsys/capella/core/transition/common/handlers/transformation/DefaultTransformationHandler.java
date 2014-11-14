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
package org.polarsys.capella.core.transition.common.handlers.transformation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.ScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.rules.IRuleTransformation;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 */
public class DefaultTransformationHandler implements ITransformationHandler {

  public IStatus isOrWillBeTransformed(EObject source_p, IContext context_p) {

    IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);

    ITraceabilityHandler handler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER);
    boolean result = handler.isTraced(source_p, context_p);

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

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public EObject getLevelElement(EObject source_p, IContext context_p) {
    return source_p.eContainer();
  }

  /**
   * Experimental {@inheritDoc}
   */
  public EObject getBestTracedElement(EObject source_p, IContext context_p, ISelectionContext sContext_p) {
    // If some choices, we should ask to the user to make a choice
    // Retrieve the first matching element
    for (EObject target : TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(source_p, context_p)) {
      if ((sContext_p == null) || sContext_p.match(source_p, target, context_p)) {
        return target;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public EClass getTargetType(EObject source_p, IContext context_p) {
    IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);

    try {
      MappingPossibility mapping = ruleHandler.getApplicablePossibility(source_p);
      if (mapping != null) {
        IRule<?> rule = ruleHandler.getApplicablePossibility(source_p).getCompleteRule();
        if ((rule != null) && (rule instanceof IRuleTransformation)) {
          IRuleTransformation deeperRule = (IRuleTransformation) rule;
          return deeperRule.getTargetType(source_p, context_p);
        }
      }
    } catch (MappingPossibilityResolutionException exception_p) {
      // Nothing to report
    }
    return null;
  }

  public IStatus checkTransformRequired(EObject source_p, IContext context_p, EObject sourceElement) {
    IStatus result = Status.OK_STATUS;

    if ((sourceElement != null)) {
      if (!(TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(sourceElement, context_p).isOK())) {
        result =
            new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an element which is not transitioned.", LogHelper
                .getInstance().getText(source_p), LogHelper.getInstance().getText(source_p.eClass())));
      }
    } else {
      result =
          new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an null element.",
              LogHelper.getInstance().getText(source_p), LogHelper.getInstance().getText(source_p.eClass())));
    }

    return result;
  }

  public IStatus checkTransformRequired(EObject source_p, IContext context_p, EObject sourceElement, EObject targetElement) {
    IStatus result = Status.OK_STATUS;

    if ((sourceElement != null) && (targetElement != null)) {
      if (!(TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(sourceElement, context_p).isOK() && TransformationHandlerHelper
          .getInstance(context_p).isOrWillBeTransformed(targetElement, context_p).isOK())) {
        result =
            new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an element which is not transitioned.", LogHelper
                .getInstance().getText(source_p), LogHelper.getInstance().getText(source_p.eClass())));
      }
    } else {
      result =
          new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an null element.",
              LogHelper.getInstance().getText(source_p), LogHelper.getInstance().getText(source_p.eClass())));
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  public EObject getBestTracedElement(EObject root_p, IContext context_p, EClass class_p) {
    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION);
    ISelectionContext context = new EClassSelectionContext(sContext, class_p);
    return getBestTracedElement(root_p, context_p, context);
  }

  /**
   * {@inheritDoc}
   */
  public EObject getBestTracedElement(EObject root_p, IContext context_p, EClass class_p, EObject element_p, EObject result_p) {
    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, element_p,
            result_p);
    return getBestTracedElement(root_p, context_p, sContext);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void postTransformElement(EObject element_p, EObject result_p, IContext context_p) {
    AttachmentHelper.getInstance(context_p).createdElement(element_p, result_p, context_p);
  }

}
