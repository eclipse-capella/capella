/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_PhysicalArchitecture_AllocatedLogicalArchitecture extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * Gets...
	 * <p>
	 * All the Logical Architectures contained by the current Element's
	 * alternative decomposition hierarchy.
	 * </p>
	 * <p>
	 * Except The current Logical Architectures.
	 * </p>
	 * <p>
	 * Refer MQRY_PhysicalArch_Allocation_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof PhysicalArchitecture) {
			PhysicalArchitecture physicalArchitecture = (PhysicalArchitecture) element;
			availableElements.addAll(getRule_MQRY_PhysicalArch_Allocation_11(physicalArchitecture));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_PhysicalArch_Allocation_11(PhysicalArchitecture currentPhysicalArchitecture) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering parentSystemEngineering = PhysicalArchitectureExt.getParentSystemEngineering(currentPhysicalArchitecture);
		availableElements.addAll(SystemEngineeringExt.getLogicalArchitecturesFiltered(parentSystemEngineering, currentPhysicalArchitecture));
		return availableElements;
	}

}