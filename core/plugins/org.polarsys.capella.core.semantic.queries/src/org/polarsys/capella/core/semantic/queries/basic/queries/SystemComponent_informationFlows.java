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

import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class SystemComponent_informationFlows implements IQuery {

	/**
	 * 
	 */
	public SystemComponent_informationFlows() {
    // do nothing
	}

	/**
	 * current.ownedComponentPort.flows
     * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof SystemComponent) {
			SystemComponent lc = (SystemComponent) object;
			for (ComponentPort port : ComponentExt.getOwnedComponentPort(lc)) {
			  
			  EList<AbstractInformationFlow> informationFlows = port.getInformationFlows();
			  for (AbstractInformationFlow abstractInformationFlow : informationFlows) {
			    result.add(abstractInformationFlow);
        }
			}
		}
		
		return result;
	}

}
