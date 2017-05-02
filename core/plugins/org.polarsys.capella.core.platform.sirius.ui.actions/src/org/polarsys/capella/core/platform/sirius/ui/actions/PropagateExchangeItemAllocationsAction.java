/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
   * Adds all EIs from the selected exchange(s) to the associated ports
   */
  public class PropagateExchangeItemAllocationsAction extends AbstractFixAction {
   @Override
   protected ICommand createCommand(IProgressMonitor progressMonitor) {
     return new ExchangeItemAllocationOnPortsCommand(getSelectedElements(), progressMonitor, Mode.PROPAGATE);
   }
 }