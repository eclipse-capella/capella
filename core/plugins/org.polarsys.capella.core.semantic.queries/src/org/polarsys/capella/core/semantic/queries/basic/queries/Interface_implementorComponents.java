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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.semantic.queries.basic.queries.utils.QueriesFilters;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This is the query for interfaces implementor components
 */
public class Interface_implementorComponents implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof Interface) {
      Interface itf = (Interface) object_p;
      // Retrieve Component by Implement link AND by Provider Port
      result.addAll(itf.getImplementorComponents());
      result.addAll(InterfaceExt.getProviderComponent(itf));
    }
    //Filters the query result to remove the actors
    return QueriesFilters.filterListToRemoveActors(result);
  }

}
