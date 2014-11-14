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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.la.CapabilityRealization;

public class GetCurrent_CapabilityRealization_InteractingComponents extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> currentElements = getCurrentElements(capellaElement, false);
		return (List) currentElements;
	}

	/** 
	 * <p>
	 * Gets all the Components interacting with the current Capability
	 * Realization.
	 * </p>
	 * <p>
	 * Refer MQRY_CapabilityRealization_Actors_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof CapabilityRealization) {
			CapabilityRealization currentCapabilityUseCase = (CapabilityRealization) element_p;
			for (ActorCapabilityRealizationInvolvement inv : currentCapabilityUseCase.getInvolvedActors()) {
				Actor actor = (Actor) inv.getInvolved();
				if (actor != null) {
					currentElements.add(actor);
				}
			}
			for (SystemComponentCapabilityRealizationInvolvement inv : currentCapabilityUseCase.getInvolvedSystemComponents()) {
				SystemComponent system = (SystemComponent) inv.getInvolved();
				if (system != null) {
					currentElements.add(system);
				}
			}
		}
		return currentElements;
	}

}