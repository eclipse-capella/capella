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
package org.polarsys.capella.common.ef.command;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.transaction.TransactionalCommandStack;

/**
 * A command that is modifying or reading model objects, and thus need to be executed in the correct environment.
 */
public interface ICommand extends Runnable {
  /**
   * Is the command only reading model objects or is it modifying any of them ?
   * @return <code>true</code> if read only, <code>false</code> if any model object is modified by the command.
   */
  boolean isReadOnly();

  /**
   * Command has been interrupted.<br>
   * Implementation is free to take any action that seems necessary.
   */
  void commandInterrupted();

  /**
   * Command has been rolled back.<br>
   * Implementation is free to take any action that seems necessary.
   */
  void commandRolledBack();

  /**
   * Get the execution options map.<br>
   * {@link TransactionalCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}.
   * @return can be <code>null</code> if default options are acceptable.
   */
  @SuppressWarnings("unchecked")
  Map getExecutionOptions();

  /**
   * Get readable command name.
   * @return
   */
  String getName();

  /**
   * Returns the collection of things which this command wishes to present as the objects affected by the command. Typically should could be used as the
   * selection that should be highlighted to best illustrate the effect of the command. The result of calling this before an <code>execute</code>,
   * <code>redo</code>, or <code>undo</code> is undefined. The result may be different after an <code>undo</code> than it is after an
   * <code>execute</code> or <code>redo</code>, but the result should be the same (equivalent) after either an <code>execute</code> or <code>redo</code>.
   * @return the collection of things which this command wishes to present as the objects affected by the command.
   */
  Collection<?> getAffectedObjects();
}
