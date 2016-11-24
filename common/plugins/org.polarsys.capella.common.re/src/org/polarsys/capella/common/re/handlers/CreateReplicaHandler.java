/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  }

}
