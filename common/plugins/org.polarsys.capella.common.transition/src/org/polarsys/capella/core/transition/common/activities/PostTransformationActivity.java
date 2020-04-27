/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyChangeEvent;
import org.polarsys.capella.core.transition.common.handlers.notify.NotifyHandlerHelper;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class PostTransformationActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.PostTransformationActivity"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus _run(ActivityParameters activityParams) {

    IContext context = getContext(activityParams);

    //Notify listeners that transformation ends
    NotifyHandlerHelper.getInstance(context).notify(ITransitionConstants.NOTIFY__END_TRANSFORMATION, INotifyChangeEvent.DEFAULT, context);

    return Status.OK_STATUS;
  }

}
