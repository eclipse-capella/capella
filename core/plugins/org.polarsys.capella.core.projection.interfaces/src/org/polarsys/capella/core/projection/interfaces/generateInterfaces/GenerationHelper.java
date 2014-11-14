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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.Collection;
import java.util.HashSet;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class GenerationHelper {

  /**
   * Returns all exchange items which will be allocated into the created interface
   */
  public static Collection<ExchangeItem> getExchangeItems(ComponentExchange connection_p) {
    Collection<ExchangeItem> items = new HashSet<ExchangeItem>();

    for (AbstractExchangeItem exchangeItem : connection_p.getConvoyedInformations()) {
      items.add((ExchangeItem) exchangeItem);
    }

    for (ComponentExchangeFunctionalExchangeAllocation allocation : connection_p.getOutgoingComponentExchangeFunctionalExchangeAllocations()) {
      FunctionalExchange exchange = allocation.getAllocatedFunctionalExchange();
      items.addAll(exchange.getExchangedItems());
    }
    
    return items;
  }
  
  /**
   * Returns all exchange items which will be allocated into the created interface
   */
  public static  Collection<ExchangeItem> getExchangeItems(FunctionalExchange connection_p) {
    Collection<ExchangeItem> items = new HashSet<ExchangeItem>();
    items.addAll(connection_p.getExchangedItems());
    return items;
  }

  
}
