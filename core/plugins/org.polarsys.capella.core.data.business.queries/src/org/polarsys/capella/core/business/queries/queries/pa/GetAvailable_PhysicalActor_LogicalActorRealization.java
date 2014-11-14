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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_PhysicalActor_LogicalActorRealization extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof PhysicalActor) {
			PhysicalActor actor = (PhysicalActor) element_p;
			availableElements.addAll(getRule_MQRY_LogicalActor_ActorRealization_11(systemEngineering, actor));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_LogicalActor_ActorRealization_11(SystemEngineering systemEng_p, PhysicalActor element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		LogicalArchitecture arch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEng_p);
		LogicalActorPkg ownedActorPkg = arch.getOwnedLogicalActorPkg();
		List<LogicalActor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);
		if (null != element_p) {
			EList<LogicalActorRealization> ownedActorRealisations = element_p.getOwnedLogicalActorRealizations();
			for (LogicalActorRealization actRealisation : ownedActorRealisations) {
				TraceableElement targetElement = actRealisation.getTargetElement();
				if (null != targetElement) {
					if (allActors.contains(targetElement)) {
						allActors.remove(targetElement);
					}
				}
			}
		}
		for (LogicalActor actor : allActors) {
			availableElements.add(actor);
		}
		return availableElements;
	}

}