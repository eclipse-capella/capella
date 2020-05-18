/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.exchanges.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ui.services.commands.ActionCommandDelegate;
import org.polarsys.capella.core.projection.exchanges.actions.MDGenerateLinksAction;

public class GenerateLinksHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    MDGenerateLinksAction action = new MDGenerateLinksAction();
    ActionCommandDelegate delegate = new ActionCommandDelegate(event);
    action.selectionChanged(delegate, HandlerUtil.getCurrentSelection(event));
    action.run(delegate);
    return null;
  }
  
}
