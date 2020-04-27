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

import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return Incoming functional exchanges for current system, logical or physical function
 * 
 * 
 */
public class Function_incomingDataflows implements IQuery {

  /**
	 * 
	 */
  public Function_incomingDataflows() {
    // Does nothing
  }

  /**
   * 
   * Get incoming flows: ownedFlowPorts.incomingFlows
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    if (object instanceof SystemFunction || object instanceof LogicalFunction || 
        object instanceof PhysicalFunction) {
      AbstractFunction sf = (AbstractFunction) object;
      EList<InputPin> fpins = sf.getInputs();
      for (InputPin fpin : fpins) {
        if (fpin instanceof FunctionInputPort) {
          FunctionInputPort fport = (FunctionInputPort) fpin;
          EList<ActivityEdge> incoming = fport.getIncoming();
          if (!incoming.isEmpty()) {
            result.addAll(incoming);            
          }
        }
      }
    }
    
    return result;
  }
}
