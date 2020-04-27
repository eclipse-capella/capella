/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class ExchangeSpecification_owner implements IQuery {

	/**
	 * 
	 */
	public ExchangeSpecification_owner() {
	  // do nothing
	}

	/** 
	 *  
	 * current.ownerStructure
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof FunctionalExchangeSpecification) {
			FunctionalExchangeSpecification e = (FunctionalExchangeSpecification) object;
	     EObject container = e.eContainer();
      if(null != container)
        result.add(container);
		}
		else if (object instanceof ComponentExchange) {
		  ComponentExchange e = (ComponentExchange) object;
			EObject container = e.eContainer();
			if(null != container)
      result.add(container);
		}
		return result;
	}
}
