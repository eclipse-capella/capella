/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ui.services.commands.ActionCommandDelegate;
import org.polarsys.capella.core.platform.sirius.ui.actions.ConcretizeFunctionAllocationAction;

/**
 * Handler for the ConcretizeFunctionAllocation accelerator,
 * 
 * Delegate event handling and determine accelerator visibility.
 * @author ebausson
 */
public class ConcretizeFunctionAllocationHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ActionCommandDelegate delegate = new ActionCommandDelegate(event);
    ConcretizeFunctionAllocationAction action = new ConcretizeFunctionAllocationAction();
    action.selectionChanged(delegate, HandlerUtil.getCurrentSelection(event));
    action.run(delegate);
    return null;
  }

  @Override
  public boolean isEnabled() {
    ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getSelectionProvider().getSelection();
    return (selection != null && selection instanceof IStructuredSelection && !selection.isEmpty());
  }

}