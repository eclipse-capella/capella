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
   * Set editing domain provider to use.
   * @param editingDomainProvider_p A not <code>null</code> editing domain instance.
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
