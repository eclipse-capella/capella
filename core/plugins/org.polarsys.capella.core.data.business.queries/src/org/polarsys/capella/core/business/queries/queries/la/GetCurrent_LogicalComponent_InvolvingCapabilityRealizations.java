/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class GetCurrent_LogicalComponent_InvolvingCapabilityRealizations extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
	  CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> currentElements = new ArrayList<EObject>();
    if (capellaElement instanceof CapabilityRealizationInvolvedElement) {
      currentElements.addAll(((CapabilityRealizationInvolvedElement) capellaElement).getInvolvingCapabilityRealizations());
    }
    return (List) currentElements;
	}

}