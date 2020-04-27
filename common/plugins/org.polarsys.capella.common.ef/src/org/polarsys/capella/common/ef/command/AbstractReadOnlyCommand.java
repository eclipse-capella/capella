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
package org.polarsys.capella.common.ef.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.transaction.RunnableWithResult;

/**
 * A command that accesses model objects in read-only mode.<br>
 * Also allows to set a result through the {@link #setResult(Object)} method in the {@link #run()} method implementation.<br>
 * This result can then be read at the end of the call, using the {@link #getResult()} method.
 */
public abstract class AbstractReadOnlyCommand extends RunnableWithResult.Impl implements ICommand {
  /**
   * @see org.polarsys.capella.common.ef.command.ICommand#isReadOnly()
   */
  public boolean isReadOnly() {
    return true;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.ICommand#commandInterrupted()
   */
  public void commandInterrupted() {
    // Default implementation does nothing.
  }

  /**
   * @see org.polarsys.capella.common.ef.command.ICommand#commandRolledBack()
   */
  public void commandRolledBack() {
    // Default implementation does nothing.
  }

  /**
   * @see org.polarsys.capella.common.ef.command.ICommand#getExecutionOptions()
   */
  @SuppressWarnings("unchecked")
  public Map getExecutionOptions() {
    return Collections.emptyMap();
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * @see org.polarsys.capella.common.ef.command.ICommand#getName()
   */
  public String getName() {
    return getClass().getSimpleName();
  }

  /**
   * Not applicable for read only command.
   * @return an empty list.
   */
  final public Collection<?> getAffectedObjects() {
    return Collections.EMPTY_LIST;
  }
}
