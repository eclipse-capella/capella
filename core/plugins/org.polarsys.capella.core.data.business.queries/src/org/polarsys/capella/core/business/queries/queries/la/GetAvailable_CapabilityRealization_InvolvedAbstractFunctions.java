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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_CapabilityRealization_InvolvedAbstractFunctions extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof AbstractCapability) {
			availableElements.addAll(getRule_MQRY_AbstractCapability_AvailableFunctions_11((AbstractCapability) element));
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 */
	protected List<CapellaElement> getRule_MQRY_AbstractCapability_AvailableFunctions_11(AbstractCapability ele) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele);
		if (currentBlockArchitecture != null) {
			availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture));
		}
		return availableElements;
	}

	/** 
	 * @param arch
	 * @return
	 */
	protected List<CapellaElement> getElementsFromBlockArchitecture(AbstractFunctionalArchitecture arch) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		FunctionPkg functionPkg = arch.getOwnedFunctionPkg();
		if (functionPkg != null) {
			TreeIterator<Object> allContents = EcoreUtil.getAllContents(functionPkg, true);
			while (allContents.hasNext()) {
				Object object = allContents.next();
				if (object instanceof AbstractFunction) {
					availableElements.add((CapellaElement) object);
				}
			}
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof AbstractCapability) {
			AbstractCapability ele = (AbstractCapability) element;
			for (AbstractFunctionAbstractCapabilityInvolvement inv : ele.getOwnedAbstractFunctionAbstractCapabilityInvolvements()) {
				currentElements.add(inv.getFunction());
			}
		}
		return currentElements;
	}

}