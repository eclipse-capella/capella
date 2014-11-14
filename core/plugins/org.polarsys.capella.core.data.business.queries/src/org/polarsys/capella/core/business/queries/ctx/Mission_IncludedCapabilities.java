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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class Mission_IncludedCapabilities implements IBusinessQuery {

	/**
	 * <p>
	 * Gets all the capabilities that can be included to the mission except
	 * those which are already included.
	 * </p>
	 * <p>
	 * Refer MQRY_Mission_CapabilityUseCase_Included_11
	 * </p>
	 * 
	 * @param sysEng_p
	 *            the SystemEngineering Pkg
	 * @param currentMission_p
	 *            the current Mission
	 * @return list of CapabilityUseCases
	 */
	private List<CapellaElement> getRule_MQRY_Mission_CapabilityUseCase_Included_11(SystemEngineering sysEng_p, Mission currentMission_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng_p)) {
			if (AbstractCapabilityExt.isIncluded(capabilityUseCase, currentMission_p))
				continue;
			availableElements.add(capabilityUseCase);
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the capabilities that can be included to the mission except
	 * those which are already included.
	 * </p>
	 * <p>
	 * Refer MQRY_Mission_CapabilityUseCase_Included_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof Mission) {
			Mission currentMission = (Mission) element_p;
			availableElements.addAll(getRule_MQRY_Mission_CapabilityUseCase_Included_11(systemEngineering, currentMission));
		} else if (element_p instanceof MissionPkg) {
			availableElements.addAll(CapellaElementExt.getAllCapabilities(systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the capabilities that are included to the mission.
	 * </p>
	 * <p>
	 * Refer MQRY_ Mission_Capability_Included_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof Mission) {
			Mission currentMission = (Mission) element_p;
			for (CapabilityExploitation exploitation : currentMission.getOwnedCapabilityExploitations()) {
				currentElements.add(exploitation.getCapability());
			}
		}

		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.MISSION;
	}

	public List<EReference> getEStructuralFeatures() {
	  List<EReference> list = new ArrayList<EReference>(1);
	  list.add(CtxPackage.Literals.MISSION__OWNED_CAPABILITY_EXPLOITATIONS);
	  list.add(CtxPackage.Literals.MISSION__EXPLOITED_CAPABILITIES);
    
	  return list;
	}
}
