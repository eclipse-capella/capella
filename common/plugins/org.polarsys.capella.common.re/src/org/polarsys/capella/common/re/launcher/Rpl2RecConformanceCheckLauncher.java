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
package org.polarsys.capella.common.re.launcher;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.polarsys.capella.common.re.activities.DifferencesComputingActivity;
import org.polarsys.capella.common.re.activities.FinalizeTransitionActivity;
import org.polarsys.capella.common.re.activities.InitializeReMgtActivity;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.re2rpl.activities.DifferencesFilteringOnlyActivity;
import org.polarsys.capella.common.re.re2rpl.activities.InitializeDiffMergeUpdateReplicaActivity;
import org.polarsys.capella.common.re.re2rpl.activities.InitializeTransitionActivity;
import org.polarsys.capella.core.transition.common.activities.PostDiffMergeActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.transposer.ExtendedTransposer;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class Rpl2RecConformanceCheckLauncher extends ReLauncher {

  private IComparison comparison;

  @Override
  protected String getMapping() {
    return "org.polarsys.capella.common.re.rpl2rec.conformance.check";
  }

  @Override
  protected String getScope() {
    return "org.polarsys.capella.common.re.rpl2rec.conformance.check";
  }

  @Override
  protected String getKind() {
    // Use the same kind for update replica in order to be able to match objects in ReMatchPolicy
    // (see org.polarsys.capella.common.re.policies.match.ReMatchPolicy.getMatchID(EObject, IModelScope))
    return IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE;
  }

  @Override
  protected WorkflowActivityParameter buildInitializationActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    // InitializeTransitionActivity
    parameter.addActivity(getActivity(InitializeTransitionActivity.ID));

    parameter.addActivity(getActivity(InitializeReMgtActivity.ID));

    return parameter;
  }

  @Override
  protected WorkflowActivityParameter buildDiffMergeActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    // InitializeDiffMergeUpdateReplicaActivity
    parameter.addActivity(getActivity(InitializeDiffMergeUpdateReplicaActivity.ID));

    // DifferencesComputingActivity
    parameter.addActivity(getActivity(DifferencesComputingActivity.ID));

    // DifferencesFilteringActivity
    parameter.addActivity(getActivity(DifferencesFilteringOnlyActivity.ID));

    return parameter;
  }

  @Override
  protected WorkflowActivityParameter buildFinalizationActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    // FinalizeTransitionActivity
    parameter.addActivity(getActivity(FinalizeTransitionActivity.ID));

    // PostDiffMergeActivity
    parameter.addActivity(getActivity(PostDiffMergeActivity.ID));

    return parameter;
  }

  /**
   * 
   * @return the comparison result which is computed when running the {@link DifferencesComputingActivity}. The
   *         comparison is <b>only</b> available after calling
   *         {@link #launch(java.util.Collection, String, String, org.eclipse.core.runtime.IProgressMonitor)}
   */
  public IComparison getComparison() {
    return comparison;
  }

  @Override
  protected void dispose() {
    // Get the comparison result before disposing the context
    ExtendedTransposer transposer = getTransposer();
    if (transposer != null) {
      IContext context = transposer.getContext();
      if (context != null) {
        comparison = (IComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
      }
    }
    super.dispose();
  }
}
