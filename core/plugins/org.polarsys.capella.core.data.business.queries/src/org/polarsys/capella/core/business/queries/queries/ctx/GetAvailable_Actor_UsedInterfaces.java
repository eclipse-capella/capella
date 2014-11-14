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
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class GetAvailable_Actor_UsedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained in the interface package and all of its sub packages.
   * </p>
   * <p>
   * Refer MQRY_Actor_UsedInterfaces_1
   * </p>
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    if (null == systemEngineering) {
      return availableElements;
    }
    if (element_p instanceof Actor) {
      Actor currentActor = (Actor) element_p;
      availableElements.addAll(getInterfacesFromSystemEngineering(currentActor, systemEngineering));
      availableElements.addAll(getAllInterfacesFromShared(currentActor, systemEngineering));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained in the interface package and all of its sub packages of the SystemEngineering Package.
   * </p>
   * <p>
   * Except the interfaces that are already used by the current actor
   * </p>
   * <p>
   * Refer MQRY_Actor_UsedInterfaces_11
   * </p>
   * @param currentActor_pthe current {@link Actor}
   * @param systemEngineering_pthe {@link SystemEngineering}
   * @return list of interfaces
   */
  private List<CapellaElement> getInterfacesFromSystemEngineering(Actor currentActor_p, SystemEngineering systemEngineering_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemAnalysis sa = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering_p);
    if (sa != null) {
      availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(sa.getOwnedInterfacePkg(), currentActor_p, true));
    }
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering_p);
    if (oa != null) {
      availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(oa.getOwnedInterfacePkg(), currentActor_p, true));
    }
    List<Actor> allActors = ActorPkgExt.getAllActors(SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering_p).getOwnedActorPkg());
    for (Actor actor : allActors) {
      availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(actor.getOwnedInterfacePkg(), currentActor_p, true));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained in the interface package and all of its sub packages of the Shared Package.
   * </p>
   * <p>
   * Except the interfaces that are already used by the current actor
   * </p>
   * <p>
   * Refer MQRY_Actor_UsedInterfaces_12
   * </p>
   * @param currentActor_pthe current {@link Actor}
   * @param systemEngineering_pthe {@link SystemEngineering}
   * @return list of interfaces
   */
  private List<CapellaElement> getAllInterfacesFromShared(Actor currentActor_p, SystemEngineering systemEngineering_p) {
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

}
