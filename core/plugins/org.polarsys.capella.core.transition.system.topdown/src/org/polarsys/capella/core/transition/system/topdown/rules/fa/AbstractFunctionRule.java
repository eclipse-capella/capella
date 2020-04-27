/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.fa;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.attachment.AbstractFunctionAttachmentHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AbstractFunctionRule extends org.polarsys.capella.core.transition.system.rules.fa.AbstractFunctionRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    //transform if there is at least one child not previously transformed
    AbstractFunction sourceElement = (AbstractFunction) element_p;

    IStatus result = super.transformRequired(sourceElement, context_p);
    if (result.isOK()) {
      if (FunctionExt.isRootFunction(element_p) || (FunctionExt.isLeaf((AbstractFunction) element_p))
          || (AbstractFunctionAttachmentHelper.getInstance(context_p).getNbUntransitionedSubFunctions(sourceElement, context_p) > 0)
          || TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(element_p, context_p)) {
        return result;
      }
    }
    return new Status(IStatus.WARNING, Messages.Activity_Transformation, "Sub Function already transitioned");
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {

    super.retrieveGoDeep(source_p, result_p, context_p);

    //but we return children
    AbstractFunction element = (AbstractFunction) source_p;

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.addAll(FunctionExt.getOwnedFunctionPorts(element));
      result_p.addAll(FunctionExt.getOwnedFunctionPkgs(element));
      result_p.addAll(element.getOwnedFunctionalChains());

      result_p.addAll(getOwnedFunctions(element));
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, FunctionExt.getOwnedFunctionPorts(element), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, FunctionExt.getOwnedFunctionPkgs(element), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, getOwnedFunctions(element), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedFunctionalChains(), context_p);

      //Transition only already transitioned states
      for (EObject sourceState : element.getAvailableInStates()) {
        if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(sourceState, context_p)) {
          result_p.add(sourceState);
        }
      }
    }
  }

  /**
   * Returns owned function pkgs
   * @param function_p
   * @return
   */
  public Collection<? extends AbstractFunction> getOwnedFunctions(AbstractFunction function_p) {
    return function_p.getOwnedFunctions();
  }

}
