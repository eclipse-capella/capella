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

package org.polarsys.capella.common.re.launcher;

import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.launcher.IDefaultWorkflow;
import org.polarsys.capella.core.transition.common.launcher.ILoopActivityDispatcher;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class LoopActivityDispatcher implements ILoopActivityDispatcher {

  public boolean loop(IContext context, String current) {
    if (IDefaultWorkflow.WORKFLOW_STEP__DIFF_MERGE.equals(current)) {
      return !(ReplicableElementHandlerHelper.getInstance(context).getListSources(context).isEmpty());
    }
    return false;
  }
}
