/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_Actor_FunctionalAllocation extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof Actor) {
			availableElements.addAll(getRule_MQRY_Component_FunctionalAllocation_11(SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering)));
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets available functions to be allocated to the component
	 * </p>
	 * @param element the component
	 * @return list of Function
	 */
	protected List<CapellaElement> getRule_MQRY_Component_FunctionalAllocation_11(BlockArchitecture arch) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<AbstractFunction> allLeafFunctions = FunctionExt.getAllLeafAbstractFunctions(arch);
		List<AbstractFunction> listTORemove = new ArrayList<AbstractFunction>();
		for (AbstractFunction function : allLeafFunctions) {
			if (!function.getAllocationBlocks().isEmpty()) {
				listTORemove.add(function);
			}
		}
		allLeafFunctions.removeAll(listTORemove);
		availableElements.addAll(allLeafFunctions);
		return availableElements;
	}

}