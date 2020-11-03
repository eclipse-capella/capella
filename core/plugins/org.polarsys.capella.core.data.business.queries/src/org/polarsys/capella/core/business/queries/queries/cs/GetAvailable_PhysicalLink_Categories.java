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
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof PhysicalLink) {
			PhysicalLink physicalLink = (PhysicalLink) element;
			availableElements.addAll(getRule_MQRY_PhysicalLink_Categories_11(physicalLink));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_PhysicalLink_Categories_11(PhysicalLink physicalLink) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(physicalLink);
		availableElements.addAll(getElementsFromBlockArchitecture(arch));
		return availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof PhysicalLink) {
			PhysicalLink physicalLink = (PhysicalLink) element;
			EList<PhysicalLinkCategory> categories = physicalLink.getCategories();
			for (PhysicalLinkCategory categorie : categories) {
				currentElements.add(categorie);
			}
		}
		return currentElements;
	}

	/** 
	 * Gets all the physical link categories from the BlockArchitecture
	 * @param arch
	 * @return list of PhysicalLinkCategories
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch != null) {
			for (EObject obj : EObjectExt.getAll(arch, CsPackage.Literals.PHYSICAL_LINK_CATEGORY)) {
				availableElements.add((CapellaElement) obj);
			}
		}
		return availableElements;
	}

}