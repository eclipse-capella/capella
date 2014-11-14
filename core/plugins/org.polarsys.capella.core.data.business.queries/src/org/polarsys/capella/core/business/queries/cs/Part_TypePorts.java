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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.utils.ListExt;

@Deprecated
public class Part_TypePorts implements IBusinessQuery {

	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (element_p instanceof Part) {
			Part property = (Part) element_p;
			availableElements.addAll(getRule_MQRY_PhysicalPart_TypePorts_11(property));

		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element_p.eContainer());
		return availableElements;

	}

	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		// do nothing

		return currentElements;
	}

	/*
	 * All the Ports contained into the current PhysicalPart's type
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalPart_TypePorts_11(Part currentPhysicalPart_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		PhysicalComponent pc = (PhysicalComponent) currentPhysicalPart_p.getType();
		if (pc != null) {
			availableElements.addAll(pc.getOwnedPartitions());
		}

		return availableElements;
	}

	public EClass getEClass() {
		return null;
	}

	public List<EReference> getEStructuralFeatures() {
    return null;
  }
}
