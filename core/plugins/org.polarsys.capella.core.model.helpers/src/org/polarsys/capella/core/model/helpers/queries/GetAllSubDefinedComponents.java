/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.queries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 */
public class GetAllSubDefinedComponents extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    BlockArchitecture block = (BlockArchitecture) input;
    return (List) getAllSubDefinedComponents(block);
  }

  public List<Component> getAllSubDefinedComponents(BlockArchitecture architecture) {
    List<Component> comps = new ArrayList<Component>();
    LinkedList<Component> subs = new LinkedList<Component>();

    subs.addAll(ComponentExt.getSubDefinedComponents(architecture));
    while (subs.size() > 0) {
      Component sub = subs.removeFirst();
      comps.add(sub);
      List<Component> internal = ComponentExt.getSubDefinedComponents(sub);
      comps.addAll(internal);
      subs.addAll(internal);
    }
    return comps;
  }
}
