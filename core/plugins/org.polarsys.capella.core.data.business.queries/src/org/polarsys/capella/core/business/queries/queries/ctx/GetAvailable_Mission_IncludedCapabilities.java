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
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Mission_IncludedCapabilities extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the capabilities that can be included to the mission except
	 * those which are already included.
	 * </p>
	 * <p>
	 * Refer MQRY_Mission_CapabilityUseCase_Included_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof Mission) {
			Mission currentMission = (Mission) element_p;
			availableElements.addAll(getRule_MQRY_Mission_CapabilityUseCase_Included_11(systemEngineering, currentMission));
		} else if (element_p instanceof MissionPkg) {
			availableElements.addAll(CapellaElementExt.getAllCapabilities(systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the capabilities that can be included to the mission except
	 * those which are already included.
	 * </p>
	 * <p>
	 * Refer MQRY_Mission_CapabilityUseCase_Included_11
	 * </p>
	 * @param sysEng_pthe SystemEngineering Pkg
	 * @param currentMission_pthe current Mission
	 * @return list of CapabilityUseCases
	 */
	private List<CapellaElement> getRule_MQRY_Mission_CapabilityUseCase_Included_11(SystemEngineering sysEng_p, Mission currentMission_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng_p)) {
			if (AbstractCapabilityExt.isIncluded(capabilityUseCase, currentMission_p))
				continue;
			availableElements.add(capabilityUseCase);
		}
		return availableElements;
	}

}