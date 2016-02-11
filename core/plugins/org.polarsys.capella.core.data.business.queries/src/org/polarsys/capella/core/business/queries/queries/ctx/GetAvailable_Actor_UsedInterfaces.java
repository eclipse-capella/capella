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

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

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
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (null == systemEngineering) {
      return availableElements;
    }
    if (element instanceof Actor) {
      Actor currentActor = (Actor) element;
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
   * @param currentActorthe current {@link Actor}
   * @param systemEngineeringthe {@link SystemEngineering}
   * @return list of interfaces
   */
  private List<CapellaElement> getInterfacesFromSystemEngineering(Actor currentActor, SystemEngineering systemEngineering) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemAnalysis sa = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
    if (sa != null) {
      availableElements.addAll(InterfacePkgExt.getAllInterfaces(sa.getOwnedInterfacePkg()));
    }
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
    if (oa != null) {
      availableElements.addAll(InterfacePkgExt.getAllInterfaces(oa.getOwnedInterfacePkg()));
    }
    List<Actor> allActors = ActorPkgExt.getAllActors(SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering).getOwnedActorPkg());
    for (Actor actor : allActors) {
      availableElements.addAll(InterfacePkgExt.getAllInterfaces(actor.getOwnedInterfacePkg()));
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
   * @param currentActorthe current {@link Actor}
   * @param systemEngineeringthe {@link SystemEngineering}
   * @return list of interfaces
   */
  private List<CapellaElement> getAllInterfacesFromShared(Actor currentActor, SystemEngineering systemEngineering) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    return availableElements;
  }

}
