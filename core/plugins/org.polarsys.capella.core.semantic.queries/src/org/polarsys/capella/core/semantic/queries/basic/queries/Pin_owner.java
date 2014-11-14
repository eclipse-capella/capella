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

import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Pin_owner implements IQuery {

	/**
	 * 
	 */
	public Pin_owner() {
    // do nothing
	}

	/** 
	 *  
	 * current.ownerElement
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof Pin) {
			Pin p = (Pin) object_p;
			result.add(p.eContainer());
		}
		return result;
	}
}
