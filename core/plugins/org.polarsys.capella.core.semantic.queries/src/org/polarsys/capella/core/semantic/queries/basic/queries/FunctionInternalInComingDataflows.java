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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.common.data.activity.ActivityNode;

/**
 * 
 * Return all the outGoing Functional Exchanges whose target is not 
 * the current function and the subFunciton of current function 
 * 
 * 
 */
public class FunctionInternalInComingDataflows extends AbstractFunctionInternalDataflows {

  public FunctionInternalInComingDataflows() {
    // Does nothing
  }

  /**
   * Return source node
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractFunctionInternalDataflows#getActivityNode(org.polarsys.capella.core.data.fa.FunctionalExchange)
   */
  @Override
  public List<ActivityNode> getActivityNode(FunctionalExchange functionalExchange) {
    List<ActivityNode> result = new ArrayList<ActivityNode>(0);
    if (null != functionalExchange) {
      ActivityNode source = functionalExchange.getSource();
      if (null != source) {
        result.add(source);
      }
    }
    
    return result;
  }

  /**
   * return outGoing functional exchanges
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractFunctionInternalDataflows#getFunctionalExchanges(org.polarsys.capella.core.data.fa.AbstractFunction)
   */
  @Override
  public List<FunctionalExchange> getFunctionalExchanges(AbstractFunction abstractFunction) {
    if (abstractFunction != null) {
      List<FunctionalExchange> inComingExchange = FunctionExt.getIncomingExchange(abstractFunction);
      if (!inComingExchange.isEmpty()) {
        return inComingExchange;
      }
    }
    
    return new ArrayList<FunctionalExchange>(0);
  }
  
}
