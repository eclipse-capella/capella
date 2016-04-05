/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers.queries.filters;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class KeepPrimitiveClassInstanceOfSpecificEClassFilter implements IQueryFilter {

  protected EClass eClass;

  public KeepPrimitiveClassInstanceOfSpecificEClassFilter(EClass eClass) {
    this.eClass = eClass;
  }

  @Override
  public boolean keepElement(Object element, IQueryContext context) {
    if (eClass.isInstance(element)) {
      if (element instanceof Class) {
        Class currentClass = (Class) element;
        return currentClass.isPrimitive();
      }
    }
    return true;
  }

}
