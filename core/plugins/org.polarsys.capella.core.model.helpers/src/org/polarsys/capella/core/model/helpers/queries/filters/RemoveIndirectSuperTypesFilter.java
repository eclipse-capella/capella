/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;

/**
 * A filter that rejects an element if it is an indirect
 * generalization/supertype of a given context element.
 * Example: If C extends B extends A, and C is the context element,
 * B will pass the filter, but A will not.
 */
public class RemoveIndirectSuperTypesFilter implements IQueryFilter {

  private GeneralizableElement currentElement;

  public RemoveIndirectSuperTypesFilter(GeneralizableElement currentElement) {
    this.currentElement = currentElement;
  }

  @Override
  public boolean keepElement(Object object, IQueryContext context) {
    if (object instanceof GeneralizableElement) {
      GeneralizableElement genElt = (GeneralizableElement) object;
      for (GeneralizableElement sup : currentElement.getSuper()) {        
        return !GeneralizableElementExt.getAllSuperGeneralizableElements(sup).contains(genElt);
      }
    }
    return true;
  }

}
