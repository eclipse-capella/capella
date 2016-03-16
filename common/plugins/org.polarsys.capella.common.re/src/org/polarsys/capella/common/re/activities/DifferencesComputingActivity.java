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

package org.polarsys.capella.common.re.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.common.re.policies.match.ReMatchPolicy;
import org.polarsys.capella.common.re.policies.merge.ReMergePolicy;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesComputingActivity extends org.polarsys.capella.core.transition.common.activities.DifferencesComputingActivity implements
    ITransposerWorkflow {

  public static final String ID = DifferencesComputingActivity.class.getCanonicalName();

  /**
   * {@inheritDoc}
   */
  @Override
  protected IMergePolicy createMergePolicy(IContext context) {
    return new ReMergePolicy(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IMatchPolicy createMatchPolicy(IContext context) {
    return new ReMatchPolicy(context);
  }

  @Override
  @SuppressWarnings("unchecked")
  public IStatus _run(ActivityParameters activityParams) {
    return super._run(activityParams);
  }

}
