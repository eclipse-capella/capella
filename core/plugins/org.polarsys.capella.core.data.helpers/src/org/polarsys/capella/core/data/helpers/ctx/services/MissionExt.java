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
	 * @param mission
	 *            the mission whose exploited capability will be retrieved
	 * @return the exploited capabilities
	 */
	public static List<Capability> getExploitedCapabilities(Mission mission) {
		List<Capability> exploitedCapabilities = new ArrayList<>();
		List<CapabilityExploitation> exploitationSet = mission.getOwnedCapabilityExploitations();
		for (CapabilityExploitation exploit : exploitationSet) {
			Capability capability = exploit.getCapability();
			if(capability != null) {
			  exploitedCapabilities.add(capability);			  
			}
		}
		return exploitedCapabilities;
	}

	/**
	 * This method retrieves the involved actors.
	 * 
	 * @param mission
	 *            the mission whose contributing actors will be retrieved
	 * @return the contributing actors
	 */
	public static List<Actor> getInvolvedActors(Mission mission) {
		List<Actor> involvedActors = new ArrayList<>();
		List<ActorMissionInvolvement> contributionSet = mission.getInvolvedActors();
		for (ActorMissionInvolvement involvement : contributionSet) {
			Actor actor = involvement.getActor();
			if(actor != null) {
			  involvedActors.add(actor);			  
			}
		}
		return involvedActors;
	}

	/**
	 * @param mission
	 *            The mission.
	 * @param actors
	 *            The actors to remove.
	 */
	public static void removeInvolvedActors(Mission mission, List<Actor> actors) {
		List<ActorMissionInvolvement> removedLinks = new ArrayList<>();

		for (Object involvement : mission.getInvolvedActors()) {
			ActorMissionInvolvement actorInvolvement = (ActorMissionInvolvement) involvement;
			if (actors.contains(actorInvolvement.getActor())) {
				removedLinks.add(actorInvolvement);
			}
		}

		for (ActorMissionInvolvement actorMissionInvolvement : removedLinks) {
			actorMissionInvolvement.destroy();
		}
	}

	/**
	 * @param mission
	 * @param useCases
	 */
	public static void removeExploitedCapabilities(Mission mission, List<Capability> useCases) {
		List<CapabilityExploitation> removedLinks = new ArrayList<>();

		for (Object capabilityExp : mission.getOwnedCapabilityExploitations()) {
			CapabilityExploitation capabilitExploitation = (CapabilityExploitation) capabilityExp;
			if (useCases.contains(capabilitExploitation.getCapability())) {
				removedLinks.add(capabilitExploitation);
			}
		}

		for (CapabilityExploitation capabilityExploitation : removedLinks) {
			capabilityExploitation.destroy();
		}
	}
}
