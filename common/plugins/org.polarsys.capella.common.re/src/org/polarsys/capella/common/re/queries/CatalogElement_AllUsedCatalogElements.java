/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  public List<Object> execute(Object input, IQueryContext context) {
    if ((input != null) && (input instanceof CatalogElement)) {
      List<Object> objs = new ArrayList<Object>();

      LinkedList<CatalogElement> elements = new LinkedList<CatalogElement>();
      elements.add((CatalogElement) input);
      String queryUsedELements = CatalogElement_UsedElements.class.getSimpleName();

      HashSet<CatalogElement> visited = new HashSet<CatalogElement>();
      while (!elements.isEmpty()) {
        CatalogElement element = elements.removeFirst();
        if (!visited.contains(element)) {
          visited.add(element);
          objs.add(element);

          Collection<EObject> usedElements = QueryInterpretor.executeQuery(queryUsedELements, element, context);
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
