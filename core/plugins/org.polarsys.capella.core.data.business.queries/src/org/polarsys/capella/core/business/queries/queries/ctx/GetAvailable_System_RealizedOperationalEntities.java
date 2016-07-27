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
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_System_RealizedOperationalEntities extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * Gets all the entities in the Operational Analysis,
	 * except the entities that are already realized by the current system.
	 * Refer MQRY_System_RealizedOperationalEntities_1
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof System) {
			System currentSystem = (System) element;
			availableElements.addAll(getRule_MQRY_System_RealizedOperationalEntities_11(systemEngineering, currentSystem));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * Gets all the entities in the Operational Analysis,
	 * except the entities that are already realized by the current system.
	 * Refer MQRY_System_RealizedOperationalEntities_1
	 * @param sysEngthe {@link SystemEngineering}
	 * @param currentSystemthe current System
	 * @return list of {@link CapellaElement}
	 */
	private List<CapellaElement> getRule_MQRY_System_RealizedOperationalEntities_11(SystemEngineering sysEng, System currentSystem) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (EObject opEntity : EObjectExt.getAll(sysEng, OaPackage.Literals.ENTITY)) {
			availableElements.add((CapellaElement) opEntity);
		}
		return availableElements;
	}

}