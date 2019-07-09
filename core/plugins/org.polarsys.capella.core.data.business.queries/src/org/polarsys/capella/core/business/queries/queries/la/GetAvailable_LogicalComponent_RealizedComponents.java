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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_LogicalComponent_RealizedComponents extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    CapellaElement element = (CapellaElement)input;
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    availableElements.addAll(BlockArchitectureExt.getAllComponents(SystemEngineeringExt.getLogicalArchitecture(sysEng)));
    return (List) availableElements;
	}

}