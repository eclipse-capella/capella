/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.launcher;

import org.polarsys.capella.core.transition.common.activities.DifferencesComputingActivity;
import org.polarsys.capella.core.transition.common.activities.DifferencesMergingActivity;
import org.polarsys.capella.core.transition.common.activities.FinalizeTransitionActivity;
import org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity;
import org.polarsys.capella.core.transition.common.activities.InitializeScopeActivity;
import org.polarsys.capella.core.transition.common.activities.InitializeTransformationActivity;
import org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity;
import org.polarsys.capella.core.transition.common.activities.PostDiffMergeActivity;
import org.polarsys.capella.core.transition.common.activities.PostTransformationActivity;
import org.polarsys.capella.core.transition.common.activities.TransformationActivity;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;

/**
 * 
 */
public class TransitionLauncher extends DefaultLauncher {

  @Override
  protected WorkflowActivityParameter buildInitializationActivities() {
    return buildPreAnalysisActivities();
  }

  @Override
  protected WorkflowActivityParameter buildTranspositionActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    if (getTransposer() != null) {

      // TransformationActivity
      parameter.addActivity(getActivity(TransformationActivity.ID));

    }

    return parameter;
  }

  @Deprecated
  protected WorkflowActivityParameter buildPostExecutionActivities() {
    return buildDiffMergeActivities();
  }

  /**
   * Activities to load in the workflow element of cadence "PRE ANALYSIS"
   * 
   * @return associated workflow element
   */
  @Deprecated
  protected WorkflowActivityParameter buildPreAnalysisActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    if (getTransposer() != null) {
      // InitializeTransitionActivity
      parameter.addActivity(getActivity(InitializeTransitionActivity.ID));
      GenericParameter<IRulesHandler> param = new GenericParameter<IRulesHandler>(
          InitializeTransitionActivity.PARAMETER_RULE_HANDLER, getTransposer().getRulesHandler(),
          "Transposer Rule handler"); //$NON-NLS-1$
      parameter.addParameter(getActivity(InitializeTransitionActivity.ID), param);

      // InitializeTransformationActivity
      parameter.addActivity(getActivity(InitializeTransformationActivity.ID));

      // InitializeScopeActivity
      parameter.addActivity(getActivity(InitializeScopeActivity.ID));

    }

    return parameter;
  }

  /**
   * Activities to load in the workflow element of cadence "POST EXECUTION"
   * 
   * @return associated workflow element
   */
  protected WorkflowActivityParameter buildFinalizationActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    if (getTransposer() != null) {

      // PostDiffMergeActivity
      parameter.addActivity(getActivity(PostDiffMergeActivity.ID));

      // PostDiffMergeActivity
      parameter.addActivity(getActivity(FinalizeTransitionActivity.ID));

    }

    return parameter;
  }
  
  protected WorkflowActivityParameter buildDiffMergeActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    if (getTransposer() != null) {

      // PostTransformationActivity
      parameter.addActivity(getActivity(PostTransformationActivity.ID));

      // InitializeDiffMergeFromTransformationActivity
      parameter.addActivity(getActivity(InitializeDiffMergeFromTransformationActivity.ID));

      // DifferencesComputingActivity
      parameter.addActivity(getActivity(DifferencesComputingActivity.ID));

      // DifferencesFilteringActivity
      parameter.addActivity(getActivity(DifferencesMergingActivity.ID));

    }

    return parameter;
  }

}
