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
package org.polarsys.capella.core.re.handlers.replicable;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.re.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementHandler extends org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandler {

  @Override
  public CatalogElementPkg getRootPackage(EObject object_p) {

    SystemEngineering eng = null;//
    if (object_p instanceof SystemEngineering) {
      eng = (SystemEngineering) object_p;
    } else {
      eng = (SystemEngineering) EcoreUtil2.getFirstContainer(object_p, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    }
    if (eng != null) {
      for (ElementExtension extension : eng.getOwnedExtensions()) {
        if (extension instanceof CatalogElementPkg) {
          return (CatalogElementPkg) extension;
        }
      }
      RecCatalog pkg = ReFactory.eINSTANCE.createRecCatalog();
      pkg.setName("REC Catalog");
      eng.getOwnedExtensions().add(pkg);
      return pkg;
    }

    return null;
  }

  @Override
  public Collection<Object> getAllDefinedReplicableElements(IContext context_p) {
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CATALOG_ELEMENTS_FOR_LIB, selection.iterator().next(), new QueryContext());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<CompliancyDefinition> getAllDefinedCompliancy(EObject location_p) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COMPLIANCY_DEFINITIONS_FOR_LIB, location_p, new QueryContext());

  }

  @Override
  public Collection<Object> getAllDefinedRecReplicableElements(IContext context_p) {
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CATALOG_ELEMENTS_FOR_LIB, selection.iterator().next(), new QueryContext(),
        new IQueryFilter() {

          @Override
          public boolean keepElement(Object element_p, IQueryContext context_p) {
            return (element_p != null) && (element_p instanceof CatalogElement) && (((CatalogElement) element_p).getKind() == CatalogElementKind.REC);
          }
        });
  }

  @Override
  public Collection<Object> getAllDefinedCatalogElementPkgs(IContext context_p) {
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    //Promote is removed from now
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CATALOG_PKGS, selection.iterator().next(), new QueryContext());
  }

}
