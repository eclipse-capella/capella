/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.MergeHandlerHelper;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesMergingActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.DifferencesMergingActivity"; //$NON-NLS-1$

  @Override
  @SuppressWarnings("unchecked")
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    Collection<IDifference> fromReference = (Collection<IDifference>) context
        .get(ITransitionConstants.MERGE_REFERENCE_DIFFERENCES);
    Collection<IDifference> fromTarget = (Collection<IDifference>) context
        .get(ITransitionConstants.MERGE_TARGET_DIFFERENCES);

    IStatus result = computeDifferences(fromReference, fromTarget, context);

    boolean shouldSave = false;
    context.put(ITransitionConstants.SAVE_REQUIRED, shouldSave);

    if (result.isOK()) {
      return Status.OK_STATUS;
    }

    return Status.CANCEL_STATUS;

  }

  protected IStatus computeDifferences(Collection<IDifference> fromReference, Collection<IDifference> fromTarget,
      IContext context) {
    return MergeHandlerHelper.getInstance(context).processDifferences(context, fromReference, fromTarget);
  }

}
