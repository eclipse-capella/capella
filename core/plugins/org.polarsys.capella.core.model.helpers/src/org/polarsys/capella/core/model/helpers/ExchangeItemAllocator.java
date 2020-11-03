/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers;

import java.util.Collection;
import java.util.Collections;

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * A helper to handle allocation of exchange items to various kinds
 * of model elements. An allocator for an exchange item or several
 * exchange items is obtained via the static factory methods
 * <ul>
 * <li>{@link #allocate(Collection)}
 * <li>{@link #allocate(ExchangeItem)}
 * </ul>
 * Then, the items of the allocator are allocated via the various
 * 'on' methods. These methods can be chained, for example:
 * 
 * <pre>
 *   allocate(ei).on(functionalExchange1)
 *                           .on(functionalExchange2)
 *                           .on(componentExchange1)
 *                           .on(functionPort1);
 * </pre>
 * 
 * By default, when allocating on a functional exchange, the exchange items
 * are also allocated automagically on the connected function ports. This
 * can be turned off by using the two argument factory methods, passing <code>false</code>
 * as the second parameter.
 */
public class ExchangeItemAllocator {

  private final Collection<ExchangeItem> allocations;
  private final boolean propagateToFunctionPorts;

  private ExchangeItemAllocator(Collection<ExchangeItem> eia, boolean propagateToFunctionPorts){
    allocations = eia;
    this.propagateToFunctionPorts = propagateToFunctionPorts;
  }

  public ExchangeItemAllocator on(Interface i){
    if (i != null){
      for (ExchangeItem ei : allocations){
        if (!i.getExchangeItems().contains(ei)){
          ExchangeItemAllocation eia = CsFactory.eINSTANCE.createExchangeItemAllocation();
          eia.setAllocatedItem(ei);
          i.getOwnedExchangeItemAllocations().add(eia);
        }
      }
    }
    return this;
  }

  public ExchangeItemAllocator on(FunctionInputPort fip) {
    if (fip != null){
      for (ExchangeItem ei : allocations) {
        fip.getIncomingExchangeItems().add(ei);
      }
    }
    return this;
  }

  public ExchangeItemAllocator on(FunctionOutputPort fop){
    if (fop != null){
      for (ExchangeItem ei : allocations) {
        fop.getOutgoingExchangeItems().add(ei);
      }
    }
    return this;
  }

  public ExchangeItemAllocator on(FunctionalExchange fe){
    if (fe != null){
      for (ExchangeItem ei : allocations) {
        fe.getExchangedItems().add(ei);
      }
      if (propagateToFunctionPorts){
        on(fe.getSourceFunctionOutputPort());
        on(fe.getTargetFunctionInputPort());
      }
    }
    return this;
  }

  public ExchangeItemAllocator on(ComponentExchange ce){
    if (ce != null){
      for (ExchangeItem ei : allocations) {
        ce.getConvoyedInformations().add(ei);
      }
    }
    return this;
  }

  public ExchangeItemAllocator on(Collection<?> elements){
    for (Object e : elements){
      if (e instanceof FunctionalExchange) {
        on((FunctionalExchange) e);
      }
      else if (e instanceof ComponentExchange){
        on((ComponentExchange) e);
      }
      else if (e instanceof FunctionInputPort){
        on((FunctionInputPort) e);
      }
      else if (e instanceof FunctionOutputPort){
        on((FunctionOutputPort) e);
      }
      else if (e instanceof Interface){
        on((Interface) e);
      }
    }

    return this;
  }

  public static ExchangeItemAllocator allocate(ExchangeItem exchangeItem){
    return allocate(Collections.singleton(exchangeItem));
  }

  public static ExchangeItemAllocator allocate(Collection<ExchangeItem> exchangeItems){
    return allocate(exchangeItems, true);
  }

  public static ExchangeItemAllocator allocate(ExchangeItem exchangeItem, boolean propagateToFunctionPorts){
    return allocate(Collections.singleton(exchangeItem), propagateToFunctionPorts);
  }

  public static ExchangeItemAllocator allocate(Collection<ExchangeItem> exchangeItems, boolean propagateToFunctionPorts){
    return new ExchangeItemAllocator(exchangeItems, propagateToFunctionPorts);
  }

}