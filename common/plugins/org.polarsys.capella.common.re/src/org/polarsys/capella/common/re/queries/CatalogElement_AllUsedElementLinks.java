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

package org.polarsys.capella.common.re.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.re.CatalogElement;

/**
 *
 */
public class CatalogElement_AllUsedElementLinks extends AbstractQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    if ((input != null) && (input instanceof CatalogElement)) {
      List<Object> objs = new ArrayList<Object>();

      Collection<CatalogElement> elements = QueryInterpretor.executeQuery(CatalogElement_AllUsedCatalogElements.class.getSimpleName(), input, context);
      for (CatalogElement element : elements) {
        Collection<EObject> usedElements = QueryInterpretor.executeQuery(CatalogElement_UsedElementLinks.class.getSimpleName(), element, context);
        objs.addAll(usedElements);
      }
      return objs;
    }
    return Collections.emptyList();
  }

}
