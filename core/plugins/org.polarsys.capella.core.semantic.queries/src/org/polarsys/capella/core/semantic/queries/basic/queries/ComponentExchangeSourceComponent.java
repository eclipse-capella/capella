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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return source component of current component exchange
 * 
 *
 */
public class ComponentExchangeSourceComponent implements IQuery {

	/** 
	 * 
	 */
	public ComponentExchangeSourceComponent() {
	  // do nothing
	}

	/**
	 * 
	 * ownedCommunicationEnds.connectedPart(select "Standard Port").ownerElement (select "Physical Component" AND "not current Component") 
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) { 
    List<Object> result = new ArrayList<Object>(0);
    
    if (object_p instanceof ComponentExchange) {
      Component sourceComponent = ComponentExchangeExt.getSourceComponent((ComponentExchange) object_p);
      if (null != sourceComponent )
        result.add(sourceComponent);
    }
    
    return result;
	}
}
