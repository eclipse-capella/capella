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

package org.polarsys.capella.core.model.helpers.queries.filters;

import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;

/**
 */
public class RemoveActorsFilter implements IQueryFilter {

	@Override
	public boolean keepElement(Object object, IQueryContext context) {
		Object element = object;

		if (element instanceof Part) {
			element = ((Part) element).getAbstractType();
		}

    if (element instanceof Component && !((Component) element).isActor()) {
      return true;
    }
    return false;
	}

}
