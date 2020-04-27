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

import org.polarsys.capella.common.re.activities.AttachmentActivity;
import org.polarsys.capella.common.re.activities.DifferencesComputingActivity;
import org.polarsys.capella.common.re.activities.FinalizeTransitionActivity;
import org.polarsys.capella.common.re.activities.InitializeReMgtActivity;
import org.polarsys.capella.common.re.activities.MakeTraceabilityActivity;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.rpl2re.activities.InitializeDiffMergeUpdateReActivity;
import org.polarsys.capella.common.re.rpl2re.activities.InitializeTransitionActivity;
import org.polarsys.capella.core.transition.common.activities.DifferencesMergingActivity;
import org.polarsys.capella.core.transition.common.activities.PostDiffMergeActivity;
import org.polarsys.capella.core.transition.common.launcher.ILoopActivityDispatcher;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;

/**
 */
public class UpdateCurLauncher extends ReLauncher {

  @Override
  protected String getMapping() {
    return "org.polarsys.capella.common.re.updateCur";
  }

  @Override
  protected String getScope() {
    return "org.polarsys.capella.common.re.updateCur";
  }

  @Override
  protected String getKind() {
    return IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA;
  }

  @Override
  public ILoopActivityDispatcher createDispatcher() {
    return new LoopActivityDispatcher();
  }

  /**
   * Activities to be loaded into the workflow element of cadence "PRE ANALYSIS".
   * 
   * @return the workflow element associated
   */
  @Override
  protected WorkflowActivityParameter buildInitializationActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    // InitializeTransitionActivity
    parameter.addActivity(getActivity(InitializeTransitionActivity.ID));

    parameter.addActivity(getActivity(InitializeReMgtActivity.ID));

    return parameter;
  }

  /**
   * Activities to be loaded into the workflow element of cadence "POST EXECUTION".
   * 
   * @return the workflow element associated
   */
  @Override
  protected WorkflowActivityParameter buildDiffMergeActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    // InitializeDiffMergeFromTransformationActivity
    parameter.addActivity(getActivity(InitializeDiffMergeUpdateReActivity.ID));

    // DifferencesComputingActivity
    parameter.addActivity(getActivity(DifferencesComputingActivity.ID));

    // DifferencesMergingActivity
    parameter.addActivity(getActivity(DifferencesMergingActivity.ID));

    // MakeTraceabilityActivity
    parameter.addActivity(getActivity(MakeTraceabilityActivity.ID));

    // MakeTraceabilityActivity
    parameter.addActivity(getActivity(AttachmentActivity.ID));

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
}
