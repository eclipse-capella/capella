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

package org.polarsys.capella.core.transition.common.activities;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.capella.core.transition.common.policies.diff.ExtDiffPolicy;
import org.polarsys.capella.core.transition.common.policies.match.TraceabilityHandlerMatchPolicy;
import org.polarsys.capella.core.transition.common.policies.merge.ExtMergePolicy;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesComputingActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.DifferencesComputingActivity"; //$NON-NLS-1$

  /*
   * (non-Javadoc)
   * 
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.
   * ActivityParameters)
   */
  @Override
  @SuppressWarnings("unchecked")
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    computeDifferences(context);

    return Status.OK_STATUS;
  }

  /**
   * @param context
   */
  public void computeDifferences(IContext context) {

    IEditableModelScope sourceScope = (IEditableModelScope) context.get(ITransitionConstants.MERGE_REFERENCE_SCOPE);
    IEditableModelScope targetScope = (IEditableModelScope) context.get(ITransitionConstants.MERGE_TARGET_SCOPE);

    // Defining comparison with target as TARGET and source as REFERENCE
    IComparison comparison = createComparison(sourceScope, targetScope);

    context.put(ITransitionConstants.MERGE_COMPARISON, comparison);

    // Computing differences
    comparison.compute(createMatchPolicy(context), createDiffPolicy(context), createMergePolicy(context), null);

    // Getting differences to merge: all the presences in source
    List<IDifference> toAnalyseFromSource = comparison.getDifferences(Role.REFERENCE);
    List<IDifference> toAnalyseFromTarget = comparison.getDifferences(Role.TARGET);

    context.put(ITransitionConstants.MERGE_REFERENCE_DIFFERENCES, toAnalyseFromSource);
    context.put(ITransitionConstants.MERGE_TARGET_DIFFERENCES, toAnalyseFromTarget);

    if (displayLog(context)) {

      // Logging
      LogHelper.getInstance().debug(NLS.bind("Differences from {0}", Role.REFERENCE.toString()),
          Messages.Activity_ComputingDifferenceActivity);
      for (IDifference diff : toAnalyseFromSource) {
        LogHelper.getInstance().debug(NLS.bind(" - {0}", diff.toString()),
            Messages.Activity_ComputingDifferenceActivity);
      }

      LogHelper.getInstance().debug(NLS.bind("Differences from {0}", Role.TARGET.toString()),
          Messages.Activity_ComputingDifferenceActivity);
      for (IDifference diff : toAnalyseFromTarget) {
        LogHelper.getInstance().debug(NLS.bind(" - {0}", diff.toString()),
            Messages.Activity_ComputingDifferenceActivity);
      }

    }

  }

  /**
   * @param context
   * @return
   */
  protected boolean displayLog(IContext context) {
    return true;
  }

  /**
   * @param context
   * @return
   */
  protected IMergePolicy createMergePolicy(IContext context) {
    return new ExtMergePolicy(context);
  }

  /**
   * @param context
   * @return
   */
  protected IDiffPolicy createDiffPolicy(IContext context) {
    IDiffPolicy policy = new ExtDiffPolicy(context);
    return policy;
  }

  protected IComparison createComparison(IEditableModelScope sourceScope, IEditableModelScope targetScope) {
    return new ExtendedComparison(targetScope, sourceScope);
  }

  /**
   * @return
   */
  protected IMatchPolicy createMatchPolicy(IContext context) {
    IMatchPolicy policy = new TraceabilityHandlerMatchPolicy(context);
    return policy;
  }

}
