/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.ctx;

import static org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries.isGoodSupertypeCandidate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_Actor_InheritedActors extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets the available actors in the actor package and all the sub actor
	 * packages
	 * </p>
	 * <p>
	 *  Except the current Actor itself, Actors that specialize the current Actor,
	 *  and Actors that are indirect generalizations of the current Actor.
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_Inherited_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		boolean isElementFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isElementFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element instanceof Actor) {
			Actor currentActor = (Actor) element;
			if (!isElementFromSharedPkg)
				availableElements.addAll(getRule_MQRY_Actor_Inherited_11(systemEngineering, currentActor));
			availableElements.addAll(getRule_MQRY_Actor_Inherited_12(systemEngineering, currentActor));
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets the available actors in the actor package and all the sub actor
	 * packages of system engineering package
	 * </p>
	 * <p>
	 * Except the current Actor itself, Actors that specialize the current Actor,
   *  and Actors that are indirect generalizations of the current Actor.
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_Inherited_11
	 * </p>
	 * @param sysEngthe {@link SystemEngineering}
	 * @param currentActorthe current {@link Actor}
	 * @return list of Actors
	 */
	private List<CapellaElement> getRule_MQRY_Actor_Inherited_11(SystemEngineering sysEng, Actor currentActor) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
		ActorPkg actorPkg = ca.getOwnedActorPkg();
		if (actorPkg != null) {
			for (Actor actor : ActorPkgExt.getAllActors(actorPkg)) {
			  if (isGoodSupertypeCandidate(currentActor, actor)) {
					availableElements.add(actor);
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Actor_Inherited_12(SystemEngineering systemEngineering, Actor currentActor) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<SharedPkg> sharedPkgs = SystemEngineeringExt.getSharedPkgs(systemEngineering);
		for (SharedPkg sharedPkg : sharedPkgs) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Actor actor : GenericPkgExt.getAllActors(pkg)) {
				  if (isGoodSupertypeCandidate(currentActor, actor)) {
	          availableElements.add(actor);
	        }
				}
			}
		}
		return availableElements;
	}

}