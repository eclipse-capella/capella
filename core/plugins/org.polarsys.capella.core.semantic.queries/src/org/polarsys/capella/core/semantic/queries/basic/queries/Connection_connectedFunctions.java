/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;

/**
 *
 */
public class Connection_connectedFunctions implements IQuery {

	/** 
	 * 
	 */
	public Connection_connectedFunctions() {
	  // do nothing
	}

	/**
	 * 
	 * ownedCommunicationEnds.connectedPart(select "Standard Port").ownerElement (select "Physical Component" AND "not current Component") 
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (isValidInstanceOf(object)) {
            FunctionalExchange fun = (FunctionalExchange) object;
            EObject source = FunctionalExchangeExt.getSourceFunction(fun);
            EObject target = FunctionalExchangeExt.getTargetFunction(fun);

            if (source != null)
                result.add(source);
            if (target != null)
                result.add(target);
        }
        return result;
    }

	protected boolean isValidInstanceOf(Object exchange) {
        return (exchange instanceof FunctionalExchange) && !(BlockArchitectureExt.getRootBlockArchitecture((FunctionalExchange) exchange) instanceof OperationalAnalysis);
    }
}
