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
package org.polarsys.capella.common.re.handlers.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.DefaultScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.RuleRequiredElementsScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultDependenciesHandler implements IDependenciesHandler {

  public IScopeHandler getSharedHandler(IContext context_p) {
    if (!context_p.exists(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initSharedHandler(handler, context_p);
      context_p.put(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context_p.get(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER);
  }

  protected void initSharedHandler(DefaultScopeHandler handler_p, IContext context_p) {
    handler_p.addScopeRetriever(new SharedElementsScopeRetriever(), context_p);
  }

  public IScopeHandler getDependenciesHandler(IContext context_p) {
    if (!context_p.exists(IReConstants.DEPENDENCIES_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initDependenciesHandler(handler, context_p);
      context_p.put(IReConstants.DEPENDENCIES_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context_p.get(IReConstants.DEPENDENCIES_SCOPE_HANDLER);
  }

  protected void initDependenciesHandler(DefaultScopeHandler handler_p, IContext context_p) {
    handler_p.addScopeRetriever(new RuleRequiredElementsScopeRetriever(), context_p);
  }

  public Collection getSharedElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p) {
    return retrieveElements(getSharedHandler(context_p), elements_p, scopeElements, context_p);
  }

  public Collection getDependencies(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p) {
    return retrieveElements(getDependenciesHandler(context_p), elements_p, scopeElements, context_p);
  }

  public Collection retrieveElements(IScopeHandler handler, Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p) {
    Collection<EObject> values = new ArrayList<EObject>();
    IContext context = context_p;
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(context);

    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, context);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, context);

    scopeHandler.clear(IReConstants.REQUIRED_ELEMENTS, context);
    scopeHandler.clear(IReConstants.SHARED_ELEMENTS, context);

    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, elements_p, context);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, elements_p, context);

    handler.computeScope(elements_p, context);

    Collection<EObject> result = new ArrayList<EObject>(handler.getScope(context_p));

    for (EObject scopeElement : scopeElements) {
      result.remove(scopeElement);
      if (scopeElement instanceof CatalogElement) {
        result.removeAll(ReplicableElementHandlerHelper.getInstance(context_p).getAllElements((CatalogElement) scopeElement));
      }
    }
    return result;
  }

  public Collection getRelatedElements(Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p) {
    return retrieveRelatedElements(getRelatedElementsHandler(context_p), elements_p, scopeElements, context_p);
  }

  public IScopeHandler getRelatedElementsHandler(IContext context_p) {
    if (!context_p.exists(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initRelatedElementsHandler(handler, context_p);
      context_p.put(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context_p.get(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER);
  }

  protected void initRelatedElementsHandler(DefaultScopeHandler handler_p, IContext context_p) {
    handler_p.addScopeRetriever(new ReferencerScopeRetriever(), context_p);
    handler_p.addScopeFilter(new CatalogElementLinkFilter(), context_p);
  }

  public Collection retrieveRelatedElements(IScopeHandler handler, Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p) {
    Collection<EObject> values = new ArrayList<EObject>();
    IContext context = context_p;
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(context);

    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, context);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, context);
    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, scopeElements, context);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, scopeElements, context);

    handler.computeScope(scopeElements, context);
    values = new HashSet<EObject>(handler.getScope(context));
    values.remove(null);

    values.addAll(ReplicableElementHandlerHelper.getInstance(context).getLinkingReplicableElements(context,
        (Collection) scopeHandler.getCollection(ITransitionConstants.INITIAL_SOURCE_SCOPE, context)));

    return values;
  }

  public Collection retrieveScopeElements(IScopeHandler handler, Collection<EObject> elements_p, Collection<EObject> scopeElements, IContext context_p) {
    Collection<EObject> values = new ArrayList<EObject>();
    IContext context = context_p;
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(context);

    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, context);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, context);
    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, elements_p, context);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, elements_p, context);

    handler.computeScope(elements_p, context);
    values = new HashSet<EObject>(handler.getScope(context));
    values.remove(null);

    return values;
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
   * {@inheritDoc}
   */
  @Override
  public Collection getScopeElements(Collection<EObject> elements_p, Collection<EObject> scopeElements_p, IContext context_p) {
    return retrieveScopeElements(getScopeElementsHandler(context_p), elements_p, scopeElements_p, context_p);
  }

  public IScopeHandler getScopeElementsHandler(IContext context_p) {
    if (!context_p.exists(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initScopeElementsHandler(handler, context_p);
      context_p.put(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context_p.get(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER);
  }

  /**
   * @param handler_p
   * @param context_p
   */
  protected void initScopeElementsHandler(DefaultScopeHandler handler_p, IContext context_p) {
    handler_p.addScopeRetriever(new ContainmentScopeRetriever(), context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection getComplementaryScopeElements(Collection<EObject> elements_p, Collection<EObject> scopeElements_p, IContext context_p) {
    return retrieveScopeElements(getComplementaryScopeElementsHandler(context_p), elements_p, scopeElements_p, context_p);
  }

  public IScopeHandler getComplementaryScopeElementsHandler(IContext context_p) {
    if (!context_p.exists(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initComplementaryScopeElementsHandler(handler, context_p);
      context_p.put(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context_p.get(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER);
  }

  /**
   * @param handler_p
   * @param context_p
   */
  protected void initComplementaryScopeElementsHandler(DefaultScopeHandler handler_p, IContext context_p) {

  }

}
