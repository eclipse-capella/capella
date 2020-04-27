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

import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class RemoveSubTypesFilter implements IQueryFilter {

  private GeneralizableElement currentElement;

  public RemoveSubTypesFilter(GeneralizableElement currentElement) {
    this.currentElement = currentElement;
  }

  @Override
  public boolean keepElement(Object object, IQueryContext context) {
    if (object instanceof GeneralizableElement) {
      GeneralizableElement genElt = (GeneralizableElement) object;
      return !GeneralizableElementExt.getAllSuperGeneralizableElements(genElt).contains(currentElement);
    }
    return true;
  }

}
