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
package org.polarsys.capella.core.re.ui.launcher;

import org.polarsys.capella.core.re.launcher.UpdateReplicaLauncher;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.capella.common.re.ui.handlers.uihead.UIHeadHandler;

/**
 */
public class UpdateReplicaUiLauncher extends UpdateReplicaLauncher {

  @Override
  protected SharedWorkflowActivityParameter getSharedParameter(String workflowId_p) {
    SharedWorkflowActivityParameter parameter = super.getSharedParameter(workflowId_p);
    new UIHeadHandler().fillParameter(parameter);
    return parameter;
  }
}
