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

import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query allows to get the incoming interactions from an AbstractFuntion
 * 
 */
public class AbstractFunction_incomingInteraction implements IQuery {

  /**
	 * 
	 */
  public AbstractFunction_incomingInteraction() {
    // Does nothing
  }

  /**
   * Get the incoming flows: ownedFlowPorts.incomingFlows
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>(0);
    
    if (object_p instanceof OperationalActivity) {
      getInComingExchagnes(object_p, result);
    }else if (object_p instanceof SystemFunction || object_p instanceof LogicalFunction || object_p instanceof PhysicalFunction ) {
      getInComingExchagnes(object_p, result);
    }
    
    return result;
  }

  /**
   * @param object_p
   * @param result
   */
  private void getInComingExchagnes(Object object_p, List<Object> result) {
    List<FunctionalExchange> incomingExchange = FunctionExt.getIncomingExchange((AbstractFunction) object_p);
    result.addAll(incomingExchange);
  }
}
