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

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *  Return realizing component Exchanges of current component exchange
 * 
 *
 */
public class ExchangeSpecification_realizingDataflow implements IQuery {

	/**
	 * 
	 */
	public ExchangeSpecification_realizingDataflow() {
	  // do nothing
	}

	/** 
	 *  
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
    if (object instanceof ComponentExchange) {
		  ComponentExchange compExchange = (ComponentExchange) object;
			
			// get incoming traces and retrieve Component Exchange Allocation if any
			EList<ComponentExchangeRealization> inComingCompExcRealization = compExchange.getIncomingComponentExchangeRealizations();
			for (ComponentExchangeRealization trace : inComingCompExcRealization) {
				  ComponentExchange allocatingComponentExchange = trace.getAllocatingComponentExchange();
					if (null != allocatingComponentExchange) {
					  // get the exchange allocator that is a component exchange
						result.add(allocatingComponentExchange);							
					}
			}
		}
      return result;
	}
}
