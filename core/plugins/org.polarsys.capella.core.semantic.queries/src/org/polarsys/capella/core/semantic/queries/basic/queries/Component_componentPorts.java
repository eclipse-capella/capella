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

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 *
 */
public class Component_componentPorts implements IQuery {

	/**
	 * 
	 */
	public Component_componentPorts() {
    // do nothing
	}

	/** 
	 *  
	 * current.componentPorts
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
  public List<Object> compute(Object object) {
		if (object instanceof Component) {
		  Component lc = (Component) object;
			return (List)ComponentExt.getOwnedComponentPort(lc);
		}
		return new ArrayList<Object>(0);
	}
}
