/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ComponentExchangeCategory_Exchanges extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		if (element instanceof ComponentExchangeCategory) {
			BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
			if (null != arch) {
				availableElements.addAll(getElementsFromBlockArchitecture(arch, (ComponentExchangeCategory) element));
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * get all the component exchanges from 'exchangeCategory' parent Block Architecture
	 * @param arch 
	 * @param exchangeCategory
	 * @return list of ComponentExchange
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, ComponentExchangeCategory exchangeCategory) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch != null) {
			for (EObject obj : EObjectExt.getAll(arch, FaPackage.Literals.COMPONENT_EXCHANGE)) {
				availableElements.add((CapellaElement) obj);
			}
		}
		return availableElements;
	}

}