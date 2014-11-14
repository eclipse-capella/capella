/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
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
  public IStatus _run(ActivityParameters activityParams_p) {
    IContext context = (IContext) activityParams_p.getParameter(TRANSPOSER_CONTEXT).getValue();

    disposeSharedContents(context);

    disposeHandlers(context);

    removeTemporaryElements(context);

    return Status.OK_STATUS;

  }

  /**
   * @param context_p
   */
  protected void removeTemporaryElements(IContext context_p) {

    // Delete content of the root transformation element
    Object root = context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
    if ((root != null) && (root instanceof EObject)) {
      EObject rootObject = (EObject) root;
      if (rootObject.eContainer() == null) {
        ArrayList<EObject> toDelete = new ArrayList<EObject>();
        if (rootObject != null) {
          for (EObject o : rootObject.eContents()) {
            toDelete.add(o);
          }
        }
        AttachmentHelper.getInstance(context_p).removeElements(toDelete, context_p);

      }
    }
  }

  /**
   * @param context_p
   */
  protected void disposeSharedContents(IContext context_p) {
    Object value = context_p.get(ITransitionConstants.TRANSFORMED_ELEMENTS);
    if ((value != null) && (value instanceof Collection)) {
      ((Collection<?>) value).clear();
    }
  }

  /**
   * @param context_p
   */
  protected void disposeHandlers(IContext context_p) {
    ContextScopeHandlerHelper.getInstance(context_p).dispose(context_p);
  }

}
