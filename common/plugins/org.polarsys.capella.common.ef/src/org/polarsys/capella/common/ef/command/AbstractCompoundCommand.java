/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * Base class to implement a composite command based on {@link ICommand} interface.
 */
public abstract class AbstractCompoundCommand extends AbstractCommand {
  /**
   * Contained commands.
   */
  private List<ICommand> _commands;

  /**
   * Constructor.
   */
  protected AbstractCompoundCommand() {
    this(new ArrayList<ICommand>(0));
  }

  /**
   * Constructor.
   * @param commands used to initialize this compound command.
   */
  protected AbstractCompoundCommand(List<ICommand> commands) {
    // Precondition.
    Assert.isNotNull(commands);
    _commands = commands;
  }

  /**
   * Append specified command to this compound command's list of commands.
   * @param command the added command.
   */
  public void append(ICommand command) {
    _commands.add(command);
  }

  /**
   * Prepend specified command to this compound command's list of commands.
   * @param command the added command.
   */
  public void prepend(ICommand command) {
    _commands.add(0, command);
  }

  /**
   * Insert specified command at the specified index in this compound command's list of commands.
   * @param index index at which the specified command is to be inserted.
   * @param command
   * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size()).
   */
  public void insert(int index, ICommand command) {
    _commands.add(index, command);
  }

  /**
   * Return the number of contained commands in this compound one.
   * @return
   */
  public int getContainedCommandsSize() {
    return _commands.size();
  }

  /**
   * Return an unmodifiable view of contained commands.
   * @return an unmodifiable and not <code>null</code> list of {@link ICommand}.
   */
  public List<ICommand> getContainedCommands() {
    return Collections.unmodifiableList(_commands);
  }

  /**
   * @see org.polarsys.capella.common.ef.command.ICommand#isReadOnly()
   */
  public boolean isReadOnly() {
    boolean isReadOnly = true;
    // Iterate over contained commands to search one that is not a read only one.
    Iterator<ICommand> commands = _commands.iterator();
    while (isReadOnly && commands.hasNext()) {
      ICommand command = commands.next();
      isReadOnly = command.isReadOnly();
    }
    return isReadOnly;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Iterate over contained commands to run each contained command.
    for (ICommand command : _commands) {
      doRunContainedCommand(command);
    }
  }

  /**
   * Allow sub-classes to handle the execution of each contained command in this compound one.<br>
   * Default behavior simply runs specified contained command.
   * @param aContainedCommand
   */
  protected void doRunContainedCommand(ICommand aContainedCommand) {
    aContainedCommand.run();
  }
}
