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
package org.polarsys.capella.core.business.queries.la;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class CapabilityRealization_InheritedCapabilities implements IBusinessQuery {

	/**
	 * <p>
	 * Gets all the capabilities except those that are in the inheritance
	 * hierarchy of the current Capability and those that are already
	 * extended/included by the current capability from the system engineering
	 * package.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Inherited_11
	 * </p>
	 * 
	 * @param sysEng_p
	 *            the system engineering
	 * @param currentCapabilityUseCase_p
	 *            the current capability
	 * @param isFilterRequired
	 *            flag for checking with current capability
	 * @return list of {@link CapabilityRealizationUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_CapabiiltyUseCase_Inherited_11(SystemEngineering sysEng_p, CapabilityRealization currentCapabilityUseCase_p, boolean isFilterRequired) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng_p)) {
			if (capabilityUseCase instanceof CapabilityRealization) {
				if (isFilterRequired) {
					if (currentCapabilityUseCase_p.equals(capabilityUseCase)) {
						continue;
					}
					if (!AbstractCapabilityExt.isRelated(currentCapabilityUseCase_p, capabilityUseCase)
							&& !AbstractCapabilityExt.isRelated(capabilityUseCase, currentCapabilityUseCase_p)) {
						availableElements.add(capabilityUseCase);
					}
				} else {
					availableElements.add(capabilityUseCase);
				}
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the capabilities except those that are in the inheritance
	 * hierarchy of the current Capability from teh system engineering package.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Inherited_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof CapabilityRealization) {
			CapabilityRealization currentCapabilityUseCase = (CapabilityRealization) element_p;
			availableElements.addAll(getRule_MQRY_CapabiiltyUseCase_Inherited_11(systemEngineering, currentCapabilityUseCase, true));
		} else if (element_p instanceof CapabilityRealizationPkg) {
			availableElements.addAll(getRule_MQRY_CapabiiltyUseCase_Inherited_11(systemEngineering, null, false));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the capabilities that extend the current Capability.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Inherited_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof CapabilityRealization) {
			CapabilityRealization currentCapabilityUseCase = (CapabilityRealization) element_p;
			currentElements.addAll(currentCapabilityUseCase.getSuper());
			currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(currentCapabilityUseCase);
		}

		return currentElements;
	}

	public EClass getEClass() {
		return LaPackage.Literals.CAPABILITY_REALIZATION;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS);
	}
}
