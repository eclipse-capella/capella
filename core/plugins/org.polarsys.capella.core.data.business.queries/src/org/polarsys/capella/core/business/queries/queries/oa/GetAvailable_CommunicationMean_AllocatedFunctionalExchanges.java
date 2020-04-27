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
package org.polarsys.capella.core.business.queries.queries.oa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_CommunicationMean_AllocatedFunctionalExchanges extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
		if (null == arch) {
			return availableElements;
		}
		if (element instanceof CommunicationMean) {
			CommunicationMean currentCapabilityUseCase = (CommunicationMean) element;
			availableElements.addAll(getRule_MQRY_RealizableExchangesFromCommunicationMean(arch, currentCapabilityUseCase));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_RealizableExchangesFromCommunicationMean(BlockArchitecture sysEng, CommunicationMean element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		Entity sourceEntity = (Entity) element.getSource();
		Entity targetEntity = (Entity) element.getTarget();
		availableElements.addAll(getAllocationFunctionalExchangeByRoles(sourceEntity, targetEntity));
		EList<FunctionalExchange> functionalExchanges = element.getAllocatedFunctionalExchanges();
		for (FunctionalExchange functionalExchange : functionalExchanges) {
			availableElements.remove(functionalExchange);
		}
		return availableElements;
	}

	/** 
	 * @param sourceEntity
	 * @param targetEntity
	 * @return
	 */
	private Collection<? extends FunctionalExchange> getAllocationFunctionalExchangeByRoles(Entity sourceEntity, Entity targetEntity) {
		List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
		List<AbstractFunction> sourceActivities = new ArrayList<AbstractFunction>();
		List<AbstractFunction> targetActivities = new ArrayList<AbstractFunction>();
		Collection<Component> allSourceDescendants = ComponentExt.getAllSubUsedComponents(sourceEntity);
		allSourceDescendants.add(sourceEntity);
		Collection<Component> allTargetDescendants = ComponentExt.getAllSubUsedComponents(targetEntity);
		allTargetDescendants.add(targetEntity);
		for (Component partitionableElement : allSourceDescendants) {
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
		for (Component partitionableElement : allTargetDescendants) {
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