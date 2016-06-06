/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class SystemComponent_internalIncomingInformationflows implements IQuery {

  /**
   * 
   */
  public SystemComponent_internalIncomingInformationflows() {
    // do nothing
  }

  /**
   * Gathering recursively all sub components:
   * current.ownedPartitions.representedElement
   * Gathering all dataflows
   * (using current.ownedPartitions.type.componentPorts.incomingFlows)
   * Displaying all internal exchanges
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof SystemComponent) {
      SystemComponent lc = (SystemComponent) object;
      EList<Partition> partitions = lc.getOwnedPartitions();
      for (Partition partition : partitions) {
        Type subcomp = partition.getType();
        if (null != subcomp) {
          if (subcomp instanceof Component) {
            SystemComponent_incomingInformationflows incomInfoFlows = new SystemComponent_incomingInformationflows();
            List<Object> computes = incomInfoFlows.compute(subcomp);
            if (!computes.isEmpty()) {
              result.addAll(computes);
            }
          }
        }
      }
    }
    return result;
  }

}
