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

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return Component Exchanges and Delegation of current Component Port
 *
 */
public abstract class AbstractComponentPortComponentExchanges implements IQuery {

	/**
	 * 
	 */
	public AbstractComponentPortComponentExchanges() {
    // do nothing
	}

	/**
	 * 
	 * source.owner
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof ComponentPort) {
		  ComponentPort port = (ComponentPort) object_p;
			List<AbstractInformationFlow> flows = getInformationFlows(port);
			for (AbstractInformationFlow abstractInformationFlow : flows) {
        if (abstractInformationFlow instanceof ComponentExchange) {
            if (isValidComponentExchange((ComponentExchange) abstractInformationFlow)) {
              result.add(abstractInformationFlow);
          }    
        }
      }
			
		}
		
    return result;
	}
	
	public abstract boolean isValidComponentExchange(ComponentExchange exchange_p);
	
	public abstract List<AbstractInformationFlow> getInformationFlows(ComponentPort port_p);
}
