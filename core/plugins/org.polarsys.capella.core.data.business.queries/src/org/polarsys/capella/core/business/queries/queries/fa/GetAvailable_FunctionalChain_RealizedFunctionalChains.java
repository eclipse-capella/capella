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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_FunctionalChain_RealizedFunctionalChains extends AbstractQuery {

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
		BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element);
		if (null != rootBlockArchitecture) {
			EList<BlockArchitecture> allocatedArchitectures = rootBlockArchitecture.getAllocatedArchitectures();
			for (BlockArchitecture blockArchitecture : allocatedArchitectures) {
				if (null != blockArchitecture) {
					List<FunctionalChain> allFunctionalChains = FunctionalChainExt.getAllFunctionalChains(blockArchitecture);
					if (!allFunctionalChains.isEmpty()) {
						availableElements.addAll(allFunctionalChains);
					}
				}
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element);
		return availableElements;
	}

}