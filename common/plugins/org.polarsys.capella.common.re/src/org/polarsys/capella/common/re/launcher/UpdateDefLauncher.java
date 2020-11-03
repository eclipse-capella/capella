/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.launcher.ILoopActivityDispatcher;

/**
 */
public class UpdateDefLauncher extends UpdateCurLauncher {

  @Override
  protected String getMapping() {
    return "org.polarsys.capella.common.re.updateDef";
  }

  @Override
  protected String getScope() {
    return "org.polarsys.capella.common.re.updateDef";
  }
  
  @Override
  public ILoopActivityDispatcher createDispatcher() {
    return new LoopActivityDispatcher();
  }

  @Override
  protected String getKind() {
    return IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA;
  }
}
