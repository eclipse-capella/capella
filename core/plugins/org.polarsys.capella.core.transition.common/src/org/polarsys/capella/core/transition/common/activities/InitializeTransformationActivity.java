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

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyChangeEvent;
import org.polarsys.capella.core.transition.common.handlers.notify.NotifyHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public abstract class InitializeTransformationActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.InitializeTransformationActivity"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public String getActivityIdentifier() {
    return ITransitionSteps.INITIALIZE_TRANSFORMATION;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams_p) {
    IContext context = (IContext) activityParams_p.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    initializeTransformation(context, activityParams_p);

    // Workarounded log. It not truly exact but make a log before the transition :)
    LogHelper.getInstance().debug(NLS.bind("Start activity ''{0}''", "Transformation"), Messages.Activity_Transition); //$NON-NLS-1$

    //Notify listeners that transformation begins
    NotifyHandlerHelper.getInstance(context).notify(ITransitionConstants.NOTIFY__BEGIN_TRANSFORMATION, INotifyChangeEvent.DEFAULT, context);

    return Status.OK_STATUS;
  }

  /**
   * Initialize the transformation traceability handler and set it into context via TRANSFORMATION_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTraceabilityTransformationHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultTraceabilityTransformationHandler();
    }
    context_p.put(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default transformation traceability handler for common transition
   * @return
   */
  protected IHandler createDefaultTraceabilityTransformationHandler() {
    return ITraceabilityHandler.DEFAULT;
  }

  /**
   * Should initialize TRANSFORMATION_SOURCE, TRANSFORMATION_SOURCE_CONTAINER, TRANSFORMATION_TARGET_CONTAINER, TRANSFORMATION_SCOPE
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTransformation(IContext context_p, ActivityParameters activityParams_p) {
    IStatus status = Status.OK_STATUS;

    status = initializeTraceabilityTransformationHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeSource(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status =
        checkParameters(context_p, new String[] { ITransitionConstants.SCOPE_SOURCES, ITransitionConstants.TRANSFORMATION_SOURCES,
                                                 ITransitionConstants.TRANSFORMATION_SOURCE_ROOT });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTarget(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.TRANSFORMATION_TARGET_ROOT });
    if (!checkStatus(status)) {
      return status;
    }

    context_p.put(ITransitionConstants.TRACEABILITY_HANDLER, context_p.get(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER));

    status = checkParameters(context_p, new String[] { ITransitionConstants.TRACEABILITY_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    return Status.OK_STATUS;
  }

  @Override
  protected IStatus verificationRun(IContext context_p, ActivityParameters activityParams_p) {

    //!!! TRACEABILITY_HANDLER must be TRACEABILITY_TRANSFORMATION_HANDLER before triggering transformation !!!
    context_p.put(ITransitionConstants.TRACEABILITY_HANDLER, context_p.get(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER));

    return super.verificationRun(context_p, activityParams_p);

  }

  /**
   * In a common transition, 
   * TRANSFORMATION_TARGET_ROOT = createTargetTransformationContainer(TRANSITION_TARGET_RESOURCE)
   */
  protected IStatus initializeTarget(IContext context_p, ActivityParameters activityParams_p) {
    // Retrieve the target of the transformation, also known as the source of the merge
    EObject target = createTargetTransformationContainer((Resource) context_p.get(ITransitionConstants.TRANSITION_TARGET_RESOURCE), context_p);
    context_p.put(ITransitionConstants.TRANSFORMATION_TARGET_ROOT, target);
    return Status.OK_STATUS;
  }

  protected abstract EObject createTargetTransformationContainer(Resource source_p, IContext context_p);

  /**
   * SCOPE_SOURCES = TRANSITION_SOURCES
   * TRANSFORMATION_SOURCES = TRANSITION_SOURCES
   * TRANSFORMATION_SOURCE_ROOT = TRANSITION_SOURCE_ROOT
   * 
   * INITIAL_SOURCE_SCOPE = TRANSITION_SOURCES
   * SOURCE_SCOPE = INITIAL_SOURCE_SCOPE
   */
  protected IStatus initializeSource(IContext context_p, ActivityParameters activityParams_p) {
    // Retrieve the source of the transformation
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      context_p.put(ITransitionConstants.SCOPE_SOURCES, context_p.get(ITransitionConstants.TRANSITION_SOURCES));
      context_p.put(ITransitionConstants.TRANSFORMATION_SOURCES, context_p.get(ITransitionConstants.TRANSITION_SOURCES));
      context_p.put(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT, context_p.get(ITransitionConstants.TRANSITION_SOURCE_ROOT));
    }

    //Add on source scope
    ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.INITIAL_SOURCE_SCOPE, selection, context_p);
    ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, selection, context_p);

    return Status.OK_STATUS;
  }

}
