/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_ComponentExchange_Categories extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof ComponentExchange) {
			ComponentExchange componentExchange = (ComponentExchange) element;
			availableElements.addAll(getRule_MQRY_ComponentExchange_Categories_11(componentExchange));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_ComponentExchange_Categories_11(ComponentExchange componentExchange) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(componentExchange);
		availableElements.addAll(getElementsFromBlockArchitecture(arch));
		return availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof ComponentExchange) {
			ComponentExchange componentExchange = (ComponentExchange) element;
			EList<ComponentExchangeCategory> categories = componentExchange.getCategories();
			for (ComponentExchangeCategory categorie : categories) {
				currentElements.add(categorie);
			}
		}
		return currentElements;
	}

	/** 
	 * Gets all the component exchange categories from the BlockArchitecture
	 * @param arch
	 * @return list of ComponentExchangeCategories
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch != null) {
			for (EObject obj : EObjectExt.getAll(arch, FaPackage.Literals.COMPONENT_EXCHANGE_CATEGORY)) {
				availableElements.add((CapellaElement) obj);
			}
		}
		return availableElements;
	}

}