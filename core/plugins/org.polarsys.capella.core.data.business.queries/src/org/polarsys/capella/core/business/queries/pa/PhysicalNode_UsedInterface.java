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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class PhysicalNode_UsedInterface implements IBusinessQuery {

	/**
	 * <p>
	 * Gets All the Interfaces contained in the Interface Package (and all of
	 * its sub-packages) of the Physical Architecture layer.
	 * </p>
	 * <p>
	 * Except The interfaces that are already used by the current Physical
	 * Component.
	 * </p>
	 * <p>
	 * Refer MQRY_ PhysicalComponent_UsedInterfaces_1
	 * </p>
	 * 
	 * @param systemEngineering_p
	 *            the {@link System}
	 * @param currentPC_p
	 *            the current {@link PhysicalComponent}
	 * @return list of interfaces
	 */
	private List<CapellaElement> getRule_MQRY_PC_UsedInterfaces_11(SystemEngineering systemEngineering_p, PhysicalComponent currentPC_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		availableElements.addAll(PhysicalArchitectureExt.getOwnedInterfacesFromPhysicalLayerFiltered(systemEngineering_p, currentPC_p, true));
		return availableElements;
	}

	/**
	 * <p>
	 * Gets All the Interfaces contained in the Interface Package (and all of
	 * its sub-packages) of the Physical Architecture layer.
	 * </p>
	 * <p>
	 * Except The interfaces that are already used by the current Physical
	 * Component.
	 * </p>
	 * <p>
	 * Refer MQRY_ PhysicalComponent_UsedInterfaces_1
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

		if (element_p instanceof PhysicalComponent) {
			PhysicalComponent currentPC = (PhysicalComponent) element_p;

			availableElements.addAll(getRule_MQRY_PC_UsedInterfaces_11(systemEngineering, currentPC));
		} else if (element_p instanceof PhysicalArchitecture || element_p instanceof PhysicalComponentPkg) {
			availableElements.addAll(getRule_MQRY_PC_UsedInterfaces_11(systemEngineering, null));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * *
	 * <p>
	 * Gets all the interfaces that are used by the current Physical Component.
	 * </p>
	 * <p>
	 * Refer MQRY_ PhysicalComponent_UsedInterfaces_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof PhysicalComponent) {
			currentElements.addAll(ComponentExt.getUsedInterfaces((PhysicalComponent) element_p));
		}
		return currentElements;
	}

	public EClass getEClass() {
		return PaPackage.Literals.PHYSICAL_NODE;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CsPackage.Literals.COMPONENT__USED_INTERFACES);
	}
}
