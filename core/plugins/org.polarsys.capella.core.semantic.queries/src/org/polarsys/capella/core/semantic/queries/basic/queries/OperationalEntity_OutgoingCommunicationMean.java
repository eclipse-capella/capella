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

import java.util.List;

import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;

/**
 * Return outgoing communication means of current entity
 * 
 *
 */
public class OperationalEntity_OutgoingCommunicationMean extends AbsEntityCommunicationMean{

	public OperationalEntity_OutgoingCommunicationMean() {
		// does nothing
	}
	
	/**
	 * return outgoing information flow
	 */
	@Override
	public List<AbstractInformationFlow> getInformationFlows(Entity entity_p) {
		if (null != entity_p) {
			return entity_p.getOutgoingInformationFlows();
		}
		return null;
	}
	
}

