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

package org.polarsys.capella.common.ui.services.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

/**
 */
public abstract class AbstractLocateInViewPartHandler extends AbstractLocateInWorkbenchPartHandler {

  /**
   * Get the targeted part id i.e the part id in which a new selection will be sent.
   * @return can be <code>null</code>.
   */
  protected abstract String getTargetedPartId();

  /**
   * Overridden to show the view in which the handled selection will be considered as new selection.<br>
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInWorkbenchPartHandler#handleSelection(org.eclipse.jface.viewers.ISelection,
   *      org.eclipse.ui.IWorkbenchPart, org.eclipse.core.commands.ExecutionEvent)
   * @return the targeted view.
   */
  @Override
  protected IViewPart handleSelection(ISelection selection, IWorkbenchPart activePart, ExecutionEvent event) {
    String targetedPartId = getTargetedPartId();
    if (null != targetedPartId) {
      try {
        return getWorkbenchWindow(event).getActivePage().showView(targetedPartId);
      } catch (PartInitException exception) {
        // Do nothing.
      }
    }
    return null;
  }
}
