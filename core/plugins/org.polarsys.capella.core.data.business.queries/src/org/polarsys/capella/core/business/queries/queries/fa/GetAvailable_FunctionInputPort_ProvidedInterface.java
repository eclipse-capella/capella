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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_FunctionInputPort_ProvidedInterface extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the Interfaces contained by the Interface Package (and all of
	 * its subpackages) of the current Element's parent (can be a Logical
	 * Component, a Block Architecture root package or the SystemEngineering
	 * package).
	 * </p>
	 * <p>
	 * Gets all the Interfaces contained by the Interface Package (and all of
	 * its subpackages) of the current Element's parents hierarchy.
	 * </p>
	 * <p>
	 * Except the current Interface itself and interfaces in the inheritance
	 * hierarchy of the current Interface
	 * </p>
	 * <p>
	 * Refer MQRY_Interface_Inherited_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (element_p instanceof Port) {
			Port ele = (Port) element_p;
			availableElements.addAll(getRule_MQRY_Port_ProvidedInterfaces_11(systemEngineering, ele));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element_p);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Port_ProvidedInterfaces_11(SystemEngineering systemEng_p, Port element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = null;
		arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
		availableElements.addAll(getElementsFromBlockArchitecture(arch, element_p));
		if (!(arch instanceof OperationalAnalysis)) {
			availableElements.addAll(getRule_MQRY_Port_ProvidedInterfaces_11_1(systemEng_p, element_p));
		}
		return availableElements;
	}

	/** 
	 * All the Interfaces contained by the Interface Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, Port element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		InterfacePkg interfacePkg = arch_p.getOwnedInterfacePkg();
		if (interfacePkg != null) {
			for (Interface inter : InterfacePkgExt.getAllInterfaces(interfacePkg)) {
				if (inter != null) {
					availableElements.add(inter);
				}
			}
		}
		EObject container = element_p.eContainer();
		if (container != null && container instanceof Component) {
			Component parentComponent = (Component) container;
			if (parentComponent != null) {
				for (Interface inter : InterfacePkgExt.getAllInterfaces(parentComponent.getOwnedInterfacePkg())) {
					if (inter != null) {
						availableElements.add(inter);
					}
				}
			}
		}
		for (Interface itf : element_p.getProvidedInterfaces()) {
			availableElements.remove(itf);
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Port_ProvidedInterfaces_11_1(SystemEngineering systemEng_p, Port element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = null;
		arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
		OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEng_p);
		if (null != oa) {
			availableElements.addAll(getElementsFromBlockArchitecture(oa, element_p));
		} else {
			SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEng_p);
			availableElements.addAll(getElementsFromBlockArchitecture(ca, element_p));
		}
		if (null != arch) {
			if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture)) {
				SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(systemEng_p);
				availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, element_p));
			}
			if ((arch instanceof PhysicalArchitecture)) {
				LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEng_p);
				availableElements.addAll(getElementsFromBlockArchitecture(logArch, element_p));
			}
		}
		return availableElements;
	}

}