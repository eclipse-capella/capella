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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.semantic.queries.basic.queries.utils.QueriesFilters;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query allows to get the actors implementing the current interface.
 */
public class Interface_implementorActors implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    // List<Object> componentsList = new ArrayList<Object>();
    if (object instanceof Interface) {
      // Gets the components implementing the interface
      Interface itf = (Interface) object;
      // Retrieve Component by Implement link AND by Provider Port
      result.addAll(itf.getImplementorComponents());
      result.addAll(InterfaceExt.getProviderComponent(itf));
    }
    return QueriesFilters.filterListToGetOnlyActors(result);
  }
}
