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

import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 *
 */
public class Connection_connectedOperationalActivities extends Connection_connectedFunctions {

	/** 
	 * 
	 */
	public Connection_connectedOperationalActivities() {
	  // do nothing
	}

    @Override
    protected boolean isValidInstanceOf(Object exchange) {
        return (exchange instanceof FunctionalExchange) && (BlockArchitectureExt.getRootBlockArchitecture((FunctionalExchange) exchange) instanceof OperationalAnalysis);
    }
}
