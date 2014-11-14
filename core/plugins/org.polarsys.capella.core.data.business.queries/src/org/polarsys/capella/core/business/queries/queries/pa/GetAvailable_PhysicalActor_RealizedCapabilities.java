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
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalActor_RealizedCapabilities extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * Gets all the capabilities in the Functional Aspect Package and all of its
	 * sub packages, except the capabilities that are already involved with the
	 * current actor. Refer MQRY_Actor_Capabilities_1
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof Actor) {
			Actor currentActor = (Actor) element_p;
			availableElements.addAll(getRule_MQRY_Actor_Capabilities_11(systemEngineering, currentActor));
		} else if (element_p instanceof ActorPkg) {
			availableElements.addAll(CapellaElementExt.getAllCapabilities(systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * Gets all the capabilities in the Functional Aspect Package and all of its
	 * sub packages, except the capabilities that are already involved with the
	 * current actor. Refer MQRY_Actor_Capabilities_1
	 * @param sysEng_pthe {@link SystemEngineering}
	 * @param currentActor_pthe current Actor
	 * @return list of {@link CapabilityUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_Actor_Capabilities_11(SystemEngineering sysEng_p, Actor currentActor_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng_p)) {
			if (CapabilityExt.hasInvolved(capabilityUseCase, currentActor_p))
				continue;
			availableElements.add(capabilityUseCase);
		}
		return availableElements;
	}

}