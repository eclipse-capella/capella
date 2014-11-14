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
package org.polarsys.capella.common.re.re2rpl.activities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.ScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.PartialRootedModelScope;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.traceability.ReConfiguration;
import org.polarsys.capella.common.re.merge.scope.ReSourceScope;
import org.polarsys.capella.common.re.merge.scope.ReTargetScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class InitializeDiffMergeUpdateReplicaActivity extends InitializeDiffMergeFromTransformationActivity {

  public static final String ID = InitializeDiffMergeUpdateReplicaActivity.class.getCanonicalName();

  protected CatalogElement source;
  protected CatalogElement target;

  @Override
  public IStatus _run(ActivityParameters activityParams_p) {

    IContext context = (IContext) activityParams_p.getParameter(TRANSPOSER_CONTEXT).getValue();

    //Compute scope and additional elements
    IContextScopeHandler scopeHandler = ContextScopeHandlerHelper.getInstance(context);
    Collection<EObject> scopeElements = new HashSet<EObject>();
    scopeHandler.clear(ITransitionConstants.INITIAL_SOURCE_SCOPE, context);
    scopeHandler.clear(ITransitionConstants.SOURCE_SCOPE, context);
    scopeHandler.addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, scopeElements, context);
    scopeHandler.addAll(ITransitionConstants.SOURCE_SCOPE, scopeElements, context);

    IStatus status = ScopeHandlerHelper.getInstance(context).computeScope(scopeElements, context);
    if (!checkStatus(status)) {
      return status;
    }

    Collection<EObject> sources =
        OptionsHandlerHelper.getInstance(context).getCollectionValue(context, (String) context.get(ITransitionConstants.OPTIONS_SCOPE),
            IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE, (Collection) Collections.emptyList());

    if (sources.iterator().hasNext()) {
      source = (CatalogElement) sources.iterator().next();
      ReplicableElementHandlerHelper.getInstance(context).setReplicableElement(context, source);
      ReplicableElementHandlerHelper.getInstance(context).setSource(context, source);
    }

    Collection<EObject> targets =
        OptionsHandlerHelper.getInstance(context).getCollectionValue(context, (String) context.get(ITransitionConstants.OPTIONS_SCOPE),
            IReConstants.PROPERTY__REPLICABLE_ELEMENT__TARGET, (Collection) Collections.emptyList());

    if (targets.iterator().hasNext()) {
      target = (CatalogElement) targets.iterator().next();
      ReplicableElementHandlerHelper.getInstance(context).setReplica(context, target);
      ReplicableElementHandlerHelper.getInstance(context).setTarget(context, target);
    }

    IStatus parent = super._run(activityParams_p);
    return parent;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  @Override
  protected IStatus initializeReferenceScope(IContext context_p, ActivityParameters activityParams_p) {

    //Mergeable scope is only elements directly linked to source replicable element. Unmodifiable elements, shared elements are computed
    //If replicable element is into a writeable area, we could consider to let computed scope to be merge-scope, but some elements can 
    //be linked to source replicable element, adding links to them.

    Collection<EObject> scopeElements = new HashSet<EObject>();
    //Scope is computed, we put it into Merge Scope
    scopeElements =
        OptionsHandlerHelper.getInstance(context_p).getCollectionValue(context_p, (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE),
            IReConstants.PROPERTY__MERGE_SOURCE_SCOPE, (Collection) Collections.emptyList());

    //Ensure unwanted elements not in scope!
    scopeElements.remove(source);
    scopeElements.remove(target);

    //Scope is computed, we put it into Merge Scope
    ITraceabilityHandler handler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    IFeaturedModelScope sourceScope = new ReSourceScope(source, handler, scopeElements, context_p);
    context_p.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);
    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context_p));

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  @Override
  protected IStatus initializeTargetScope(IContext context_p, ActivityParameters activityParams_p) {

    //Target is the ReplicableElement
    Collection<EObject> scopeElements = new HashSet<EObject>();

    //Scope is computed, we put it into Merge Scope
    scopeElements =
        OptionsHandlerHelper.getInstance(context_p).getCollectionValue(context_p, (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE),
            IReConstants.PROPERTY__MERGE_TARGET_SCOPE, (Collection) Collections.emptyList());

    //Ensure unwanted elements not in scope!
    scopeElements.remove(source);
    scopeElements.remove(target);

    ITraceabilityHandler handler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);
    IFeaturedModelScope targetScope = new ReTargetScope(target, handler, scopeElements, context_p);
    context_p.put(ITransitionConstants.MERGE_TARGET_SCOPE, targetScope);
    ((PartialRootedModelScope) targetScope).build(getTargetFilter(context_p));

    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeTraceabilitySourceHandler(IContext context_p, ActivityParameters activityParams_p) {
    return super.initializeTraceabilitySourceHandler(context_p, activityParams_p);
  }

  @Override
  protected IStatus initializeTraceabilityTargetHandler(IContext context_p, ActivityParameters activityParams_p) {
    return super.initializeTraceabilityTargetHandler(context_p, activityParams_p);
  }

  /**
   * Create default traceability handler for source of diffMerge
   */
  @Override
  protected IHandler createDefaultTraceabilitySourceHandler() {
    ITraceabilityConfiguration configuration = new ReConfiguration(source);
    return new CompoundTraceabilityHandler(configuration);
  }

  /**
   * Create default traceability handler for target of diffMerge
   */
  @Override
  protected IHandler createDefaultTraceabilityTargetHandler() {
    ITraceabilityConfiguration configuration = new ReConfiguration(source, target);
    return new CompoundTraceabilityHandler(configuration);
  }

  @Override
  protected IModelScopeFilter getReferenceFilter(final IContext context_p) {
    return new IModelScopeFilter() {
      public boolean accepts(EObject element_p) {
        return ((ReSourceScope) (context_p.get(ITransitionConstants.MERGE_REFERENCE_SCOPE))).getInitialElements().contains(element_p);
      }
    };
  }

  @Override
  protected IModelScopeFilter getTargetFilter(final IContext context_p) {
    return new IModelScopeFilter() {
      public boolean accepts(EObject element_p) {
        return ((ReSourceScope) (context_p.get(ITransitionConstants.MERGE_TARGET_SCOPE))).getInitialElements().contains(element_p);
      }
    };
  }
}
