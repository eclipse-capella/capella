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

import java.util.List;

import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;

/**
 * Return incoming communication mean link of current entity
 * 
 * 
 */
public class OperationalEntity_IncomingCommunicationMean extends AbsEntityCommunicationMean{

	public OperationalEntity_IncomingCommunicationMean() {
		// does nothing
	}

	/**
	 * return incoming information flow
	 */
	@Override
	public List<AbstractInformationFlow> getInformationFlows(Entity entity) {
		if (null != entity) {
			return entity.getIncomingInformationFlows();
		}
		return null;
	}
	
	
}
