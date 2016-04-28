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

package org.polarsys.capella.core.transition.common.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffScope;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewerFactory;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesMergingActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.DifferencesMergingActivity"; //$NON-NLS-1$

  /*
   * (non-Javadoc)
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    performMerge(context);

    return Status.OK_STATUS;
  }

  /**
   * @param selection_p
   */
  public void performMerge(IContext context) {

    Collection<IDifference> toMergeFromReference = (Collection<IDifference>) context.get(ITransitionConstants.MERGE_REFERENCE_DIFFERENCES_TO_MERGE);
    Collection<IDifference> toMergeFromTarget = (Collection<IDifference>) context.get(ITransitionConstants.MERGE_TARGET_DIFFERENCES_TO_MERGE);

    // Defining comparison with target as TARGET and source as REFERENCE
    IComparison comparison = (IComparison) context.get(ITransitionConstants.MERGE_COMPARISON);

    boolean shouldSave = false;

    if ((toMergeFromReference != null) && (!toMergeFromReference.isEmpty())) {
      LogHelper.getInstance().debug(NLS.bind(" - Merge {0} differences into {1} scope", Role.TARGET.toString(), Role.TARGET.toString()),
          Messages.Activity_MergingDifferenceActivity);

      LogHelper.getInstance().debug("Merge of Differences :", Messages.Activity_MergingDifferenceActivity);

      Collection<IDifference> merged = comparison.merge(toMergeFromReference, Role.TARGET, true, new NullProgressMonitor());

      // Logging
      for (IDifference diff : merged) {
        displayLog(diff, DiffScope.Source, context);
      }

      shouldSave = true;
    } else {
      LogHelper.getInstance().debug(" - No merge from reference needed", Messages.Activity_MergingDifferenceActivity);
    }

    if ((toMergeFromTarget != null) && (!toMergeFromTarget.isEmpty())) {
      LogHelper.getInstance().debug(NLS.bind(" - Merge {0} differences into {1} scope", Role.TARGET.toString(), Role.TARGET.toString()),
          Messages.Activity_MergingDifferenceActivity);

      LogHelper.getInstance().debug("Merge of Differences :", Messages.Activity_MergingDifferenceActivity);

      Collection<IDifference> merged = comparison.merge(toMergeFromTarget, Role.TARGET, true, new NullProgressMonitor());

      // Logging
      for (IDifference diff : merged) {
        displayLog(diff, DiffScope.Target, context);
      }

      shouldSave = true;
    } else {
      LogHelper.getInstance().debug(" - No merge from target needed", Messages.Activity_MergingDifferenceActivity);
    }

    context.put(ITransitionConstants.SAVE_REQUIRED, shouldSave);

  }

  private void displayLog(IDifference diff, DiffScope diffscope, IContext context) {
    IDiffModelViewer diffModelViewer = IDiffModelViewerFactory.eINSTANCE.createDiffModelViewer(diff, diffscope, FilterAction.TARGET, context, false);
    EObject me = diffModelViewer.getSemanticElementDiff();
    Object[] listObject = null;
    if (me != null) {
      context.put(ITransitionConstants.TRACEABILITY_HANDLER, context.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER));
      Collection<EObject> sourceObject = TraceabilityHandlerHelper.getInstance(context).retrieveSourceElements(me, context);

      if (sourceObject != null) {
        EObject targetObject = null;

        if (diff instanceof IElementRelativeDifference) {
          IElementRelativeDifference techdiff = (IElementRelativeDifference) diff;
          targetObject = techdiff.getElementMatch().get(Role.TARGET);
        }

        if (targetObject != null) {
          listObject = new Object[] { sourceObject, targetObject };
        } else {
          listObject = new Object[] { sourceObject };
        }
      }
    }

    if (listObject != null) {
      LogHelper.getInstance().debug(NLS.bind(" - Merged : {0}", diffModelViewer.getTextDiff()), listObject, Messages.Activity_MergingDifferenceActivity);

    } else {
      LogHelper.getInstance().debug(NLS.bind(" - Merged : {0}", diffModelViewer.getTextDiff()), Messages.Activity_MergingDifferenceActivity);
    }

  }
}
