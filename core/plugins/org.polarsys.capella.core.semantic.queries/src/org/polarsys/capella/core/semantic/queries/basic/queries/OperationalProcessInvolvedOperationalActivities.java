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

import org.polarsys.capella.core.data.oa.OperationalProcess;

/**
 * Return involved operational activities of the current operational process
 * 
 *
 */
public class OperationalProcessInvolvedOperationalActivities extends FunctionalChain_enactedFunctions {

	/**
	 * 
	 */
	public OperationalProcessInvolvedOperationalActivities() {
    // do nothing
	}

	/**
	 * 
	 * current.getEnactedFunctions
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
  @Override
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof OperationalProcess) {
			List<Object> compute = super.compute(object);
			if(!compute.isEmpty())
				result.addAll(compute);
		}
		return result;
	}
}
