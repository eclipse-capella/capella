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

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 */
public class ExchangesItemAllocationInterface implements IQuery {

  /**
	 * 
	 */
  public ExchangesItemAllocationInterface() {
    // Does nothing
  }

  /**
   * 
   * current.flowSource.ownerElement
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation exchangItemAll = (ExchangeItemAllocation) object;
      // because its container
      EObject container = exchangItemAll.eContainer();
      if (null != container && container instanceof Interface) {
        result.add(container);
      }
      
    }
    return result;
  }
}
