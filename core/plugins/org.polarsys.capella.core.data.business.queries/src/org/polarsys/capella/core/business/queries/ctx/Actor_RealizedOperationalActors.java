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
package org.polarsys.capella.core.business.queries.ctx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class Actor_RealizedOperationalActors implements IBusinessQuery {

	/**
   * Gets all the actors in the Operational Actor Package and all of its
   * sub packages, except the actors that are already realized by the
   * current actor. Refer MQRY_Actor_RealizedOperationalActors_1
	 * 
	 * @param sysEng_p
	 *            the {@link SystemEngineering}
	 * @param currentActor_p
	 *            the current Actor
	 * @return list of {@link CapabilityUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_Actor_RealizedOperationalActors_11(SystemEngineering sysEng_p, Actor currentActor_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (EObject opActor : EObjectExt.getAll(sysEng_p, OaPackage.Literals.OPERATIONAL_ACTOR)) {
			availableElements.add((CapellaElement) opActor);
		}
		return availableElements;
	}

	/**
	 * Gets all the actors in the Operational Actor Package and all of its
	 * sub packages, except the actors that are already realized by the
	 * current actor. Refer MQRY_Actor_RealizedOperationalActors_1
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof Actor) {
			Actor currentActor = (Actor) element_p;
			availableElements.addAll(getRule_MQRY_Actor_RealizedOperationalActors_11(systemEngineering, currentActor));
		}
		availableElements = ListExt.removeDuplicates(availableElements);

		return availableElements;
	}

	/**
	 * Gets all the Actors in the Operational Actors Package and all of its
	 * sub packages, that are realized by the current actor.
	 * Refer MQRY_Actor_RealizedOperationalActors_1
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof Actor) {
			Actor currentActor = (Actor) element_p;
			for (OperationalActor actor : currentActor.getRealizedOperationalActors()) {
				currentElements.add(actor);
			}
		}

		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.ACTOR;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CtxPackage.Literals.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS);
  }
	
}
