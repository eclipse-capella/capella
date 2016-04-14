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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return involved functions of the current functional chain
 * 
 *
 */
public class FunctionalChain_enactedFunctions implements IQuery {

	/**
	 * 
	 */
	public FunctionalChain_enactedFunctions() {
    // do nothing
	}

	/** 
	 *  
	 * current.getEnactedFunctions
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof FunctionalChain) {
			FunctionalChain fc = (FunctionalChain) object;
			// get involved functions
			EList<AbstractFunction> enactedFunctions = fc.getEnactedFunctions();
			if(!enactedFunctions.isEmpty())
				result.addAll(enactedFunctions);
		}
		return result;
	}
}
