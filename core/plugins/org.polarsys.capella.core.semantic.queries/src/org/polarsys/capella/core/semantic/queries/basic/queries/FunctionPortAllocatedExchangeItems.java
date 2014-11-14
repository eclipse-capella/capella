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

import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return inComing and outGoingExchagneItems of ExchagneItems Function Ports
 */
public class FunctionPortAllocatedExchangeItems implements IQuery {

	/**
	 * 
	 */
	public FunctionPortAllocatedExchangeItems() {
    // do nothing
	}

	/** 
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof FunctionPort) {
		  if (object_p instanceof FunctionInputPort) {
		    FunctionInputPort input = (FunctionInputPort) object_p;
		    EList<ExchangeItem> exchangeItems = input.getIncomingExchangeItems();
		    if (!exchangeItems.isEmpty()) {
          result.addAll(exchangeItems);
        }
      }else  if (object_p instanceof FunctionOutputPort) {
        FunctionOutputPort input = (FunctionOutputPort) object_p;
        EList<ExchangeItem> exchangeItems = input.getOutgoingExchangeItems();
        if (!exchangeItems.isEmpty()) {
          result.addAll(exchangeItems);
        }
      }
		}
		return result;
	}
}
