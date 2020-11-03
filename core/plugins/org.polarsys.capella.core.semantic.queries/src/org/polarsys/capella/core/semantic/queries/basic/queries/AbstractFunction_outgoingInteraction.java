/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query allows to get the outgoing interactions from an AbstractFuntion
 * 
 */
public class AbstractFunction_outgoingInteraction implements IQuery {

  /**
	 * 
	 */
  public AbstractFunction_outgoingInteraction() {
    // Does nothing
  }

  /**
   * current.ownedFlowPorts.outgoingFlows
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof OperationalActivity) {
      getOutGoingExchagnes(object, result);
    }else if (object instanceof SystemFunction || object instanceof LogicalFunction || object instanceof PhysicalFunction ) {
      getOutGoingExchagnes(object, result);
    }
    
    return result;
  }

  /**
   * @param object
   * @param result
   */
  private void getOutGoingExchagnes(Object object, List<Object> result) {
    List<FunctionalExchange> outGoingExchange = FunctionExt.getOutGoingExchange((AbstractFunction) object);
    if(!outGoingExchange.isEmpty())
      result.addAll(outGoingExchange);
  }
}
