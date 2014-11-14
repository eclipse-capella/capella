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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.LogicalComponentPkgExt;
import org.polarsys.capella.core.model.helpers.SystemComponentExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class LogicalComponent_InvolvedRealizations implements IBusinessQuery {

	/**
	 * <p>
	 * Gets all the Realizations contained in the Functional Aspect Package (and
	 * all of its sub-packages) of the current Logical Component's parent (Refer
	 * MQRY_LogicalComponent_Realizations_11)
	 * </p>
	 * 
	 * @param currentLC_p
	 *            the current LogicalComponent
	 * @param parentLC_p
	 *            the parent
	 * @return list of {@link CapabilityRealizationUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_LogicalComponentRealizations_11(LogicalComponent currentLC_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		Object parent = currentLC_p.eContainer();
		if (null != parent) {
			if (parent instanceof LogicalComponentPkg) {
				LogicalComponentPkg rootLCPkg = LogicalComponentPkgExt.getRootLogicalComponentPkg((LogicalComponentPkg) parent);
				parent = rootLCPkg.eContainer();
			}
			if (parent instanceof LogicalComponent || parent instanceof LogicalArchitecture) {
				availableElements.addAll(SystemComponentExt.getCapabilityRealizationUseCasesFiltered(currentLC_p, (CapellaElement) parent));
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the Realizations contained in the Functional Aspect Package (and
	 * all of its sub-packages) of the current Logical Component's parent (can
	 * be a Logical Component, a Logical Architecture Decomposition package, or
	 * the Logical Architecture root package).
	 * </p>
	 * <p>
	 * Except The Realizations that are already involved with the current
	 * Logical Component.
	 * </p>
	 * <p>
	 * Refer MQRY_ LogicalComponent_Realizations_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (null == systemEngineering) {
			return availableElements;
		}

		// Update Mode
		if (element_p instanceof LogicalComponent) {
			LogicalComponent currentLC = (LogicalComponent) element_p;
			availableElements.addAll(getRule_MQRY_LogicalComponentRealizations_11(currentLC));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the capability realizations involved with the current logical
	 * component
	 * </p>
	 * <p>
	 * Refer MQRY_ LogicalComponent_Realizations_1
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

		if (element_p instanceof LogicalComponent) {
			currentElements.addAll(SystemComponentExt.getInvolvedCapabilityRealisations((LogicalComponent) element_p));
		}
		return currentElements;
	}

	public EClass getEClass() {
		return LaPackage.Literals.LOGICAL_COMPONENT;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CsPackage.Literals.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS);
	}
}
