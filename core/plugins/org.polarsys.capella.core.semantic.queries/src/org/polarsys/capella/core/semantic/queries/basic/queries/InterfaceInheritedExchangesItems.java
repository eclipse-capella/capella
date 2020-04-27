/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.ExchangeItem;

public class InterfaceInheritedExchangesItems implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Interface) {
      Interface itf = (Interface) object;
      // list to collect all the super interfaces + current
      List<GeneralizableElement> interfaces = new ArrayList<GeneralizableElement>();

      // add all super interfaces of current interfaces
      List<GeneralizableElement> allSuperGenElts = GeneralizableElementExt.getAllSuperGeneralizableElements(itf);
      if (!allSuperGenElts.isEmpty()) {
        interfaces.addAll(allSuperGenElts);
      }

      // get related exchageItems
      for (GeneralizableElement generalizableElement : interfaces) {

        if (generalizableElement instanceof Interface) {
          EList<ExchangeItemAllocation> ownedExchangeItemAllocations = ((Interface) generalizableElement)
              .getOwnedExchangeItemAllocations();

          for (ExchangeItemAllocation exchangeItemAllocation : ownedExchangeItemAllocations) {
            AbstractExchangeItem allocatedItem = exchangeItemAllocation.getAllocatedItem();

            if (allocatedItem instanceof ExchangeItem) {
              result.add(allocatedItem);
            }
          }
        }
      }
    }

    return result;
  }
}
