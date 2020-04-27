/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return outgoing function ports of current Physical Port
 *
 */
public class PhysicalPortOutgoingFunctionPorts implements IQuery{

	/**
	 * 
	 */
	public PhysicalPortOutgoingFunctionPorts() {
    // do nothing
	}

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    if (object instanceof PhysicalPort) {
      PhysicalPort port = (PhysicalPort) object;
      EList<PortAllocation> allocations = port.getOutgoingPortAllocations();
      for (PortAllocation allocation : allocations) {
        Port allocatedPort = allocation.getAllocatedPort();
        if (null != allocatedPort && allocatedPort instanceof FunctionPort) {
          result.add(allocatedPort);
        }
      }
    }
    
    return result;
  }

  
}
