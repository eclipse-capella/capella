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
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Part_TypePorts extends AbstractQuery {

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
			availableElements.addAll(getRule_MQRY_PhysicalPart_TypePorts_11(property));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element_p.eContainer());
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_PhysicalPart_TypePorts_11(Part currentPhysicalPart_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		PhysicalComponent pc = (PhysicalComponent) currentPhysicalPart_p.getType();
		if (pc != null) {
			availableElements.addAll(pc.getOwnedPartitions());
		}
		return availableElements;
	}

}