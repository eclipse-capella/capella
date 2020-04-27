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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Capability_scenarios implements IQuery {

	public Capability_scenarios() {
    // do nothing
	}

	/** 
	 *  
	 * current.ownedScenario
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof AbstractCapability) {
			AbstractCapability c = (AbstractCapability) object;
			EList<Scenario> ownedScenarios = c.getOwnedScenarios();
			if(!ownedScenarios.isEmpty())
				result.addAll(ownedScenarios);
		}
		return result;
	}
}
