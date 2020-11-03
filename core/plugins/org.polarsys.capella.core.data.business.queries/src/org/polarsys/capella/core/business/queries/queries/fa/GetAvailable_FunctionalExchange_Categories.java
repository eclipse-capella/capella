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
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_FunctionalExchange_Categories extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof FunctionalExchange) {
			FunctionalExchange functionalExchange = (FunctionalExchange) element;
			availableElements.addAll(getRule_MQRY_FunctionalExchange_Categories_11(functionalExchange));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_FunctionalExchange_Categories_11(FunctionalExchange functionalExchange) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(functionalExchange);
		availableElements.addAll(getElementsFromBlockArchitecture(arch));
		return availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof FunctionalExchange) {
			FunctionalExchange functionalExchange = (FunctionalExchange) element;
			EList<ExchangeCategory> categories = functionalExchange.getCategories();
			for (ExchangeCategory categorie : categories) {
				currentElements.add(categorie);
			}
		}
		return currentElements;
	}

	/** 
	 * Gets all the exchange categories from the BlockArchitecture
	 * @param arch
	 * @return list of ExchangeCategories
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch != null) {
			for (EObject obj : EObjectExt.getAll(arch, FaPackage.Literals.EXCHANGE_CATEGORY)) {
				availableElements.add((CapellaElement) obj);
			}
		}
		return availableElements;
	}

}