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
package org.polarsys.capella.core.re.handlers.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.DefaultScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.re.handlers.scope.DefaultDependenciesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapellaDependenciesHandler extends DefaultDependenciesHandler {

  @Override
  protected void initSharedHandler(DefaultScopeHandler handler_p, IContext context_p) {
    super.initSharedHandler(handler_p, context_p);
  }

  @Override
  protected void initDependenciesHandler(DefaultScopeHandler handler_p, IContext context_p) {
    super.initDependenciesHandler(handler_p, context_p);
  }

  @Override
  protected void initRelatedElementsHandler(DefaultScopeHandler handler_p, IContext context_p) {
    super.initRelatedElementsHandler(handler_p, context_p);
    handler_p.addScopeRetriever(new IScopeRetriever() {

      @Override
      public IStatus init(IContext context_p) {
        return Status.OK_STATUS;
      }

      @Override
      public IStatus dispose(IContext context_p) {
        return Status.OK_STATUS;
      }

      @Override
      public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {
        return null;
      }

      @Override
      public Collection<? extends EObject> retrieveRelatedElements(EObject element_p, IContext context_p) {
        if (element_p instanceof Component) {
          Collection<EObject> childs = new HashSet<EObject>();
          for (ComponentPort port : ComponentExt.getOwnedComponentPort((Component) element_p)) {
            childs.addAll(port.getInformationFlows());
          }

          for (PhysicalPort port : ComponentExt.getOwnedPhysicalPort((Component) element_p)) {
            childs.addAll(port.getInvolvedLinks());
          }
          return childs;
        }
        return null;
      }
    }, context_p);
  }

  @Override
  protected void initScopeElementsHandler(DefaultScopeHandler handler_p, IContext context_p) {
    super.initScopeElementsHandler(handler_p, context_p);

    handler_p.addScopeRetriever(new PartTypeRetriever(), context_p);
    handler_p.addScopeRetriever(new DeployedElementRetriever(), context_p);

    handler_p.addScopeFilter(new AbstractTraceFilter(), context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initComplementaryScopeElementsHandler(DefaultScopeHandler handler_p, IContext context_p) {

    super.initComplementaryScopeElementsHandler(handler_p, context_p);

    handler_p.addScopeRetriever(new IScopeRetriever() {

      Collection<AbstractTrace> traces = new HashSet<AbstractTrace>();
      Collection<Involvement> involvements = new HashSet<Involvement>();
      Collection<AbstractDeploymentLink> relationship = new HashSet<AbstractDeploymentLink>();

      @Override
      public IStatus init(IContext context_p) {
        return Status.OK_STATUS;
      }

      @Override
      public IStatus dispose(IContext context_p) {
        return Status.OK_STATUS;
      }

      @Override
      public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {

        Collection<EObject> result = new ArrayList<EObject>();

        for (AbstractTrace trace : traces) {
          if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, trace.getSourceElement(), context_p)) {
            if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, trace.getTargetElement(), context_p)) {
              result.add(trace);
            }
          }
        }

        for (Involvement involvment : involvements) {
          if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, involvment.getInvolver(), context_p)) {
            if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, involvment.getInvolved(), context_p)) {
              result.add(involvment);
            }
          }
        }

        for (AbstractDeploymentLink link : relationship) {
          if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, link.getDeployedElement(), context_p)) {
            if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, link.getLocation(), context_p)) {
              result.add(link);
            }
          }
        }

        return result;
      }

      @Override
      public Collection<? extends EObject> retrieveRelatedElements(EObject element_p, IContext context_p) {

        ContextScopeHandlerHelper.getInstance(context_p).add(ITransitionConstants.SOURCE_SCOPE, element_p, context_p);

        if (element_p instanceof TraceableElement) {
          traces.addAll(((TraceableElement) element_p).getIncomingTraces());
          traces.addAll(((TraceableElement) element_p).getOutgoingTraces());
        }

        if (element_p instanceof InvolvedElement) {
          for (EObject reference : EObjectExt.getReferencers(element_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED)) {
            involvements.add((Involvement) reference);
          }
        }

        if (element_p instanceof DeployableElement) {
          relationship.addAll(((DeployableElement) element_p).getDeployingLinks());
        }
        if (element_p instanceof DeploymentTarget) {
          relationship.addAll(((DeploymentTarget) element_p).getDeploymentLinks());
        }

        if (!ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.INITIAL_SOURCE_SCOPE, element_p, context_p)) {
          return Collections.emptyList();
        }
        Collection<EObject> result = new ArrayList<EObject>();

        //Special case for part and components
        if (element_p instanceof Part) {
          result.add(((Part) element_p).getAbstractType());
        }
        if (element_p instanceof Component) {
          result.addAll(((Component) element_p).getRepresentingPartitions());
        }

        return result;
      }
    }, context_p);

  }
}
