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

package org.polarsys.capella.common.re.ui.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

/**
 * Returns for a CatalogElement its origin
 * Returns for other elements, its referencing REC
 */
public class CatalogElementOrigin implements IQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof CatalogElement) {
      CatalogElement element = (CatalogElement) object;
      if (element.getOrigin() != null) {
        result.add(element.getOrigin());
      }
    } else {

      Collection<EObject> links = EObjectExt.getReferencers((EObject) object, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
      for (EObject link : links) {
        if (link != null) {
          if (link instanceof CatalogElementLink) {
            CatalogElement source = ((CatalogElementLink) link).getSource();

            if (source != null) {
              if (source.getKind() == CatalogElementKind.REC) {
                result.add(source);
              }
            }

          }
        }
      }
    }
    return result;
  }
}
