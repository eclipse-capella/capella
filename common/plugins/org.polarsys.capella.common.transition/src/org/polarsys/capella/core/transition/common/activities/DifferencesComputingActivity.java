/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
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
    EComparison comparison = createComparison(sourceScope, targetScope);

    context.put(ITransitionConstants.MERGE_COMPARISON, comparison);

    // Computing differences
    comparison.compute(createMatchPolicy(context), createDiffPolicy(context), createMergePolicy(context), null);

    // Getting differences to merge: all the presences in source
    Collection<IDifference<EObject>> toAnalyseFromSource = comparison.getDifferences(Role.REFERENCE);
    Collection<IDifference<EObject>> toAnalyseFromTarget = comparison.getDifferences(Role.TARGET);

    context.put(ITransitionConstants.MERGE_REFERENCE_DIFFERENCES, toAnalyseFromSource);
    context.put(ITransitionConstants.MERGE_TARGET_DIFFERENCES, toAnalyseFromTarget);

    if (displayLog(context)) {

      // Logging
      LogHelper.getInstance().debug(NLS.bind("Differences from {0}", Role.REFERENCE.toString()),
          Messages.Activity_ComputingDifferenceActivity);
      for (IDifference<EObject> diff : toAnalyseFromSource) {
        LogHelper.getInstance().debug(NLS.bind(" - {0}", diff.toString()),
            Messages.Activity_ComputingDifferenceActivity);
      }

      LogHelper.getInstance().debug(NLS.bind("Differences from {0}", Role.TARGET.toString()),
          Messages.Activity_ComputingDifferenceActivity);
      for (IDifference<EObject> diff : toAnalyseFromTarget) {
        LogHelper.getInstance().debug(NLS.bind(" - {0}", diff.toString()),
            Messages.Activity_ComputingDifferenceActivity);
      }

    }

  }

  protected boolean displayLog(IContext context) {
    return true;
  }

  protected IMergePolicy<EObject> createMergePolicy(IContext context) {
    return new ExtMergePolicy(context);
  }

  protected IDiffPolicy<EObject> createDiffPolicy(IContext context) {
    return new ExtDiffPolicy(context);
  }

  protected EComparison createComparison(IEditableModelScope sourceScope, IEditableModelScope targetScope) {
    return new ExtendedComparison(targetScope, sourceScope);
  }

  protected IMatchPolicy<EObject> createMatchPolicy(IContext context) {
    return new TraceabilityHandlerMatchPolicy(context);
  }

}
