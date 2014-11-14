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

import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class RemoveSuperTypesFilter implements IQueryFilter {

  private GeneralizableElement currentElement;

  public RemoveSuperTypesFilter(GeneralizableElement currentElement_) {
    currentElement = currentElement_;
  }

  @Override
  public boolean keepElement(Object object, IQueryContext context_p) {
    if (object instanceof GeneralizableElement) {
      GeneralizableElement genElt = (GeneralizableElement) object;
      return !GeneralizableElementExt.getAllSuperGeneralizableElements(currentElement).contains(genElt);
    }
    return true;
  }

}
