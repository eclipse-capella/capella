/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.pa;

import static org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries.isGoodSupertypeCandidate;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_PhysicalComponent_Super extends AbstractQuery {

  /**
   * Gets the available actors in the actor package and all the sub actor packages, 
   * Except the current Actor itself, Actors that specialize the current Actor,
   * and Actors that are indirect generalizations of the current Actor.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries()
        .getSystemEngineering(capellaElement);
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    PhysicalArchitecture ca = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
    for (Component actor : BlockArchitectureExt.getAllComponents(ca)) {
      if (isGoodSupertypeCandidate((Component) capellaElement, actor)) {
        availableElements.add(actor);
      }
    }
    return (List) availableElements;
  }
}