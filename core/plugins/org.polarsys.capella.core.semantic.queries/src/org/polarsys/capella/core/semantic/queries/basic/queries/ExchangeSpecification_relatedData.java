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

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 */
public class ExchangeSpecification_relatedData implements IQuery {

	/**
	 * 
	 */
	public ExchangeSpecification_relatedData() {
		// do nothing
	}

	/**
	 * 
	 * current.exchangedInformations.representedData
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof ComponentExchange) {
			ComponentExchange e = (ComponentExchange) object_p;
			for (AbstractExchangeItem informationItem : e.getConvoyedInformations()) {
				List<AbstractType> data = ExchangeItemExt.getData(informationItem);
				if (!data.isEmpty()) {
					result.addAll(data);
				}
			}
		}
		return result;
	}
}
