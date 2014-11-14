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
   * @param domain_p
   */
  public AbstractNonDirtyingRecordingCommand(TransactionalEditingDomain domain_p) {
    super(domain_p);
  }

  /**
   * Constructor.
   * @param domain_p
   * @param label_p
   */
  public AbstractNonDirtyingRecordingCommand(TransactionalEditingDomain domain_p, String label_p) {
    super(domain_p, label_p);
  }

  /**
   * Constructor.
   * @param domain_p
   * @param label_p
   * @param description_p
   */
  public AbstractNonDirtyingRecordingCommand(TransactionalEditingDomain domain_p, String label_p, String description_p) {
    super(domain_p, label_p, description_p);
  }
}
