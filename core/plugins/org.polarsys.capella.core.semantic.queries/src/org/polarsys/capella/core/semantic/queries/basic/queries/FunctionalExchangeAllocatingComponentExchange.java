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

import org.polarsys.capella.core.data.oa.CommunicationMean;

/**
 * Return allocating component exchange(!communication mean) of current functional exchanges(which is also called
 * as Interaction on Operational Analysis level)
 * 
 * 
 */
public class FunctionalExchangeAllocatingComponentExchange extends
		FunctionalExchange_relatedComponentDataflow {

	/**
	 * constructor
	 */
	public FunctionalExchangeAllocatingComponentExchange() {
		// Does nothing
	}

	@Override
	public boolean isValidInstanceOf(Object element_p) {
		if (null != element_p && !(element_p instanceof CommunicationMean)) {
			return true;
		}
		return false;
	}
}
