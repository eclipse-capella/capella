/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

/**
 * DWF_DC_12 - Function must be allocated to only one component, including actor
 */
public class DWF_DC_12_Resolver extends AbstractSelectOneResolver {

  public DWF_DC_12_Resolver() {
  }

  protected void openSelectionDialog(EObject semanticElement, IMarker marker) {
    List<Component> allocatingComponents = new ArrayList<Component>();
    for (ComponentFunctionalAllocation cfa : ((AbstractFunction) semanticElement).getComponentFunctionalAllocations()) {
      Component comp = (Component) cfa.getSourceElement();
      allocatingComponents.add(comp);
    }
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        SelectElementsDialog dialog = new SelectElementsDialog(activeShell, "Function Allocation",
            "Please select a single allocating Component\nOther allocations will be removed", allocatingComponents);
        if (dialog.open() == Dialog.OK) {
          Component result = (Component) dialog.getResult().get(0);
          allocatingComponents.remove(result);

          List<ComponentFunctionalAllocation> cfaToBeDeleted = new ArrayList<ComponentFunctionalAllocation>();
          for (Component toBeDeallocated : allocatingComponents) {
            for (AbstractTrace outgoing : toBeDeallocated.getOutgoingTraces()) {
              if (outgoing instanceof ComponentFunctionalAllocation) {
                ComponentFunctionalAllocation cfa = (ComponentFunctionalAllocation) outgoing;
                if (cfa.getFunction() == semanticElement) {
                  cfaToBeDeleted.add(cfa);
                }
              }
            }
          }
          boolean confirmDeletion = CapellaDeleteCommand
              .confirmDeletion(TransactionHelper.getExecutionManager(semanticElement), cfaToBeDeleted);
          if (confirmDeletion) {
            CapellaDeleteCommand command = new CapellaDeleteCommand(
                TransactionHelper.getExecutionManager(semanticElement), cfaToBeDeleted, false, false, true);
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
    return obj != null && obj instanceof AbstractFunction;
  }
}
