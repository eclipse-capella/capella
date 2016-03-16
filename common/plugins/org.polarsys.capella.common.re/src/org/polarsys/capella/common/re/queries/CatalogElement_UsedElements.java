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

package org.polarsys.capella.common.re.queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;

/**
 *
 */
public class CatalogElement_UsedElements extends AbstractQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    if ((input != null) && (input instanceof CatalogElement)) {
      List<Object> objs = new ArrayList<Object>();
      for (CatalogElementLink link : ((CatalogElement) input).getOwnedLinks()) {
        if (link.getTarget() != null) {
          objs.add(link.getTarget());
        }
      }
      return objs;
    }
    return Collections.emptyList();
  }

}
