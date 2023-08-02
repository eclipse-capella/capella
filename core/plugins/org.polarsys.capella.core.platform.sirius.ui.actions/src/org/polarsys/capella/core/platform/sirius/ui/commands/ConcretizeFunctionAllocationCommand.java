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
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;

/**
 * Command for the ConcretizeFunctionAllocation accelerator
 * 
 * @author ebausson
 */
public class ConcretizeFunctionAllocationCommand extends AbstractFixCommand {

  public ConcretizeFunctionAllocationCommand(Collection<ModelElement> selection) {
    super(selection);
  }

  @Override
  protected void process(ModelElement element) {
    if ((element instanceof LogicalFunction) || (element instanceof SystemFunction) || (element instanceof PhysicalFunction)) {
      AbstractFunction motherFunction = (AbstractFunction) element;
      Component component = resolveMotherFunctionAllocation(motherFunction);
      if (component != null) {
        createAlloction(component, motherFunction);
      }
    }
  }

  private Component resolveMotherFunctionAllocation(AbstractFunction motherFunction) {
    List<Component> motherFunctionAllocation = new ArrayList<Component>();
    // In case the function is a leaf, there are already
    // two queries that do the job:
    // - Function Actor Allocation
    // - Function Component Allocation
    if (!FunctionExt.isLeaf(motherFunction)) {
      // Check block allocation
      EList<AbstractFunctionalBlock> blockAllocations = motherFunction.getAllocationBlocks();

      // If mother is already allocated, there are already queries
      // that do the job so only get the leaves allocation in case the
      // mother is not already allocated
      if ((null == blockAllocations) || blockAllocations.isEmpty()) {
        motherFunctionAllocation.addAll(AbstractFunctionExt.getMotherFunctionAllocation(motherFunction));
        if (motherFunctionAllocation.size() == 1) {
          return AbstractFunctionExt.getMotherFunctionAllocation(motherFunction).get(0);
        }
      }
    }
    return null;
  }

  private void createAlloction(Component component, AbstractFunction function) {
    ComponentFunctionalAllocation allocation = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
    if (component != null) {
      allocation.setSourceElement(component);
      allocation.setTargetElement(function);
      component.getOwnedFunctionalAllocation().add(allocation);
    }
  }

}
