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
	 * @param actor_p
	 *            the actor in which the capability will not be involved to
	 * @param capabilityUseCase_p
	 *            the non involved capability
	 */
	public static void removeInvolvedCapability(Actor actor_p, AbstractCapability capabilityUseCase_p) {
		ActorCapabilityInvolvement actorCapabilityInvolvement = null;
		ListIterator<ActorCapabilityInvolvement> it = actor_p.getParticipationsInCapabilities().listIterator();
		while (it.hasNext()) {
			ActorCapabilityInvolvement inv = it.next();
			if (inv.getCapability().equals(capabilityUseCase_p)) {
				actorCapabilityInvolvement = inv;
			}
		}
		if (actorCapabilityInvolvement != null) {
			((Capability) capabilityUseCase_p.eContainer()).getOwnedActorCapabilityInvolvements().remove(actorCapabilityInvolvement);
			actorCapabilityInvolvement.destroy();
		}
	}

	/**
	 * This method retrieves the involved capabilities.
	 * 
	 * @param actor_p
	 *            the actor whose involved capabilities will be retrieved
	 * @return the involved capabilities
	 */
	public static List<Capability> getInvolvedCapabilities(Actor actor_p) {
		List<Capability> involvedCapabilities = new ArrayList<Capability>();

		for (ActorCapabilityInvolvement actorCapabilityInvolvement : actor_p.getParticipationsInCapabilities()) {
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
	 * @param actor_p
	 *            the actor in which the capability will be involved to
	 * @param capabilityUseCase_p
	 *            the involved capability
	 */
	public static void addInvolvedCapabilityUseCase(Actor actor_p, Capability capabilityUseCase_p) {
		if ((actor_p != null) && (capabilityUseCase_p != null)) {
			ActorCapabilityInvolvement capabilityInv = CtxFactory.eINSTANCE.createActorCapabilityInvolvement();
			capabilityInv.setInvolved(actor_p);
			capabilityInv.setInvolver(capabilityUseCase_p);
			((Capability) capabilityUseCase_p.eContainer()).getOwnedActorCapabilityInvolvements().add(capabilityInv);
		}
	}


	/**
	 * This method checks whether the actor interacts with the CapabilityUseCase
	 * 
	 * 
	 * @param actor_p
	 *            the interacting actor
	 * @param capabilityUseCase_p
	 *            the CapabilityUseCase
	 * @return true if the actor interacts with the CapabilityUseCase
	 */
	static public boolean isInteracting(Actor actor_p, AbstractCapability capabilityUseCase_p) {
		boolean isInteracting = false;

		for (ActorCapabilityInvolvement actorCapabilityInvolvement : actor_p.getParticipationsInCapabilities()) {
			if (actorCapabilityInvolvement.getCapability().equals(capabilityUseCase_p)) {
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
	 * @param actor_p
	 *            the interacting actor
	 * @param mission_p
	 *            the Mission
	 * @return true if the actor interacts with the Mission
	 */
	static public boolean isInteracting(Actor actor_p, Mission mission_p) {
		boolean isInteracting = false;

		for (ActorMissionInvolvement actorMissionInvolvement : mission_p.getInvolvedActors()) {
			if (actorMissionInvolvement.getActor().equals(actor_p)) {
				isInteracting = true;
				break;
			}
		}

		return isInteracting;
	}
}
