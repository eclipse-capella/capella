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

package org.polarsys.capella.core.model.helpers.queries.filters;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class KeepClassWithSamePrimitiveStateFilter implements IQueryFilter {

  protected Class theClass;

  public KeepClassWithSamePrimitiveStateFilter(Class class1) {
    theClass = class1;
  }

  @Override
  public boolean keepElement(Object element, IQueryContext context) {
    Class clazz = (Class) element;
    if (theClass.isIsPrimitive() != clazz.isIsPrimitive()) {
      return false;
    }
    return true;
  }

}
