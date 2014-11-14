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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellacore.Type;

/**
 * 
 * Return internal outgoing component exchanges
 * 
 */
public class ComponentInternalOutgoingComponentExchanges extends ComponentOutgoingComponentExchange{

  /**
   * 
   */
  public ComponentInternalOutgoingComponentExchanges() {
    // do nothing
  }

  /**
   * Gathering recursively all sub components:
   * current.ownedPartitions.representedElement
   * Gathering all dataflows
   * (using current.ownedPartitions.type.componentPorts.outgoingFlows)
   * Displaying all outgoing exchanges
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (isValidComponentForComponentExchanges(object_p)) {
      List<Partition> ownedPartitions = getOwnedPartitionableElements(object_p);
      for (Partition partition : ownedPartitions) {
        Type subcomp = partition.getType();
        if (null != subcomp && subcomp instanceof Component) {
            List<Object> computes = super.compute(subcomp);
            if (!computes.isEmpty()) {
              result.addAll(computes);
            }
        }
      }
    }
    return result;
  }



}
