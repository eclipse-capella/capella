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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

public class RemoveInvalidFunctionalExchangeAllocations extends AbstractReadWriteCommand {

  private ComponentExchange exchange;

  public RemoveInvalidFunctionalExchangeAllocations(ComponentExchange exchange) {
    this.exchange = exchange;
  }

  @Override
  public String getName() {
    return "Remove invalid functional exchange allocations"; //$NON-NLS-1$
  }

  public void run() {
    final List<ComponentExchangeFunctionalExchangeAllocation> linksToRemove = new ArrayList<ComponentExchangeFunctionalExchangeAllocation>(1);
    EList<FunctionalExchange> allocatedExchanges = exchange.getAllocatedFunctionalExchanges();
    // if no allocated exchanges found, no further check needed
    if (allocatedExchanges.size() < 1) {
      return;
    }
    EList<ComponentExchangeFunctionalExchangeAllocation> links = exchange.getOwnedComponentExchangeFunctionalExchangeAllocations();
    List<CapellaElement> availableExhcnage = ComponentExchangeExt.getValidFEAvailableForAllocation(exchange);
    // collect invalid links to remove
    if ((null != availableExhcnage) && (null != allocatedExchanges)) {
      for (FunctionalExchange allocatedExchange : allocatedExchanges) {
        if (!availableExhcnage.contains(allocatedExchange)) {
          for (ComponentExchangeFunctionalExchangeAllocation link : links) {
            FunctionalExchange targetExchange = link.getAllocatedFunctionalExchange();
            if ((null != allocatedExchange) && allocatedExchange.equals(targetExchange)) {
              linksToRemove.add(link);
            }
          }
        }
      }
    }

    final boolean flag[] = { false };
    final ExecutionManager em = TransactionHelper.getExecutionManager(linksToRemove);
    AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
      public void run() {
        // remove component allocation or activity allocation
        if (!linksToRemove.isEmpty()) {
          // execute the command
          boolean confirmDeletion = CapellaDeleteCommand.confirmDeletion(em, linksToRemove);
          if (confirmDeletion) {
            CapellaDeleteCommand command = new CapellaDeleteCommand(em, linksToRemove, false, false, true);
            if (command.canExecute()) {
              command.execute();
              // flag element deletion
              flag[0] = true;
            }
          }
        }
      }
    };
    em.execute(abstrctCommand);
  }
}
