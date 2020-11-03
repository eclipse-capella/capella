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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.common.helpers.query.IQuery;
/**
 * Return realized communication mean of current component exchange
 * 
 *
 */
public class ComponenExchangeRealizedCommunicationMean implements IQuery{

	/**
	 * 
	 */
	public ComponenExchangeRealizedCommunicationMean() {
	  // do nothing
	}

	 /** 
   *  
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof ComponentExchange && !(object instanceof CommunicationMean)) {
      ComponentExchange compExchange = (ComponentExchange) object;
      
      // get outGoing Component Exchange realization link
      EList<ComponentExchangeRealization> ouComingCompExcRealization = compExchange.getOutgoingComponentExchangeRealizations();
      for (ComponentExchangeRealization trace : ouComingCompExcRealization) {
          ComponentExchange allocatedComponentExchange = trace.getAllocatedComponentExchange();
          if (null != allocatedComponentExchange && allocatedComponentExchange instanceof CommunicationMean) {
            result.add(allocatedComponentExchange);              
          }
      }
    }
      return result;
  }
}
