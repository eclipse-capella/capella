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

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Capability_InteractingActors extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof Capability) {
			availableElements.addAll(getRule_MQRY_Capability_AvailableActors((Capability) element_p));
		}
		return availableElements;
	}

	/** 
	 * same level visibility layer
	 * @param element_p
	 */
	private List<CapellaElement> getRule_MQRY_Capability_AvailableActors(Capability element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(element_p);
		if (currentBlockArchitecture != null) {
			availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture));
		}
		for (CapellaElement element : getCurrentElements(element_p, false)) {
			availableElements.remove(element);
		}
		return availableElements;
	}

	/** 
	 * @param arch_p
	 * @return
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, true);
		while (allContents.hasNext()) {
			Object object = allContents.next();
			if (object instanceof Actor) {
				availableElements.add((CapellaElement) object);
			}
		}
		return availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof Capability) {
			Capability ele = (Capability) element_p;
			for (ActorCapabilityInvolvement inv : ele.getOwnedActorCapabilityInvolvements()) {
				Actor actor = inv.getActor();
				if (null != actor) {
					currentElements.add(actor);
				}
			}
		}
		return currentElements;
	}

}