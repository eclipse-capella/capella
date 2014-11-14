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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class SystemComponent_internalInformationflows implements IQuery {

	/**
	 * 
	 */
	public SystemComponent_internalInformationflows() {
	  // do nothing
	}

	/**
	 * Gathering recursively all sub components:
     * current.ownedPartitions.representedElement
     * Gathering all dataflows BETWEEN these components
     * (using current.ownedPartitions.type.componentPorts.flows)
     * Displaying all internal exchanges
     * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
	  List<Object> result = new ArrayList<Object>();
    if (object_p instanceof SystemComponent) {
      SystemComponent lc = (SystemComponent) object_p;
      EList<Partition> partitions = lc.getOwnedPartitions();
      for (Partition partition : partitions) {
        Type subcomp = partition.getType();
        if (null != subcomp) {
          if (subcomp instanceof Component) {
            for (ComponentPort port : ComponentExt.getOwnedComponentPort((Component)subcomp)) {
              result.add(port.getInformationFlows());
            }
          }
        }
      }
    }
    return result;
	}

}
