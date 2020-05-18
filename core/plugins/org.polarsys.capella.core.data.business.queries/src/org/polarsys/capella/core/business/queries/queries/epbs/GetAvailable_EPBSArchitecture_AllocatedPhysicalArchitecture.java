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
package org.polarsys.capella.core.business.queries.queries.epbs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.helpers.epbs.services.EPBSArchitectureExt;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_EPBSArchitecture_AllocatedPhysicalArchitecture extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/**
	 * Gets ...
	 * <p>
	 * All the Physical Architectures contained by the current Element's
	 * alternative decomposition hierarchy.
	 * </p>
	 * <p>
	 * Except The current Physical Architecture.
	 * </p>
	 * <p>
	 * Refer MQRY_EPBSArchitecture_Allocation_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof EPBSArchitecture) {
			EPBSArchitecture epbsArchitecture = (EPBSArchitecture) element;
			availableElements.addAll(getRule_MQRY_EPBSArchitecture_Allocation_11(epbsArchitecture));
		}
		return availableElements;
	}

	/**
	 * All the Physical Architectures contained by the current Element's
	 * alternative decomposition hierarchy.
	 */
	private List<CapellaElement> getRule_MQRY_EPBSArchitecture_Allocation_11(EPBSArchitecture currentEPBSArchitecture) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering parentSystemEngineering = EPBSArchitectureExt.getParentSystemEngineering(currentEPBSArchitecture);
		for (PhysicalArchitecture pa : SystemEngineeringExt.getAllPhysicalArchitectures(parentSystemEngineering)) {
			if (pa != null) {
			  availableElements.add(pa);
			}
		}
		return availableElements;
	}

}