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
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
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
public class Actor_ImplementedInterfaces implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

	/**
	 * <p>
	 * Gets all the interfaces contained in the interface package and all of its
	 * sub packages of the SystemEngineering Package.
	 * </p>
	 * <p>
	 * Except the interfaces that are already implemented by the current actor
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_ImplInterfaces_11
	 * </p>
	 * 
	 * @param currentActor_p
	 *            the current {@link Actor}
	 * @param systemEngineering_p
	 *            the {@link SystemEngineering}
	 * @return list of interfaces
	 */
	private List<CapellaElement> getRule_MQRY_Actor_ImplInterfaces11(Actor currentActor_p, SystemEngineering systemEngineering_p) {
		// The list of Capella elements to return.
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering_p).getOwnedInterfacePkg(),
				currentActor_p, false));
		
    //All Interface Inside Interface
    List<Actor> allActors = ActorPkgExt.getAllActors(SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering_p).getOwnedActorPkg());
    for (Actor actor : allActors) {
      availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(actor.getOwnedInterfacePkg(), currentActor_p, true));
    }
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the interfaces contained in the interface package and all of its
	 * sub packages of the Shared Package.
	 * </p>
	 * <p>
	 * Except the interfaces that are already implemented by the current actor
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_ImplInterfaces_12
	 * </p>
	 * 
	 * @param currentActor_p
	 *            the current {@link Actor}
	 * @param systemEngineering_p
	 *            the {@link SystemEngineering}
	 * @return list of interfaces
	 */
	private List<CapellaElement> getRule_MQRY_Actor_ImplInterfaces12(Actor currentActor_p, SystemEngineering systemEngineering_p) {
		// The list of Capella elements to return.
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		List<SharedPkg> sharedPkgs = SystemEngineeringExt.getSharedPkgs(currentActor_p);
		for (SharedPkg sharedPkg : sharedPkgs) {

		  GenericPkg genericPkg = sharedPkg.getOwnedGenericPkg();
      if (genericPkg != null) {
        availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(genericPkg, currentActor_p, true));
      }
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the interfaces contained in the interface package and all of its
	 * sub packages of the system engineering & shared package.
	 * </p>
	 * <p>
	 * Except those that are implemented by the current actor.
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_ImplInterfaces_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		// The list of Capella elements to returns.
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (null == systemEngineering) {
			return availableElements;
		}

		if (element_p instanceof Actor) {

			Actor currentActor = (Actor) element_p;

			availableElements.addAll(getRule_MQRY_Actor_ImplInterfaces11(currentActor, systemEngineering));
			availableElements.addAll(getRule_MQRY_Actor_ImplInterfaces12(currentActor, systemEngineering));
		} 
		
		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the interfaces implemented by the current actor.
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_ImplInterfaces_1
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

		if (element_p instanceof Actor) {
			Actor currentActor = (Actor) element_p;
			currentElements.addAll(ComponentExt.getImplementedInterfaces(currentActor));
		}
		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.ACTOR;
	}

	public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(1);
    list.add(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES);
    list.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    return list;
  }

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  return RefactorDebugger.callAndTestQuery("GetAvailable_Actor_ImplementedInterfaces__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  return RefactorDebugger.callAndTestQuery("GetCurrent_Actor_ImplementedInterfaces", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
	}
	
}
