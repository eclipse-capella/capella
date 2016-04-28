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

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return physical link involved in physical path involvement
 */
public class PhysicalPathInvolvmentPhysicalLink  implements IQuery {

	/**
	 * {@inheritDoc}
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>(1);
		if (object instanceof PhysicalPathInvolvement) {
			PhysicalPathInvolvement ppi = (PhysicalPathInvolvement) object;
			AbstractPathInvolvedElement involvedElement = ppi.getInvolvedElement();
			if (involvedElement instanceof PhysicalLink) {
				result.add(involvedElement);
			}
		}
		return result;
	}
}
