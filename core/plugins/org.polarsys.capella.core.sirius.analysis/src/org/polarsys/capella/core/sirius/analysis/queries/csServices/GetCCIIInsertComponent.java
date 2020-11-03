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
package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class GetCCIIInsertComponent extends AbstractQuery {

  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    EObject element = (EObject) input_p;
    Collection<Component> components = new java.util.HashSet<Component>();

    // Add components accessible by namespace
    components.addAll(ComponentExt.getAvailableComponentsByNamespace(element));

    if (element instanceof BlockArchitecture) {
      List<Component> cs = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_SUB_DEFINED_COMPONENTS, element, context_p);
      components.addAll(cs);
    }

    if (element instanceof Component) {
      Component component = (Component) element;
      if (!CsServices.getService().isMultipartMode(component)) {
        // Remove component from existing part
        components.removeAll(CsServices.getService().getSubUsedComponents(component));
        // Remove current component and remove all containers of current component
        components.remove(element);
        components.removeAll(CsServices.getService().getParentContainersByParts(component));
      }
    }

    if (element instanceof PhysicalComponent) {
      components = CsServices.getService().filterPhysicalComponentsByNature((PhysicalComponent) element, components);
    }
    return new ArrayList<Object>(components);
  }
}
