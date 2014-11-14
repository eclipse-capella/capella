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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class PhysicalArchitecture_AllocatedLogicalArchitecture implements IBusinessQuery {

	/*
	 * All the Logical Architectures contained by the current Element's
	 * alternative decomposition hierarchy.
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalArch_Allocation_11(PhysicalArchitecture currentPhysicalArchitecture_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering parentSystemEngineering = PhysicalArchitectureExt.getParentSystemEngineering(currentPhysicalArchitecture_p);
		availableElements.addAll(SystemEngineeringExt.getLogicalArchitecturesFiltered(parentSystemEngineering, currentPhysicalArchitecture_p));
		return availableElements;
	}

	/**
	 * Gets...
	 * <p>
	 * All the Logical Architectures contained by the current Element's
	 * alternative decomposition hierarchy.
	 * </p>
	 * <p>
	 * Except The current Logical Architectures.
	 * </p>
	 * <p>
	 * Refer MQRY_PhysicalArch_Allocation_1
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

		if (element_p instanceof PhysicalArchitecture) {
			PhysicalArchitecture physicalArchitecture = (PhysicalArchitecture) element_p;
			availableElements.addAll(getRule_MQRY_PhysicalArch_Allocation_11(physicalArchitecture));
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Refer MQRY_PhysicalArchitecture_Allocation_1
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

		if (element_p instanceof PhysicalArchitecture) {
			PhysicalArchitecture physicalArchitecture = (PhysicalArchitecture) element_p;
			currentElements.addAll(physicalArchitecture.getAllocatedArchitectures());
		}
		return currentElements;
	}

	public EClass getEClass() {
		return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS);
	}
}
