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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_LogicalActor_InheritedActors extends AbstractQuery {

  /**
   * Gets the available actors in the actor package and all the sub actor packages of system engineering package, except the current actor and the actors in the
   * inheritance hierarchy of the current actor
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement element_p = (CapellaElement) input;
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (systemEngineering == null) {
      return (List) availableElements;
    }
    if (element_p instanceof LogicalActor) {
      LogicalActor currentActor = (LogicalActor) element_p;
      LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
      LogicalActorPkg actorPkg = la.getOwnedLogicalActorPkg();
      if (actorPkg != null) {
        for (LogicalActor actor : ActorPkgExt.getAllActors(actorPkg)) {
          if ((actor == null) || currentActor.equals(actor)) {
            continue;
          }
          if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentActor).contains(actor)
              && !GeneralizableElementExt.getAllSuperGeneralizableElements(actor).contains(currentActor)) {
            availableElements.add(actor);
          }
        }
      }
    }
    return (List) availableElements;
  }
}