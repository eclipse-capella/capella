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

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 * Return parent(a function) of current function
 *
 */
public class AbstractFunction_parentFunction implements IQuery {

	/**
	 * 
	 */
	public AbstractFunction_parentFunction() {
    // do nothing
	}

	/**
	 * 
	 * owner
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof AbstractFunction) {
		  AbstractFunction function = (AbstractFunction) object_p;
		  CapellaElement element = getParent(function);
		  if (null != element && element instanceof AbstractFunction) result.add(element);
		}
		return result;
	}

	/**
	 * @param function_p
	 * @return
	 */
	private CapellaElement getParent(EObject function_p) {
		EObject eContainer = function_p.eContainer();
		if(null != eContainer){ 
			if (eContainer instanceof AbstractFunction ){
				return (CapellaElement) eContainer;
			}else if (eContainer instanceof FunctionPkg){
				return getParent(eContainer);
			}
		}
		
		return null;
	}

}
