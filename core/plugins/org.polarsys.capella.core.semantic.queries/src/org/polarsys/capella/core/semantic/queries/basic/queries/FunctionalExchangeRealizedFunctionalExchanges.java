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

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

/**
 * Return realized functional exchanges of current functional exchanges
 * 
 * 
 */
public class FunctionalExchangeRealizedFunctionalExchanges extends AbsFunctionalExchangeRealizedFunctionalExchanges{

	/**
	 * 
	 */
	public FunctionalExchangeRealizedFunctionalExchanges() {
		// do nothing
	}

 
	@Override
	public boolean isValidArchitectureLavel(BlockArchitecture arch) {
		// get realized interaction if current functional exchange is in (logical or physical level)
		if (null != arch && arch instanceof LogicalArchitecture || arch instanceof PhysicalArchitecture) {

			return true;
		}
		
		return false;
	}
}
