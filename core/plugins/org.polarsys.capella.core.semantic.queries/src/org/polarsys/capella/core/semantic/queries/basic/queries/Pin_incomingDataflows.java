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

import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Pin_incomingDataflows implements IQuery {

	/**
	 * 
	 */
	public Pin_incomingDataflows() {
    // do nothing
	}

	/** 
	 * 
	 * current.exchanges
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof FunctionInputPort) {
			FunctionInputPort fp = (FunctionInputPort) object_p;
			result.addAll(fp.getIncoming());
		}
		else if (object_p instanceof FunctionOutputPort) {
			FunctionOutputPort fp = (FunctionOutputPort) object_p;
			result.addAll(fp.getOutgoing());
		}
		return result;
	}
}
