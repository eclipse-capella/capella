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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
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
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		if (element instanceof PhysicalNode) {
			PhysicalNode pn = (PhysicalNode) element;
			List<CapellaElement> candidates = getRule_MQRY_PN_Deployers_11(pn);
			for (CapellaElement capellaElement : candidates) {
				if (!PhysicalComponentExt.isDeployedOn((PhysicalComponent) capellaElement, pn)) {
					availableElements.add(capellaElement);
				}
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element.eContainer());
		availableElements.remove(element);
		return availableElements;
	}

	/** 
	 * get all the PC within current PC's SystemEngineering if currentPC is a
	 * PhysicalNode, it can not be deployed on PhysicalComponent. Other cases
	 * are possible
	 * @param currentPCactual element
	 * @return all PC within currentPC's SystemEngineering
	 */
	private List<CapellaElement> getRule_MQRY_PN_Deployers_11(PhysicalComponent currentPC) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<PhysicalComponent> comps = SystemEngineeringExt.getAllPhysicalComponents(currentPC);
		for (PhysicalComponent physicalComponent : comps) {
			if (currentPC instanceof PhysicalNode) {
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
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof PhysicalComponent) {
			PhysicalComponent pc = (PhysicalComponent) element;
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