/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Actor_InteractingCapabilities extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * Gets all the capabilities in the Functional Aspect Package and all of its
	 * sub packages, except the capabilities that are already involved with the
	 * current actor. Refer MQRY_Actor_Capabilities_1
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof Actor) {
			Actor currentActor = (Actor) element;
			availableElements.addAll(getRule_MQRY_Actor_Capabilities_11(systemEngineering, currentActor));
		} else if (element instanceof ActorPkg) {
			availableElements.addAll(CapellaElementExt.getAllCapabilities(systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * Gets all the capabilities in the Functional Aspect Package and all of its
	 * sub packages, except the capabilities that are already involved with the
	 * current actor. Refer MQRY_Actor_Capabilities_1
	 * @param sysEngthe {@link SystemEngineering}
	 * @param currentActorthe current Actor
	 * @return list of {@link CapabilityUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_Actor_Capabilities_11(SystemEngineering sysEng, Actor currentActor) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng)) {
			availableElements.add(capabilityUseCase);
		}
		return availableElements;
	}

}