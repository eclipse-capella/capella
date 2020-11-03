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

package org.polarsys.capella.core.transition.common.activities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.DefaultMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.PartialRootedModelScope;
import org.polarsys.capella.core.transition.common.merge.scope.ReferenceModelScope;
import org.polarsys.capella.core.transition.common.merge.scope.TargetModelScope;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class InitializeDiffMergeFromTransformationActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity"; //$NON-NLS-1$

  /**
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    IStatus status = initializeTraceabilitySourceHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTraceabilityTargetHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeMergeHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeReferenceScope(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTargetScope(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeReferenceScope(IContext context, ActivityParameters activityParams) {
    EObject sourceTop = (EObject) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
    context.put(ITransitionConstants.MERGE_REFERENCE_CONTAINER, sourceTop);

    List<EObject> rootSource = new ArrayList<>();
    rootSource.add((EObject) context.get(ITransitionConstants.MERGE_REFERENCE_CONTAINER));

    IEditableModelScope sourceScope = new ReferenceModelScope(rootSource, context);

    context.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);

    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context));

    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeTargetScope(IContext context, ActivityParameters activityParams) {

    EObject targetTop = (EObject) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
    context.put(ITransitionConstants.MERGE_TARGET_CONTAINER, targetTop);

    List<EObject> rootTarget = new ArrayList<>();
    rootTarget.add((EObject) context.get(ITransitionConstants.MERGE_TARGET_CONTAINER));

    TargetModelScope targetScope = new TargetModelScope(rootTarget, context);
    context.put(ITransitionConstants.MERGE_TARGET_SCOPE, targetScope);
    targetScope.build(getTargetFilter(context));

    return Status.OK_STATUS;
  }

  /**
   * Initialize the Merge handler and set it into context via ITransitionConstants.MERGE_DIFFERENCES_HANDLER
   */
  protected IStatus initializeMergeHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.MERGE_DIFFERENCES_HANDLER, activityParams);
    if (handler == null) {
      handler = new DefaultMergeHandler();
    }
    context.put(ITransitionConstants.MERGE_DIFFERENCES_HANDLER, handler);
    IStatus status = handler.init(context);
    if (handler instanceof IMergeHandler) {
      initializeCategoriesHandlers(context, (IMergeHandler) handler, activityParams);
    }

    return status;
  }

  protected IStatus initializeCategoriesHandlers(IContext context, IMergeHandler handler,
      ActivityParameters activityParams) {
    return Status.OK_STATUS;
  }

  /**
   * Initialize the traceability handler for source of diffMerge and set it into context via
   * TRACEABILITY_SOURCE_MERGE_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeTraceabilitySourceHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER,
        activityParams);
    if (handler == null) {
      handler = createDefaultTraceabilitySourceHandler(context);
    }
    context.put(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * Create default traceability handler for source of diffMerge
   * 
   * @return
   */
  protected IHandler createDefaultTraceabilitySourceHandler(IContext context) {
    return ITraceabilityHandler.DEFAULT;
  }

  /**
   * Initialize the traceability handler for target of diffMerge and set it into context via
   * TRACEABILITY_TARGET_MERGE_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeTraceabilityTargetHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER,
        activityParams);
    if (handler == null) {
      handler = createDefaultTraceabilityTargetHandler(context);
    }
    context.put(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * Create default traceability handler for target of diffMerge
   * 
   * @return
   */
  protected IHandler createDefaultTraceabilityTargetHandler(IContext context) {
    return ITraceabilityHandler.DEFAULT;
  }

  protected IModelScopeFilter getReferenceFilter(final IContext context) {
    // No specific rule here.

    return new IModelScopeFilter() {
      public boolean accepts(EObject element) {
        return true;
      }
    };
  }

  protected IModelScopeFilter getTargetFilter(final IContext context) {

    return new IModelScopeFilter() {
      public boolean accepts(EObject element) {
        return true;

      }
    };
  }
}
