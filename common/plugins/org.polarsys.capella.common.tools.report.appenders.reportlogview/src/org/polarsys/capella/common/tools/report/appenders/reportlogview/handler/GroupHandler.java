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
