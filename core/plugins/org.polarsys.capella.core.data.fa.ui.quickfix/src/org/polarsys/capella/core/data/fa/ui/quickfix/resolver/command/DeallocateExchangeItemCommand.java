/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

/**
 * Deallocates a single {@link ExchangeItem} from a collection of target objects. Supported target types are
 * <ul>
 * <li>{@link FunctionalExchange}
 * <li>{@link ComponentExchange}
 * <li>{@link Interface}
 * </ul>
 * For FunctionalExchange/ComponentExchange, the command removes the exchange item from
 * {@link FunctionalExchange#getExchangedItems() exchangedItems}/ {@link ComponentExchange#getConvoyedInformations()
 * convoyedInformations}. For Interfaces, matching {@link ExchangeItemAllocation} objects are deleted, so this will open
 * the capella delete dialog for confirmation.
 */
public class DeallocateExchangeItemCommand extends RecordingCommand {

  protected boolean cancelled = false;
  final ExchangeItem exchangeItem;
  final Collection<? extends EObject> targets;
  protected final ExecutionManager manager;

  public DeallocateExchangeItemCommand(TransactionalEditingDomain domain, ExchangeItem exchangeItem,
      Collection<? extends EObject> targets) {
    super(domain);
    manager = ExecutionManagerRegistry.getInstance().getExecutionManager(domain);
    this.targets = targets;
    this.exchangeItem = exchangeItem;
  }

  @Override
  protected void doExecute() {

    Collection<ExchangeItemAllocation> allocationsToDelete = new ArrayList<ExchangeItemAllocation>();

    for (EObject e : targets) {
      if (e instanceof FunctionalExchange) {
        ((FunctionalExchange) e).getExchangedItems().remove(exchangeItem);
      } else if (e instanceof ComponentExchange) {
        ((ComponentExchange) e).getConvoyedInformations().remove(exchangeItem);
      } else if (e instanceof Interface) {
        for (EObject allocation : EObjectExt.getReferencers(exchangeItem,
            CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM)) {
          if (((ExchangeItemAllocation) allocation).getAllocatingInterface() == e) {
            allocationsToDelete.add((ExchangeItemAllocation) allocation);
          }
        }
      }
    }
    deleteAllocations(allocationsToDelete);
  }

  protected void deleteAllocations(Collection<ExchangeItemAllocation> allocationsToDelete) {
    if (allocationsToDelete.isEmpty()) {
      return;
    }
    boolean confirmDeletion = CapellaDeleteCommand.confirmDeletion(manager, allocationsToDelete);
    if (confirmDeletion) {
      CapellaDeleteCommand command = new CapellaDeleteCommand(manager, allocationsToDelete, false, false, true);
      if (command.canExecute()) {
        command.execute();
      } else {
        cancelled = true;
        throw new OperationCanceledException();
      }
    } else {
      cancelled = true;
      throw new OperationCanceledException();
    }
  }

  @Override
  public Collection<?> getResult() {
    if (cancelled) {
      return Collections.singleton(Status.CANCEL_STATUS);
    }
    return super.getResult();
  }
}