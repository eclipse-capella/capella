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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_LogicalActor_ActorRealization extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof LogicalActor) {
			LogicalActor logicalActor = (LogicalActor) element_p;
			availableElements.addAll(getRule_MQRY_LogicalActor_ActorRealization_11(systemEngineering, logicalActor));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_LogicalActor_ActorRealization_11(SystemEngineering systemEng_p, LogicalActor element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		SystemAnalysis arch = SystemEngineeringExt.getOwnedSystemAnalysis(systemEng_p);
		ActorPkg ownedActorPkg = arch.getOwnedActorPkg();
		List<Actor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);
		if (null != element_p) {
			EList<SystemActorRealization> ownedActorRealisations = element_p.getOwnedSystemActorRealizations();
			for (SystemActorRealization actRealisation : ownedActorRealisations) {
				TraceableElement targetElement = actRealisation.getTargetElement();
				if (null != targetElement) {
					if (allActors.contains(targetElement)) {
						allActors.remove(targetElement);
					}
				}
			}
		}
		for (Actor function : allActors) {
			availableElements.add(function);
		}
		return availableElements;
	}

}