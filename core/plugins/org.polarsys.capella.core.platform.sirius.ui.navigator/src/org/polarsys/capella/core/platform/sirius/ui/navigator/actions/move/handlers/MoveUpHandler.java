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

import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveUpAction;
import org.polarsys.capella.core.ui.toolkit.actions.move.AbstractMoveAction;

/**
 * Move up handler that runs a MoveUpAction.
 */
public class MoveUpHandler extends AbstractMoveHandler {
  /**
   * @see org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.handlers.AbstractMoveHandler#createMoveAction(org.eclipse.emf.edit.domain.EditingDomain)
   */
  @Override
  protected AbstractMoveAction createMoveAction() {
    return new MoveUpAction();
  }
}
