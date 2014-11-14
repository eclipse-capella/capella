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
package org.polarsys.capella.common.helpers.operations;

/**
 * A long running operations listener, that allows reactions to different steps.<br>
 * See associated extension point definition for contributing listeners.
 */
public interface ILongRunningListener {
  /**
   * Is given listener handling specified long running operation type events ?
   * @param longRunningOperationClass_p <code>true</code> to handle events for this class, <code>false</code> to be ignored.
   */
  public boolean isListenerFor(Class<?> longRunningOperationClass_p);

  /**
   * A long running operation is about to start/starting.
   * @param operation_p The operation type.
   */
  public void operationStarting(Class<?> operationClass_p);

  /**
   * A long running operation has been aborted/canceled/roll-backed/...
   * @param operation_p The operation type.
   */
  public void operationAborted(Class<?> operationClass_p);

  /**
   * A long running operation has just finished its execution.
   * @param operationClass_p The operation type.
   */
  public void operationEnded(Class<?> operationClass_p);
}
