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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.handlers;

import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveDownAction;
import org.polarsys.capella.core.ui.toolkit.actions.move.AbstractMoveAction;

/**
 * Move down handler that runs a MoveUpAction.
 */
public class MoveDownHandler extends AbstractMoveHandler {
  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.handlers.AbstractMoveHandler#createMoveAction(org.eclipse.emf.edit.domain.EditingDomain)
   */
  @Override
  protected AbstractMoveAction createMoveAction() {
    return new MoveDownAction();
  }
}
