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
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.helpers.pa.services.ComponentInstanceExt;
import org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ComponentInstance_Deployments extends AbstractQuery {

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
		if (element instanceof ComponentInstance) {
			ComponentInstance pc = (ComponentInstance) element;
			List<CapellaElement> candidates = getRule_MQRY_PO_Deployments_11(pc);
			for (CapellaElement capellaElement : candidates) {
				if (!ComponentInstanceExt.isDeployedOn(pc, (ComponentInstance) capellaElement)) {
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
	 * get all the available POs
	 * @param currentPOactual element
	 * @return all PO available from the instantiated PhysicalComponent
	 */
	private List<CapellaElement> getRule_MQRY_PO_Deployments_11(ComponentInstance currentPO) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<AbstractPhysicalInstance> allPOs = SystemEngineeringExt.getAllPhysicalObject(currentPO);
		Type instantiatedPC = currentPO.getType();
		for (AbstractPhysicalInstance physObj : allPOs) {
			if (physObj instanceof ComponentInstance) {
				for (AbstractDeploymentLink abstDepl : ((ComponentInstance) physObj).getType().getDeployingLinks()) {
					if (abstDepl.getLocation() == instantiatedPC) {
						availableElements.add(physObj);
					}
				}
			}
		}
		return availableElements;
	}

	/** 
	 * @param onlyGenerated
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof ComponentInstance) {
			ComponentInstance pc = (ComponentInstance) element;
			List<AbstractDeploymentLink> links = pc.getDeploymentLinks();
			for (AbstractDeploymentLink abstractDeployment : links) {
				if (abstractDeployment instanceof TypeDeploymentLink) {
					currentElements.add(abstractDeployment.getDeployedElement());
				}
			}
		}
		return currentElements;
	}

}