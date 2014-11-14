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
package org.polarsys.capella.core.business.queries.epbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.SystemComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class ConfigurationItem_InvolvedRealization implements IBusinessQuery {

	/*
	 * Gets all the realizations contained in the FunctionalAspect Package (and
	 * sub pkgs) of the EPBS Layer
	 */
	private List<CapellaElement> getRule_MQRY_ConfigurationItem_Realizations_11(ConfigurationItem currentConfigurationItem_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		EPBSArchitecturePkg epbsArchPkg = SystemEngineeringExt.getEPBSArchitecturePkg(systemEngineering_p);
		if (null != epbsArchPkg) {
			for (EPBSArchitecture epbsArch : epbsArchPkg.getOwnedEPBSArchitectures()) {
				for (CapabilityRealization realization : CapellaElementExt.getAllCapabilityRealizationInvolvedWith(epbsArch)) {
					if (SystemComponentExt.isRealizationInvolved(currentConfigurationItem_p, realization))
						continue;
					availableElements.add(realization);
				}
			}
		}
		EPBSArchitecture epbsArch = SystemEngineeringExt.getEPBSArchitecture(systemEngineering_p);
		if (null != epbsArch) {
			for (CapabilityRealization realization : CapellaElementExt.getAllCapabilityRealizationInvolvedWith(epbsArch)) {
				if (SystemComponentExt.isRealizationInvolved(currentConfigurationItem_p, realization))
					continue;
				availableElements.add(realization);
			}
		}
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof ConfigurationItem) {
			ConfigurationItem currentCI = (ConfigurationItem) element_p;
			availableElements.addAll(getRule_MQRY_ConfigurationItem_Realizations_11(currentCI, systemEngineering));

		}
		availableElements = ListExt.removeDuplicates(availableElements);

		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof ConfigurationItem) {
			currentElements.addAll(SystemComponentExt.getInvolvedCapabilityRealisations((ConfigurationItem) element_p));
		}

		return currentElements;
	}

	public EClass getEClass() {
		return EpbsPackage.Literals.CONFIGURATION_ITEM;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS);
	}
}
