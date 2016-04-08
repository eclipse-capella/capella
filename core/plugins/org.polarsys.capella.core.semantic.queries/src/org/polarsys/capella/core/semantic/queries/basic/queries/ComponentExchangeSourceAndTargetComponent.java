/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
 * Return source and target component of current component exchange
 * 
 *
 */
public class ComponentExchangeSourceAndTargetComponent implements IQuery {

	/** 
	 * 
	 */
	public ComponentExchangeSourceAndTargetComponent() {
	  // do nothing
	}

	/**
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) { 
    List<Object> result = new ArrayList<Object>(0);
    
    if (object instanceof ComponentExchange) {
      Component sourceComponent = ComponentExchangeExt.getSourceComponent((ComponentExchange) object);
      if (null != sourceComponent )
        result.add(sourceComponent);
      Component targetComponent = ComponentExchangeExt.getTargetComponent((ComponentExchange) object);
      if (null != targetComponent )
        result.add(targetComponent);
      
    }
    
    return result;
	}
}
