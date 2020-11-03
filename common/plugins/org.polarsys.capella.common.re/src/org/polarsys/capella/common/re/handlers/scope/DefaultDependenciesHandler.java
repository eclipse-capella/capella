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
package org.polarsys.capella.common.re.handlers.scope;

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
import org.polarsys.capella.core.transition.common.handlers.scope.ExtendedScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultDependenciesHandler implements IDependenciesHandler {

  protected IScopeHandler getSharedHandler(IContext context) {
    if (!context.exists(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER)) {
      ExtendedScopeHandler handler = new ExtendedScopeHandler(IReConstants.RE_MAPPING,
          IReConstants.RE_PURPOSE__SHARED_ELEMENTS);
      handler.init(context);
      context.put(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.SHARED_ELEMENTS_SCOPE_HANDLER);
  }

  protected IScopeHandler getValidSharedHandler(IContext context) {
    if (!context.exists(IReConstants.VALID_SHARED_ELEMENTS_SCOPE_HANDLER)) {
      ExtendedScopeHandler handler = new ExtendedScopeHandler(IReConstants.RE_MAPPING,
          IReConstants.RE_PURPOSE__VALID_SHARED_ELEMENTS);
      handler.setFilterExcluding(false);
      handler.init(context);
      context.put(IReConstants.VALID_SHARED_ELEMENTS_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.VALID_SHARED_ELEMENTS_SCOPE_HANDLER);
  }

  protected IScopeHandler getDependenciesHandler(IContext context) {
    if (!context.exists(IReConstants.DEPENDENCIES_SCOPE_HANDLER)) {
      ExtendedScopeHandler handler = new ExtendedScopeHandler(IReConstants.RE_MAPPING,
          IReConstants.RE_PURPOSE__DEPENDENCIES);
      handler.init(context);
      context.put(IReConstants.DEPENDENCIES_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.DEPENDENCIES_SCOPE_HANDLER);
  }

  protected IScopeHandler getScopeElementsHandler(IContext context) {
    if (!context.exists(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER)) {
      ExtendedScopeHandler handler = new ExtendedScopeHandler(IReConstants.RE_MAPPING,
          IReConstants.RE_PURPOSE__SCOPE_ELEMENTS);
      handler.init(context);
      context.put(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.SCOPE_COMPUTATION_SCOPE_HANDLER);
  }

  protected IScopeHandler getComplementaryScopeElementsHandler(IContext context) {
    if (!context.exists(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER)) {
      ExtendedScopeHandler handler = new ExtendedScopeHandler(IReConstants.RE_MAPPING,
          IReConstants.RE_PURPOSE__COMPLEMENTARY_SCOPE_ELEMENTS);
      handler.init(context);
      context.put(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER);
  }

  protected IScopeHandler getRelatedElementsHandler(IContext context) {
    if (!context.exists(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER)) {
      ExtendedScopeHandler handler = new ExtendedScopeHandler(IReConstants.RE_MAPPING,
          IReConstants.RE_PURPOSE__RELATED_ELEMENTS);
      handler.init(context);
      context.put(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER, handler);
    }
    return (IScopeHandler) context.get(IReConstants.RELATED_ELEMENTS_SCOPE_HANDLER);
  }

  public Collection<EObject> getSharedElements(Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    return retrieveElements(getSharedHandler(context), initialScopeElements, scopeElements, context);
  }

  public Collection<EObject> getDependencies(Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    return retrieveElements(getDependenciesHandler(context), initialScopeElements, scopeElements, context);
  }

  public Collection<EObject> getRelatedElements(Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    return retrieveRelatedElements(getRelatedElementsHandler(context), initialScopeElements, scopeElements, context);
  }

  @Override
  public Collection<EObject> getScopeElements(Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    return retrieveScopeElements(getScopeElementsHandler(context), initialScopeElements, scopeElements, context);
  }

  @Override
  public Collection<EObject> getComplementaryScopeElements(Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    return retrieveScopeElements(getComplementaryScopeElementsHandler(context), initialScopeElements, scopeElements,
        context);
  }

  @Override
  public Collection<EObject> getValidSharedElements(Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    return retrieveScopeElements(getValidSharedHandler(context), initialScopeElements, scopeElements, context);
  }

  public Collection<EObject> retrieveElements(IScopeHandler handler, Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    Collection<EObject> result = retrieveScopeElements(handler, initialScopeElements, scopeElements, context);

    for (EObject scopeElement : scopeElements) {
      result.remove(scopeElement);
      if (scopeElement instanceof CatalogElement) {
        result.removeAll(
            ReplicableElementHandlerHelper.getInstance(context).getAllElements((CatalogElement) scopeElement));
      }
    }
    return result;
  }

  public Collection<EObject> retrieveRelatedElements(IScopeHandler handler, Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {

    handler.computeScope(initialScopeElements, context);
    Collection<EObject> result = retrieveScopeElements(handler, initialScopeElements, scopeElements, context);

    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(context);
    result.addAll(ReplicableElementHandlerHelper.getInstance(context).getLinkingReplicableElements(context,
        (Collection) scopeHandler.getCollection(ITransitionConstants.INITIAL_SOURCE_SCOPE, context)));
    result.remove(null);

    return result;
  }

  public Collection<EObject> retrieveScopeElements(IScopeHandler handler, Collection<EObject> initialScopeElements,
      Collection<EObject> scopeElements, IContext context) {
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(context);

    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, context);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, context);
    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, initialScopeElements, context);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, initialScopeElements, context);

    handler.computeScope(initialScopeElements, context);
    Collection<EObject> result = new HashSet<EObject>(handler.getScope(context));
    result.remove(null);

    return result;
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

}
