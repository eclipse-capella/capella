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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.common.data.activity.ActivityNode;

/**
 * 
 * Return all the incoming Functional Exchanges whose source is not 
 * the current function and the subFunciton of current function 
 * 
 * 
 */
public class FunctionInternalOutGoingDataflows extends AbstractFunctionInternalDataflows {

  public FunctionInternalOutGoingDataflows() {
    // Does nothing
  }

  /**
   * Return target node
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractFunctionInternalDataflows#getActivityNode(org.polarsys.capella.core.data.fa.FunctionalExchange)
   */
  @Override
  public List<ActivityNode> getActivityNode(FunctionalExchange functionalExchange_p) {
    List<ActivityNode> result = new ArrayList<ActivityNode>(0);
    if (null != functionalExchange_p) {
      ActivityNode target = functionalExchange_p.getTarget();
      if (null != target) {
        result.add(target);
      }
    }
    
    return result;
  }

  /**
   * return outGoing functional exchanges
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractFunctionInternalDataflows#getFunctionalExchanges(org.polarsys.capella.core.data.fa.AbstractFunction)
   */
  @Override
  public List<FunctionalExchange> getFunctionalExchanges(AbstractFunction abstractFunction_p) {
    if (abstractFunction_p != null) {
      List<FunctionalExchange> outGoingExchange = FunctionExt.getOutGoingExchange(abstractFunction_p);
      if (!outGoingExchange.isEmpty()) {
        return outGoingExchange;
      }
    }
    
    return new ArrayList<FunctionalExchange>(0);
  }
  
}
