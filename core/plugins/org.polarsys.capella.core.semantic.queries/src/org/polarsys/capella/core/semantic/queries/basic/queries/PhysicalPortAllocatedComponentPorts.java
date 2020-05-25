/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;

public class PhysicalPortAllocatedComponentPorts implements IQuery {

  @Override
  public List<Object> compute(Object object) {

    List<Object> result = new ArrayList<Object>();
    if (object instanceof PhysicalPort) {
      
      PhysicalPort phPort = (PhysicalPort) object;
      EList<ComponentPort> allocatedComponentPorts = phPort.getAllocatedComponentPorts();
      if (!allocatedComponentPorts.isEmpty()) {
        result.addAll(allocatedComponentPorts);
      }
    }
    return result;
  }
}
