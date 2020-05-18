/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.re.commands.CreateReplicaCommand;
import org.polarsys.capella.core.transition.common.commands.CommandHandler;

/**
 */
public class CreateReplicaHandler extends CommandHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected ICommand createCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
    return new CreateReplicaCommand(selection, progressMonitor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext) {
    super.setEnabled(evaluationContext);

    Collection<?> seleciton =  getInitialSelection(evaluationContext);
    if (seleciton.isEmpty()) {
      setBaseEnabled(false);
      return;
    }
    setBaseEnabled(true);
  }

}
