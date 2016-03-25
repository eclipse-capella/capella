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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.filter.CompoundFilteringItems;
import org.polarsys.capella.core.transition.common.handlers.filter.DefaultFilteringDifferencesHandler;
import org.polarsys.capella.core.transition.common.handlers.filter.FilteringDifferencesHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.filter.IFilteringDifferencesHandler;
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
public class DifferencesFilteringActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.DifferencesFilteringActivity"; //$NON-NLS-1$

  /**
   * Initialize the Options handler and set it into context via ITransitionConstants.OPTIONS_HANDLER
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeFilteringDifferencesHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.FILTERING_DIFFERENCES_HANDLER, activityParams);
    if (handler == null) {
      handler = new DefaultFilteringDifferencesHandler();
    }
    context.put(ITransitionConstants.FILTERING_DIFFERENCES_HANDLER, handler);
    IStatus status = handler.init(context);
    if ((handler != null) && (handler instanceof CompoundFilteringItems)) {
      initializeFilterItemHandlers(context, (CompoundFilteringItems) handler, activityParams);
    }

    return status;
  }

  protected IStatus initializeFilterItemHandlers(IContext context, CompoundFilteringItems handler, ActivityParameters activityParams) {
    return Status.OK_STATUS;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters)
   */
  @Override
  @SuppressWarnings("unchecked")
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    IStatus status = initializeFilteringDifferencesHandler(context, activityParams);
    if (status.matches(IStatus.CANCEL)) {
      return status;
    }

    Collection<IDifference> fromReference = (Collection<IDifference>) context.get(ITransitionConstants.MERGE_REFERENCE_DIFFERENCES);
    Collection<IDifference> fromTarget = (Collection<IDifference>) context.get(ITransitionConstants.MERGE_TARGET_DIFFERENCES);

    Collection<IDifference> toReference = new ArrayList<IDifference>();
    Collection<IDifference> toTarget = new ArrayList<IDifference>();

    boolean result = computeDifferences(fromReference, fromTarget, toReference, toTarget, context);

    if (result) {
      context.put(ITransitionConstants.MERGE_REFERENCE_DIFFERENCES_TO_MERGE, toReference);
      context.put(ITransitionConstants.MERGE_TARGET_DIFFERENCES_TO_MERGE, toTarget);
      return Status.OK_STATUS;
    }

    return Status.CANCEL_STATUS;

  }

  /**
   * @return true if merge action can be applied
   */
  protected boolean computeDifferences(Collection<IDifference> fromReference, Collection<IDifference> fromTarget, Collection<IDifference> toReference,
      Collection<IDifference> toTarget, IContext context) {

    Collection<IDifference> filteredFromReference = new ArrayList<IDifference>();
    Collection<IDifference> filteredFromTarget = new ArrayList<IDifference>();

    IFilteringDifferencesHandler handler = FilteringDifferencesHandlerHelper.getInstance(context);

    IStatus status = handler.processDifferences(context, fromReference, fromTarget);

    if (status.isOK()) {

      //Retrieve differences from source differences
      for (IDifference difference : fromReference) {
        Role role = handler.getMergeDestination(context, difference, Role.REFERENCE);
        if (role == null) {
          filteredFromReference.add(difference);
        } else if (role == Role.REFERENCE) {
          toReference.add(difference);
        } else if (role == Role.TARGET) {
          toTarget.add(difference);
        }
      }

      //Retrieve differences from target differences
      for (IDifference difference : fromTarget) {
        Role role = handler.getMergeDestination(context, difference, Role.TARGET);
        if (role == null) {
          filteredFromTarget.add(difference);
        } else if (role == Role.REFERENCE) {
          toReference.add(difference);
        } else if (role == Role.TARGET) {
          toTarget.add(difference);
        }
      }

      if (displayLog(context)) {

        // Logging
        LogHelper.getInstance().debug("Differences :", Messages.Activity_FilteringDifferenceActivity);
        for (IDifference diff : toReference) {
          displayLog(diff, "To Merge from Reference", DiffScope.Source, FilterAction.TARGET, context);
        }

        for (IDifference diff : toTarget) {
          displayLog(diff, "To Merge from Target", DiffScope.Target, FilterAction.TARGET, context);
        }

        for (IDifference diff : filteredFromReference) {
          displayLog(diff, "Filtered from Reference", DiffScope.Source, FilterAction.NO_ACTION, context);
        }

        for (IDifference diff : filteredFromTarget) {
          displayLog(diff, "Filtered from Target", DiffScope.Target, FilterAction.NO_ACTION, context);
        }

      }
      return true;
    }
    return false;
  }

  /**
   * @param context
   * @return
   */
  protected boolean displayLog(IContext context) {
    return true;
  }

  private void displayLog(IDifference diff, String difftext, DiffScope diffscope, FilterAction diffaction, IContext context) {
    IDiffModelViewer diffModelViewer = IDiffModelViewerFactory.eINSTANCE.createDiffModelViewer(diff, diffscope, diffaction, context, false);
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
      LogHelper.getInstance().debug(NLS.bind(" - {0} : {1}", difftext, diffModelViewer.getTextDiff()), listObject,
          Messages.Activity_MergingDifferenceActivity);

    } else {
      LogHelper.getInstance().debug(NLS.bind(" - {0} : {1}", difftext, diffModelViewer.getTextDiff()), Messages.Activity_MergingDifferenceActivity);
    }

  }

}
