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
package org.polarsys.capella.core.transition.system.topdown.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.EmptyPackageCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.OutsideArchitectureCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.RealizationLinkCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.TargetDifferencesCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.UpdatedAttributeCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.UpdatedReferenceCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.policies.diff.TopDownDiffPolicy;
import org.polarsys.capella.core.transition.system.topdown.policies.match.TopDownMatchPolicy;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesComputingActivity
    extends org.polarsys.capella.core.transition.system.activities.DifferencesComputingActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.DifferencesComputingActivity"; //$NON-NLS-1$

  @Override
  protected IStatus initializeCategoriesHandlers(IContext context, IMergeHandler handler,
      ActivityParameters activityParams) {
    super.initializeCategoriesHandlers(context, handler, activityParams);

    handler.addCategory(new TargetDifferencesCategoryFilter(context), context);
    handler.addCategory(new OutsideArchitectureCategoryFilter(context), context);
    handler.addCategory(new RealizationLinkCategoryFilter(context), context);
    handler.addCategory(new EmptyPackageCategoryFilter(context), context);
    handler.addCategory(new UpdatedAttributeCategoryFilter(context), context);
    handler.addCategory(new UpdatedReferenceCategoryFilter(context), context);

    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  @Override
  protected IMatchPolicy createMatchPolicy(IContext context) {
    IMatchPolicy policy = new TopDownMatchPolicy(context);
    return policy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IDiffPolicy createDiffPolicy(IContext context) {
    IDiffPolicy policy = new TopDownDiffPolicy(context);
    return policy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean displayLog(IContext context) {
    return OptionsHandlerHelper.getInstance(context).getBooleanValue(context, ITopDownConstants.OPTIONS_SCOPE,
        ITopDownConstants.OPTIONS_LOG, ITopDownConstants.OPTIONS_LOG__DEFAULT.booleanValue());
  }

}
