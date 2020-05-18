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

/**
 * Base class to implement {@link ICommand}.
 */
public abstract class AbstractCommand implements ICommand {

	/**
	 * true if command has been interrupted
	 */
	protected boolean isInterrupted = false;
	/**
	 * true if command has been rolled back
	 */	
  protected boolean isRolledBack = false;
	
  @Override
  public void commandInterrupted() {
    isInterrupted = true;
  }

  @Override
  public void commandRolledBack() {
    isRolledBack = true;
  }
  
  /**
   * @return true if command has been interrupted
   */
  public boolean isInterrupted() {
  	return isInterrupted;
  }
  
  /**
   * @return true if command has been rolled back
   */
  public boolean isRolledBack() {
  	return isRolledBack;
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
