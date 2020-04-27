/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.capellamodeller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_SystemEngineering_ReusedSharedPkg extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * Gets...
	 * <p>
	 * All the Shared Packages contained by the current Project.
	 * </p>
	 * <p>
	 * Except The currently reused Shared Packages.
	 * </p>
	 * <p>
	 * Refer MQRY_SystemEngineering_Reused_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof SystemEngineering) {
			SystemEngineering systemEngineering = (SystemEngineering) element;
			availableElements.addAll(getRule_MQRY_SystemEngineering_Reused_11(systemEngineering));
		}
		return availableElements;
	}

	/** 
	 * All the Shared Packages contained by the current Element's Project.
	 */
	private List<CapellaElement> getRule_MQRY_SystemEngineering_Reused_11(SystemEngineering currentSystemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		Project prj = CapellaQueries.getInstance().getRootQueries().getProject(currentSystemEngineering);
		Set<EObject> sharedPkgSet = EObjectExt.getAll(prj, SharedmodelPackage.Literals.SHARED_PKG);
		for (EObject obj : sharedPkgSet) {
			if (!SystemEngineeringExt.getSharedPkgs(currentSystemEngineering).contains(obj))
				availableElements.add((CapellaElement) obj);
		}
		return availableElements;
	}

}