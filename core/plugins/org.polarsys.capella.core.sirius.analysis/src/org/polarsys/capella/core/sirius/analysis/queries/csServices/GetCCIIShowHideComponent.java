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
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;

public class GetCCIIShowHideComponent extends AbstractQuery {

  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    List<Component> result = getSubComponents((EObject) input_p);
    result = filter(result);
    return (List) result;
  }

  protected List<Component> filter(List<Component> result) {
    result = QueryInterpretor.executeFilter(result, new RemoveActorsFilter());
    return result;
  }

  private List<Component> getSubComponents(EObject target) {
    List<Component> components = new ArrayList<Component>();
    if (null == target) {
      return components;
    }
    if (target instanceof BlockArchitecture) {
      components.addAll(ComponentExt.getSubDefinedComponents((BlockArchitecture) target));

    } else if (target instanceof Component) {
      components.addAll(ComponentExt.getSubDefinedComponents((Component) target));
      components.addAll(ComponentExt.getSubUsedComponents((Component) target));
    } else if (target instanceof ComponentPkg) {
      components.addAll(ComponentPkgExt.getOwnedComponents((ComponentPkg) target));
      components.addAll(ComponentPkgExt.getSubUsedComponents((ComponentPkg) target));
    }

    components.remove(target);
    return components;
  }
}
