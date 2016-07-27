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
package org.polarsys.capella.core.transition.system.topdown.ui.launcher;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeUIDifferencesHandler;
import org.polarsys.capella.core.transition.system.topdown.launcher.HeadlessIntramodelLauncher;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;

public class IntramodelLauncher extends HeadlessIntramodelLauncher {

  @Override
  protected SharedWorkflowActivityParameter getSharedParameter(String workflowId) {
    SharedWorkflowActivityParameter parameter = super.getSharedParameter(workflowId);

    // Add UI Merge handler
    GenericParameter<IHandler> param = new GenericParameter<IHandler>(ITransitionConstants.MERGE_DIFFERENCES_HANDLER,
        new MergeUIDifferencesHandler(), "Merge UI wizard"); //$NON-NLS-1$
    parameter.addSharedParameter(param);

    return parameter;
  }

}
