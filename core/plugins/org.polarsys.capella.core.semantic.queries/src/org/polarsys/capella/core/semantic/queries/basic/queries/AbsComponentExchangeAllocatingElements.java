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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return allocating elements of current component exchange
 * 
 *
 */
public abstract class AbsComponentExchangeAllocatingElements implements IQuery {

	public AbsComponentExchangeAllocatingElements() {
    // do nothing
	}

  /**
   * current.functionalExchanges
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof ComponentExchange && !(object instanceof CommunicationMean)) {
      // Component Exchange
      ComponentExchange exchange = (ComponentExchange) object;
      // collect component exchange allocation links
      EList<AbstractTrace> traces = exchange.getIncomingTraces();
      for (AbstractTrace trace : traces) {
        if (trace instanceof ComponentExchangeAllocation) {
          // get source element
          TraceableElement element = trace.getSourceElement();
          if (element != null && isValidInstanceOf(element)) {
            result.add(element);
          }
        }
      }
    }
    return result;
  }
	
	/**
	 * filter the valid instance type
	 * @param element
	 * @return
	 */
	abstract public boolean isValidInstanceOf(EObject element);
}
