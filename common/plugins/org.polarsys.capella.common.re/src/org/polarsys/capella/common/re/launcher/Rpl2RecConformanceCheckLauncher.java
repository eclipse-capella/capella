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
package org.polarsys.capella.common.re.launcher;

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.polarsys.capella.common.re.activities.DifferencesComputingActivity;
import org.polarsys.capella.common.re.activities.FinalizeTransitionActivity;
import org.polarsys.capella.common.re.activities.InitializeReMgtActivity;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.re2rpl.activities.InitializeDiffMergeUpdateReplicaActivity;
import org.polarsys.capella.common.re.re2rpl.activities.InitializeTransitionActivity;
import org.polarsys.capella.core.transition.common.activities.DifferencesMergingActivity;
import org.polarsys.capella.core.transition.common.activities.PostDiffMergeActivity;
import org.polarsys.capella.core.transition.common.handlers.merge.DefaultMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;

public class Rpl2RecConformanceCheckLauncher extends ReLauncher {

  private boolean isConform;

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

    parameter.addActivity(getActivity(DifferencesMergingActivity.ID));

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

  protected IMergeHandler createMergeHandler(){
    return new DefaultMergeHandler(true){

      @Override
      protected void mergeDifferences(IComparison comparison, Collection<IDifference> differences) {
        isConform = differences.isEmpty();
      }
    };
  }

  public boolean isConform(){
    return isConform;
  }
}
