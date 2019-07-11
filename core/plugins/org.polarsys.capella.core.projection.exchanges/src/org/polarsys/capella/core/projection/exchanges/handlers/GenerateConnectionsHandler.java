/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.exchanges.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ui.services.commands.ActionCommandDelegate;
import org.polarsys.capella.core.projection.exchanges.actions.MDGenerateConnectionsAction;

public class GenerateConnectionsHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    MDGenerateConnectionsAction action = new MDGenerateConnectionsAction();
    ActionCommandDelegate delegate = new ActionCommandDelegate(event);
    action.selectionChanged(delegate, HandlerUtil.getCurrentSelection(event));
    action.run(delegate);
    return null;
  }

}
