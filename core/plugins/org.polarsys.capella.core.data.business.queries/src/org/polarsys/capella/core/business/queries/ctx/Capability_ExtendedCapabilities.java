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
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class Capability_ExtendedCapabilities implements IBusinessQuery {

	/**
	 * <p>
	 * Gets all the capabilities except those that are in the inheritance
	 * hierarchy of the current Capability and those that are already
	 * extended/included by the current capability from the system engineering
	 * package.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Extended_11
	 * </p>
	 * 
	 * @param sysEng_p
	 *            the system engineering
	 * @param currentCapabilityUseCase_p
	 *            the current capability
	 * @param isFilterRequired
	 *            flag for checking with current capability
	 * @return list of {@link CapabilityUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_Capability_Extended_11(SystemEngineering sysEng_p, Capability currentCapabilityUseCase_p, boolean isFilterRequired) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng_p)) {
				if (isFilterRequired) {
					if (currentCapabilityUseCase_p.equals(capabilityUseCase))
						continue;
					if (!AbstractCapabilityExt.isRelated(currentCapabilityUseCase_p, capabilityUseCase)
							&& !AbstractCapabilityExt.isRelated(capabilityUseCase, currentCapabilityUseCase_p)) {
						availableElements.add(capabilityUseCase);
					}
				} else {
					availableElements.add(capabilityUseCase);
				}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the capabilities except those that are already
	 * extended/included/inherited by the current capability.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Extended_1
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

		if (element_p instanceof Capability) {
			Capability currentCapabilityUseCase = (Capability) element_p;
			availableElements.addAll(getRule_MQRY_Capability_Extended_11(systemEngineering, currentCapabilityUseCase, true));
		} else if (element_p instanceof CapabilityPkg) {
			availableElements.addAll(getRule_MQRY_Capability_Extended_11(systemEngineering, null, false));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * <p>
	 * Gets the Capabilities included/extended/inherited by the current
	 * capability.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Extended_1
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

		if (element_p instanceof Capability) {
			Capability currentCapabilityUseCase = (Capability) element_p;
			for (AbstractCapabilityExtend extend : currentCapabilityUseCase.getExtends()) {
				currentElements.add(extend.getExtended());
			}
		}

		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.CAPABILITY;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES);
  }
	
}
