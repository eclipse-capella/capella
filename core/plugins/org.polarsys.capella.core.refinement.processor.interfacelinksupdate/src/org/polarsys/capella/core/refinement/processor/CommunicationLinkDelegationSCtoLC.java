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
package org.polarsys.capella.core.refinement.processor;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;

public class CommunicationLinkDelegationSCtoLC {

	protected List<Entry<Component, Component>> pairs = new ArrayList<Entry<Component, Component>>();

	public CommunicationLinkDelegationSCtoLC(LogicalComponent logicalComponent_p) {
		computeComponentPairsForComponentFromLogicalLevel(logicalComponent_p);
	}

	public CommunicationLinkDelegationSCtoLC(LogicalActor logicalActor_p) {
		computeComponentPairsForComponentFromLogicalLevel(logicalActor_p);
	}

	public CommunicationLinkDelegationSCtoLC(LogicalActorPkg logicalActorPck_p) {
		for (Component logicalActor : ActorPkgExt.getAllActors(logicalActorPck_p)) {
			computeComponentPairsForComponentFromLogicalLevel(logicalActor);
		}
	}

	public CommunicationLinkDelegationSCtoLC(System systemComponent) {
		computeComponentPairsForComponentFromSystemLevel(systemComponent);
	}

	public CommunicationLinkDelegationSCtoLC(Actor actor_p) {
		computeComponentPairsForComponentFromSystemLevel(actor_p);
	}

	public CommunicationLinkDelegationSCtoLC(ActorPkg actorPck_p) {
		for (Component actor : ActorPkgExt.getAllActors(actorPck_p)) {
			computeComponentPairsForComponentFromSystemLevel(actor);
		}
	}

	private void computeComponentPairsForComponentFromLogicalLevel(Component logicalComponent_p) {
		for (Component sourceComponent : logicalComponent_p.getAllocatedComponents()) {
			pairs.add(new SimpleEntry<Component, Component>(sourceComponent, logicalComponent_p));
		}
	}

	private void computeComponentPairsForComponentFromSystemLevel(Component systemComponent_p) {
		for (Component targetComponent : systemComponent_p.getAllocatingComponents()) {
			pairs.add(new SimpleEntry<Component, Component>(systemComponent_p, targetComponent));
		}
	}

	public void perform() {
		for (Entry<Component, Component> pair : pairs) {
			perform(pair.getKey(), pair.getValue());
		}
	}

	protected void perform(Component source, Component target) {
		// we create a table for performance purpose
		Hashtable<ExchangeItem, List<CommunicationLink>> item2targetLinks = new Hashtable<ExchangeItem, List<CommunicationLink>>();
		for (CommunicationLink targetLink : target.getOwnedCommunicationLinks()) {
			ExchangeItem item = targetLink.getExchangeItem();
			List<CommunicationLink> links = item2targetLinks.get(item);
			if (links == null) {
				links = new ArrayList<CommunicationLink>();
				item2targetLinks.put(item, links);
			}
			links.add(targetLink);
		}
		// we check that each link defined at the system level has an equivalent at the logical level
		// if not, an equivalent link is created at the logical level
		for (CommunicationLink sourceLink : source.getOwnedCommunicationLinks()) {
			CommunicationLinkKind kind = sourceLink.getKind();
			ExchangeItem item = sourceLink.getExchangeItem();
			List<CommunicationLink> candidatelinks = item2targetLinks.get(item);
			CommunicationLink equivalentLink = null;			
			if (candidatelinks != null) {
				for (CommunicationLink targetLink : candidatelinks) {
					if (targetLink.getKind() == kind) {
						equivalentLink = targetLink;
						break;
					}
				}
			}
			if (equivalentLink == null) {
				// create an equivalent link at the logical level
				CommunicationLink link = CommunicationFactory.eINSTANCE.createCommunicationLink();
				target.getOwnedCommunicationLinks().add(link);
				link.setKind(sourceLink.getKind());
				link.setExchangeItem(item);				
				CapellaElementExt.creationService(link);				
			} else {
				candidatelinks.remove(equivalentLink);
				if (candidatelinks.isEmpty()) {
					item2targetLinks.remove(item);
				}
			}
		}
		// we check that some link at the logical level has no equivalent at the system level
		// if not, the link is deleted
		Enumeration<ExchangeItem> items = item2targetLinks.keys();
		while (items.hasMoreElements()) {
			ExchangeItem item = items.nextElement();
			for (CommunicationLink link : item2targetLinks.get(item)) {
				// delete the link
				EcoreUtil.delete(link);
			}
		}
	}
}
