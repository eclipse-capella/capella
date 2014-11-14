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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public abstract class Component_FunctionalAllocation implements IBusinessQuery {
  /**
   * <p>
   * Gets available functions to be allocated to the component
   * </p>
   * @param element_p the component
   * @return list of Function
   */
  protected List<CapellaElement> getRule_MQRY_Component_FunctionalAllocation_11(BlockArchitecture arch_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    List<AbstractFunction> allLeafFunctions = FunctionExt.getAllLeafAbstractFunctions(arch_p);

    // remove functions if already allocated by another Component
    //
    List<AbstractFunction> listTORemove = new ArrayList<AbstractFunction>();
    for (AbstractFunction function : allLeafFunctions) {
      if (!function.getAllocationBlocks().isEmpty()) {
        listTORemove.add(function);
      }
    }
    allLeafFunctions.removeAll(listTORemove);

    // final result
    availableElements.addAll(allLeafFunctions);

    return availableElements;
  }
}
