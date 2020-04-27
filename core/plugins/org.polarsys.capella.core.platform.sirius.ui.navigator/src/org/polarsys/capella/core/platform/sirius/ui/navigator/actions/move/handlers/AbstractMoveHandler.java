/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.ui.toolkit.actions.move.AbstractMoveAction;

/**
 * Base class to implement handlers that runs {@link AbstractMoveAction}.
 */
public abstract class AbstractMoveHandler extends AbstractUiHandler {
  /**
   * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
   */
  public Object execute(ExecutionEvent arg0) throws ExecutionException {
    // Get the selection for this handler.
    List<EObject> selectedObjects = getSelection(arg0, EObject.class);
    // Create the move action.
    AbstractMoveAction moveAction = createMoveAction();
    // Update the move action selection.
    moveAction.selectionChanged(new StructuredSelection(selectedObjects));
    boolean cr = moveAction.updateSelection(new StructuredSelection(selectedObjects));
    if (cr) {
      // Run it.
      moveAction.run();
    }
    return null;
  }

  /**
   * Create a move action.
   * 
   * @param domain_p
   * @return must be not <code>null</code>.
   */
  protected abstract AbstractMoveAction createMoveAction();
}
