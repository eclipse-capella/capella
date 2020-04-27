/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.ui.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.re.ui.handlers.uihead.UIHeadHandler;
import org.polarsys.capella.core.re.commands.CreateReplicaCommand;
import org.polarsys.capella.core.re.handlers.CreateReplicaHandler;
import org.polarsys.capella.core.transition.common.commands.DefaultCommand;

public class CreateReplicaUiHandler extends CreateReplicaHandler {

  @Override
  protected ICommand createCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
    DefaultCommand command = new CreateReplicaCommand(selection, progressMonitor);
    command.addParameters(new UIHeadHandler(false));
    return command;
  }

}
