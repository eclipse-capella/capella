/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class PhysicalComponent_InternalPhysicalLinks implements IQuery {

  public PhysicalComponent_InternalPhysicalLinks() {
    // do nothing
  }

  /**
   * Gathering recursively all sub physical components : Gathering all internal physical links
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<PhysicalLink> allPLs = new ArrayList<>();
    List<Object> result = new ArrayList<>();

    if (object instanceof Component) {
      Component seletectedComponent = (Component) object;
      Collection<Part> usedParts = ComponentExt.getAllSubUsedParts(seletectedComponent, true);
      for (Part part : usedParts) {
        allPLs.addAll(PhysicalLinkExt.getAllRelatedPhysicalLinks(part));
      }
      usedParts.addAll(ComponentExt.getRepresentingParts(seletectedComponent));
      
      // If source or target is one of the used parts or representing parts, filter
      for (PhysicalLink pl : allPLs) {
        if (!ListExt.containsAny(PhysicalLinkExt.getSourceParts(pl), usedParts)) {
          result.add(pl);
        }
        if (!ListExt.containsAny(PhysicalLinkExt.getTargetParts(pl), usedParts)) {
          result.add(pl);
        }
      }
    }
    return result;
  }

}
