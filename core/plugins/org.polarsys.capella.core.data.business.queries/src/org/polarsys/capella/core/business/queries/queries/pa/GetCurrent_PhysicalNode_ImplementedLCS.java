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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetCurrent_PhysicalNode_ImplementedLCS extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> currentElements = getCurrentElements(capellaElement, false);
		return (List) currentElements;
	}

	/** 
	 * <p>
	 * Gets all the Logical Components that are implemented by a Physical
	 * Component
	 * </p>
	 * <p>
	 * Refer MQRY_ PhysicalComponent_ImplLogicalComp_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (null == systemEngineering) {
			return currentElements;
		}
		if (element_p instanceof PhysicalComponent) {
			PhysicalComponent currentPC = (PhysicalComponent) element_p;
			for (LogicalComponentRealization lcImplementation : currentPC.getLogicalComponentRealizations()) {
				currentElements.add(lcImplementation.getAllocatedComponent());
			}
			for (LogicalComponentRealization lcImplementation : currentPC.getOwnedLogicalComponentRealizations()) {
				currentElements.add(lcImplementation.getAllocatedComponent());
			}
			currentElements = ListExt.removeDuplicates(currentElements);
		}
		return currentElements;
	}

}