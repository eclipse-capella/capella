/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.ui.IWorkbenchPart;

import org.polarsys.capella.common.ui.services.commands.AbstractLocateInWorkbenchPartHandler;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SemanticLocateInCapellaExplorerAction;

/**
 * Handler that runs the actions that shows All related elements from a diagram selection.
 */
public class ShowRelatedElementsHandler extends AbstractLocateInWorkbenchPartHandler {
  /**
   * {@inheritDoc}
   */
  @Override
  protected Object handleSelection(ISelection selection_p, IWorkbenchPart activePart_p, ExecutionEvent event_p) {
    SemanticLocateInCapellaExplorerAction relatedAction = new SemanticLocateInCapellaExplorerAction();
    relatedAction.selectionChanged(null, selection_p);
    relatedAction.setActivePart(null, activePart_p);
    relatedAction.run(null);
    return null;
  }
}
