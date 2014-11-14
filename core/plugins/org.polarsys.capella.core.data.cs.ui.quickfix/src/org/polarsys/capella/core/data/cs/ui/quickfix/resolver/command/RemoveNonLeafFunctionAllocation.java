/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

public class RemoveNonLeafFunctionAllocation extends AbstractReadWriteCommand {

  private Component element;

  public RemoveNonLeafFunctionAllocation(Component element_p) {
    element = element_p;
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
      CapellaDeleteCommand command = new CapellaDeleteCommand(MDEAdapterFactory.getExecutionManager(), linksToRemove, false, true, true);
      if (command.canExecute()) {
        command.execute();
      }
    }
  }
}
