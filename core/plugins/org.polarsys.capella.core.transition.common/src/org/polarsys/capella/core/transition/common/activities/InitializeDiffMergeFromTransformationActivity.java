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
package org.polarsys.capella.core.transition.common.activities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
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
  public IStatus _run(ActivityParameters activityParams_p) {
    IContext context = (IContext) activityParams_p.getParameter(TRANSPOSER_CONTEXT).getValue();

    IStatus status = Status.OK_STATUS;

    status = initializeTraceabilitySourceHandler(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTraceabilityTargetHandler(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeReferenceScope(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTargetScope(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeReferenceScope(IContext context_p, ActivityParameters activityParams_p) {
    EObject sourceTop = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
    context_p.put(ITransitionConstants.MERGE_REFERENCE_CONTAINER, sourceTop);

    List<EObject> rootSource = new ArrayList<EObject>();
    rootSource.add((EObject) context_p.get(ITransitionConstants.MERGE_REFERENCE_CONTAINER));

    IEditableModelScope sourceScope = new ReferenceModelScope(rootSource, context_p);

    context_p.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);

    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context_p));

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTargetScope(IContext context_p, ActivityParameters activityParams_p) {

    EObject targetTop = (EObject) context_p.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
    context_p.put(ITransitionConstants.MERGE_TARGET_CONTAINER, targetTop);

    List<EObject> rootTarget = new ArrayList<EObject>();
    rootTarget.add((EObject) context_p.get(ITransitionConstants.MERGE_TARGET_CONTAINER));

    IEditableModelScope targetScope = new TargetModelScope(rootTarget, context_p);
    context_p.put(ITransitionConstants.MERGE_TARGET_SCOPE, targetScope);

    ((PartialRootedModelScope) targetScope).build(getTargetFilter(context_p));

    return Status.OK_STATUS;
  }

  /**
   * Initialize the traceability handler for source of diffMerge and set it into context via TRACEABILITY_SOURCE_MERGE_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTraceabilitySourceHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultTraceabilitySourceHandler(context_p);
    }
    context_p.put(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default traceability handler for source of diffMerge
   * @return
   */
  protected IHandler createDefaultTraceabilitySourceHandler(IContext context_p) {
    return ITraceabilityHandler.DEFAULT;
  }

  /**
   * Initialize the traceability handler for target of diffMerge and set it into context via TRACEABILITY_TARGET_MERGE_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTraceabilityTargetHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultTraceabilityTargetHandler(context_p);
    }
    context_p.put(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default traceability handler for target of diffMerge
   * @return
   */
  protected IHandler createDefaultTraceabilityTargetHandler(IContext context_p) {
    return ITraceabilityHandler.DEFAULT;
  }

  protected IModelScopeFilter getReferenceFilter(final IContext context_p) {
    // No specific rule here.

    return new IModelScopeFilter() {
      public boolean accepts(EObject element_p) {
        return true;
      }
    };
  }

  protected IModelScopeFilter getTargetFilter(final IContext context_p) {

    return new IModelScopeFilter() {
      public boolean accepts(EObject element_p) {
        return true;

      }
    };
  }
}
