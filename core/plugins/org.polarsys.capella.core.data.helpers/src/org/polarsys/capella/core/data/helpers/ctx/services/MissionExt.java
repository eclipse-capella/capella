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
package org.polarsys.capella.core.data.helpers.ctx.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.Mission;

/**
 * Mission helpers
 * 
 */
public class MissionExt {

	/**
	 * This method retrieves the exploited capabilities.
	 * 
	 * @param mission_p
	 *            the mission whose exploited capability will be retrieved
	 * @return the exploited capabilities
	 */
	public static List<Capability> getExploitedCapabilities(Mission mission_p) {
		List<Capability> exploitedCapabilities = new ArrayList<Capability>();
		List<CapabilityExploitation> exploitationSet = mission_p.getOwnedCapabilityExploitations();
		for (CapabilityExploitation exploit : exploitationSet) {
			exploitedCapabilities.add(exploit.getCapability());
		}
		return exploitedCapabilities;
	}

	/**
	 * This method retrieves the involved actors.
	 * 
	 * @param mission_p
	 *            the mission whose contributing actors will be retrieved
	 * @return the contributing actors
	 */
	public static List<Actor> getInvolvedActors(Mission mission_p) {
		List<Actor> involvedActors = new ArrayList<Actor>();
		List<ActorMissionInvolvement> contributionSet = mission_p.getInvolvedActors();
		for (ActorMissionInvolvement involvement : contributionSet) {
			involvedActors.add(involvement.getActor());
		}
		return involvedActors;
	}

	/**
	 * @param mission_p
	 *            The mission.
	 * @param actors_p
	 *            The actors to remove.
	 */
	public static void removeInvolvedActors(Mission mission_p, List<Actor> actors_p) {
		List<ActorMissionInvolvement> removedLinks = new ArrayList<ActorMissionInvolvement>();

		for (Object involvement : mission_p.getInvolvedActors()) {
			ActorMissionInvolvement actorInvolvement = (ActorMissionInvolvement) involvement;
			if (actors_p.contains(actorInvolvement.getActor())) {
				removedLinks.add(actorInvolvement);
			}
		}

		for (ActorMissionInvolvement actorMissionInvolvement : removedLinks) {
			actorMissionInvolvement.destroy();
		}
	}

	/**
	 * @param mission_p
	 * @param useCases_p
	 */
	public static void removeExploitedCapabilities(Mission mission_p, List<Capability> useCases_p) {
		List<CapabilityExploitation> removedLinks = new ArrayList<CapabilityExploitation>();

		for (Object capabilityExp : mission_p.getOwnedCapabilityExploitations()) {
			CapabilityExploitation capabilitExploitation = (CapabilityExploitation) capabilityExp;
			if (useCases_p.contains(capabilitExploitation.getCapability())) {
				removedLinks.add(capabilitExploitation);
			}
		}

		for (CapabilityExploitation capabilityExploitation : removedLinks) {
			capabilityExploitation.destroy();
		}
	}
}
