/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.allocators;

import java.util.Arrays;
import java.util.Collection;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;

/**
 * A helper to handle allocation of Functions to Components.
 * An allocator for one or several functions is obtained via the static factory
 * methods
 * <ul>
 * <li>{@link #allocate(Collection)}
 * <li>{@link #allocate(AbstractFunction)}
 * </ul>
 * Then, the items of the allocator are allocated via the 'on' method.
 *
 *<pre>
 *   allocate(lf).on(lc)
 * </pre>
 *
 * Note that functions that are already allocated are ignored and no
 * other validation of compatibility between component and function
 * is performed, i.e. you can allocate a Logical Function on a System
 * if you want, but you don't want that.
 *
 *
 */
public class FunctionAllocator {

  private final Collection<? extends AbstractFunction> allocations;

  FunctionAllocator(Collection<? extends AbstractFunction> eia){
    allocations = eia;
  }

  public FunctionAllocator on(Component c){
    for (AbstractFunction f : allocations){
      if (f.getAllocationBlocks().isEmpty()){
        ComponentFunctionalAllocation cfa = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
        cfa.setSourceElement(c);
        cfa.setTargetElement(f);
        c.getOwnedFunctionalAllocation().add(cfa);
      }
    }
    return this;
  }

  public static FunctionAllocator allocate(Collection<? extends AbstractFunction> functs){
    return new FunctionAllocator(functs);
  }

  public static FunctionAllocator allocate(AbstractFunction...abstractFunctions) {
    return new FunctionAllocator(Arrays.asList(abstractFunctions));
  }

}