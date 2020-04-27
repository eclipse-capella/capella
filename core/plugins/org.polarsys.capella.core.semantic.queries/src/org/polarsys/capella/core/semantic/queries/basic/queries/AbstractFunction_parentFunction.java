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
	public List<Object> compute(Object object) {
		
		List<Object> result = new ArrayList<Object>();
		if (object instanceof AbstractFunction) {
		  AbstractFunction function = (AbstractFunction) object;
		  CapellaElement element = getParent(function);
		  if (null != element && element instanceof AbstractFunction) result.add(element);
		}
		return result;
	}

	/**
	 * @param function
	 * @return
	 */
	private CapellaElement getParent(EObject function) {
		EObject eContainer = function.eContainer();
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
