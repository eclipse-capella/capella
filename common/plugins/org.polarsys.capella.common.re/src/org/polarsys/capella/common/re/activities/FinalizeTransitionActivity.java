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
package org.polarsys.capella.common.re.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class FinalizeTransitionActivity extends org.polarsys.capella.core.transition.common.activities.FinalizeTransitionActivity {
  public static final String ID = "org.polarsys.capella.common.re.activities.FinalizeTransitionActivity"; //$NON-NLS-1$

  @Override
  public IStatus _run(ActivityParameters activityParams_p) {
    IContext context = (IContext) activityParams_p.getParameter(TRANSPOSER_CONTEXT).getValue();

    super._run(activityParams_p);

    //All newly created valid links should not be removed. Other links should be. 
    ContextScopeHandlerHelper.getInstance(context).addAll(IReConstants.VIRTUAL_LINKS_3,
        ContextScopeHandlerHelper.getInstance(context).getCollection(IReConstants.VIRTUAL_LINKS_2, context), context);

    ReplicableElementHandlerHelper.getInstance(context).cleanVirtualLinks(context);

    return Status.OK_STATUS;
  }
}
