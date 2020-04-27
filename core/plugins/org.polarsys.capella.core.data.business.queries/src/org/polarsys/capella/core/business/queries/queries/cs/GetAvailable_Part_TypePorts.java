/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
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
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		if (element instanceof Part) {
			Part property = (Part) element;
			availableElements.addAll(getRule_MQRY_PhysicalPart_TypePorts_11(property));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element.eContainer());
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_PhysicalPart_TypePorts_11(Part currentPhysicalPart) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		PhysicalComponent pc = (PhysicalComponent) currentPhysicalPart.getType();
		if (pc != null) {
			availableElements.addAll(pc.getContainedParts());
		}
		return availableElements;
	}

}