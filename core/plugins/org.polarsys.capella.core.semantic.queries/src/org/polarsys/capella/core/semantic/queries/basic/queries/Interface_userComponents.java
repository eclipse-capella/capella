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
 * This is the query returning the user components of an interface.
 */
public class Interface_userComponents implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Interface) {
      Interface itf = (Interface) object;
      // Retrieve Component by Use link AND by Require Port
      result.addAll(itf.getUserComponents());
      result.addAll(InterfaceExt.getRequireComponent(itf));
    }
    //Filters the list to remove the actors from it
    return QueriesFilters.filterListToRemoveActors(result);
  }
}
