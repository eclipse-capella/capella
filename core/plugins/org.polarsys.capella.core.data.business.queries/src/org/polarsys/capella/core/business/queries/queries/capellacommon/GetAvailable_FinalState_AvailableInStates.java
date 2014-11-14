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
package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_FinalState_AvailableInStates extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof State) {
			availableElements.addAll(getRule_MQRY_State_AvailableFunctions_11((State) element_p));
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 * @param state_p
	 */
	private List<CapellaElement> getRule_MQRY_State_AvailableFunctions_11(State state_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(state_p);
		if (null != arch) {
			availableElements.addAll(getElementsFromBlockArchitecture(arch, state_p));
		}
		return availableElements;
	}

	/** 
	 * @param arch_p
	 * @param state_p
	 * @return
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, State state_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch_p != null) {
			TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, false);
			while (allContents.hasNext()) {
				Object object = allContents.next();
				if (object instanceof AbstractFunction) {
					availableElements.add((CapellaElement) object);
				}
			}
		}
		for (CapellaElement elt : getCurrentElements(state_p, false)) {
			availableElements.remove(elt);
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof State) {
			for (EObject referencer : EObjectExt.getReferencers(element_p, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES)) {
				currentElements.add((CapellaElement) referencer);
			}
		}
		return currentElements;
	}

}