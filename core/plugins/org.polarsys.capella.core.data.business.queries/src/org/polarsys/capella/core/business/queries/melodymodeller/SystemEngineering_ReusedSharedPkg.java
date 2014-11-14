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
package org.polarsys.capella.core.business.queries.melodymodeller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class SystemEngineering_ReusedSharedPkg implements IBusinessQuery {

	/**
	 * All the Shared Packages contained by the current Element's Project.
	 */
	private List<CapellaElement> getRule_MQRY_SystemEngineering_Reused_11(SystemEngineering currentSystemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		Project prj = CapellaQueries.getInstance().getRootQueries().getProject(currentSystemEngineering_p);
		Set<EObject> sharedPkgSet = EObjectExt.getAll(prj, SharedmodelPackage.Literals.SHARED_PKG);
		for (EObject obj : sharedPkgSet) {
			if (!SystemEngineeringExt.getSharedPkgs(currentSystemEngineering_p).contains(obj))
				availableElements.add((CapellaElement) obj);
		}

		return availableElements;
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
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (element_p instanceof SystemEngineering) {
			SystemEngineering systemEngineering = (SystemEngineering) element_p;
			availableElements.addAll(getRule_MQRY_SystemEngineering_Reused_11(systemEngineering));
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Refer MQRY_SystemEngineering_Reused_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof SystemEngineering) {
			SystemEngineering systemEngineering = (SystemEngineering) element_p;
			currentElements.addAll(SystemEngineeringExt.getSharedPkgs(systemEngineering));
		}

		return currentElements;
	}

	@Override
	public EClass getEClass() {
		return CapellamodellerPackage.Literals.SYSTEM_ENGINEERING;
	}

	@Override
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.REUSER_STRUCTURE__OWNED_REUSE_LINKS);
	}
}
