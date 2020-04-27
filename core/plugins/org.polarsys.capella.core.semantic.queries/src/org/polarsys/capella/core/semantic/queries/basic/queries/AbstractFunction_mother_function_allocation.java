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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;

/**
 * This query allow to display in the semantic browser a mother function
 * allocation to a given component/actor.
 * 
 * @see the definition of a mother function
 */
public class AbstractFunction_mother_function_allocation implements IQuery {

	/**
	 * Constructor.
	 */
	public AbstractFunction_mother_function_allocation() {
		// Do nothing...
	}

	/**
	 * @param selectedObject
	 *            The diagram element selected (Logical Function ; Physical
	 *            Function ; System Function)
	 * 
	 * @return The component/actor the function is allocated to, in case:
	 *         <ul>
	 *         <li>The function is a mother function</li>
	 *         <li>All its leaf are allocated to a same component/actor</li>
	 *         </ul>
	 * 
	 *         Otherwise returns an empty list.
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	@Override
	public List<Object> compute(Object selectedObject) {
		List<Object> result = new ArrayList<Object>();
		if ((selectedObject instanceof LogicalFunction) || (selectedObject instanceof SystemFunction)
				|| (selectedObject instanceof PhysicalFunction)) {
			AbstractFunction motherFunction = (AbstractFunction) selectedObject;

			// In case the function is a leaf, there are already
			// two queries that do the job:
			// - Function Actor Allocation
			// - Function Component Allocation
			if (!FunctionExt.isLeaf(motherFunction)) {
				// Check block allocation
				EList<AbstractFunctionalBlock> blockAllocations = motherFunction.getAllocationBlocks();

				// If mother is already allocated, there are already queries
				// that do the job so only get the leaves allocation in case the
				// mother is not already allocated
				if ((null == blockAllocations) || blockAllocations.isEmpty()) {
					result.addAll(AbstractFunctionExt.getMotherFunctionAllocation(motherFunction));
				}
			}
		}

		return result;
	}

}
