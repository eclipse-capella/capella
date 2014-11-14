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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;

public class MDTraceViewListener extends ResourceSetListenerImpl {

  boolean _rollbackRequested = false;

  /**
   * {@inheritDoc}
   */
  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event_p) throws RollbackException {
    if (_rollbackRequested) {
      throw new RollbackException(Status.CANCEL_STATUS);
    }
    return null;
  }

  /**
   * @param b_p
   */
  public void setRollbackRequested(boolean b_p) {
    _rollbackRequested = b_p;

  }
}