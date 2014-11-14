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

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return realized functional exchanges of current functional exchanges
 * 
 * 
 */
public abstract class AbsFunctionalExchangeRealizedFunctionalExchanges
		implements IQuery {

	/**
	 * 
	 */
	public AbsFunctionalExchangeRealizedFunctionalExchanges() {
		// do nothing
	}

	/**
	 * 
	 * current.outExchangeRealisations.allocatedExchange
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof FunctionalExchange) {
			FunctionalExchange e = (FunctionalExchange) object_p;
			// get root architecture
			BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(e);
			if (isValidArchitectureLavel(arch)) {
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
		}
		return result;
	}

	abstract public boolean isValidArchitectureLavel(BlockArchitecture arch_p);
}
