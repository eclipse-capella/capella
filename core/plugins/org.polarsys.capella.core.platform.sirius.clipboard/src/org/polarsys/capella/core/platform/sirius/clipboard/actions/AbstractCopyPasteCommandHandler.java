/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.actions;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;

/**
 * An abstract handler for Capella Diagram Copy and Paste
 */
public abstract class AbstractCopyPasteCommandHandler extends AbstractUiHandler {
  
  /**
   * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
   */
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    AbstractCopyPasteAction action = getAction();
    action.setActivePart(null, getActivePart());
    action.selectionChanged(null, getSelection());
    action.run(null);
    return null;
  }
  
  /**
   * Return the action to execute
   * @return a non-null action
   */
  protected abstract AbstractCopyPasteAction getAction();
  
  /**
   * Return the workbench part that is currently active
   * @return a potentially null workbench part
   */
  protected IWorkbenchPart getActivePart() {
    return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
  }
  
  /**
   * Return the selection of the currently active workbench part
   * @return a potentially null selection
   */
  protected ISelection getSelection() {
    ISelection result = null;
    try {
      IWorkbenchPart activePart = getActivePart();
      if (activePart != null) {
        result = activePart.getSite().getSelectionProvider().getSelection();
      }
    } catch (Exception e) {
      // Just proceed
    }
    return result;
  }
  
  /**
   * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    boolean result = false;
    ISelection selection = getSelection();
    if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
      if (((IStructuredSelection)selection).getFirstElement() instanceof EditPart) {
        AbstractCopyPasteAction action = getAction();
        action.selectionChanged(null, selection);
        result = action.isEnabled();
      }
    }
    return result;
  }
  
}
