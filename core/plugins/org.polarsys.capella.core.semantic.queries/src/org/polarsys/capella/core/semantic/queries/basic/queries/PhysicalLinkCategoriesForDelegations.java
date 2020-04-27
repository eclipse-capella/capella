/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return categories linked to exchangeItem
 * 
 */
public class PhysicalLinkCategoriesForDelegations implements IQuery {

  /**
   *  default
   */
  public PhysicalLinkCategoriesForDelegations() {
    // Does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();

    if (object instanceof PhysicalLink) {
      PhysicalLink exchange = (PhysicalLink) object;
      if (PhysicalLinkExt.isDelegation(exchange)) {
        if (exchange.getCategories().isEmpty()) {
          Port sourcePort = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourcePort(exchange);
          if (sourcePort instanceof PhysicalPort) {
            for (PhysicalLink related : ((PhysicalPort) sourcePort).getInvolvedLinks()) {
              if (!PhysicalLinkExt.isDelegation(related)) {
                for (PhysicalLinkCategory category : related.getCategories()) {
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
