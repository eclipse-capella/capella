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

package org.polarsys.capella.core.model.helpers.queries.filters;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;

/**
 */
public class PreviousInterfacesForImplementationFilter implements IQueryFilter {

  private Component component;

  public PreviousInterfacesForImplementationFilter(Component component) {
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
      if (realizedComponent.getImplementedInterfaces().contains(inte)) {
        return true;
      }
      if (realizedComponent.getProvidedInterfaces().contains(inte)) {
        return true;
      }
    }
    return false;
  }

}
