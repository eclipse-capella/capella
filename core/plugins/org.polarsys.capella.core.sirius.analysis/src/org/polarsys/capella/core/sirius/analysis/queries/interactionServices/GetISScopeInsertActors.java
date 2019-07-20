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

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;

public class GetISScopeInsertActors extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    BlockArchitecture architecture = (BlockArchitecture) input;
    List<Part> parts = new ArrayList<>();
    List<AbstractActor> actors = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_SUB_DEFINED_ACTORS, architecture, new QueryContext());
    for (AbstractActor actor : actors) {
      parts.addAll(ComponentExt.getRepresentingParts(actor));
    }
    return (List) ListExt.removeDuplicates((List) parts);
  }
}
