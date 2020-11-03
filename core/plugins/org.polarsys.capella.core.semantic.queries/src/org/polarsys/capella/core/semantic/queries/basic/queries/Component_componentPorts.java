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
