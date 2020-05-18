/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.la;

import static org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries.isGoodSupertypeCandidate;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_LogicalComponent_Super extends AbstractQuery {

  /**
   * Gets the available actors in the actor package and all the sub actor packages of system engineering package, Except
   * the current Actor itself, Actors that specialize the current Actor, and Actors that are indirect generalizations of
   * the current Actor.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (input instanceof LogicalComponent) {
      LogicalComponent component = (LogicalComponent) input;
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries()
          .getSystemEngineering(component);
      LogicalArchitecture ca = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
      for (Component actor : BlockArchitectureExt.getAllComponents(ca)) {
        if (isGoodSupertypeCandidate(component, actor)) {
          if (component.isActor() == actor.isActor()) {
            availableElements.add(actor);
          }
        }
      }
    }
    return (List) availableElements;
  }
}