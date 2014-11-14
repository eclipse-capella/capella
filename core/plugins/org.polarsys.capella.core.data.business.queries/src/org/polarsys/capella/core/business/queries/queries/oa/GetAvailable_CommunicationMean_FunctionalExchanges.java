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
package org.polarsys.capella.core.business.queries.queries.oa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.capellacore.services.CapellaElementExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_CommunicationMean_FunctionalExchanges extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
		if (null == arch) {
			return availableElements;
		}
		if (element_p instanceof CommunicationMean) {
			CommunicationMean currentCapabilityUseCase = (CommunicationMean) element_p;
			availableElements.addAll(getRule_MQRY_RealizableExchangesFromCommunicationMean(arch, currentCapabilityUseCase));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_RealizableExchangesFromCommunicationMean(BlockArchitecture sysEng_p, CommunicationMean element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		Entity sourceEntity = (Entity) element_p.getSource();
		Entity targetEntity = (Entity) element_p.getTarget();
		availableElements.addAll(getAllocationFunctionalExchangeByRoles(sourceEntity, targetEntity));
		EList<FunctionalExchange> functionalExchanges = element_p.getAllocatedFunctionalExchanges();
		for (FunctionalExchange functionalExchange : functionalExchanges) {
			availableElements.remove(functionalExchange);
		}
		return availableElements;
	}

	/** 
	 * @param sourceEntity_p
	 * @param targetEntity_p
	 * @return
	 */
	private Collection<? extends FunctionalExchange> getAllocationFunctionalExchangeByRoles(Entity sourceEntity_p, Entity targetEntity_p) {
		List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
		List<AbstractFunction> sourceActivities = new ArrayList<AbstractFunction>();
		List<AbstractFunction> targetActivities = new ArrayList<AbstractFunction>();
		List<PartitionableElement> allSourceDescendants = CapellaElementExt.getAllDescendants(sourceEntity_p);
		allSourceDescendants.add(sourceEntity_p);
		List<PartitionableElement> allTargetDescendants = CapellaElementExt.getAllDescendants(targetEntity_p);
		allTargetDescendants.add(targetEntity_p);
		for (PartitionableElement partitionableElement : allSourceDescendants) {
			if (partitionableElement instanceof Entity) {
				for (RoleAllocation ra : ((Entity) partitionableElement).getOwnedRoleAllocations()) {
					for (ActivityAllocation aa : ra.getRole().getActivityAllocations()) {
						sourceActivities.add(aa.getActivity());
					}
				}
			}
			if (partitionableElement instanceof AbstractFunctionalBlock) {
				AbstractFunctionalBlock absFunBlock = (AbstractFunctionalBlock) partitionableElement;
				sourceActivities.addAll(absFunBlock.getAllocatedFunctions());
			}
		}
		for (PartitionableElement partitionableElement : allTargetDescendants) {
			if (partitionableElement instanceof Entity) {
				for (RoleAllocation ra : ((Entity) partitionableElement).getOwnedRoleAllocations()) {
					for (ActivityAllocation aa : ra.getRole().getActivityAllocations()) {
						targetActivities.add(aa.getActivity());
					}
				}
			}
			if (partitionableElement instanceof AbstractFunctionalBlock) {
				AbstractFunctionalBlock absFunBlock = (AbstractFunctionalBlock) partitionableElement;
				targetActivities.addAll(absFunBlock.getAllocatedFunctions());
			}
		}
		for (AbstractFunction abstractFun : sourceActivities) {
			List<FunctionalExchange> outGoingExchange = FunctionExt.getOutGoingExchange(abstractFun);
			for (ActivityEdge trace : outGoingExchange) {
				if (trace instanceof FunctionalExchange) {
					FunctionalExchange fe = (FunctionalExchange) trace;
					if (targetActivities.contains(fe.getTarget())) {
						result.add(fe);
					}
				}
			}
		}
		return result;
	}

}