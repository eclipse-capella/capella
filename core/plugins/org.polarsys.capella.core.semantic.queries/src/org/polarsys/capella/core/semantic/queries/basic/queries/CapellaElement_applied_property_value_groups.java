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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;

/**
 * This query allow to display in the semantic browser the allocation of
 * property value groups for a given element.
 */
public class CapellaElement_applied_property_value_groups implements IQuery {

	/**
	 * Constructor.
	 */
	public CapellaElement_applied_property_value_groups() {
		// Do nothing...
	}

	/**
	 * Compute the query that results in displaying the applied property value
	 * groups for a given element.
	 * 
	 * @param object
	 *            the selected element to apply the query on.
	 * 
	 * @return the list of property value groups applied to the element.
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.
	 *      Object)
	 */
	@Override
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof CapellaElement) {
			CapellaElement elt = (CapellaElement) object;
			for (PropertyValueGroup pvg : elt.getAppliedPropertyValueGroups()) {
				result.add(pvg);
			}
		}

		return result;
	}

}
