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
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof FunctionalExchangeSpecification) {
			FunctionalExchangeSpecification e = (FunctionalExchangeSpecification) object_p;
	     EObject container = e.eContainer();
      if(null != container)
        result.add(container);
		}
		else if (object_p instanceof ComponentExchange) {
		  ComponentExchange e = (ComponentExchange) object_p;
			EObject container = e.eContainer();
			if(null != container)
      result.add(container);
		}
		return result;
	}
}
