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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_PhysicalLink_RealizedPhysicalLinks extends AbstractQuery {

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
		if (element instanceof PhysicalLink) {
			availableElements.addAll(getRule_MQRY_PhysicalLink_RealizedPhysicalLinks_11((PhysicalLink) element));
		}
		return availableElements;
	}

	/** 
	 * @param element the physical link
	 * @return list of physical links
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalLink_RealizedPhysicalLinks_11(PhysicalLink element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> allPhysicalLinks = new ArrayList<EObject>();
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
		for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
			allPhysicalLinks.addAll(EObjectExt.getAll(block, CsPackage.Literals.PHYSICAL_LINK));
		}
		for (EObject function : allPhysicalLinks) {
			availableElements.add((CapellaElement) function);
		}
		return availableElements;
	}

}