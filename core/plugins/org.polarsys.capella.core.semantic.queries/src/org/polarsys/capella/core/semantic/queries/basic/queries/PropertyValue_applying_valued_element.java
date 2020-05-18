/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * This query allow to display in the semantic browser the allocating elements
 * for a given property value.
 */
public class PropertyValue_applying_valued_element implements IQuery {

	/**
	 * Constructor.
	 */
	public PropertyValue_applying_valued_element() {
		// Do nothing...
	}

	/**
	 * Compute the query that results in displaying the elements the property
	 * value is applied on.
	 * 
	 * @param object
	 *            the selected element (property value) to apply the query on.
	 * 
	 * @return the list of elements the property value is applied on.
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.
	 *      Object)
	 */
	@Override
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof AbstractPropertyValue) {
			AbstractPropertyValue pv = (AbstractPropertyValue) object;
			for (CapellaElement elt : pv.getValuedElements()) {
				result.add(elt);
			}
		}

		return result;
	}

}
