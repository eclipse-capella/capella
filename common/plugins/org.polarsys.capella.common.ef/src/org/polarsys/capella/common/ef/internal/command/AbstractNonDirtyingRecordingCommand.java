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

package org.polarsys.capella.common.ef.internal.command;

import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * A recording command that changes the model, but act as not changing it (as far as the command stack is concerned).<br>
 * Typically used in the case of modifying the model for adaptation purposes (e.g. responding to a get by a local set, ...).
 */
public abstract class AbstractNonDirtyingRecordingCommand extends RecordingCommand implements NonDirtying {
  /**
   * Constructor.
   * @param domain
   */
  public AbstractNonDirtyingRecordingCommand(TransactionalEditingDomain domain) {
    super(domain);
  }

  /**
   * Constructor.
   * @param domain
   * @param label
   */
  public AbstractNonDirtyingRecordingCommand(TransactionalEditingDomain domain, String label) {
    super(domain, label);
  }

  /**
   * Constructor.
   * @param domain
   * @param label
   * @param description
   */
  public AbstractNonDirtyingRecordingCommand(TransactionalEditingDomain domain, String label, String description) {
    super(domain, label, description);
  }
}
