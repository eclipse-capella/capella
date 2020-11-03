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
package org.polarsys.capella.common.re.rpl2re.activities;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.activities.InitializeDiffMergeActivity;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.merge.scope.ReSourceScope;
import org.polarsys.capella.common.re.merge.scope.ReTargetScope;
import org.polarsys.capella.common.re.rpl2re.merge.AvoidSuffixesToRecCategoryFilter;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.PartialRootedModelScope;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class InitializeDiffMergeUpdateReActivity extends InitializeDiffMergeActivity {

  public static final String ID = InitializeDiffMergeUpdateReActivity.class.getCanonicalName();

  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    CatalogElement etarget = ReplicableElementHandlerHelper.getInstance(context).getInitialTarget(context);
    CatalogElement esource = ReplicableElementHandlerHelper.getInstance(context).getInitialSource(context);

    ReplicableElementHandlerHelper.getInstance(context).setTarget(context, etarget);
    ReplicableElementHandlerHelper.getInstance(context).setSource(context, esource);

    return super._run(activityParams);
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  @Override
  protected IStatus initializeReferenceScope(IContext context, ActivityParameters activityParams) {

    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    // Scope is computed, we put it into Merge Scope
    Collection<EObject> scopeElements = OptionsHandlerHelper.getInstance(context).getCollectionValue(context,
        (String) context.get(ITransitionConstants.OPTIONS_SCOPE), IReConstants.PROPERTY__MERGE_SOURCE_SCOPE,
        (Collection) Collections.emptyList());

    // Ensure unwanted elements not in scope!
    scopeElements.remove(source);
    scopeElements.remove(target);

    IEditableModelScope sourceScope = new ReSourceScope(source, scopeElements, context);
    context.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);
    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context));

    if (source == null) {
      // Only if create a new RE or update current replica (not definition), we don't create new elements.
      // When we will want to promote it into library, it will be another story
      Object location = OptionsHandlerHelper.getInstance(context).getValue(context,
          (String) context.get(ITransitionConstants.OPTIONS_SCOPE), IReConstants.PROPERTY__LOCATION_TARGET, null);

      if (ReplicableElementHandlerHelper.getInstance(context).isDefaultLocation((EObject) location, context)) {
        ContextScopeHandlerHelper.getInstance(context).addAll(IReConstants.UNMERGEABLE_ELEMENTS, scopeElements,
            context);
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  @Override
  protected IStatus initializeTargetScope(IContext context, ActivityParameters activityParams) {

    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    // Target is the ReplicableElement
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    // Scope is computed, we put it into Merge Scope
    Collection<EObject> scopeElements = OptionsHandlerHelper.getInstance(context).getCollectionValue(context,
        (String) context.get(ITransitionConstants.OPTIONS_SCOPE), IReConstants.PROPERTY__MERGE_TARGET_SCOPE,
        (Collection) Collections.emptyList());

    // Ensure unwanted elements not in scope!
    scopeElements.remove(source);
    scopeElements.remove(target);

    IEditableModelScope targetScope = new ReTargetScope(target, scopeElements, context);
    context.put(ITransitionConstants.MERGE_TARGET_SCOPE, targetScope);
    ((PartialRootedModelScope) targetScope).build(getTargetFilter(context));

    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeCategoriesHandlers(IContext context, IMergeHandler handler,
      ActivityParameters activityParams) {

    super.initializeCategoriesHandlers(context, handler, activityParams);

    handler.addCategory(new AvoidSuffixesToRecCategoryFilter(context), context);

    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeTraceabilitySourceHandler(IContext context, ActivityParameters activityParams) {
    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeTraceabilityTargetHandler(IContext context, ActivityParameters activityParams) {
    return Status.OK_STATUS;
  }

  @Override
  protected IModelScopeFilter getReferenceFilter(final IContext context) {
    return new IModelScopeFilter() {
      public boolean accepts(EObject element) {
        return ((ReSourceScope) (context.get(ITransitionConstants.MERGE_REFERENCE_SCOPE))).getInitialElements()
            .contains(element);
      }
    };
  }

  @Override
  protected IModelScopeFilter getTargetFilter(final IContext context) {
    return new IModelScopeFilter() {
      public boolean accepts(EObject element) {
        return ((ReTargetScope) (context.get(ITransitionConstants.MERGE_TARGET_SCOPE))).getInitialElements()
            .contains(element);
      }
    };
  }
}
