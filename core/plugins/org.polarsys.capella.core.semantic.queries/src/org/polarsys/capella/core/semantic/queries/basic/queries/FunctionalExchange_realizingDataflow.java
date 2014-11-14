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
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof FunctionalExchange) {
			FunctionalExchange e = (FunctionalExchange) object_p;
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
