/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

public class RemoveNonLeafFunctionAllocation extends AbstractReadWriteCommand {

  private Component element;

  public RemoveNonLeafFunctionAllocation(Component element) {
    this.element = element;
  }

  @Override
  public String getName() {
    return "Remove all non leaf allocated functions"; //$NON-NLS-1$
  }

  public void run() {
    List<ComponentFunctionalAllocation> linksToRemove = new ArrayList<ComponentFunctionalAllocation>(1);
    EList<ComponentFunctionalAllocation> functionalAllocations = element.getFunctionalAllocations();
    // if no allocated exchanges found, no further check needed
    if (functionalAllocations.size() < 1) {
      return;
    }
    // collect invalid links to remove
    for (ComponentFunctionalAllocation functionalAllocation : functionalAllocations) {
      AbstractFunction function = functionalAllocation.getFunction();
      if (!FunctionExt.isLeaf(function)) {
        if (null != function) {
          linksToRemove.add(functionalAllocation);
        }
      }
    }

    // remove links
    if (!linksToRemove.isEmpty()) {
      CapellaDeleteCommand command = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(linksToRemove), linksToRemove, false, true, true);
      if (command.canExecute()) {
        command.execute();
      }
    }
  }
}
