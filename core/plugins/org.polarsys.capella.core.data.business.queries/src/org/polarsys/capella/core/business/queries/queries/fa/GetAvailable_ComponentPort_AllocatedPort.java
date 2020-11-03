/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.fa;

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

public class GetAvailable_ComponentPort_AllocatedPort extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		if (element instanceof Port) {
			for (EObject port : getRule_MQRY_Port_AllocatedPorts_11((Port) element)) {
			  availableElements.add((CapellaElement) port);
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element);
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<EObject> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<EObject> currentElements = new ArrayList<EObject>();
		if (element instanceof Port) {
			Port elt = (Port) element;
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

	protected List<EObject> getRule_MQRY_Port_AllocatedPorts_11(Port element) {
		EObject ownerObj = element.eContainer();
		if (!(ownerObj instanceof Component)) {
			return Collections.emptyList();
		}
		List<EObject> resultFunctionPorts = new ArrayList<EObject>();
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