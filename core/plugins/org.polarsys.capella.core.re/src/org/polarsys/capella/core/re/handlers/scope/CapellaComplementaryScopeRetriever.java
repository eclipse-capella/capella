/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.handlers.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CapellaComplementaryScopeRetriever implements IScopeRetriever {

  Collection<AbstractTrace> traces = new HashSet<AbstractTrace>();
  Collection<Involvement> involvements = new HashSet<Involvement>();
  Collection<AbstractDeploymentLink> relationship = new HashSet<AbstractDeploymentLink>();

  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context) {

    Collection<EObject> result = new ArrayList<EObject>();

    for (AbstractTrace trace : traces) {
      if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE,
          trace.getSourceElement(), context)) {
        if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE,
            trace.getTargetElement(), context)) {
          result.add(trace);
        }
      }
    }

    for (Involvement involvment : involvements) {
      if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE,
          involvment.getInvolver(), context)) {
        if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE,
            involvment.getInvolved(), context)) {
          result.add(involvment);
        }
      }
    }

    for (AbstractDeploymentLink link : relationship) {
      if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE,
          link.getDeployedElement(), context)) {
        if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE,
            link.getLocation(), context)) {
          result.add(link);
        }
      }
    }

    return result;
  }

  @Override
  public Collection<? extends EObject> retrieveRelatedElements(EObject element, IContext context) {

    ContextScopeHandlerHelper.getInstance(context).add(ITransitionConstants.SOURCE_SCOPE, element, context);

    if (element instanceof TraceableElement) {
      traces.addAll(((TraceableElement) element).getIncomingTraces());
      traces.addAll(((TraceableElement) element).getOutgoingTraces());
    }

    if (element instanceof InvolvedElement) {
      for (EObject reference : EObjectExt.getReferencers(element,
          CapellacorePackage.Literals.INVOLVEMENT__INVOLVED)) {
        involvements.add((Involvement) reference);
      }
    }

    if (element instanceof DeployableElement) {
      relationship.addAll(((DeployableElement) element).getDeployingLinks());
    }
    if (element instanceof DeploymentTarget) {
      relationship.addAll(((DeploymentTarget) element).getDeploymentLinks());
    }

    if (!ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.INITIAL_SOURCE_SCOPE, element,
        context)) {
      return Collections.emptyList();
    }
    Collection<EObject> result = new ArrayList<EObject>();

    // Special case for part and components
    if (element instanceof Part) {
      result.add(((Part) element).getAbstractType());
    }
    if (element instanceof Component) {
      result.addAll(((Component) element).getRepresentingParts());
    }

    return result;
  }
}
