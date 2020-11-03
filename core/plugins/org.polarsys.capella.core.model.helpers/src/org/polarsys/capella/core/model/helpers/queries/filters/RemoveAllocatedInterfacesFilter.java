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

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class RemoveAllocatedInterfacesFilter implements IQueryFilter {

  @Override
  public boolean keepElement(Object object1, IQueryContext context) {
    Interface inte = (Interface) object1;
    for (AbstractTrace trace : inte.getIncomingTraces()) {
      if (trace instanceof LogicalInterfaceRealization) {
        return false;
      }
      if (trace instanceof ContextInterfaceRealization) {
        return false;
      }
    }
    return true;
  }

}
