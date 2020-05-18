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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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

public class GetAvailable_ComponentExchange_AllocatedFunctionalExchanges extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
		if (null == arch) {
			return availableElements;
		}
		if (element instanceof ComponentExchange) {
			ComponentExchange currentCapabilityUseCase = (ComponentExchange) element;
			availableElements.addAll(getRuleConnectionConnectionFunctionalExchangeAllocation(arch, currentCapabilityUseCase));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRuleConnectionConnectionFunctionalExchangeAllocation(BlockArchitecture sysEng, ComponentExchange element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		AbstractFunctionalBlock source = null;
		AbstractFunctionalBlock target = null;
		InformationsExchanger informationsExchangerSource = element.getSource();
		InformationsExchanger informationsExchangerTarget = element.getTarget();
		if ((informationsExchangerSource != null) && (informationsExchangerTarget != null)) {
			source = ComponentExchangeExt.getSourceComponent(element);
			target = ComponentExchangeExt.getTargetComponent(element);
			availableElements.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(source, target));
			availableElements.addAll(AbstractFunctionExt.getAllAllocatedFunctionalExchangeFiltered(target, source));
		}
		List<CapellaElement> allReadyAllocatedExchanges = new ArrayList<CapellaElement>();
		for (CapellaElement capellaElement : availableElements) {
			if (capellaElement instanceof FunctionalExchange) {
				FunctionalExchange exchange = (FunctionalExchange) capellaElement;
				EList<AbstractTrace> incomingTraces = exchange.getIncomingTraces();
				for (AbstractTrace abstractTrace : incomingTraces) {
					if (abstractTrace instanceof ComponentExchangeFunctionalExchangeAllocation && ((ComponentExchangeFunctionalExchangeAllocation)abstractTrace).getAllocatingComponentExchange() != element) {
						allReadyAllocatedExchanges.add(capellaElement);
					}
				}
			}
		}
		availableElements.removeAll(allReadyAllocatedExchanges);
		return availableElements;
	}

}