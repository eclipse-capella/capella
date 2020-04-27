/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;

/**
 * Returns a
 *
 */
public class GetAllCatalogElementsQuery extends AbstractQuery {

  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    List<Object> values = new ArrayList<Object>();
    if (input_p instanceof CatalogElementPkg) {
      for (CatalogElement element : ((CatalogElementPkg) input_p).getOwnedElements()) {
        values.addAll(QueryInterpretor.executeQuery(getIdentifier(), element, context_p));
      }

      for (CatalogElementPkg pkg : ((CatalogElementPkg) input_p).getOwnedElementPkgs()) {
        values.addAll(QueryInterpretor.executeQuery(getIdentifier(), pkg, context_p));
      }

    } else if (input_p instanceof CatalogElement) {
      values.add(input_p);

      for (CatalogElement element : ((CatalogElement) input_p).getOwnedElements()) {
        values.addAll(QueryInterpretor.executeQuery(getIdentifier(), element, context_p));
      }

    } else if (input_p instanceof EObject) {
      for (CatalogElementPkg pkg : getRootPackages((EObject) input_p)) {
        values.addAll(QueryInterpretor.executeQuery(getIdentifier(), pkg, context_p));
      }
    }

    return values;
  }

  public Collection<CatalogElementPkg> getRootPackages(EObject object_p) {
    List<CatalogElementPkg> values = new ArrayList<CatalogElementPkg>();
    SystemEngineering eng = null;
    if (object_p instanceof SystemEngineering) {
      eng = (SystemEngineering) object_p;
    } else {
      eng = (SystemEngineering) EcoreUtil2.getFirstContainer(object_p, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    }
    if (eng != null) {
      for (ElementExtension extension : eng.getOwnedExtensions()) {
        if (extension instanceof CatalogElementPkg) {
          values.add((CatalogElementPkg) extension);
        }
      }
    }
    return values;
  }

}
