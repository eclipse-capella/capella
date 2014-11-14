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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.ServiceExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Service_ThrownException extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets...
	 * </p>
	 * <p>
	 * All the Exceptions contained by the Exception Package (and all of its
	 * subpackages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 * </p>
	 * <p>
	 * All the Exceptions contained by the Exception Packages (and all of its
	 * subpackages) of the current Element's parents hierarchy according to
	 * layer visibility and multiple decomposition rules.
	 * </p>
	 * <p>
	 * All the Exceptions contained by the Shared package (and all of its
	 * sub-packages).
	 * </p>
	 * <p>
	 * Except the currently thrown Exception
	 * </p>
	 * <p>
	 * See rule MQRY_Service_Throw_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		boolean isServiceFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
			if (sharedPkg != null) {
				for (ReuseLink link : sharedPkg.getReuseLinks()) {
					if (SystemEngineeringExt.getSystemEngineering(link) != null) {
						systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
						isServiceFromSharedPkg = true;
						break;
					}
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element_p instanceof Service) {
			Service currentService = (Service) element_p;
			if (!isServiceFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Service_Throw_11(currentService));
				availableElements.addAll(getRule_MQRY_Service_Throw_12(currentService));
			}
			availableElements.addAll(getRule_MQRY_Service_Throw_13(currentService, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_Throw_11(Service currentService_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		availableElements.addAll(ServiceExt.getFilteredExceptions(currentService_p, ServiceExt.getExceptionsFromRootComponentArchitecture(currentService_p)));
		availableElements.addAll(ServiceExt.getFilteredExceptions(currentService_p, ServiceExt.getExceptionsFromRootComponent(currentService_p)));
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_Throw_12(Service currentService_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		availableElements.addAll(ServiceExt.getFilteredExceptions(currentService_p, ServiceExt.getExceptionsFromParentHierarchy(currentService_p)));
		availableElements.addAll(getRule_MQRY_Service_Throw_12_1(currentService_p));
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_Throw_13(Service currentService_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				availableElements.addAll(ServiceExt.getFilteredExceptions(currentService_p, GenericPkgExt.getAllExceptions(pkg)));
			}
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (dataPkg != null) {
				availableElements.addAll(ServiceExt.getFilteredExceptions(currentService_p, DataPkgExt.getAllExceptions(dataPkg)));
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_Throw_12_1(Service currentService_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		ComponentArchitecture arch = ServiceExt.getRootComponentArchitecture(currentService_p);
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentService_p);
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
		availableElements.addAll(ServiceExt.getExceptionsFromComponentArchitecture(ca));
		if (arch != null) {
			if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				availableElements.addAll(ServiceExt.getExceptionsFromComponentArchitecture(SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering)));
			}
			if (arch instanceof EPBSArchitecture) {
				availableElements.addAll(ServiceExt.getExceptionsFromComponentArchitecture(SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering)));
			}
		}
		return availableElements;
	}

}