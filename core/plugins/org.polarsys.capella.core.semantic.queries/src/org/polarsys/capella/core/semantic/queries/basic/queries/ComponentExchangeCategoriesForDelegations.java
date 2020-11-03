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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return categories linked to exchangeItem
 * 
 */
public class ComponentExchangeCategoriesForDelegations implements IQuery {

  /**
   *  default
   */
  public ComponentExchangeCategoriesForDelegations() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();

    if (object instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) object;
      if (ComponentExchangeExt.isDelegation(exchange)) {
        if (exchange.getCategories().isEmpty()) {
          Port sourcePort = ComponentExchangeExt.getSourcePort(exchange);
          if (sourcePort instanceof ComponentPort) {
            for (ComponentExchange related : ((ComponentPort) sourcePort).getComponentExchanges()) {
              if (!ComponentExchangeExt.isDelegation(related)) {
                for (ComponentExchangeCategory category : related.getCategories()) {
                  if (!result.contains(category)) {
                    result.add(category);
                  }
                }
              }
            }
          }
        }
      }
    }
    return result;
  }
}
