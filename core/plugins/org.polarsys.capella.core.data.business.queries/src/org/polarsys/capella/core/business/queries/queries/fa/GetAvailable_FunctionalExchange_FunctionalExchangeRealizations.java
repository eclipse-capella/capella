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
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_FunctionalExchange_FunctionalExchangeRealizations extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		List<BlockArchitecture> exploredArchitectures = new ArrayList<BlockArchitecture>();
		EObject currentArchitecture = element;
		while (!(currentArchitecture instanceof BlockArchitecture)) {
			currentArchitecture = currentArchitecture.eContainer();
			if (currentArchitecture == null) {
				return availableElements;
			}
		}
		SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (sysEng == null) {
			return availableElements;
		}
		if (currentArchitecture instanceof SystemAnalysis) {
			exploredArchitectures.add(SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng));
		} else if (currentArchitecture instanceof LogicalArchitecture) {
			exploredArchitectures.add(SystemEngineeringExt.getOwnedSystemAnalysis(sysEng));
		} else if (currentArchitecture instanceof PhysicalArchitecture) {
			exploredArchitectures.addAll(SystemEngineeringExt.getAllLogicalArchitecture(element));
		} else {
			return availableElements;
		}
		for (BlockArchitecture anArchitecture : exploredArchitectures) {
			for (EObject aFunctionalExchange : EObjectExt.getAll(anArchitecture, FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
				availableElements.add((CapellaElement) aFunctionalExchange);
			}
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof FunctionalExchange) {
			FunctionalExchange ele = (FunctionalExchange) element;
			EList<FunctionalExchangeRealization> ownedFunctionalExchangeRealisations = ele.getOwnedFunctionalExchangeRealizations();
			for (FunctionalExchangeRealization functionalExchangeRealisation : ownedFunctionalExchangeRealisations) {
				currentElements.add((CapellaElement) functionalExchangeRealisation.getTargetElement());
			}
		}
		return currentElements;
	}

}