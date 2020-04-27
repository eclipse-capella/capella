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

import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;

/**
 *
 */
public class DefaultLauncher extends TransposerLauncher {

  @Override
  protected String getWorkflow() {
    return ITransposerWorkflow.TRANSPOSER_WORKFLOW;
  }

  @Override
  protected String[] getWorkflowElements(String workflowId) {
    return new String[] { IDefaultWorkflow.WORKFLOW_STEP__INITIALIZATION, IDefaultWorkflow.WORKFLOW_STEP__TRANSPOSITION,
                         IDefaultWorkflow.WORKFLOW_STEP__DIFF_MERGE };
  }

  @Override
  protected String[] getFinalWorkflowElements(String workflowId) {
    return new String[] { IDefaultWorkflow.WORKFLOW_STEP__FINALIZATION };
  }

  @Override
  protected WorkflowActivityParameter getParameter(String workflowId, String workflowElement) {


    if (IDefaultWorkflow.WORKFLOW_STEP__INITIALIZATION.equals(workflowElement)) {
      return buildInitializationActivities();

    } else if (IDefaultWorkflow.WORKFLOW_STEP__TRANSPOSITION.equals(workflowElement)) {
      return buildTranspositionActivities();

    } else if (IDefaultWorkflow.WORKFLOW_STEP__DIFF_MERGE.equals(workflowElement)) {
      return buildDiffMergeActivities();

    } else if (IDefaultWorkflow.WORKFLOW_STEP__FINALIZATION.equals(workflowElement)) {
      return buildFinalizationActivities();
    }

    return new WorkflowActivityParameter();
  }

  /**
   * @return
   */
  protected WorkflowActivityParameter buildFinalizationActivities() {
    return new WorkflowActivityParameter();
  }

  /**
   * @return
   */
  protected WorkflowActivityParameter buildDiffMergeActivities() {
    return new WorkflowActivityParameter();
  }

  /**
   * @return
   */
  protected WorkflowActivityParameter buildTranspositionActivities() {
    return new WorkflowActivityParameter();
  }

  /**
   * @return
   */
  protected WorkflowActivityParameter buildInitializationActivities() {
    return new WorkflowActivityParameter();
  }
}
