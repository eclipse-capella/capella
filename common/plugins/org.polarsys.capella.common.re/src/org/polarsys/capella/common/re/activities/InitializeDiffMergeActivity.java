/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.re.handlers.merge.AvoidReAttributeCategoryFilter;
import org.polarsys.capella.common.re.handlers.merge.AvoidUnsynchronizedFeatureCategoryFilter;
import org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class InitializeDiffMergeActivity extends InitializeDiffMergeFromTransformationActivity
    implements ITransposerWorkflow {

  public static final String ID = InitializeDiffMergeActivity.class.getCanonicalName();

  @Override
  protected IStatus initializeCategoriesHandlers(IContext context, IMergeHandler handler,
      ActivityParameters activityParams) {
    super.initializeCategoriesHandlers(context, handler, activityParams);

    handler.addCategory(new AvoidReAttributeCategoryFilter(context), context);

    handler.addCategory(new AvoidUnsynchronizedFeatureCategoryFilter(context), context);

    return Status.OK_STATUS;
  }

}
