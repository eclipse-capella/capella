/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query returns  communicationLink target exchangeItem
 * 
 * 
 */
public class CommunicationLinkComponent implements IQuery {

	public CommunicationLinkComponent() {
		// does nothing
	}

	/**
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof CommunicationLink) {
			CommunicationLink communicationLink = (CommunicationLink) object;
			// because communication link parent can only be Component
			EObject container = communicationLink.eContainer();
			if (container instanceof Component) {
			  result.add(container);
      }
		}
		
		return result;
	}

}
