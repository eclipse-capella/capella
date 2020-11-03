/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ef;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.ef.internal.command.AbstractNonDirtyingRecordingCommand;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Execution manager.<br>
 * Allows for the usage of model objects within the corresponding transactional editing domain.<br>
 */
public class ExecutionManager {
  /**
   * Editing domain provider.
   */
  private TransactionalEditingDomain _editingDomain;

  /**
   *
   */
  public ExecutionManager() {
    // Get editing domain.
    _editingDomain = createEditingDomain();
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
   * @param command
   */
  public void execute(ICommand command) {
    TransactionalEditingDomain editingDomain = getEditingDomain();
    // Preconditions.
    Assert.isNotNull(editingDomain);
    Assert.isNotNull(command);
    // Check whether command is read-only or read-write.
    if (command.isReadOnly()) {
      executeReadOnlyCommand(command, editingDomain);
    } else {
      executeReadWriteCommand(command, editingDomain);
    }
  }

  /**
   * Execute a command in read-only mode.
   * @param command
   * @param editingDomain
   */
  protected void executeReadOnlyCommand(ICommand command, TransactionalEditingDomain editingDomain) {
    try {
      editingDomain.runExclusive(command);
    } catch (InterruptedException ie) {
      command.commandInterrupted();
    }
  }

  /**
   * Execute a command in read-write mode.
   * @param command
   * @param editingDomain
   */
  protected void executeReadWriteCommand(final ICommand command, TransactionalEditingDomain editingDomain) {
    TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
    try {
      stack.execute(createRecordingCommand(command, editingDomain), command.getExecutionOptions());
    } catch (InterruptedException exception) {
      command.commandInterrupted();
    } catch (RollbackException exception) {
      command.commandRolledBack();
    }
  }

  /**
   * Create the recording command that should be executed.
   * @param command
   * @param editingDomain
   * @return
   */
  protected RecordingCommand createRecordingCommand(final ICommand command, TransactionalEditingDomain editingDomain) {
    RecordingCommand result = null;
    // Command is likely to change the model, but cheats the command stack not to do so.
    if (command instanceof NonDirtying) {
      result = new AbstractNonDirtyingRecordingCommand(editingDomain) {
        @Override
        protected void doExecute() {
          command.run();
        }

        /**
         * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
         */
        @Override
        public Collection<?> getAffectedObjects() {
          return command.getAffectedObjects();
        }
      };
    } else { // Command is not hiding from modifying the model.
      result = new RecordingCommand(editingDomain) {
        @Override
        protected void doExecute() {
          command.run();
        }

        /**
         * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
         */
        @Override
        public Collection<?> getAffectedObjects() {
          return command.getAffectedObjects();
        }
      };
    }
    result.setLabel(command.toString());
    return result;
  }

  /**
   * Capella Editing Domain id.<br>
   * Must not be used outside this plug-in. Please use instead, the {@link ExecutionManagerRegistry} to get an {@link ExecutionManager} that points this editing
   * domain.
   */
  static final String EDITING_DOMAIN_ID = "org.polarsys.capella.common.platform.sirius.ted.EditingDomain"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.common.ef.IEditingDomainProvider#getEditingDomain()
   */
  protected TransactionalEditingDomain createEditingDomain() {
    // Ask the EMF transactional registry the capella editing domain from its id.
    //return TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(EDITING_DOMAIN_ID);
	TransactionalEditingDomain.Factory factory = getEditingDomainFactory();
	if (null != factory) {
	  return factory.createEditingDomain();
	}
    return null;
  }

  public TransactionalEditingDomain.Factory getEditingDomainFactory() {
    IConfigurationElement configurationElement = ExtensionPointHelper.getConfigurationElement("org.eclipse.emf.transaction", "editingDomains", EDITING_DOMAIN_ID);
    if (null != configurationElement) {
      return (TransactionalEditingDomain.Factory) ExtensionPointHelper.createInstance(configurationElement, "factory");
    }
    return null;
  }
}
