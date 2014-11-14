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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_PhysicalLink_Categories extends AbstractQuery {

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
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof PhysicalLink) {
			PhysicalLink physicalLink = (PhysicalLink) element_p;
			availableElements.addAll(getRule_MQRY_PhysicalLink_Categories_11(physicalLink));
			List<CapellaElement> currentElements = getCurrentElements(physicalLink, false);
			if (!currentElements.isEmpty() && !availableElements.isEmpty()) {
				availableElements.removeAll(currentElements);
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_PhysicalLink_Categories_11(PhysicalLink physicalLink_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(physicalLink_p);
		availableElements.addAll(getElementsFromBlockArchitecture(arch));
		return availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof PhysicalLink) {
			PhysicalLink physicalLink = (PhysicalLink) element_p;
			EList<PhysicalLinkCategory> categories = physicalLink.getCategories();
			for (PhysicalLinkCategory categorie : categories) {
				currentElements.add(categorie);
			}
		}
		return currentElements;
	}

	/** 
	 * Gets all the physical link categories from the BlockArchitecture
	 * @param arch_p
	 * @return list of PhysicalLinkCategories
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch_p != null) {
			for (EObject obj : EObjectExt.getAll(arch_p, CsPackage.Literals.PHYSICAL_LINK_CATEGORY)) {
				availableElements.add((CapellaElement) obj);
			}
		}
		return availableElements;
	}

}