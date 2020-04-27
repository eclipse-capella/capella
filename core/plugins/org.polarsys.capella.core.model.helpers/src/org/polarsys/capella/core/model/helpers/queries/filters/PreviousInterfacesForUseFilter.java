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

package org.polarsys.capella.core.model.helpers.queries.filters;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;

/**
 */
public class PreviousInterfacesForUseFilter implements IQueryFilter {

  private Component component;

  public PreviousInterfacesForUseFilter(Component component) {
    this.component = component;
  }

  @Override
  public boolean keepElement(Object object1, IQueryContext iQueryContext1) {
    Interface inte = (Interface) object1;
    EList<Component> lcs = component.getRealizedComponents();
    if (lcs.isEmpty()) {
      return true;
    }
    for (Component realizedComponent : lcs) {
      if (realizedComponent.getUsedInterfaces().contains(inte)) {
        return true;
      }
      if (realizedComponent.getRequiredInterfaces().contains(inte)) {
        return true;
      }
    }
    return false;
  }

}
