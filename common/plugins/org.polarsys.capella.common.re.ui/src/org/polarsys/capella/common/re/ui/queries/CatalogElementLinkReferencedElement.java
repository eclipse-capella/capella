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

package org.polarsys.capella.common.re.ui.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.re.CatalogElementLink;

/**
 * Returns for a CatalogElement its origin
 * Returns for other elements, its referencing REC
 */
public class CatalogElementLinkReferencedElement implements IQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof CatalogElementLink) {
      CatalogElementLink element = (CatalogElementLink) object;
      if (element.getTarget() != null) {
        result.add(element.getTarget());
      }
    }
    return result;
  }
}
