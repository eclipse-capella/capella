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
package org.polarsys.capella.core.sirius.analysis.queries.interactionServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;
import org.polarsys.capella.core.sequencediag.InteractionAspectService;

public class GetISScopeInsertComponents extends AbstractQuery {

  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    Scenario scenario = (Scenario) input_p;
    Collection<Part> partsFilter = new ArrayList<Part>();
    for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
      if (role.getRepresentedInstance() instanceof Part) {
        partsFilter.add((Part) role.getRepresentedInstance());
      }
    }
    List<Part> parts = new ArrayList<Part>(new InteractionAspectService().getAvailableParts(scenario, partsFilter));
    return (List) QueryInterpretor.executeFilter(parts, new RemoveActorsFilter());
  }
}
