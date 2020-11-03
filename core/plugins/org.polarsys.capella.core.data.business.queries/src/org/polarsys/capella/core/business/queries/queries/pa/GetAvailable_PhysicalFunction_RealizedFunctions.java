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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_PhysicalFunction_RealizedFunctions extends AbstractQuery {

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
		if (element instanceof AbstractFunction) {
			AbstractFunction elt = (AbstractFunction) element;
			availableElements.addAll(getRule_MQRY_AbstractFunction_RealizedFunctions_11(elt));
		}
		return availableElements;
	}

	/** 
	 * @param element the abstract function
	 * @return list of Function
	 */
	protected List<CapellaElement> getRule_MQRY_AbstractFunction_RealizedFunctions_11(AbstractFunction element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> allFunctions = new ArrayList<EObject>();
		if (null != element) {
			BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
			if (arch != null) {
				for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
					allFunctions.addAll(EObjectExt.getAll(block, FaPackage.Literals.ABSTRACT_FUNCTION));
				}
			}
			EList<FunctionRealization> ownedFunctionRealisations = element.getOwnedFunctionRealizations();
			for (FunctionRealization ownedFunctionRealisation : ownedFunctionRealisations) {
				TraceableElement targetElement = ownedFunctionRealisation.getTargetElement();
				if (null != targetElement) {
					if (allFunctions.contains(targetElement)) {
						allFunctions.remove(targetElement);
					}
				}
			}
		}
		for (EObject function : allFunctions) {
			availableElements.add((CapellaElement) function);
		}
		return availableElements;
	}

}