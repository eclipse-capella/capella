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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query returns all the allocated exchangeItems of current interfaces and all super Interfaces
 */
public class InterfaceExchangesItems implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof Interface) {
      Interface itf = (Interface) object_p;
      // list to collect all the super interfaces + current
      List<GeneralizableElement> interfaces = new ArrayList<GeneralizableElement>();
      // add current interface
      interfaces.add(itf);
      // add all super interfaces of current interfaces 
      List<GeneralizableElement> allSuperGenElts = GeneralizableElementExt.getAllSubGeneralizableElements(itf);
      if (!allSuperGenElts.isEmpty()) {
        interfaces.addAll(allSuperGenElts);
      }
      
      // get related exchageItems
      for (GeneralizableElement generalizableElement : interfaces) {
        if (generalizableElement instanceof Interface) {
          EList<ExchangeItemAllocation> ownedExchangeItemAllocations = ((Interface)generalizableElement).getOwnedExchangeItemAllocations();
          for (ExchangeItemAllocation exchangeItemAllocation : ownedExchangeItemAllocations) {
            AbstractExchangeItem allocatedItem = exchangeItemAllocation.getAllocatedItem();
            if (null != allocatedItem && allocatedItem instanceof ExchangeItem) {
              result.add(allocatedItem);
            }
          }  
        }
      }


    }
    
    return result;
  }
}
