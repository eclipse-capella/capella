/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.Region;

public class EntryExitPoint_ParentRegion implements IQuery {

	public EntryExitPoint_ParentRegion() {
		// nothing to do here
	}

	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof EntryPointPseudoState || object instanceof ExitPointPseudoState) {
		  Pseudostate state = (Pseudostate) object;
		  EObject container = state.eContainer();
		  if (container instanceof Region) {
		    result.add(container);
		  }
		}
		return result;
	}

}
