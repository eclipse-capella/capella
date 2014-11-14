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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Connection_ConnectionFunctionalExchangeAllocations extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
		if (null == arch) {
			return availableElements;
		}
		if (element_p instanceof ComponentExchange) {
			ComponentExchange currentCapabilityUseCase = (ComponentExchange) element_p;
			availableElements.addAll(getRuleConnectionConnectionFunctionalExchangeAllocation(arch, currentCapabilityUseCase));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRuleConnectionConnectionFunctionalExchangeAllocation(BlockArchitecture sysEng_p, ComponentExchange element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		AbstractFunctionalBlock source = null;
		AbstractFunctionalBlock target = null;
		InformationsExchanger informationsExchangerSource = element_p.getSource();
		InformationsExchanger informationsExchangerTarget = element_p.getTarget();
		if ((informationsExchangerSource != null) && (informationsExchangerTarget != null)) {
			source = ComponentExchangeExt.getSourceComponent(element_p);
			target = ComponentExchangeExt.getTargetComponent(element_p);
			availableElements.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(source, target));
			availableElements.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(target, source));
		}
		List<CapellaElement> allReadyAllocatedExchanges = new ArrayList<CapellaElement>();
		for (CapellaElement capellaElement : availableElements) {
			if (capellaElement instanceof FunctionalExchange) {
				FunctionalExchange exchange = (FunctionalExchange) capellaElement;
				EList<AbstractTrace> incomingTraces = exchange.getIncomingTraces();
				for (AbstractTrace abstractTrace : incomingTraces) {
					if (abstractTrace instanceof ComponentExchangeFunctionalExchangeAllocation) {
						allReadyAllocatedExchanges.add(capellaElement);
					}
				}
			}
		}
		return availableElements;
	}

}