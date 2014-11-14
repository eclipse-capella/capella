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
package org.polarsys.capella.common.re.rpl2re.activities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.traceability.ReConfiguration;
import org.polarsys.capella.common.re.merge.scope.ReSourceScope;
import org.polarsys.capella.common.re.merge.scope.ReTargetScope;
import org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.PartialRootedModelScope;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class InitializeDiffMergeUpdateReActivity extends InitializeDiffMergeFromTransformationActivity {

  public static final String ID = InitializeDiffMergeUpdateReActivity.class.getCanonicalName();

  @Override
  public IStatus _run(ActivityParameters activityParams_p) {
    IContext context = (IContext) activityParams_p.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    CatalogElement etarget = ReplicableElementHandlerHelper.getInstance(context).getInitialTarget(context);
    CatalogElement esource = ReplicableElementHandlerHelper.getInstance(context).getInitialSource(context);

    ReplicableElementHandlerHelper.getInstance(context).setTarget(context, etarget);
    ReplicableElementHandlerHelper.getInstance(context).setSource(context, esource);

    return super._run(activityParams_p);
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  @Override
  protected IStatus initializeReferenceScope(IContext context_p, ActivityParameters activityParams_p) {

    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

    Collection<EObject> scopeElements = new HashSet<EObject>();
    //Scope is computed, we put it into Merge Scope
    scopeElements =
        OptionsHandlerHelper.getInstance(context_p).getCollectionValue(context_p, (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE),
            IReConstants.PROPERTY__MERGE_SOURCE_SCOPE, (Collection) Collections.emptyList());

    //Ensure unwanted elements not in scope!
    scopeElements.remove(source);
    scopeElements.remove(target);

    ITraceabilityHandler handler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    IEditableModelScope sourceScope = new ReSourceScope(source, handler, scopeElements, context_p);
    context_p.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);
    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context_p));

    if (source == null) {
      //Only if create a new RE or update current replica (not definition), we don't create new elements.
      //When we will want to promote it into library, it will be another story

      Collection selection = (Collection) context_p.get(ITransitionConstants.TRANSITION_SOURCES);

      Object location =
          OptionsHandlerHelper.getInstance(context_p).getValue(context_p, (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE),
              IReConstants.PROPERTY__LOCATION_TARGET, null);

      if (ReplicableElementHandlerHelper.getInstance(context_p).isDefaultLocation((EObject) location, context_p)) {
        ContextScopeHandlerHelper.getInstance(context_p).addAll(IReConstants.UNMERGEABLE_ELEMENTS, scopeElements, context_p);
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  @Override
  protected IStatus initializeTargetScope(IContext context_p, ActivityParameters activityParams_p) {

    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

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
    IEditableModelScope targetScope = new ReTargetScope(target, handler, scopeElements, context_p);
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
  protected IHandler createDefaultTraceabilitySourceHandler(IContext context_p) {
    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

    ITraceabilityConfiguration configuration = new ReConfiguration(source);
    return new CompoundTraceabilityHandler(configuration);
  }

  /**
   * Create default traceability handler for target of diffMerge
   */
  @Override
  protected IHandler createDefaultTraceabilityTargetHandler(IContext context_p) {
    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

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
