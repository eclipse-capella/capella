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
import java.util.ListIterator;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * Actor helpers
 * 
 */
public class ActorExt {


	/**
	 * This method removes an involved actor.
	 * 
	 * @param actor
	 *            the actor in which the capability will not be involved to
	 * @param capabilityUseCase
	 *            the non involved capability
	 */
	public static void removeInvolvedCapability(Actor actor, AbstractCapability capabilityUseCase) {
		ActorCapabilityInvolvement actorCapabilityInvolvement = null;
		ListIterator<ActorCapabilityInvolvement> it = actor.getParticipationsInCapabilities().listIterator();
		while (it.hasNext()) {
			ActorCapabilityInvolvement inv = it.next();
			if (inv.getCapability().equals(capabilityUseCase)) {
				actorCapabilityInvolvement = inv;
			}
		}
		if (actorCapabilityInvolvement != null) {
			((Capability) capabilityUseCase.eContainer()).getOwnedActorCapabilityInvolvements().remove(actorCapabilityInvolvement);
			actorCapabilityInvolvement.destroy();
		}
	}

	/**
	 * This method retrieves the involved capabilities.
	 * 
	 * @param actor
	 *            the actor whose involved capabilities will be retrieved
	 * @return the involved capabilities
	 */
	public static List<Capability> getInvolvedCapabilities(Actor actor) {
		List<Capability> involvedCapabilities = new ArrayList<Capability>();

		for (ActorCapabilityInvolvement actorCapabilityInvolvement : actor.getParticipationsInCapabilities()) {
			Capability capabilitySpecificationUseCase = actorCapabilityInvolvement.getCapability();
			if (capabilitySpecificationUseCase != null) {
				involvedCapabilities.add(capabilitySpecificationUseCase);
			}
		}

		return involvedCapabilities;
	}


	/**
	 * This method adds an involved capability.
	 * 
	 * @param actor
	 *            the actor in which the capability will be involved to
	 * @param capabilityUseCase
	 *            the involved capability
	 */
	public static void addInvolvedCapabilityUseCase(Actor actor, Capability capabilityUseCase) {
		if ((actor != null) && (capabilityUseCase != null)) {
			ActorCapabilityInvolvement capabilityInv = CtxFactory.eINSTANCE.createActorCapabilityInvolvement();
			capabilityInv.setInvolved(actor);
			capabilityInv.setInvolver(capabilityUseCase);
			((Capability) capabilityUseCase.eContainer()).getOwnedActorCapabilityInvolvements().add(capabilityInv);
		}
	}


	/**
	 * This method checks whether the actor interacts with the CapabilityUseCase
	 * 
	 * 
	 * @param actor
	 *            the interacting actor
	 * @param capabilityUseCase
	 *            the CapabilityUseCase
	 * @return true if the actor interacts with the CapabilityUseCase
	 */
	static public boolean isInteracting(Actor actor, AbstractCapability capabilityUseCase) {
		boolean isInteracting = false;

		for (ActorCapabilityInvolvement actorCapabilityInvolvement : actor.getParticipationsInCapabilities()) {
			if (actorCapabilityInvolvement.getCapability().equals(capabilityUseCase)) {
				isInteracting = true;
				break;
			}
		}

		return isInteracting;
	}

	/**
	 * This method checks whether the actor interacts with the Mission
	 * 
	 * 
	 * @param actor
	 *            the interacting actor
	 * @param mission
	 *            the Mission
	 * @return true if the actor interacts with the Mission
	 */
	static public boolean isInteracting(Actor actor, Mission mission) {
		boolean isInteracting = false;

		for (ActorMissionInvolvement actorMissionInvolvement : mission.getInvolvedActors()) {
			if (actorMissionInvolvement.getActor().equals(actor)) {
				isInteracting = true;
				break;
			}
		}

		return isInteracting;
	}
}
