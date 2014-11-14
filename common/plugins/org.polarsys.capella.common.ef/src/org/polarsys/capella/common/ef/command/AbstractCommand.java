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
package org.polarsys.capella.common.ef.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Base class to implement {@link ICommand}.
 */
public abstract class AbstractCommand implements ICommand {
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
   * Get readable command name.
   * @return
   */
  public String getName() {
    return getClass().getSimpleName();
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * Default behavior returns an empty list.
   * @return an empty list.
   */
  public Collection<?> getAffectedObjects() {
    return Collections.EMPTY_LIST;
  }

}
