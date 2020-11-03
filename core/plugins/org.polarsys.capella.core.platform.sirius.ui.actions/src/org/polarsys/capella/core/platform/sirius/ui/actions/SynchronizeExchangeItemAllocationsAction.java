/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.ExchangeItemAllocationOnPortsCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.ExchangeItemAllocationOnPortsCommand.Mode;

/**
 * Set the EIs on a port exactly to those of the selected exchange
 */
public class SynchronizeExchangeItemAllocationsAction extends AbstractFixAction {
  @Override
  protected ICommand createCommand(IProgressMonitor progressMonitor) {
    return new ExchangeItemAllocationOnPortsCommand(getSelectedElements(), progressMonitor, Mode.SYNCHRONIZE);
  }
}