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
package org.polarsys.capella.core.business.queries.queries.ctx;

import static org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries.isGoodSupertypeCandidate;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

public class GetAvailable_SystemComponent_Super extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (input instanceof SystemComponent) {
      SystemComponent systemComponent = (SystemComponent) input;
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries()
          .getSystemEngineering(systemComponent);
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
      for (Component actor : BlockArchitectureExt.getAllComponents(ca)) {
        if (isGoodSupertypeCandidate(systemComponent, actor)) {
          if (systemComponent.isActor() == actor.isActor()
              || CapellaModelPreferencesPlugin.getDefault().isComponentNonActorInheritanceAllowed()) {
            availableElements.add(actor);
          }
        }
      }
    }
    return (List) availableElements;
  }

}