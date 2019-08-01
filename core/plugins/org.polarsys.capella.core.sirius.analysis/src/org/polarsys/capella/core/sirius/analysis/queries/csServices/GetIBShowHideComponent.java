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
package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;
import org.polarsys.capella.core.sirius.analysis.CsServices;

public class GetIBShowHideComponent extends AbstractQuery {

  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    DSemanticDecorator decorator_p = (DSemanticDecorator) input_p;
    List<Component> components = new ArrayList<Component>();

    if (!(decorator_p.getTarget() instanceof Component)) {
      return (List) new ArrayList<Component>();
    }
    EObject target = CsServices.getService().getIBTarget(decorator_p);
    if (decorator_p instanceof DDiagram) {
      if (target instanceof Component) {
        components.addAll(CsServices.getService().getBrothersComponents((Component) target));
        components.addAll(ComponentExt.getSubDefinedComponents((Component) target));
        components.addAll(ComponentExt.getSubUsedComponents((Component) target));
        components.add((Component) target);

      } else if (target instanceof BlockArchitecture) {
        components.addAll(ComponentExt.getSubDefinedComponents((BlockArchitecture) target));
      }
    } else if (target instanceof Component) {
      components = new ArrayList<Component>();
      components.addAll(ComponentExt.getSubDefinedComponents((Component) target));
      components.addAll(ComponentExt.getSubUsedComponents((Component) target));
    }
    components = QueryInterpretor.executeFilter(components, new MultiFilter(new IQueryFilter[] { new RemoveActorsFilter()}));
    return (List) new ArrayList<Component>(components);
  }
}
