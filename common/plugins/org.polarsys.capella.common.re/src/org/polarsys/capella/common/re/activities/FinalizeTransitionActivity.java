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

package org.polarsys.capella.common.re.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.handlers.location.LocationHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class FinalizeTransitionActivity extends org.polarsys.capella.core.transition.common.activities.FinalizeTransitionActivity {
  public static final String ID = "org.polarsys.capella.common.re.activities.FinalizeTransitionActivity"; //$NON-NLS-1$

  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IContext context = (IContext) activityParams.getParameter(TRANSPOSER_CONTEXT).getValue();

    super._run(activityParams);

    //All newly created valid links should not be removed. Other links should be. 
    ReplicableElementHandlerHelper.getInstance(context).cleanVirtualLinks(context);
    LocationHandlerHelper.getInstance(context).cleanLocations(context);

    Collection<EObject> toRemove = ReplicableElementHandlerHelper.getInstance(context).getDeletableElements(context);
    if (!toRemove.isEmpty()) {
      AttachmentHelper.getInstance(context).removeElements(toRemove, context);
    }
    return Status.OK_STATUS;
  }
}
