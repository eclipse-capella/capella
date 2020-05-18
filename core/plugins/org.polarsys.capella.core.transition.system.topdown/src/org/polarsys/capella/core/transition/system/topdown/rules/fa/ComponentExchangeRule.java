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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ComponentExchangeRule extends org.polarsys.capella.core.transition.system.rules.fa.ComponentExchangeRule {

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return FaPackage.Literals.COMPONENT_EXCHANGE;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    ComponentExchange sourceElement = (ComponentExchange) source_p;

    java.util.Collection<EObject> transfoSources = (java.util.Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (transfoSources.contains(source_p)) {
      ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, sourceElement.getSource(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, sourceElement.getTarget(), context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedComponentExchangeEnds(), context_p);
    }

    result_p.add(sourceElement.getSource());
    result_p.add(sourceElement.getTarget());
    result_p.addAll(sourceElement.getOwnedComponentExchangeEnds());
    result_p.addAll(sourceElement.getOwnedComponentExchangeFunctionalExchangeAllocations());
    result_p.addAll(sourceElement.getConvoyedInformations());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      ComponentExchange element = (ComponentExchange) element_p;

      java.util.Collection<EObject> transfoSources = (java.util.Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
      if (transfoSources.contains(element_p)) {
        return result;
      }
      if (!ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element_p, context_p)) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "not in scope");
      }
      if (element.getSource() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "source null");
      }
      if (element.getTarget() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "target null");
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(element.getSource(), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "source");
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(element.getTarget(), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transition, "target");
      }
    }
    return result;
  }

  @Override
  protected void attachExchangeRelated(EObject element_p, EObject result_p, IContext context_p) {

    ComponentExchange transfoSource = (ComponentExchange) element_p;
    ComponentExchange transfoTarget = (ComponentExchange) result_p;
    boolean valid = true;

    InformationsExchanger outputNode = transfoTarget.getSource();
    if ((outputNode == null) && (transfoSource.getSource() instanceof Entity)) {
      ComponentPort outputPort = PortExt.createOutFlowPort("out" + transfoSource.getName()); //$NON-NLS-1$
      transfoTarget.setSource(outputPort);
      Component transformedAction =
          (Component) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(transfoSource.getSource(), context_p,
              SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION));
      transformedAction.getOwnedFeatures().add(outputPort);
      valid = false;
    }

    InformationsExchanger inputNode = transfoTarget.getTarget();
    if ((inputNode == null) && (transfoSource.getTarget() instanceof Entity)) {
      ComponentPort outputPort = PortExt.createInFlowPort("in" + transfoSource.getName()); //$NON-NLS-1$
      transfoTarget.setTarget(outputPort);
      Component transformedAction =
          (Component) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(transfoSource.getTarget(), context_p,
              SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION));
      transformedAction.getOwnedFeatures().add(outputPort);
      valid = false;
    }

    if (valid) {
      super.attachExchangeRelated(element_p, result_p, context_p);
    }

  }

}
