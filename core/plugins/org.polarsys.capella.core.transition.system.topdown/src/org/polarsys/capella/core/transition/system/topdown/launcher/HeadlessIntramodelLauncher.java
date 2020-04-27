/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.launcher;

import org.polarsys.capella.core.transition.common.activities.FinalizeTransitionActivity;
import org.polarsys.capella.core.transition.common.activities.InitializeScopeActivity;
import org.polarsys.capella.core.transition.common.activities.PostDiffMergeActivity;
import org.polarsys.capella.core.transition.common.activities.PostTransformationActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.common.handlers.log.ILogHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.launcher.TransitionLauncher;
import org.polarsys.capella.core.transition.system.activities.DifferencesMergingActivity;
import org.polarsys.capella.core.transition.system.topdown.activities.DifferencesComputingActivity;
import org.polarsys.capella.core.transition.system.topdown.activities.InitializeDiffMergeActivity;
import org.polarsys.capella.core.transition.system.topdown.activities.InitializeTransformationActivity;
import org.polarsys.capella.core.transition.system.topdown.activities.InitializeTransitionActivity;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.log.TopDownLogHandler;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.WorkflowActivityParameter;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;

public class HeadlessIntramodelLauncher extends TransitionLauncher {

  private final String MAPPING = "org.polarsys.capella.core.transition.system.topdown"; //$NON-NLS-1$

  @Override
  protected void initializeLogHandler() {
    ILogHandler handler = new TopDownLogHandler(getReportComponent());
    handler.init(TransitionContext.EMPTY_CONTEXT);
    LogHelper.setInstance(handler);
  }

  @Override
  protected String getReportComponent() {
    return "Transition";
  }

  @Override
  protected String getMapping() {
    return MAPPING;
  }

  @Override
  protected String getName() {

    String kind = getOptionScope();

    if (ITopDownConstants.TRANSITION_TOPDOWN_ACTOR.equals(kind)) {
      return "Actor Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY.equals(kind)) {
      return "Capability Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_DATA.equals(kind)) {
      return "Data Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM.equals(kind)) {
      return "Exchange Item Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL.equals(kind)) {
      return "Functional Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE.equals(kind)) {
      return "Interface Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_LC2PC.equals(kind)) {
      return "LC / LA to PC / PA Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OA2SC.equals(kind)) {
      return "Operational Activity to System Capability Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OA2SM.equals(kind)) {
      return "Operational Activity to System Mission Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OC2SM.equals(kind)) {
      return "Operational Capability to System Mission Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR.equals(kind)) {
      return "Operational Entity to System Actor Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM.equals(kind)) {
      return "Operational Entity to System Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE.equals(kind)) {
      return "Property Value Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE.equals(kind)) {
      return "State Machine Transition";

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM.equals(kind)) {
      return "System Transition";
      
    } else if (ITopDownConstants.TRANSITION_TOPDOWN_PC2CI.equals(kind)) {
      return "PC to CI Transition";
    }

    return getOptionScope();
  }

  protected String getOptionScope() {
    return ITopDownConstants.TRANSITION_TOPDOWN;
  }

  /**
   * Activities to load in the workflow element of cadence "PRE ANALYSIS"
   * 
   * @return associated workflow element
   */
  @Override
  protected WorkflowActivityParameter buildPreAnalysisActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    if (getTransposer() != null) {

      // InitializeTransitionActivity
      parameter.addActivity(InitializeTransitionActivity.ID);

      // Add Rule handler
      GenericParameter<IRulesHandler> param2 = new GenericParameter<IRulesHandler>(
          org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity.PARAMETER_RULE_HANDLER,
          getTransposer().getRulesHandler(), "Transposer Rule handler"); //$NON-NLS-1$
      parameter.addParameter(InitializeTransitionActivity.ID, param2);

      GenericParameter<String> param3 = new GenericParameter<String>(ITransitionConstants.OPTIONS_SCOPE,
          getOptionScope(), "Option scope"); //$NON-NLS-1$
      parameter.addParameter(InitializeTransitionActivity.ID, param3);


      // InitializeTransformationActivity
      parameter.addActivity(InitializeTransformationActivity.ID);

      // InitializeScopeActivity
      parameter.addActivity(InitializeScopeActivity.ID);

    }

    return parameter;
  }

  /**
   * Activities to load in the workflow element of cadence "POST EXECUTION"
   * 
   * @return associated workflow element
   */
  @Override
  protected WorkflowActivityParameter buildDiffMergeActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    if (getTransposer() != null) {

      // PostTransformationActivity
      parameter.addActivity(PostTransformationActivity.ID);

      // InitializeDiffMergeActivity
      parameter.addActivity(InitializeDiffMergeActivity.ID);

      // DifferencesComputingActivity
      parameter.addActivity(DifferencesComputingActivity.ID);

      // DifferencesMergingActivity
      parameter.addActivity(DifferencesMergingActivity.ID);

    }

    return parameter;
  }
  
  /**
   * Activities to load in the workflow element of cadence "POST EXECUTION"
   * 
   * @return associated workflow element
   */
  @Override
  protected WorkflowActivityParameter buildFinalizationActivities() {
    WorkflowActivityParameter parameter = new WorkflowActivityParameter();

    if (getTransposer() != null) {

      // PostDiffMergeActivity
      parameter.addActivity(PostDiffMergeActivity.ID);

      // FinalizeTransitionActivity
      parameter.addActivity(FinalizeTransitionActivity.ID);

    }

    return parameter;
  }
}
