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
 * Returns referencingLinks.getOrigin.getTarget() for each referencing RPL/REC_RPL
 * 
 */
public class ReferencingReplicableElementLinks implements IQuery {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();

    if (object instanceof CatalogElement) {
      CatalogElement source = (CatalogElement) object;

    } else {
      Collection<EObject> links = EObjectExt.getReferencers((EObject) object, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
      for (EObject link : links) {
        if (link != null) {
          if (link instanceof CatalogElementLink) {
            CatalogElementLink mLink = (CatalogElementLink) link;

            CatalogElement source = ((CatalogElementLink) link).getSource();

            if (source != null) {
              if (((source.getKind() == CatalogElementKind.RPL) || (source.getKind() == CatalogElementKind.REC_RPL))) {
                if ((mLink.getOrigin() != null) && (mLink.getOrigin().getTarget() != null)) {
                  result.add(mLink.getOrigin().getTarget());

                }
              }
            }

          }
        }
      }
    }

    return result;
  }
}
