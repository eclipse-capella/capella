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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_PhysicalComponent_FunctionalAllocation extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets available FunctionalAllocation for the physical component.
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof PhysicalComponent) {
			PhysicalComponent comp = (PhysicalComponent) element_p;
			if (!(comp.getNature() == PhysicalComponentNature.NODE)) {
				availableElements.addAll(getRule_MQRY_Component_FunctionalAllocation_11(SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering)));
			}
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets available functions to be allocated to the component
	 * </p>
	 * @param element_p the component
	 * @return list of Function
	 */
	protected List<CapellaElement> getRule_MQRY_Component_FunctionalAllocation_11(BlockArchitecture arch_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<AbstractFunction> allLeafFunctions = FunctionExt.getAllLeafAbstractFunctions(arch_p);
		List<AbstractFunction> listTORemove = new ArrayList<AbstractFunction>();
		for (AbstractFunction function : allLeafFunctions) {
			if (!function.getAllocationBlocks().isEmpty()) {
				listTORemove.add(function);
			}
		}
		allLeafFunctions.removeAll(listTORemove);
		availableElements.addAll(allLeafFunctions);
		return availableElements;
	}

}