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
package org.polarsys.capella.common.tig.ef;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.polarsys.capella.common.tig.common.Contributor;
import org.polarsys.capella.common.tig.common.IContributor;
import org.polarsys.capella.common.tig.ef.command.ICommand;
import org.polarsys.capella.common.tig.ef.internal.command.AbstractNonDirtyingRecordingCommand;

/**
 * Execution manager.<br>
 * Allows for the usage of model objects within the corresponding transactional editing domain.<br>
 */
public class ExecutionManager implements IContributor {
  /**
   * Editing domain provider.
   */
  private TransactionalEditingDomain _editingDomain;
  /**
   * Contribution attributes.
   */
  private IContributor _contributor;

  /**
   * Set editing domain provider to use.
   * @param editingDomainProvider_p A not <code>null</code> editing domain instance.
   */
  public ExecutionManager(IEditingDomainProvider editingDomainProvider_p) {
    // Precondition.
    Assert.isNotNull(editingDomainProvider_p);
    // New contributor.
    _contributor = new Contributor();
    // Get editing domain.
    _editingDomain = editingDomainProvider_p.getEditingDomain();
  }

  /**
   * Get in-use editing domain.
   * @return
   */
  public TransactionalEditingDomain getEditingDomain() {
    return _editingDomain;
  }

  /**
   * Execute given command in associated editing domain.
   * @param command_p
   */
  public void execute(ICommand command_p) {
    TransactionalEditingDomain editingDomain = getEditingDomain();
    // Preconditions.
    Assert.isNotNull(editingDomain);
    Assert.isNotNull(command_p);
    // Check whether command is read-only or read-write.
    if (command_p.isReadOnly()) {
      executeReadOnlyCommand(command_p, editingDomain);
    } else {
      executeReadWriteCommand(command_p, editingDomain);
    }
  }

  /**
   * Execute a command in read-only mode.
   * @param command_p
   * @param editingDomain_p
   */
  protected void executeReadOnlyCommand(ICommand command_p, TransactionalEditingDomain editingDomain_p) {
    try {
      editingDomain_p.runExclusive(command_p);
    } catch (InterruptedException ie_p) {
      command_p.commandInterrupted();
    }
  }

  /**
   * Execute a command in read-write mode.
   * @param command_p
   * @param editingDomain_p
   */
  protected void executeReadWriteCommand(final ICommand command_p, TransactionalEditingDomain editingDomain_p) {
    TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain_p.getCommandStack();
    try {
      stack.execute(createRecordingCommand(command_p, editingDomain_p), command_p.getExecutionOptions());
    } catch (InterruptedException exception_p) {
      command_p.commandInterrupted();
    } catch (RollbackException exception_p) {
      command_p.commandRolledBack();
    }
  }

  /**
   * Create the recording command that should be executed.
   * @param command_p
   * @param editingDomain_p
   * @return
   */
  protected RecordingCommand createRecordingCommand(final ICommand command_p, TransactionalEditingDomain editingDomain_p) {
    RecordingCommand result = null;
    // Command is likely to change the model, but cheats the command stack not to do so.
    if (command_p instanceof NonDirtying) {
      result = new AbstractNonDirtyingRecordingCommand(editingDomain_p) {
        @Override
        protected void doExecute() {
          command_p.run();
        }

        /**
         * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
         */
        @Override
        public Collection<?> getAffectedObjects() {
          return command_p.getAffectedObjects();
        }
      };
    } else { // Command is not hiding from modifying the model.
      result = new RecordingCommand(editingDomain_p) {
        @Override
        protected void doExecute() {
          command_p.run();
        }

        /**
         * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
         */
        @Override
        public Collection<?> getAffectedObjects() {
          return command_p.getAffectedObjects();
        }
      };
    }
    result.setLabel(command_p.toString());
    return result;
  }

  /**
   * @see org.polarsys.capella.common.tig.common.IContributor#getId()
   */
  public String getId() {
    return _contributor.getId();
  }

  /**
   * @see org.polarsys.capella.common.tig.common.IContributor#setId(java.lang.String)
   */
  public void setId(String id_p) {
    _contributor.setId(id_p);
  }
}
