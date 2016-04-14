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

import org.polarsys.capella.core.data.oa.CommunicationMean;

/**
 * Return allocating communication mean of current functional exchanges(which is also called
 * as Interaction on Operational Analysis level)
 * 
 * 
 */
public class FunctionalExchangeAllocatingCommunicationMean extends
		FunctionalExchange_relatedComponentDataflow {

	/**
	 * constructor
	 */
	public FunctionalExchangeAllocatingCommunicationMean() {
		// Does nothing
	}
  
	@Override
	public boolean isValidInstanceOf(Object element) {
		if (null != element && element instanceof CommunicationMean) {
			return true;
		}
		return false;
	}
}
