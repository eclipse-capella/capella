/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query returns all the allocated exchangeItems of current interfaces and all super Interfaces
 */
public class InterfaceExchangesItems implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {

    List<Object> result = new ArrayList<Object>();
    if (object instanceof Interface) {

      Interface itf = (Interface) object;
      EList<ExchangeItemAllocation> ownedExchangeItemAllocations = itf.getOwnedExchangeItemAllocations();
      for (ExchangeItemAllocation exchangeItemAllocation : ownedExchangeItemAllocations) {

        AbstractExchangeItem allocatedItem = exchangeItemAllocation.getAllocatedItem();
        if (null != allocatedItem && allocatedItem instanceof ExchangeItem) {
          result.add(allocatedItem);
        }
      }
    }

    return result;
  }
}
