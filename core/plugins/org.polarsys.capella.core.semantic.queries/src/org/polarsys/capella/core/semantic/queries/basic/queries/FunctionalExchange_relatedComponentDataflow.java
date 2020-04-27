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
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return allocating component exchanges of current functional exchanges
 * 
 * 
 */
public abstract class FunctionalExchange_relatedComponentDataflow implements IQuery {

	/**
	 * constructor
	 */
	public FunctionalExchange_relatedComponentDataflow() {
		// Does nothing
	}

	/**
	 * 
	 * current.getComponentExchanges
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof FunctionalExchange) {
			FunctionalExchange functionalExchange = (FunctionalExchange) object;
			EList<ComponentExchange> componentExchanges = functionalExchange.getAllocatingComponentExchanges();
			for (ComponentExchange componentExchange : componentExchanges) {
				if(isValidInstanceOf(componentExchange)){
					result.add(componentExchange);
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
	abstract public boolean isValidInstanceOf(Object element);
}
