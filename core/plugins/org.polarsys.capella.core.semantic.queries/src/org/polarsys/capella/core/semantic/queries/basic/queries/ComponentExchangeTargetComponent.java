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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return target component of current component exchange
 * 
 *
 */
public class ComponentExchangeTargetComponent implements IQuery {

	/** 
	 * 
	 */
	public ComponentExchangeTargetComponent() {
	  // do nothing
	}

	/**
	 * 
	 * ownedCommunicationEnds.connectedPart(select "Standard Port").ownerElement (select "Physical Component" AND "not current Component") 
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) { 
    List<Object> result = new ArrayList<Object>(0);
    
    if (object instanceof ComponentExchange) {
      Component targetComponent = ComponentExchangeExt.getTargetComponent((ComponentExchange) object);
      if (null != targetComponent)
        result.add(targetComponent);
    }
    
    return result;
	}
}
