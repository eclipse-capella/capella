/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_PhysicalComponent_LogicalComponentRealization extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the owned FunctionalAllocation of the system.
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof PhysicalComponent) {
			PhysicalComponent elt = (PhysicalComponent) element;
			availableElements.addAll(getRule_MQRY_LogicalComponent_FunctionalAllocation_11(systemEngineering, elt));
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the owned FunctionalAllocation targetElement of the system.
	 * </p>
	 * @param elementthe system
	 * @return list of Function
	 */
	private List<CapellaElement> getRule_MQRY_LogicalComponent_FunctionalAllocation_11(SystemEngineering systemEng, PhysicalComponent element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<LogicalComponent> allComp = SystemEngineeringExt.getAllAbstractLogicalComponents(element);
		if (null != element) {
			EList<LogicalComponentRealization> ownedLogicalComponentRealisations = element.getOwnedLogicalComponentRealizations();
			for (LogicalComponentRealization ownedLogicalComponentRealisation : ownedLogicalComponentRealisations) {
				TraceableElement targetElement = ownedLogicalComponentRealisation.getTargetElement();
				if (null != targetElement && targetElement instanceof LogicalComponent) {
					if (allComp.contains(targetElement)) {
						allComp.remove(targetElement);
					}
				}
			}
		}
		for (LogicalComponent logComp : allComp) {
			availableElements.add(logComp);
		}
		return availableElements;
	}

}