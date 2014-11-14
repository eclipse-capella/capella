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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalNode;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalNode_Deployers extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof PhysicalNode) {
			PhysicalNode pn = (PhysicalNode) element_p;
			List<CapellaElement> candidates = getRule_MQRY_PN_Deployers_11(pn);
			for (CapellaElement capellaElement : candidates) {
				if (!PhysicalComponentExt.isDeployedOn((PhysicalComponent) capellaElement, pn)) {
					availableElements.add(capellaElement);
				}
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element_p.eContainer());
		availableElements.remove(element_p);
		availableElements.removeAll(getCurrentElements(element_p, false));
		return availableElements;
	}

	/** 
	 * get all the PC within current PC's SystemEngineering if currentPC_p is a
	 * PhysicalNode, it can not be deployed on PhysicalComponent. Other cases
	 * are possible
	 * @param currentPC_pactual element
	 * @return all PC within currentPC_p's SystemEngineering
	 */
	private List<CapellaElement> getRule_MQRY_PN_Deployers_11(PhysicalComponent currentPC_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<PhysicalComponent> comps = SystemEngineeringExt.getAllPhysicalComponents(currentPC_p);
		for (PhysicalComponent physicalComponent : comps) {
			if (currentPC_p instanceof PhysicalNode) {
				if (physicalComponent instanceof PhysicalNode) {
					availableElements.add(physicalComponent);
				}
			} else {
				availableElements.add(physicalComponent);
			}
		}
		return availableElements;
	}

	/** 
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof PhysicalComponent) {
			PhysicalComponent pc = (PhysicalComponent) element_p;
			List<AbstractDeploymentLink> links = pc.getDeployingLinks();
			for (AbstractDeploymentLink abstractDeployment : links) {
				if (abstractDeployment instanceof TypeDeploymentLink) {
					currentElements.add(abstractDeployment.getLocation());
				}
			}
		}
		return currentElements;
	}

}