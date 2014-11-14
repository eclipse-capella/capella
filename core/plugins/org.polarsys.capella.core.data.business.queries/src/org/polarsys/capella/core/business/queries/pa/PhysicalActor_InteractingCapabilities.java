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
package org.polarsys.capella.core.business.queries.pa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityExt;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class PhysicalActor_InteractingCapabilities implements IBusinessQuery {

	/**
	 * Gets all the capabilities in the Functional Aspect Package and all of its
	 * sub packages, except the capabilities that are already involved with the
	 * current actor. Refer MQRY_Actor_Capabilities_1
	 * 
	 * @param sysEng_p
	 *            the {@link SystemEngineering}
	 * @param currentActor_p
	 *            the current Actor
	 * @return list of {@link CapabilityUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_Actor_Capabilities_11(SystemEngineering sysEng_p, Actor currentActor_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng_p)) {
			if (CapabilityExt.hasInvolved(capabilityUseCase, currentActor_p))
				continue;
			availableElements.add(capabilityUseCase);
		}
		return availableElements;
	}

	/**
	 * Gets all the capabilities in the Functional Aspect Package and all of its
	 * sub packages, except the capabilities that are already involved with the
	 * current actor. Refer MQRY_Actor_Capabilities_1
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof Actor) {
			Actor currentActor = (Actor) element_p;

			availableElements.addAll(getRule_MQRY_Actor_Capabilities_11(systemEngineering, currentActor));
		} else if (element_p instanceof ActorPkg) {
			availableElements.addAll(CapellaElementExt.getAllCapabilities(systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);

		return availableElements;
	}

	/**
	 * Gets all the capabilities in the Functional Aspect Package and all of its
	 * sub packages, that are involved with the current actor. Refer
	 * MQRY_Actor_Capabilities_1
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof Actor) {

			Actor currentActor = (Actor) element_p;
			for (ActorCapabilityInvolvement involvement : currentActor.getParticipationsInCapabilities()) {
				currentElements.add(involvement.getCapability());
			}
		}

		return currentElements;
	}

	public EClass getEClass() {
		return PaPackage.Literals.PHYSICAL_ACTOR;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS);
	}
}
