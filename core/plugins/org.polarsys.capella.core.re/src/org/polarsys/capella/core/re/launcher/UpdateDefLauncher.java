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
package org.polarsys.capella.core.re.launcher;

import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.common.handlers.log.ILogHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.system.handlers.log.CapellaLogHandler;

/**
 */
public class UpdateDefLauncher extends org.polarsys.capella.common.re.launcher.UpdateDefLauncher {

  @Override
  protected void initializeLogHandler() {
    ILogHandler handler = new CapellaLogHandler(getReportComponent());
    handler.init(TransitionContext.EMPTY_CONTEXT);
    LogHelper.setInstance(handler);
  }

  @Override
  protected String getMapping() {
    return "org.polarsys.capella.core.re.updateDef";
  }

  @Override
  protected String getScope() {
    return "org.polarsys.capella.core.re.updateDef";
  }

  @Override
  protected void initOverrides() {
    addOverrides(org.polarsys.capella.common.re.rpl2re.activities.InitializeTransitionActivity.ID,
        org.polarsys.capella.core.re.rpl2re.activities.InitializeTransitionActivity.ID);

    addOverrides(org.polarsys.capella.common.re.activities.InitializeReMgtActivity.ID,
        org.polarsys.capella.core.re.activities.InitializeReMgtActivity.ID);

    addOverrides(org.polarsys.capella.common.re.re2rpl.activities.InitializeTransitionActivity.ID,
        org.polarsys.capella.core.re.re2rpl.activities.InitializeTransitionActivity.ID);

    addOverrides(org.polarsys.capella.common.re.activities.AttachmentActivity.ID,
        org.polarsys.capella.core.re.activities.AttachmentActivity.ID);

    addOverrides(org.polarsys.capella.common.re.activities.DifferencesComputingActivity.ID,
        org.polarsys.capella.core.re.activities.DifferencesComputingActivity.ID);

    addOverrides(org.polarsys.capella.common.re.re2rpl.activities.InitializeDiffMergeUpdateReplicaActivity.ID,
        org.polarsys.capella.core.re.re2rpl.activities.InitializeDiffMergeUpdateReplicaActivity.ID);

    addOverrides(org.polarsys.capella.common.re.rpl2re.activities.InitializeDiffMergeUpdateReActivity.ID,
        org.polarsys.capella.core.re.rpl2re.activities.InitializeDiffMergeUpdateReActivity.ID);

    addOverrides(org.polarsys.capella.common.re.re2rpl.activities.DifferencesFilteringActivity.ID,
        org.polarsys.capella.core.re.re2rpl.activities.DifferencesFilteringActivity.ID);

    addOverrides(org.polarsys.capella.common.re.rpl2re.activities.DifferencesFilteringActivity.ID,
        org.polarsys.capella.core.re.rpl2re.activities.DifferencesFilteringActivity.ID);
  }
}
