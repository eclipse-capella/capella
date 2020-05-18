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

import org.eclipse.emf.common.command.AbstractCommand;


/**
 * A read/write command that acts as not having changed the model as far as the command stack is concerned.<br>
 * Only works with {@link TigCommandStack}.<br>
 * For any other command stack, this command acts as an {@link AbstractReadWriteCommand}.
 */
public abstract class AbstractNonDirtyingCommand extends AbstractReadWriteCommand implements AbstractCommand.NonDirtying {
  // Nothing to do.
}
