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
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import org.polarsys.capella.common.ui.services.commands.AbstractLocateInWorkbenchPartHandler;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;

/**
 * Handler to locate active part selection in active Sirius diagram editor (if any).
 */
public class ShowInDiagramHandler extends AbstractLocateInWorkbenchPartHandler {
  /**
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInWorkbenchPartHandler#handleSelection(org.eclipse.jface.viewers.ISelection,
   *      org.eclipse.ui.IWorkbenchPart, org.eclipse.core.commands.ExecutionEvent)
   */
  @Override
  protected Object handleSelection(ISelection selection_p, IWorkbenchPart activePart_p, ExecutionEvent event_p) {
    ShowInDiagramAction action = new ShowInDiagramAction();
    action.selectionChanged((IStructuredSelection) selection_p);
    action.run();
    return null;
  }
}
