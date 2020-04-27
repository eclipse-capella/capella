/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.activities;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.activities.AbstractActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class MakeTraceabilityActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = MakeTraceabilityActivity.class.getCanonicalName();

  /**
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();
    IComparison comparison = (IComparison) context.get(ITransitionConstants.MERGE_COMPARISON);

    TreeIterator<IMatch> targetMatches = comparison.getAllContents(Role.TARGET);
    TreeIterator<IMatch> referenceMatches = comparison.getAllContents(Role.REFERENCE);
    List<IMatch> result = new FArrayList<IMatch>();

    while (targetMatches.hasNext()) {
      IMatch match = targetMatches.next();
      result.add(match);
    }
    while (referenceMatches.hasNext()) {
      IMatch match = referenceMatches.next();
      if (!result.contains(match)) {
        result.add(match);
      }
    }

    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    HashMap<EObject, CatalogElementLink> linksSource = new HashMap<EObject, CatalogElementLink>();
    HashMap<EObject, CatalogElementLink> linksTarget = new HashMap<EObject, CatalogElementLink>();

    //Improve that!
    if (source != null) {
      for (CatalogElementLink link : source.getOwnedLinks()) {
        linksSource.put(link.getTarget(), link);
      }
    }

    if (target != null) {
      for (CatalogElementLink link : target.getOwnedLinks()) {
        linksTarget.put(link.getTarget(), link);
      }
    }

    for (IMatch match : result) {
      EObject mSource = match.get(Role.REFERENCE);
      EObject mTarget = match.get(Role.TARGET);

      CatalogElementLink linkSource = null;
      CatalogElementLink linkTarget = null;

      if (context.exists(IReConstants.COMMAND__CURRENT_VALUE)) {
        if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(context.get(IReConstants.COMMAND__CURRENT_VALUE))
            || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(context.get(IReConstants.COMMAND__CURRENT_VALUE))
            || IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(context.get(IReConstants.COMMAND__CURRENT_VALUE))) {

          //Update ReplicableElement from Replica
          linkTarget = linksSource.get(mSource);
          linkSource = linksTarget.get(mTarget);
        } else {

          //Update Replica from ReplicableElement
          linkSource = linksSource.get(mSource);
          linkTarget = linksTarget.get(mTarget);
        }

        if ((linkSource != null) && (linkTarget != null)) {
          linkTarget.setOrigin(linkSource);

          if ((linkTarget.getTarget() instanceof CatalogElement) && (linkSource.getTarget() instanceof CatalogElement)) {
            (((CatalogElement) linkTarget.getTarget())).setOrigin((CatalogElement) linkSource.getTarget());
          }
        }
      }
    }

    return Status.OK_STATUS;
  }
}
