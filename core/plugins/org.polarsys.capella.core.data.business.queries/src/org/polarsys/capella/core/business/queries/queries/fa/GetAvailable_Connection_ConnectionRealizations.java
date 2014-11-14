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
import java.util.Collection;
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
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Connection_ConnectionRealizations extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering syseng = SystemEngineeringExt.getSystemEngineering(element_p);
		if (element_p instanceof ComponentExchange) {
			ComponentExchange ele = (ComponentExchange) element_p;
			availableElements.addAll(getElementsFromBlockArchitecture(syseng, ele));
		}
		return availableElements;
	}

	private Collection<? extends CapellaElement> getElementsFromBlockArchitecture(SystemEngineering sysEng_p, ComponentExchange currentElement_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture rootBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(currentElement_p);
		if (rootBlockArchitecture instanceof SystemAnalysis) {
			OperationalAnalysis ownedOperationalAnalysis = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng_p);
			for (EObject aComponentExchange : EObjectExt.getAll(ownedOperationalAnalysis, OaPackage.Literals.COMMUNICATION_MEAN)) {
				availableElements.add((CapellaElement) aComponentExchange);
			}
		} else if (rootBlockArchitecture instanceof LogicalArchitecture) {
			SystemAnalysis ownedContextArchitecture = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng_p);
			for (EObject aComponentExchange : EObjectExt.getAll(ownedContextArchitecture, FaPackage.Literals.COMPONENT_EXCHANGE)) {
				availableElements.add((CapellaElement) aComponentExchange);
			}
		} else if (rootBlockArchitecture instanceof PhysicalArchitecture) {
			LogicalArchitecture ownedLogicalArchitecture = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng_p);
			for (EObject aComponentExchange : EObjectExt.getAll(ownedLogicalArchitecture, FaPackage.Literals.COMPONENT_EXCHANGE)) {
				availableElements.add((CapellaElement) aComponentExchange);
			}
		}
		List<CapellaElement> currentElements = getCurrentElements(currentElement_p, false);
		for (CapellaElement capellaElement : currentElements) {
			availableElements.remove(capellaElement);
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof ComponentExchange) {
			ComponentExchange ele = (ComponentExchange) element_p;
			EList<ComponentExchangeRealization> ownedComponentExchangeRealisations = ele.getOwnedComponentExchangeRealizations();
			for (ComponentExchangeRealization componentExchangeRealisation : ownedComponentExchangeRealisations) {
				currentElements.add((CapellaElement) componentExchangeRealisation.getTargetElement());
			}
		}
		return currentElements;
	}

}