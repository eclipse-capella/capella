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

import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return realizing functional exchanges of the current functional exchange
 * 
 *
 */
public class FunctionalExchange_realizingDataflow implements IQuery {

	/**
	 * 
	 */
	public FunctionalExchange_realizingDataflow() {
	  // do nothing
	}

	/** 
	 *  
	 * current.inExchangeRealisations.allocatingExchange
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof FunctionalExchange) {
			FunctionalExchange e = (FunctionalExchange) object;
			// get the incoming link
			EList<FunctionalExchangeRealization> links = e.getIncomingFunctionalExchangeRealizations();
			for (FunctionalExchangeRealization link : links) {
				// get the source element of the link
				TraceableElement sourceElement = link.getSourceElement();
				if (sourceElement != null) {
					result.add(sourceElement);
				}
			}
		}
        return result;
	}
}
