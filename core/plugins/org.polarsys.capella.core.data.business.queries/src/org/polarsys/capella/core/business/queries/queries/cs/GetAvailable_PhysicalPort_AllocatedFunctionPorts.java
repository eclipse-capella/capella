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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalPort_AllocatedFunctionPorts extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof Port) {
			List<CapellaElement> currentElements = getCurrentElements(element, false);
			for (EObject port : getRule_MQRY_Port_AllocatedPorts_11((Port) element)) {
				if (!currentElements.contains(port)) {
					availableElements.add((CapellaElement) port);
				}
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element);
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof Port) {
			Port elt = (Port) element_p;
			EList<PortAllocation> portAllocations = elt.getOutgoingPortAllocations();
			for (PortAllocation portAllocation : portAllocations) {
				if (portAllocation.getAllocatedPort() != null) {
					currentElements.add(portAllocation.getAllocatedPort());
				}
			}
			currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(elt);
		}
		return currentElements;
	}

	protected List<CapellaElement> getRule_MQRY_Port_AllocatedPorts_11(Port element_p) {
		EObject ownerObj = element_p.eContainer();
		if (!(ownerObj instanceof Component)) {
			return Collections.emptyList();
		}
		List<CapellaElement> resultFunctionPorts = new ArrayList<CapellaElement>();
		List<Component> componentsHierarchy = ComponentExt.getAllSubUsedAndDeployedComponents((Component) ownerObj);
		for (Component component : componentsHierarchy) {
			for (ComponentFunctionalAllocation alloc : component.getFunctionalAllocations()) {
				if ((alloc.getTargetElement() != null) && (alloc.getTargetElement() instanceof AbstractFunction)) {
					resultFunctionPorts.addAll(FunctionExt.getOwnedFunctionPorts((AbstractFunction) alloc.getTargetElement()));
				}
			}
		}
		return ListExt.removeDuplicates(resultFunctionPorts);
	}

}