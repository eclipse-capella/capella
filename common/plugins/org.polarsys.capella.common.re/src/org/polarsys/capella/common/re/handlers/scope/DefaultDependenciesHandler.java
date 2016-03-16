/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

  public IScopeHandler getSharedHandler(IContext context) {
    if (!context.exists(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initSharedHandler(handler, context);
      context.put(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER);
  }

  protected void initSharedHandler(DefaultScopeHandler handler, IContext context) {
    handler.addScopeRetriever(new SharedElementsScopeRetriever(), context);
  }

  public IScopeHandler getDependenciesHandler(IContext context) {
    if (!context.exists(IReConstants.DEPENDENCIES_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initDependenciesHandler(handler, context);
      context.put(IReConstants.DEPENDENCIES_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.DEPENDENCIES_SCOPE_HANDLER);
  }

  protected void initDependenciesHandler(DefaultScopeHandler handler, IContext context) {
    handler.addScopeRetriever(new RuleRequiredElementsScopeRetriever(), context);
  }

  public Collection getSharedElements(Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    return retrieveElements(getSharedHandler(context), elements, scopeElements, context);
  }

  public Collection getDependencies(Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    return retrieveElements(getDependenciesHandler(context), elements, scopeElements, context);
  }

  public Collection retrieveElements(IScopeHandler handler, Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    IContext ctx = context;
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(ctx);

    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, ctx);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, ctx);

    scopeHandler.clear(IReConstants.REQUIRED_ELEMENTS, ctx);
    scopeHandler.clear(IReConstants.SHARED_ELEMENTS, ctx);

    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, elements, ctx);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, elements, ctx);

    handler.computeScope(elements, ctx);

    Collection<EObject> result = new ArrayList<EObject>(handler.getScope(context));

    for (EObject scopeElement : scopeElements) {
      result.remove(scopeElement);
      if (scopeElement instanceof CatalogElement) {
        result.removeAll(ReplicableElementHandlerHelper.getInstance(context).getAllElements((CatalogElement) scopeElement));
      }
    }
    return result;
  }

  public Collection getRelatedElements(Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    return retrieveRelatedElements(getRelatedElementsHandler(context), elements, scopeElements, context);
  }

  public IScopeHandler getRelatedElementsHandler(IContext context) {
    if (!context.exists(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initRelatedElementsHandler(handler, context);
      context.put(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER);
  }

  protected void initRelatedElementsHandler(DefaultScopeHandler handler, IContext context) {
    handler.addScopeRetriever(new ReferencerScopeRetriever(), context);
    handler.addScopeFilter(new CatalogElementLinkFilter(), context);
  }

  public Collection retrieveRelatedElements(IScopeHandler handler, Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    Collection<EObject> values = new ArrayList<EObject>();
    IContext ctx = context;
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(ctx);

    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, ctx);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, ctx);
    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, scopeElements, ctx);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, scopeElements, ctx);

    handler.computeScope(scopeElements, ctx);
    values = new HashSet<EObject>(handler.getScope(ctx));
    values.remove(null);

    values.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getLinkingReplicableElements(ctx,
        (Collection) scopeHandler.getCollection(ITransitionConstants.INITIAL_SOURCE_SCOPE, ctx)));

    return values;
  }

  public Collection retrieveScopeElements(IScopeHandler handler, Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    Collection<EObject> values = new ArrayList<EObject>();
    IContext ctx = context;
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(ctx);

    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, ctx);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, ctx);
    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, elements, ctx);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, elements, ctx);

    handler.computeScope(elements, ctx);
    values = new HashSet<EObject>(handler.getScope(ctx));
    values.remove(null);

    return values;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection getScopeElements(Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    return retrieveScopeElements(getScopeElementsHandler(context), elements, scopeElements, context);
  }

  public IScopeHandler getScopeElementsHandler(IContext context) {
    if (!context.exists(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initScopeElementsHandler(handler, context);
      context.put(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER);
  }

  /**
   * @param handler
   * @param context
   */
  protected void initScopeElementsHandler(DefaultScopeHandler handler, IContext context) {
    handler.addScopeRetriever(new ContainmentScopeRetriever(), context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection getComplementaryScopeElements(Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    return retrieveScopeElements(getComplementaryScopeElementsHandler(context), elements, scopeElements, context);
  }

  public IScopeHandler getComplementaryScopeElementsHandler(IContext context) {
    if (!context.exists(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER)) {
      DefaultScopeHandler handler = new DefaultScopeHandler();
      initComplementaryScopeElementsHandler(handler, context);
      context.put(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER);
  }

  /**
   * @param handler
   * @param context
   */
  protected void initComplementaryScopeElementsHandler(DefaultScopeHandler handler, IContext context) {

  }

}
