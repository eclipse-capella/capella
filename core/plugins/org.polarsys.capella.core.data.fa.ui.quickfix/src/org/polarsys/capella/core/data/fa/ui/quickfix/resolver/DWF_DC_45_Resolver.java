/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

/**
 * DWF_DC_45 - SequenceLink with no associated FunctionalChainInvolvementLinks
 */
public class DWF_DC_45_Resolver extends AbstractSelectOneResolver {
  public DWF_DC_45_Resolver() {
  }


  protected void openSelectionDialog(EObject semanticElement, IMarker marker) {
    List<PhysicalLink> allocatingPhysicalLinks = new ArrayList<PhysicalLink>(
        ((ComponentExchange) semanticElement).getAllocatorPhysicalLinks());

    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        SelectElementsDialog dialog = new SelectElementsDialog(activeShell, "Component Exchange Allocation",
            "Please select a single Physical Link to allocate\nOthers will be deallocated", allocatingPhysicalLinks);
        if (dialog.open() == Dialog.OK) {
          PhysicalLink result = (PhysicalLink) dialog.getResult().get(0);
          allocatingPhysicalLinks.remove(result);

          List<ComponentExchangeAllocation> ceaToBeDeleted = new ArrayList<ComponentExchangeAllocation>();
          for (PhysicalLink toBeDeallocated : allocatingPhysicalLinks) {
            for (AbstractTrace outgoing : toBeDeallocated.getOutgoingTraces()) {
              if (outgoing instanceof ComponentExchangeAllocation) {
                ComponentExchangeAllocation cea = (ComponentExchangeAllocation) outgoing;
                if (cea.getComponentExchangeAllocated() == semanticElement) {
                  ceaToBeDeleted.add(cea);
                }
              }
            }
          }
          boolean confirmDeletion = CapellaDeleteCommand
              .confirmDeletion(TransactionHelper.getExecutionManager(semanticElement), ceaToBeDeleted);
          if (confirmDeletion) {
            CapellaDeleteCommand command = new CapellaDeleteCommand(
                TransactionHelper.getExecutionManager(semanticElement), ceaToBeDeleted, false, false, true);
            if (command.canExecute()) {
              command.execute();
              deleteMarker(marker);
            }
          }
        }
      }
    };
    TransactionHelper.getExecutionManager(semanticElement).execute(command);
  }

  @Override
  protected boolean isAvailableFor(EObject obj) {
    return (obj != null && obj instanceof ComponentExchange);
  }

}
