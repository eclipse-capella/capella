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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.semantic.queries.basic.queries.utils.QueriesFilters;


/**
 * Allows to get the allocation blocks (Entity(!OpeartionalActor), System, LC, PC) of an abstract function.
 * 
 * 
 */
public class FunctionAllocatingComponent implements IQuery {

  public FunctionAllocatingComponent() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    result.addAll(AbstractFunctionExt.getAllocationBlocks(object_p));
    return QueriesFilters.filterListToRemoveActors(result);
  }
  
}
