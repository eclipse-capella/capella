/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.queries.interactionServices;

import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.sequencediag.ScenarioService;

public class GetISScopeInsertActors extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    Scenario scenario = (Scenario) input;
    return (List) new ScenarioService().getAllMultiInstanceRoleActorParts(scenario);
  }
}
