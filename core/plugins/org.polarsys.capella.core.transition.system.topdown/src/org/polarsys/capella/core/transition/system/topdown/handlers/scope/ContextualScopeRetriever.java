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
package org.polarsys.capella.core.transition.system.topdown.handlers.scope;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ContextualScopeRetriever implements IScopeRetriever {

  protected ITraceabilityHandler getTraceabilityTargetHandler(IContext context_p) {
    ITraceabilityHandler handler = null;
    if (!context_p.exists(ITopDownConstants.TARGET_HANGLER)) {
      handler = createDefaultTraceabilityTargetHandler();
      handler.init(context_p);
      context_p.put(ITopDownConstants.TARGET_HANGLER, handler);
    }
    handler = (ITraceabilityHandler) context_p.get(ITopDownConstants.TARGET_HANGLER);
    return handler;
  }

  protected ITraceabilityHandler createDefaultTraceabilityTargetHandler() {
    ITraceabilityConfiguration configuration = new MergeTargetConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * @param parent_p
   * @return
   */
  protected boolean isExclude(EObject source_p, EObject parent_p, IContext context_p) {
    return (parent_p instanceof BlockArchitecture) || (parent_p instanceof Project);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveRelatedElements(EObject source_p, IContext context_p) {
    EObject parent = source_p.eContainer();
    if ((parent != null) && !(isExclude(source_p, parent, context_p))) {
      ContextScopeHandlerHelper.getInstance(context_p).add(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, parent, context_p);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {
    Collection<EObject> toIgnore = new HashSet<EObject>();

    //An ignored element is an element with parent not ignored
    Iterator<EObject> ignoreds = ContextScopeHandlerHelper.getInstance(context_p).get(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, context_p);
    while (ignoreds.hasNext()) {
      EObject ignored = ignoreds.next();
      EObject parent = ignored.eContainer();
      if ((parent != null) && !(isExclude(ignored, parent, context_p))) {
        if (!ContextScopeHandlerHelper.getInstance(context_p).contains(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, parent, context_p)) {
          toIgnore.add(ignored);
        }
      }
    }

    ContextScopeHandlerHelper.getInstance(context_p).clear(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, context_p);
    ContextScopeHandlerHelper.getInstance(context_p).addAll(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, toIgnore, context_p);
    toIgnore.clear();

    //We remove from ignored all elements not already traced into target
    ignoreds = ContextScopeHandlerHelper.getInstance(context_p).get(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, context_p);
    while (ignoreds.hasNext()) {
      EObject ignored = ignoreds.next();
      if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(ignored, context_p)) {
        toIgnore.add(ignored);
      }
    }

    ContextScopeHandlerHelper.getInstance(context_p).clear(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, context_p);
    ContextScopeHandlerHelper.getInstance(context_p).addAll(ITopDownConstants.CONTEXTUAL_IGNORED_ELEMENTS, toIgnore, context_p);
    return Collections.emptyList();
  }
}
