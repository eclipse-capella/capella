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
package org.polarsys.capella.core.business.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorExt;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class Mission_InteractingActors implements IBusinessQuery {

	/**
	 * <p>
	 * Gets all the actors that can interact with the mission except those that
	 * are already interacting.
	 * </p>
	 * <p>
	 * Refer MQRY_ Mission_Interactions_11
	 * </p>
	 * 
	 * @param actorPkg_p
	 *            the ActorPkg
	 * @param currentMission_p
	 *            the current Mission
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

	/**
	 * <p>
	 * Gets all the actors that can interact with the mission except those that
	 * are already interacting.
	 * </p>
	 * <p>
	 * Refer MQRY_ Mission_InteractingActors_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		// FIXME : update to SystemAnalysis
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
	 * Gets all the actors that interact with the mission.
	 * </p>
	 * <p>
	 * Refer MQRY_ Mission_InteractingActors_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		// FIXME : update to SystemAnalysis
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof Mission) {
			Mission currentMission = (Mission) element_p;
			ActorPkg actorPkg = ca.getOwnedActorPkg();
			if (actorPkg != null) {
				for (Actor actor : ActorPkgExt.getAllActors(actorPkg)) {
					if ((actor == null))
						continue;

					if (ActorExt.isInteracting(actor, currentMission))
						currentElements.add(actor);
				}
			}
		}
		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.MISSION;
	}

	public List<EReference> getEStructuralFeatures() {
	  List<EReference> returnedList = new ArrayList<EReference>();
    returnedList.add(CtxPackage.Literals.MISSION__INVOLVED_ACTORS);
    returnedList.add(CtxPackage.Literals.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS);
    return returnedList;
	}
}
