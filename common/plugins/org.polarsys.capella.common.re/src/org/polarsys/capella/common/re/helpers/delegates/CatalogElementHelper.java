/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.helpers.delegates;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.queries.QueryIdentifierConstants;

public class CatalogElementHelper {

  private static CatalogElementHelper instance;

  private CatalogElementHelper() {
  }

  public static CatalogElementHelper getInstance() {
    if (instance == null) {
      instance = new CatalogElementHelper();
    }
    return instance;
  }

  public Object doSwitch(CatalogElement element, EStructuralFeature feature) {
    Object ret = null;

    if (RePackage.Literals.CATALOG_ELEMENT__REFERENCED_ELEMENTS.equals(feature)) {
      ret = getReferencedElements(element);
    } else if (RePackage.Literals.CATALOG_ELEMENT__REPLICATED_ELEMENTS.equals(feature)) {
      ret = getReplicatedElements(element);
    }
    return ret;
  }

  protected List<Object> getReferencedElements(CatalogElement element) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.CatalogElement_UsedElements, element,
        new QueryContext());
  }

  protected List<Object> getReplicatedElements(CatalogElement element) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.CatalogElement_ReplicatedElements, element,
        new QueryContext());
  }

}
