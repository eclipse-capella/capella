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

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.scope.ScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class InitializeScopeActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.InitializeScopeActivity"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus _run(ActivityParameters activityParams_p) {
    IContext context = (IContext) activityParams_p.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();
    IStatus status = Status.OK_STATUS;

    //Compute scope
    status = initializeTransformationScope(context, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    return Status.OK_STATUS;
  }

  /**
   * Should compute scope
   * ScopeHandlerHelper.getInstance(context_p).getScope should not be null
   * @param context_p
   */
  protected IStatus initializeTransformationScope(IContext context_p, ActivityParameters activityParams_p) {
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.SCOPE_SOURCES);
    if (selection.size() > 0) {
      ScopeHandlerHelper.getInstance(context_p).computeScope(selection, context_p);
    }
    return Status.OK_STATUS;
  }
}
