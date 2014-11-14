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

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class CapellaElement_requirement implements IQuery {

	/**
	 * 
	 */
	public CapellaElement_requirement() {
    // do nothing
	}

	/** 
	 *  
	 * current.sourceTraces(select "Requirement")
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof CapellaElement) {
			CapellaElement sf = (CapellaElement) object_p;
        	EList<AbstractTrace> traces = sf.getOutgoingTraces();
        	for (AbstractTrace trace : traces) {
        		if (trace instanceof RequirementsTrace)
        		{
        			RequirementsTrace rt = (RequirementsTrace) trace; 
        			result.add(rt.getTargetElement());
        		}
			}
		}
        return result;
	}
}
