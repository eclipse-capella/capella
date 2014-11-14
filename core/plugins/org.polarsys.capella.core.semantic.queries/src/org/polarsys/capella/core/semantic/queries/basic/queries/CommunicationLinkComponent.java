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
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof CommunicationLink) {
			CommunicationLink communicationLink = (CommunicationLink) object_p;
			// because communication link parent can only be Component
			EObject container = communicationLink.eContainer();
			if (container instanceof Component) {
			  result.add(container);
      }
		}
		
		return result;
	}

}
