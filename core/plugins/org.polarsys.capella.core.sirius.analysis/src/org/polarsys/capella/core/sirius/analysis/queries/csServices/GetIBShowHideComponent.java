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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;
import org.polarsys.capella.core.sirius.analysis.CsServices;

public class GetIBShowHideComponent extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    DSemanticDecorator decorator = (DSemanticDecorator) input;

    if (!(decorator.getTarget() instanceof Component || decorator.getTarget() instanceof ComponentPkg)) {
      return Collections.emptyList();
    }

    ModelElement target = (ModelElement) decorator.getTarget();
    List<Component> components = Collections.emptyList();

    if (decorator instanceof DDiagram) {
      BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(target);
      components = CsServices.getService().getAllSubDefinedComponents(architecture);
    } else if (target instanceof Component) {
      components = PartExt.getComponentsOfParts(ComponentExt.getAllSubUsedParts((Component) target, false));
    }

    return filter(components).stream().filter(c -> !c.getRepresentingParts().isEmpty()).collect(Collectors.toList());
  }

  protected List<Component> filter(List<Component> components) {
    components = QueryInterpretor.executeFilter(components,
        new MultiFilter(new IQueryFilter[] { new RemoveActorsFilter() }));
    return components;
  }
}
