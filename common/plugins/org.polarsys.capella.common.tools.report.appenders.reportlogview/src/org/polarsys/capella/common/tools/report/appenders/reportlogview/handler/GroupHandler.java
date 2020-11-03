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

package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

/** 
 */
public class GroupHandler extends AbstractHandler {

  public Object execute(ExecutionEvent event) throws ExecutionException {
    if(HandlerUtil.matchesRadioState(event))
      return null; // we are already in the updated state - do nothing
    
    String currentState = event.getParameter(RadioState.PARAMETER_ID);
    HandlerUtil.updateRadioState(event.getCommand(), currentState);
    return null;
  }
}
