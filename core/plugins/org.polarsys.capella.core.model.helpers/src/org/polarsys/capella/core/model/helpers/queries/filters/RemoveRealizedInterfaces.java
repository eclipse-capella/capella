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
  public boolean keepElement(Object element, IQueryContext context_p) {
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
