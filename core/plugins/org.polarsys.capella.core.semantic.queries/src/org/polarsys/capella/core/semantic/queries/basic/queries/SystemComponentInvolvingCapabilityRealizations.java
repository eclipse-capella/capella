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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.la.CapabilityRealization;

public class SystemComponentInvolvingCapabilityRealizations implements IQuery {

	@Override
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
	    // gets the Semantic Editing Domain
	    if (object instanceof SystemComponent) {
	      SystemComponent component = (SystemComponent) object;
	      EList<CapabilityRealizationInvolvement> involvingCapabilityRealizations = component.getInvolvingCapabilityRealizationInvolvements();
	      for (CapabilityRealizationInvolvement involvement : involvingCapabilityRealizations) {
	          InvolverElement involver = involvement.getInvolver();
	          if (involver instanceof CapabilityRealization) {
	            result.add(involver);
	          }
	      }
	    }
	    return result;
	}

}
