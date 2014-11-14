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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Class_RealizedClasses extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		boolean isClassFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isClassFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element_p instanceof Class) {
			if (!isClassFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Class_Realized_11(element_p));
			}
			availableElements.addAll(getRule_MQRY_Class_Realized_12((Class) element_p, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element_p);
		return availableElements;
	}

	/** 
	 * All the Classes contained by the previous architectures.
	 */
	private List<CapellaElement> getRule_MQRY_Class_Realized_11(CapellaElement currentElement_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(currentElement_p);
		if (currentBlockArchitecture != null) {
			for (BlockArchitecture previousBlockArchitecture : BlockArchitectureExt.getPreviousBlockArchitectures(currentBlockArchitecture)) {
				for (Class clazz : DataPkgExt.getAllClasses(previousBlockArchitecture.getOwnedDataPkg())) {
					availableElements.add(clazz);
				}
			}
		}
		return availableElements;
	}

	/** 
	 * All the Classes contained by the Shared Package (and all of its
	 * sub-packages).
	 */
	private List<CapellaElement> getRule_MQRY_Class_Realized_12(Class currentClass, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering)) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Class clazz : GenericPkgExt.getAllClasses(pkg)) {
					availableElements.add(clazz);
				}
			}
			if (sharedPkg.getOwnedDataPkg() != null) {
				if (sharedPkg.getOwnedDataPkg() != null) {
					for (Class clazz : DataPkgExt.getAllClasses(sharedPkg.getOwnedDataPkg())) {
						availableElements.add(clazz);
					}
				}
			}
		}
		return availableElements;
	}

}