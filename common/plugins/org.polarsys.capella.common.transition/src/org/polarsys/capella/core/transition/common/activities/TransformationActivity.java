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

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.scope.ScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.api.ITransposer;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.api.TransposerConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * An activity triggering Transposer, according to scope provided by ScopeHandler
 *
 */
public class TransformationActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.TransformationActivity";

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus _run(ActivityParameters activityParams) {
    IContext context = getContext(activityParams);
    IStatus status = Status.OK_STATUS;

    context.put(ITransitionConstants.TRACEABILITY_HANDLER, context.get(ITransitionConstants.TRACEABILITY_TRANSFORMATION_HANDLER));

    status = initializeTransposerSources(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    ITransposer transposer = (ITransposer) context.get(ITransitionConstants.TRANSPOSER_INSTANCE);

    if (transposer != null) {
      transposer.transpose((Collection<Object>) context.get(ITransitionConstants.TRANSITION_SELECTION), getTransposerConfiguration(), null,
          getProgressMonitor());
    }

    return Status.OK_STATUS;
  }

  /**
   * Initialize Transposer sources according to scope
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeTransposerSources(IContext context, ActivityParameters activityParams) {

    // Initialize scope of the transformation according to scope of transition
    Collection<EObject> analysesSource = (Collection<EObject>) context.get(TRANSPOSER_ANALYSIS_SOURCES);
    analysesSource.addAll(ScopeHandlerHelper.getInstance(context).getScope(context));

    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected IProgressMonitor getProgressMonitor() {
    return new NullProgressMonitor();
  }

  /**
   * Build the cadence worflow
   * @return the cadence configuration for Transposer
   */
  protected TransposerConfiguration getTransposerConfiguration() {
    TransposerConfiguration configuration = new TransposerConfiguration();
    return configuration;
  }

}
