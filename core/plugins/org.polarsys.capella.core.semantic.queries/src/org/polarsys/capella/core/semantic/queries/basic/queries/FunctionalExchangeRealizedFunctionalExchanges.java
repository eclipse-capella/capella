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
	public boolean isValidArchitectureLavel(BlockArchitecture arch_p) {
		// get realized interaction if current functional exchange is in (logical or physical level)
		if (null != arch_p && arch_p instanceof LogicalArchitecture || arch_p instanceof PhysicalArchitecture) {

			return true;
		}
		
		return false;
	}
}
