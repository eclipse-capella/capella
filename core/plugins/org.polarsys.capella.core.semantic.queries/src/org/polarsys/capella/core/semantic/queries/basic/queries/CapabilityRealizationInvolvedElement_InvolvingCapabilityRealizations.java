/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;

public class CapabilityRealizationInvolvedElement_InvolvingCapabilityRealizations implements IQuery {

	@Override
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
	    if (object instanceof CapabilityRealizationInvolvedElement) {
	      CapabilityRealizationInvolvedElement component = (CapabilityRealizationInvolvedElement) object;
	      result.addAll(component.getInvolvingCapabilityRealizations());
	    }
	    return result;
	}

}
