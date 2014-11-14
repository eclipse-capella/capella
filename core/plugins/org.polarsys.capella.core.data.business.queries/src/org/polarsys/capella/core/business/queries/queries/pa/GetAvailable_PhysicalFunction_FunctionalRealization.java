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

public class GetAvailable_PhysicalFunction_FunctionalRealization extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof AbstractFunction) {
			AbstractFunction element = (AbstractFunction) element_p;
			availableElements.addAll(getRule_MQRY_AbstractFunction_RealizedFunctions_11(element));
		}
		return availableElements;
	}

	/** 
	 * @param element_p the abstract function
	 * @return list of Function
	 */
	protected List<CapellaElement> getRule_MQRY_AbstractFunction_RealizedFunctions_11(AbstractFunction element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> allFunctions = new ArrayList<EObject>();
		if (null != element_p) {
			BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
			if (arch != null) {
				for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
					allFunctions.addAll(EObjectExt.getAll(block, FaPackage.Literals.ABSTRACT_FUNCTION));
				}
			}
			EList<FunctionRealization> ownedFunctionRealisations = element_p.getOwnedFunctionRealizations();
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