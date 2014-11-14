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
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorExt;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_Mission_InteractingActors extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the actors that can interact with the mission except those that
	 * are already interacting.
	 * </p>
	 * <p>
	 * Refer MQRY_ Mission_InteractingActors_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof Mission) {
			Mission currentMission = (Mission) element_p;
			availableElements.addAll(getRule_MQRY_Mission_Interactions_11(ca.getOwnedActorPkg(), currentMission));
		} else if (element_p instanceof MissionPkg) {
			ActorPkg actorPkg = ca.getOwnedActorPkg();
			if (actorPkg != null) {
				availableElements.addAll(ActorPkgExt.getAllActors(actorPkg));
			}
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the actors that can interact with the mission except those that
	 * are already interacting.
	 * </p>
	 * <p>
	 * Refer MQRY_ Mission_Interactions_11
	 * </p>
	 * @param actorPkg_pthe ActorPkg
	 * @param currentMission_pthe current Mission
	 * @return list of Actors
	 */
	private List<CapellaElement> getRule_MQRY_Mission_Interactions_11(ActorPkg actorPkg_p, Mission currentMission_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (actorPkg_p != null) {
			for (Actor actor : ActorPkgExt.getAllActors(actorPkg_p)) {
				if ((actor == null))
					continue;
				if (ActorExt.isInteracting(actor, currentMission_p))
					continue;
				availableElements.add(actor);
			}
		}
		return availableElements;
	}

}