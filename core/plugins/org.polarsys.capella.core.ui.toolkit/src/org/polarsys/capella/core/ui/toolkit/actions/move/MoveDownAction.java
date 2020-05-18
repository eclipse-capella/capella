/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.actions.move;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;

/**
 * Move down selected elements.<br>
 * Selected elements must have the same type and the same parent.
 */
public class MoveDownAction extends AbstractMoveAction {
  /**
   * Constructor.
   */
  public MoveDownAction() {
    super(Messages.MoveDownAction_Title);
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<Object> selection_p) {
    return new CapellaMoveCommand(Messages.MoveDownAction_Title, filterSelection(selection_p), false) {

      /**
       * @see org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.AbstractMoveAction.CapellaMoveCommand#getSortedElementsToMove()
       */
      @Override
      protected List<EObject> getSortedElementsToMove() {
        List<EObject> sortedElementsToMove = super.getSortedElementsToMove();
        // When moving down, we must iterate over sorted elements in the reverse order.
        Collections.reverse(sortedElementsToMove);
        return sortedElementsToMove;
      }
    };
  }
}
