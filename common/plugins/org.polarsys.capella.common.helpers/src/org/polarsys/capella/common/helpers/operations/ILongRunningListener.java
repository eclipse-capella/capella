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

package org.polarsys.capella.common.helpers.operations;

/**
 * A long running operations listener, that allows reactions to different steps.<br>
 * See associated extension point definition for contributing listeners.
 */
public interface ILongRunningListener {
  /**
   * Is given listener handling specified long running operation type events ?
   * @param longRunningOperationClass <code>true</code> to handle events for this class, <code>false</code> to be ignored.
   */
  public boolean isListenerFor(Class<?> longRunningOperationClass);

  /**
   * A long running operation is about to start/starting.
   * @param operationClass The operation type.
   */
  public void operationStarting(Class<?> operationClass);

  /**
   * A long running operation has been aborted/canceled/roll-backed/...
   * @param operationClass The operation type.
   */
  public void operationAborted(Class<?> operationClass);

  /**
   * A long running operation has just finished its execution.
   * @param operationClass The operation type.
   */
  public void operationEnded(Class<?> operationClass);
}
