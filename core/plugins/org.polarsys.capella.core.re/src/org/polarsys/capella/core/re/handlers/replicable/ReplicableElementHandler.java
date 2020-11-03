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
package org.polarsys.capella.core.re.handlers.replicable;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.EcoreUtil2;
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
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.re.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementHandler extends org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandler {

  @Override
  public CatalogElementPkg getRootPackage(EObject object) {

    SystemEngineering eng = null;//
    if (object instanceof SystemEngineering) {
      eng = (SystemEngineering) object;
    } else {
      eng = (SystemEngineering) EcoreUtil2.getFirstContainer(object, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    }

    // https://bugs.polarsys.org/show_bug.cgi?id=1915
    if (eng == null) {
      EObject e = EcoreUtil.getRootContainer(object);
      if (e instanceof Project) {
        eng = SystemEngineeringExt.getSystemEngineering((Project) e);
      }
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
  public Collection<CatalogElement> getAllDefinedReplicableElements(IContext context) {
    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CATALOG_ELEMENTS_FOR_LIB, selection.iterator().next(), new QueryContext());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<CompliancyDefinition> getAllDefinedCompliancy(EObject location) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COMPLIANCY_DEFINITIONS_FOR_LIB, location, new QueryContext());

  }

  @Override
  public Collection<CatalogElement> getAllDefinedRecReplicableElements(IContext context) {
    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CATALOG_ELEMENTS_FOR_LIB, selection.iterator().next(), new QueryContext(),
        new IQueryFilter() {

          @Override
          public boolean keepElement(Object element, IQueryContext context) {
            return (element != null) && (element instanceof CatalogElement) && (((CatalogElement) element).getKind() == CatalogElementKind.REC);
          }
        });
  }

  @Override
  public Collection<Object> getAllDefinedCatalogElementPkgs(IContext context) {
    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    //Promote is removed from now
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CATALOG_PKGS, selection.iterator().next(), new QueryContext());
  }

}
