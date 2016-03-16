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

package org.polarsys.capella.common.re.ui.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;

/**
 * Returns all linked catalog elements (ie except all semantic elements)
 */
public class CatalogElementRelatedReplicas implements IQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof CatalogElement) {
      CatalogElement element = (CatalogElement) object;
      for (CatalogElementLink link : element.getOwnedLinks()) {
        if ((link != null) && (link.getTarget() != null) && (link.getTarget() instanceof CatalogElement)) {
          result.add(link.getTarget());
        }
      }
    }
    return result;
  }

}
