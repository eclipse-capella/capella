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

import org.eclipse.emf.common.command.Command;

/**
 * Move up selected elements.<br>
 * Selected elements must have the same type and the same parent.
 */
public class MoveUpAction extends AbstractMoveAction {
  /**
   * Constructor.
   */
  public MoveUpAction() {
    super(Messages.MoveUpAction_Title);
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<Object> selection_p) {
    return new CapellaMoveCommand(Messages.MoveUpAction_Title, filterSelection(selection_p), true);
  }
}
