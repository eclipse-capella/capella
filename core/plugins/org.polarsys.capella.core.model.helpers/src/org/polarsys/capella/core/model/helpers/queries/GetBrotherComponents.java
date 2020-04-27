/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;

/**
 */
public class GetBrotherComponents extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    Component component = (Component) input;
    Collection<Component> components = new java.util.HashSet<Component>();
    // Add components which are brothers of component-parts
    for (Part part : component.getRepresentingParts()) {
      Component container = ComponentExt.getDirectParent(part);
      if (container != null) {
        for (Part containerPart : container.getContainedParts()) {
          if (containerPart.getType() instanceof Component) {
            components.add((Component) containerPart.getType());
          }
        }
      } else {
        if (part.eContainer() instanceof ComponentPkg) {
          components.addAll(PartExt.getComponentsOfParts(((ComponentPkg) part.eContainer()).getOwnedParts())); 
        }
      }
    }
    components.remove(component);
    return new ArrayList<Object>(components);
  }
}
