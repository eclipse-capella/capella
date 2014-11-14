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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

/**
 *
 */
public class CatalogElement_ReferencingElements extends AbstractQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) {
    if ((input_p != null) && (input_p instanceof CatalogElement)) {
      List<Object> objs = new ArrayList<Object>();
      ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
      Collection<EObject> links = EObjectExt.getReferencers((CatalogElement) input_p, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
      for (EObject object : links) {
        if (object instanceof CatalogElementLink) {
          CatalogElementLink link = (CatalogElementLink) object;
          if (link.getSource() != null) {
            elements.add(link.getSource());
          }
        }
      }
      return objs;
    }
    return Collections.emptyList();
  }
}
