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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.StructuredSelection;

import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.ui.toolkit.actions.move.AbstractMoveAction;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * Base class to implement handlers that runs {@link AbstractMoveAction}.
 */
public abstract class AbstractMoveHandler extends AbstractUiHandler {
  /**
   * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
   */
  public Object execute(ExecutionEvent arg0_p) throws ExecutionException {
    // Get the capella editing domain.
    TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    // Create the move action.
    AbstractMoveAction moveAction = createMoveAction(editingDomain);
    // Get the selection for this handler.
    List<EObject> selectedObjects = getSelection(arg0_p, EObject.class);
    // Update the move action selection.
    boolean cr = moveAction.updateSelection(new StructuredSelection(selectedObjects));
    if (cr) {
      // Run it.
      moveAction.run();
    }
    return null;
  }

  /**
   * Create a move action.
   * @param domain_p
   * @return must be not <code>null</code>.
   */
  protected abstract AbstractMoveAction createMoveAction(EditingDomain domain_p);
}
