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
 * Return realized functional exchanges of current functional exchanges
 * 
 * 
 */
public class FunctionalExchange_realizedDataflow implements IQuery {

	/**
	 * 
	 */
	public FunctionalExchange_realizedDataflow() {
		// do nothing
	}

	/**
	 * 
	 * current.outExchangeRealisations.allocatedExchange
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof FunctionalExchange) {
			FunctionalExchange e = (FunctionalExchange) object;
			// get outgoing functional exchange realization link
			EList<FunctionalExchangeRealization> links = e
					.getOutgoingFunctionalExchangeRealizations();
			for (FunctionalExchangeRealization link : links) {
				// get link target element
				TraceableElement targetElement = link.getTargetElement();
				if (null != targetElement) {
					result.add(targetElement);
				}

			}
		}
		return result;
	}
}
