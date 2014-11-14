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
package org.polarsys.capella.core.ui.toolkit.actions.move;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Move up selected elements.<br>
 * Selected elements must have the same type and the same parent.
 */
public class MoveUpAction extends AbstractMoveAction {
  /**
   * Constructor.
   * @param text_p
   */
  public MoveUpAction(EditingDomain domain_p) {
    super(domain_p, Messages.MoveUpAction_Title);
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<?> selection_p) {
    return new CapellaMoveCommand(getEditingDomain(), Messages.MoveUpAction_Title, selection_p, true);
  }
}
