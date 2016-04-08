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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.semantic.queries.basic.queries.utils.QueriesFilters;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Allows to get the Realized actors of an actor
 */
public class Actor_RealizedActors implements IQuery {

  public Actor_RealizedActors() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Component) {
      Component c = (Component) object;
      EList<Component> allocatedComponents = c.getAllocatedComponents();
      if (!allocatedComponents.isEmpty()) {
        result.addAll(allocatedComponents);
      }
    }
    return QueriesFilters.filterListToGetOnlyActors(result);
  }
}
