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
package org.polarsys.capella.core.transition.system.topdown.ui.launcher;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeUIDifferencesHandler;
import org.polarsys.capella.core.transition.system.topdown.activities.DifferencesComputingActivity;
import org.polarsys.capella.core.transition.system.topdown.launcher.HeadlessIntramodelLauncher;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;

public class IntramodelLauncher extends HeadlessIntramodelLauncher {

  private final String MAPPING = "org.polarsys.capella.core.transition.system.topdown"; //$NON-NLS-1$

  @Override
  protected String getMapping() {
    return MAPPING;
  }

  /**
   * Activities to load in the workflow element of cadence "PRE ANALYSIS"
   * 
   * @return associated workflow element
   */
  @Override
  protected WorkflowActivityParameter buildPreAnalysisActivities() {
    WorkflowActivityParameter parameter = super.buildPreAnalysisActivities();

    return parameter;
  }

  /**
   * Activities to load in the workflow element of cadence "POST EXECUTION"
   * 
   * @return associated workflow element
   */
  @Override
  protected WorkflowActivityParameter buildPostExecutionActivities() {
    WorkflowActivityParameter parameter = super.buildPostExecutionActivities();

    // Add UI Filtering handler
    GenericParameter<IHandler> param = new GenericParameter<IHandler>(ITransitionConstants.MERGE_DIFFERENCES_HANDLER,
        new MergeUIDifferencesHandler(), "Transposer Options handler"); //$NON-NLS-1$
    parameter.addParameter(DifferencesComputingActivity.ID, param);

    return parameter;
  }

}
