/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import java.util.Collections;

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.PortAllocation;

public class FunctionPortAllocator {

  private final Collection<? extends FunctionPort> allocations;
  
  FunctionPortAllocator(Collection<? extends FunctionPort> eia){
    allocations = eia;
  }

  public FunctionPortAllocator on(ComponentPort cp) {
    for (FunctionPort fxp : allocations){
      if (!cp.getAllocatedFunctionPorts().contains(fxp)) { 
        PortAllocation pa = InformationFactory.eINSTANCE.createPortAllocation();
        cp.getOwnedPortAllocations().add(pa);
        pa.setSourceElement(cp);
        pa.setTargetElement(fxp);
      }
    }
    return this;
  }

  public static FunctionPortAllocator allocate(FunctionPort f){
    return allocate(Collections.singleton(f));
  }

  public static FunctionPortAllocator allocate(Collection<? extends FunctionPort> functions){
    return new FunctionPortAllocator(functions);
  }

  public static FunctionPortAllocator allocate(FunctionPort[] functionPorts) {
    return new FunctionPortAllocator(Arrays.asList(functionPorts));
  }

}
