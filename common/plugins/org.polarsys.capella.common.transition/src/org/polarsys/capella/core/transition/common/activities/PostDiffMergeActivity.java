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

package org.polarsys.capella.core.transition.common.activities;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class PostDiffMergeActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.PostDiffMergeActivity"; //$NON-NLS-1$

  /*
   * (non-Javadoc)
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    disposeSharedContents(context);

    disposeHandlers(context);

    removeTemporaryElements(context);

    return Status.OK_STATUS;

  }

  /**
   * @param context
   */
  protected void removeTemporaryElements(IContext context) {

    // Delete content of the root transformation element
    Object root = context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
    if (root instanceof EObject) {
      EObject rootObject = (EObject) root;
      if (rootObject.eContainer() == null) {
        ArrayList<EObject> toDelete = new ArrayList<EObject>();
        for (EObject o : rootObject.eContents()) {
          toDelete.add(o);
        }
        AttachmentHelper.getInstance(context).removeElements(toDelete, context);
      }
    }

  }

  /**
   * @param context
   */
  protected void disposeSharedContents(IContext context) {
    Object value = context.get(ITransitionConstants.TRANSFORMED_ELEMENTS);
    if ((value != null) && (value instanceof Collection)) {
      ((Collection<?>) value).clear();
    }
  }

  /**
   * @param context
   */
  protected void disposeHandlers(IContext context) {
    ContextScopeHandlerHelper.getInstance(context).dispose(context);
  }

}
