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
package org.polarsys.capella.core.business.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class System_ImplementedInterfaces implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

	/**
	 * <p>
	 * Gets all the Interfaces contained in the Interface Package (and all of
	 * its sub packages) of the Context Layer (includes SystemEngineering and
	 * Shared Pkg)
	 * </p>
	 * <p>
	 * Except the interfaces that are already implemented by the system.
	 * </p>
	 * <p>
	 * Refer MQRY_System_ImplInterfaces_1
	 * </p>
	 * 
	 * @param sysEng_p
	 *            the system engineering
	 * @param system_p
	 *            the system
	 * @param usedFlag_p
	 *            flag to check used/implemented interface
	 * @return list of interfaces
	 */
	private List<CapellaElement> getRule_MQRY_System_ImplInterface_11(SystemEngineering sysEng_p, System system_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<SharedPkg> sharedPkgs = SystemEngineeringExt.getSharedPkgs(system_p);
		// FIXME : update to SystemAnalysis
		availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(SystemEngineeringExt.getOwnedSystemAnalysis(sysEng_p).getOwnedInterfacePkg(), system_p, false));

		for (SharedPkg sharedPkg : sharedPkgs) {
			GenericPkg genericPkg = sharedPkg.getOwnedGenericPkg();
			if (genericPkg != null) {
				availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(genericPkg, system_p, false));
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the Interfaces contained in the Interface Package (and all of
	 * its sub packages) of the Context Layer
	 * </p>
	 * <p>
	 * Except the interfaces that are already implemented by the system.
	 * </p>
	 * <p>
	 * Refer MQRY_System_ImplInterfaces_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof System) {
			System system = (System) element_p;

			availableElements.addAll(getRule_MQRY_System_ImplInterface_11(systemEngineering, system));

		} else if (element_p instanceof SystemEngineering) {
			availableElements.addAll(getRule_MQRY_System_ImplInterface_11(systemEngineering, null));
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the interfaces used by the system.
	 * </p>
	 * <p>
	 * Refer MQRY_System_ImplInterfaces_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof System) {
			System system = (System) element_p;
			currentElements.addAll(ComponentExt.getImplementedInterfaces(system));
		}

		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.SYSTEM;
	}

	public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(1);
    list.add(CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE);
    list.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    return list;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  return RefactorDebugger.callAndTestQuery("GetAvailable_System_ImplementedInterfaces__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  return RefactorDebugger.callAndTestQuery("GetCurrent_System_ImplementedInterfaces", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
	}
}
