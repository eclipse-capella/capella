/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * Return realizing ExchangeItems of ExchangeItem
 */
public class ExchangeItem_realizingEI implements IQuery {

	/**
	 * 
	 */
	public ExchangeItem_realizingEI() {
		// do nothing
	}

	/**
	 * 
	 * current.realizingExchangeItems
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof ExchangeItem) {
			ExchangeItem ei = (ExchangeItem) object;
			EList<ExchangeItem> rei = ei.getRealizingExchangeItems();
			if (!rei.isEmpty()) {
				result.addAll(rei);
			}
		}
		return result;
	}
}
