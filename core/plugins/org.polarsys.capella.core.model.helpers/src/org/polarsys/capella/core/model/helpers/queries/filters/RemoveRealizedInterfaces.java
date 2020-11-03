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

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class RemoveRealizedInterfaces implements IQueryFilter {

  @Override
  public boolean keepElement(Object element, IQueryContext context) {
    if (element instanceof Interface) {
      Interface lcInterface = (Interface) element;
      EList<AbstractTrace> incomingTraces = lcInterface.getIncomingTraces();
      for (AbstractTrace abstractTrace : incomingTraces) {
        if (abstractTrace instanceof LogicalInterfaceRealization) {
          return false;
        }
      }
    }
    return true;
  }

}
