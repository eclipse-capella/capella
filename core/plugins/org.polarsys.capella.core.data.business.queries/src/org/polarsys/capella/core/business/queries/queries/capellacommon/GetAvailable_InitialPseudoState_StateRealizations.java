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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_InitialPseudoState_StateRealizations extends AbstractQuery {

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
		if (element_p instanceof AbstractState) {
			availableElements.addAll(getRule_MQRY_State_AvailableStates_11((AbstractState) element_p));
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 * @param state_p
	 */
	protected List<CapellaElement> getRule_MQRY_State_AvailableStates_11(AbstractState state_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(state_p);
		if (null != currentBlockArchitecture) {
			for (BlockArchitecture previousBlockArchitecture : BlockArchitectureExt.getPreviousBlockArchitectures(currentBlockArchitecture)) {
				availableElements.addAll(getElementsFromBlockArchitecture(previousBlockArchitecture, state_p));
			}
		}
		return availableElements;
	}

	/** 
	 * @param arch_p
	 * @param state_p
	 * @return
	 */
	protected List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, AbstractState state_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch_p != null) {
			TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, false);
			while (allContents.hasNext()) {
				Object object = allContents.next();
				if (object instanceof AbstractState) {
					availableElements.add((CapellaElement) object);
				}
			}
		}
		for (CapellaElement elt : getCurrentElements(state_p, false)) {
			availableElements.remove(elt);
		}
		availableElements.remove(state_p);
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof AbstractState) {
			for (AbstractStateRealization link : ((AbstractState) element_p).getOwnedAbstractStateRealizations()) {
				currentElements.add(link.getRealizedAbstractState());
			}
		}
		return currentElements;
	}

}