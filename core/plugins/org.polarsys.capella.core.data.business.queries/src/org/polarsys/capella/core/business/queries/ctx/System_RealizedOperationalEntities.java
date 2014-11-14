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
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class System_RealizedOperationalEntities implements IBusinessQuery {

	/**
   * Gets all the entities in the Operational Analysis,
   * except the entities that are already realized by the current system.
   * 
   * Refer MQRY_System_RealizedOperationalEntities_1
	 * 
	 * @param sysEng_p
	 *            the {@link SystemEngineering}
	 * @param currentSystem_p
	 *            the current System
	 * @return list of {@link CapellaElement}
	 */
	private List<CapellaElement> getRule_MQRY_System_RealizedOperationalEntities_11(SystemEngineering sysEng_p, System currentSystem_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (EObject opEntity : EObjectExt.getAll(sysEng_p, OaPackage.Literals.ENTITY)) {
			availableElements.add((CapellaElement) opEntity);
		}
		return availableElements;
	}

	/**
	 * Gets all the entities in the Operational Analysis,
	 * except the entities that are already realized by the current system.
	 * 
	 * Refer MQRY_System_RealizedOperationalEntities_1
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof System) {
		  System currentSystem = (System) element_p;
			availableElements.addAll(getRule_MQRY_System_RealizedOperationalEntities_11(systemEngineering, currentSystem));
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

		if (element_p instanceof System) {
		  System currentSystem = (System) element_p;
			for (OperationalEntityRealization realization : currentSystem.getOwnedEntityRealizations()) {
				currentElements.add(realization.getAllocatedComponent());
			}
		}

		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.SYSTEM;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CtxPackage.Literals.SYSTEM__OWNED_ENTITY_REALIZATIONS);
  }
}
