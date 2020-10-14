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

package org.polarsys.capella.core.transition.common.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.MergeHandlerHelper;
import org.polarsys.capella.core.transition.common.merge.scope.ITargetModelScope;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesMergingActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.DifferencesMergingActivity"; //$NON-NLS-1$

  @Override
  @SuppressWarnings("unchecked")
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    Collection<IDifference<EObject>> fromReference = (Collection<IDifference<EObject>>) context
        .get(ITransitionConstants.MERGE_REFERENCE_DIFFERENCES);
    Collection<IDifference<EObject>> fromTarget = (Collection<IDifference<EObject>>) context
        .get(ITransitionConstants.MERGE_TARGET_DIFFERENCES);

    IStatus result = computeDifferences(fromReference, fromTarget, context);

    boolean shouldSave = false;
    context.put(ITransitionConstants.SAVE_REQUIRED, shouldSave);

    if (result.isOK()) {
      return Status.OK_STATUS;
    }

    IEditableModelScope targetScope = (IEditableModelScope) context.get(ITransitionConstants.MERGE_TARGET_SCOPE);
    if (targetScope instanceof ITargetModelScope.Edit) {
      ((ITargetModelScope.Edit) targetScope).setDirty(false);
    }
    
    return Status.CANCEL_STATUS;

  }

  protected IStatus computeDifferences(Collection<IDifference<EObject>> fromReference, Collection<IDifference<EObject>> fromTarget,
      IContext context) {
    return MergeHandlerHelper.getInstance(context).processDifferences(context, fromReference, fromTarget);
  }

}
