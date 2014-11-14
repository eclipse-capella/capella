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
package org.polarsys.capella.common.tig.ef.command;

import java.util.Map;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.UndoContext;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.impl.InternalTransaction;

import org.polarsys.capella.common.tig.ef.internal.command.WorkspaceCommandStackImpl;

/**
 * Implementation of a transactional command stack TIG-ready based on the one provided by org.eclipse.emf.workspace.<br>
 * This command stack takes into account non dirtying EMF operations.
 */
public class TigCommandStack extends WorkspaceCommandStackImpl {
  /**
   * Constructor.
   * @param history_p
   */
  public TigCommandStack(IOperationHistory history_p) {
    super(history_p);
  }

  /**
   * Override this methods to avoid revealing internal {@link WorkspaceCommandStackImpl}.
   * @see org.polarsys.capella.common.tig.ef.internal.command.WorkspaceCommandStackImpl#getUndoContext()
   */
  @Override
  protected IUndoContext getUndoContext() {
    return new UndoContext();
  }

  /**
   * @see org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl#doExecute(org.eclipse.emf.common.command.Command, java.util.Map)
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doExecute(Command command_p, Map options_p) throws InterruptedException, RollbackException {
    if (command_p instanceof NonDirtying) {
      executeNonDirtyingCommand(command_p, options_p);
    } else {
      super.doExecute(command_p, options_p);
    }
  }

  /**
   * Execute non dirtying command in a transactional context.
   * @param command_p
   * @param options_p
   */
  @SuppressWarnings("unchecked")
  protected void executeNonDirtyingCommand(Command command_p, Map options_p) throws InterruptedException, RollbackException {
    InternalTransaction tx = createTransaction(command_p, options_p);

    try {
      basicExecute(command_p);

      // commit the transaction now
      tx.commit();
    } catch (OperationCanceledException e) {
      // snuff the exception, because this is expected (user asked to
      // cancel the model change). We will rollback, below
    } finally {
      if ((tx != null) && (tx.isActive())) {
        // roll back (some exception, possibly being thrown now or
        // an operation cancel, has occurred)
        rollback(tx);
        handleRollback(command_p, null);
      } else {
        // the transaction has already incorporated the triggers
        // into its change description, so the recording command
        // doesn't need them again
        // if (!(command_p instanceof RecordingCommand)) {
        // Command triggerCommand = tx.getTriggers();
        //
        // if (triggerCommand != null) {
        // // replace the executed command by a compound of the
        // // original and the trigger commands
        // CompoundCommand compound = new ConditionalRedoCommand.Compound();
        // compound.append(mostRecentCommand);
        // compound.append(triggerCommand);
        // mostRecentCommand = compound;
        // commandList.set(top, mostRecentCommand);
        // }
        // }
      }
    }
  }

  /**
   * @see org.eclipse.emf.transaction.impl.AbstractTransactionalCommandStack#basicExecute(org.eclipse.emf.common.command.Command)
   */
  @Override
  protected void basicExecute(Command command_p) {
    // If the command is executable, execute it.
    if (null != command_p) {
      if (command_p.canExecute()) {
        try {
          command_p.execute();
        } catch (RuntimeException exception) {
          handleError(exception);
          command_p.dispose();
        }
        // Notify listeners if any.
        notifyListeners();
      } else {
        command_p.dispose();
      }
    }
  }
}
