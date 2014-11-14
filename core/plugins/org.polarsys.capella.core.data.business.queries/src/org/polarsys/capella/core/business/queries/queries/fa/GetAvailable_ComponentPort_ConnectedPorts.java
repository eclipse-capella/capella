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

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ComponentPort_ConnectedPorts extends AbstractQuery {

	private ComponentPort thePort;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof Part) {
			Part property = (Part) element_p;
			availableElements.addAll(getRule_MQRY_StandardPort_ConnectedPorts_11(property));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.removeAll(getCurrentElements(element_p, false));
		availableElements.remove(thePort);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_StandardPort_ConnectedPorts_11(Part currentPhysicalPart_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PART, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE);
		if (query != null) {
			List<CapellaElement> pcs = query.getAvailableElements(currentPhysicalPart_p);
			pcs.add((CapellaElement) currentPhysicalPart_p.eContainer());
			List<Partition> allports = new ArrayList<Partition>(1);
			for (CapellaElement capellaElement : pcs) {
				if (capellaElement instanceof PhysicalComponent) {
					PhysicalComponent pc = (PhysicalComponent) capellaElement;
					allports.addAll(pc.getOwnedPartitions());
				}
			}
			for (Partition port : allports) {
				if (port instanceof ComponentPort && !PortExt.isNotCompatibleWith((ComponentPort) port, thePort)) {
					availableElements.add(port);
				}
			}
		}
		return availableElements;
	}

	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>(1);
		if (element_p instanceof Part && thePort != null) {
			for (ComponentExchange connection : thePort.getComponentExchanges()) {
				currentElements.addAll(FunctionalExt.getRelatedPorts(connection));
			}
		}
		return ListExt.removeDuplicates(currentElements);
	}

}