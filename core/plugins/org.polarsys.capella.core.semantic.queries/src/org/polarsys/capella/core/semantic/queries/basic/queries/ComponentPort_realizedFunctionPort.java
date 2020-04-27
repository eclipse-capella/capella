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

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return allocated ports of current Component Port
 *
 */
public class ComponentPort_realizedFunctionPort implements IQuery {

	/**
	 * 
	 */
	public ComponentPort_realizedFunctionPort() {
	  // do nothing
	}

	/** 
	 *  
	 * current.outgoingPortRealisation.realisedPort
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof ComponentPort) {
		  ComponentPort p = (ComponentPort) object;
			EList<PortAllocation> allocations = p.getOutgoingPortAllocations();
			for (PortAllocation allocation : allocations) {
				Port allocatedPort = allocation.getAllocatedPort();
				if (null != allocatedPort) {
	        result.add(allocatedPort);          
        }
			}
		}
        return result;
	}
}
