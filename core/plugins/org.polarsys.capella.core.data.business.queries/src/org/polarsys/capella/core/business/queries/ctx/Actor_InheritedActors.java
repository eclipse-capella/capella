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

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class Actor_InheritedActors implements IBusinessQuery {

	/**
	 * <p>
	 * Gets the available actors in the actor package and all the sub actor
	 * packages of system engineering package
	 * </p>
	 * <p>
	 * Except the current actor and the actors in the inheritance hierarchy of
	 * the current actor.
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_Inherited_11
	 * </p>
	 * 
	 * @param sysEng_p
	 *            the {@link SystemEngineering}
	 * @param currentActor_p
	 *            the current {@link Actor}
	 * @return list of Actors
	 */
	private List<CapellaElement> getRule_MQRY_Actor_Inherited_11(SystemEngineering sysEng_p, Actor currentActor_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

		// FIXME : update to SystemAnalysis
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng_p);
		// ActorPkg actorPkg = sysEng_p.getActorPkg();
		// FIXME : update to SystemAnalysis
		ActorPkg actorPkg = ca.getOwnedActorPkg();
		if (actorPkg != null) {
			for (Actor actor : ActorPkgExt.getAllActors(actorPkg)) {
				if ((actor == null) || currentActor_p.equals(actor)) {
					continue;
				}
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentActor_p).contains(actor)
				 && !GeneralizableElementExt.getAllSuperGeneralizableElements(actor).contains(currentActor_p))
				{
					availableElements.add(actor);
				}
			}
		}
		return availableElements;
	}

	/*
	 * Gets all the actors from SharedPkg
	 */
	private List<CapellaElement> getRule_MQRY_Actor_Inherited_12(SystemEngineering systemEngineering_p, Actor currentActor_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<SharedPkg> sharedPkgs = SystemEngineeringExt.getSharedPkgs(systemEngineering_p);
		for (SharedPkg sharedPkg : sharedPkgs) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Actor actor : GenericPkgExt.getAllActors(pkg)) {
					if ((actor == null) || currentActor_p.equals(actor)) {
						continue;
					}
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentActor_p).contains(actor)
					 && !GeneralizableElementExt.getAllSuperGeneralizableElements(actor).contains(currentActor_p))
					{
						availableElements.add(actor);
					}
				}
			}
		}
		return availableElements;
	}

	/**
	 * <p>
	 * Gets the available actors in the actor package and all the sub actor
	 * packages
	 * </p>
	 * <p>
	 * Except the current actor and the actors in the inheritance hierarchy of
	 * the current actor.
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_Inherited_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		boolean isElementFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isElementFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element_p instanceof Actor) {
			Actor currentActor = (Actor) element_p;
			if (!isElementFromSharedPkg)
				availableElements.addAll(getRule_MQRY_Actor_Inherited_11(systemEngineering, currentActor));
			availableElements.addAll(getRule_MQRY_Actor_Inherited_12(systemEngineering, currentActor));
		}

		return availableElements;
	}

	/**
	 * <p>
	 * Gets all the actors in the inheritance hierarchy of the current actor.
	 * </p>
	 * <p>
	 * Refer MQRY_Actor_Inherited_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof Actor) {
			Actor currentActor = (Actor) element_p;
			currentElements.addAll(currentActor.getSuper());
			currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(currentActor);
		}

		return currentElements;
	}

	public EClass getEClass() {
		return CtxPackage.Literals.ACTOR;
	}

	public List<EReference> getEStructuralFeatures() {
		List<EReference> refList = new ArrayList<EReference>();
		refList.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS);
		refList.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS);
	    return refList;	
  }
  
}
