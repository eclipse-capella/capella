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
package org.polarsys.capella.core.model.helpers.queries.filters;

import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;

/**
 */
public class RemoveActorsFilter implements IQueryFilter {

	@Override
	public boolean keepElement(Object element_p, IQueryContext context_p) {
		Object element = element_p;

		if (element instanceof Part) {
			element = ((Part) element).getAbstractType();
		}

		if (!(element instanceof AbstractActor) && (element instanceof Component)) {
			return true;
		}
		return false;
	}

}
