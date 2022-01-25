/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

  public IStatus isOrWillBeTransformed(EObject source, IContext context) {

    IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);

    ITraceabilityHandler handler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER);
    boolean result = handler.isTraced(source, context);

    if (!result) {
      // Element must be in the scope of the transformation, otherwise, even if the rule tells so, it will not be transformed :)
      if (ScopeHandlerHelper.getInstance(context).isInScope(source, context)) {
        try {
          MappingPossibility mapping = ruleHandler.getApplicablePossibility(source);
          if (mapping != null) {
            IRule<?> rule = ruleHandler.getApplicablePossibility(source).getCompleteRule();
            if ((rule != null) && (rule instanceof IRuleTransformation)) {
              IRuleTransformation deeperRule = (IRuleTransformation) rule;
              result = deeperRule.applyRequired(source, context).isOK() && deeperRule.transformRequired(source, context).isOK();
            }
          }
        } catch (MappingPossibilityResolutionException exception) {
          // Nothing to report
        }
      }
    }
    if (result) {
      return Status.OK_STATUS;
    }

    return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("Element ''{0}'' will not be transitioned.", LogHelper.getInstance().getText(source)));
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public EObject getLevelElement(EObject source, IContext context) {
    return source.eContainer();
  }

  /**
   * Experimental {@inheritDoc}
   */
  public EObject getBestTracedElement(EObject source, IContext context, ISelectionContext sContext) {
    // If some choices, we should ask to the user to make a choice
    // Retrieve the first matching element
    for (EObject target : TraceabilityHandlerHelper.getInstance(context).retrieveTracedElements(source, context)) {
      if ((sContext == null) || sContext.match(source, target, context)) {
        return target;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public EClass getTargetType(EObject source, IContext context) {
    IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);

    try {
      MappingPossibility mapping = ruleHandler.getApplicablePossibility(source);
      if (mapping != null) {
        IRule<?> rule = ruleHandler.getApplicablePossibility(source).getCompleteRule();
        if ((rule != null) && (rule instanceof IRuleTransformation)) {
          IRuleTransformation deeperRule = (IRuleTransformation) rule;
          return deeperRule.getTargetType(source, context);
        }
      }
    } catch (MappingPossibilityResolutionException exception) {
      // Nothing to report
    }
    return null;
  }

  public IStatus checkTransformRequired(EObject source, IContext context, EObject sourceElement) {
    IStatus result = Status.OK_STATUS;

    if ((sourceElement != null)) {
      if (!(TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(sourceElement, context).isOK())) {
        result =
            new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an element which is not transitioned.", LogHelper .getInstance().getText(source), LogHelper.getInstance().getText(source.eClass())));
      }
    } else {
      result =
          new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an null element.", LogHelper.getInstance().getText(source), LogHelper.getInstance().getText(source.eClass())));
    }

    return result;
  }

  public IStatus checkTransformRequired(EObject source, IContext context, EObject sourceElement, EObject targetElement) {
    IStatus result = Status.OK_STATUS;

    if ((sourceElement != null) && (targetElement != null)) {
      if (!(TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(sourceElement, context).isOK() && TransformationHandlerHelper
          .getInstance(context).isOrWillBeTransformed(targetElement, context).isOK())) {
        result =
            new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an element which is not transitioned.", LogHelper .getInstance().getText(source), LogHelper.getInstance().getText(source.eClass())));
      }
    } else {
      result =
          new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("{1} ''{0}'' is linked to an null element.", LogHelper.getInstance().getText(source), LogHelper.getInstance().getText(source.eClass())));
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  public EObject getBestTracedElement(EObject root, IContext context, EClass eClass) {
    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION);
    ISelectionContext classContext = new EClassSelectionContext(sContext, eClass);
    return getBestTracedElement(root, context, classContext);
  }

  /**
   * {@inheritDoc}
   */
  public EObject getBestTracedElement(EObject root, IContext context, EClass eClass, EObject element, EObject result) {
    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, element,
            result);
    ISelectionContext classContext = new EClassSelectionContext(sContext, eClass);
    return getBestTracedElement(root, context, classContext);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void postTransformElement(EObject element, EObject result, IContext context) {
    AttachmentHelper.getInstance(context).createdElement(element, result, context);
  }

}
