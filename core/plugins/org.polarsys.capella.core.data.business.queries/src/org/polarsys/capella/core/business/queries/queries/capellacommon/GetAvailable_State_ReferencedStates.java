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
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;

public class GetAvailable_State_ReferencedStates extends AbstractQuery {

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
		if (element_p instanceof IState) {
			availableElements.addAll(getRule_MQRY_State_AvailableStates_11((IState) element_p));
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 * @param state_p
	 */
	protected List<CapellaElement> getRule_MQRY_State_AvailableStates_11(IState state_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		Component ownerCpnt = (Component) EcoreUtil2.getFirstContainer(state_p, CsPackage.Literals.COMPONENT);
		if (null != ownerCpnt) {
			availableElements.addAll(getElementsFromComponent(ownerCpnt, state_p));
		}
		return availableElements;
	}

	/** 
	 * @param arch_p
	 * @param state_p
	 * @return
	 */
	protected List<CapellaElement> getElementsFromComponent(Component arch_p, IState state_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch_p != null) {
			TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, false);
			while (allContents.hasNext()) {
				Object object = allContents.next();
				if (object instanceof IState) {
					availableElements.add((CapellaElement) object);
				}
			}
		}
		for (CapellaElement elt : getCurrentElements((CapellaElement) state_p, false)) {
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
		if (element_p instanceof IState) {
			for (IState state : ((IState) element_p).getReferencedStates()) {
				currentElements.add((CapellaElement) state);
			}
		}
		return currentElements;
	}

}