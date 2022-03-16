/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.oa.CommunicationMean;

/**
 *
 */
public class Connection_connectedEntities implements IQuery {

	/** 
	 * 
	 */
	public Connection_connectedEntities() {
	  // do nothing
	}

	/**
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
    public List<Object> compute(Object object) {
        List<Object> result = new ArrayList<>();
        if (object instanceof CommunicationMean) {
            CommunicationMean comm = (CommunicationMean) object;
            EObject source = comm.getSourceEntity();
            EObject target = comm.getTargetEntity();

            if (source != null)
                result.add(source);
            if (target != null)
                result.add(target);
        }
        return result;
    }
}
