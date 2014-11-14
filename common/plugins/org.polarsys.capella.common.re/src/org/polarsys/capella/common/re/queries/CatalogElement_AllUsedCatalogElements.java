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
package org.polarsys.capella.common.re.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.re.CatalogElement;

/**
 *
 */
public class CatalogElement_AllUsedCatalogElements extends AbstractQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) {
    if ((input_p != null) && (input_p instanceof CatalogElement)) {
      List<Object> objs = new ArrayList<Object>();

      LinkedList<CatalogElement> elements = new LinkedList<CatalogElement>();
      elements.add((CatalogElement) input_p);

      HashSet<CatalogElement> visited = new HashSet<CatalogElement>();
      while (!elements.isEmpty()) {
        CatalogElement element = elements.removeFirst();
        if (!visited.contains(element)) {
          visited.add(element);
          objs.add(element);

          Collection<EObject> usedElements = QueryInterpretor.executeQuery(CatalogElement_UsedElements.class.getSimpleName(), element, context_p);
          for (EObject usedElement : usedElements) {
            if (usedElement instanceof CatalogElement) {
              objs.add(usedElement);
              elements.add((CatalogElement) usedElement);
            }
          }
        }
      }

      return objs;
    }

    return Collections.emptyList();
  }

}
