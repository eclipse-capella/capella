/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;

/**
 * This query allow to display in the semantic browser a mother activity
 * allocation to a given actor/entity/role.
 * 
 * @see the definition of a mother activity
 */
public class AbstractFunction_mother_activity_allocation implements IQuery {

	/**
	 * Constructor.
	 */
	public AbstractFunction_mother_activity_allocation() {
		// Do nothing...
	}

	/**
	 * @param selectedObject
	 *            The diagram element selected (Operational Activity)
	 * 
	 * @return The actor/entity/activity/role the activity is allocated to, in
	 *         case:
	 *         <ul>
	 *         <li>The activity is a mother activity</li>
	 *         <li>All its leaf are allocated to a same
	 *         actor/entity/role</li>
	 *         </ul>
	 * 
	 *         Otherwise returns an empty list.
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	@Override
	public List<Object> compute(Object selectedObject) {
		List<Object> result = new ArrayList<Object>();
		if ((selectedObject instanceof OperationalActivity)) {
			AbstractFunction motherActivity = (AbstractFunction) selectedObject;

			// In case the activity is a leaf, there are already
			// three queries that do the job:
			// - Actor Allocation
			// - Entity Allocation
			// - Role Allocation
			if (!FunctionExt.isLeaf(motherActivity)) {
				// Check block allocation
				EList<AbstractFunctionalBlock> blockAllocations = motherActivity.getAllocationBlocks();

				// If mother is already allocated, there are already queries
				// that do the job so only get the leaves allocation in case the
				// mother is not already allocated
				if ((null == blockAllocations) || blockAllocations.isEmpty()) {
					result.addAll(FunctionExt.getMotherFunctionAllocation(motherActivity));
				}

				// Check roles allocation
				EList<Role> roleAllocations = ((OperationalActivity) motherActivity).getAllocatingRoles();

				// If mother is already allocated, there are already queries
				// that do the job so only get the leaves allocation in case the
				// mother is not already allocated
				if ((null == roleAllocations) || roleAllocations.isEmpty()) {
					result.addAll(FunctionExt.getMotherActivityRoleAllocation(motherActivity));
				}
			}
		}

		return result;
	}

}
