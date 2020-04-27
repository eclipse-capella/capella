/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.queries.filters;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class MultiFilter implements IQueryFilter {

  private List<IQueryFilter> filters;

  public MultiFilter(List<IQueryFilter> filters_) {
    filters = filters_;
  }

  public MultiFilter(IQueryFilter[] filters_) {
    filters = Arrays.asList(filters_);
  }

  @Override
  public boolean keepElement(Object element, IQueryContext context) {
    for (int i = 0; i < filters.size(); i++) {
      if (!filters.get(i).keepElement(element, context)) {
        return false;
      }
    }
    return true;
  }
}
