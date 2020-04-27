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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return allocated interactions of current communication mean 
 *  
 *
 */
public class CommunicationMeanAllocatedInteractions implements IQuery {

	public CommunicationMeanAllocatedInteractions() {
    // do nothing
	}

	/** 
	 *  
	 * current.functionalExchanges
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof  CommunicationMean) {
		  // communication mean
		  ComponentExchange element = (ComponentExchange) object;
		  // collect allocated interactions
			EList<FunctionalExchange> functionalExchanges = element.getAllocatedFunctionalExchanges();
			if(!functionalExchanges.isEmpty())
			  result.addAll(functionalExchanges);
			
		}
        return result;
	}
}
