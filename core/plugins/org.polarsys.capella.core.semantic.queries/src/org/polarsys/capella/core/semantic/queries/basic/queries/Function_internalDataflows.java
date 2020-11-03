/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Function_internalDataflows implements IQuery {

	/**
	 * 
	 */
	public Function_internalDataflows() {
    // do nothing
	}

	/**
	 * Gathering recursively all sub components:
     * current.subFonctions
     * Gathering all dataflows BETWEEN these components
     * (using current.ownedPartitions.(select FlowPort).outgoingFlows)
     * Displaying all internal exchanges
     * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {

		List<Object> result = new ArrayList<Object>();
		if (object instanceof AbstractFunction) {
		  AbstractFunction sf = (AbstractFunction) object;
			EList<AbstractFunction> subfunctions = sf.getSubFunctions();
			for (AbstractFunction subfunction : subfunctions) {
				EList<OutputPin> fpins = subfunction.getOutputs();
				for (OutputPin fpin : fpins) {
					for (ActivityEdge edge : fpin.getOutgoing()) {
						if (subfunctions.contains(edge.getTarget().eContainer())) result.add(edge);
					}
				}
			}
		}
		return result;
	}

}
