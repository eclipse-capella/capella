/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class DefaultFilter implements IQueryFilter {

  @Override
  public boolean keepElement(Object element, IQueryContext context) {
    return true;
  }

}
