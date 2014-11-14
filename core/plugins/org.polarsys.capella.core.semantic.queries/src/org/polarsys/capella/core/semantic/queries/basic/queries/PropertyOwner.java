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

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return the owner of current capella element
 * 
 *
 */
public class PropertyOwner implements IQuery {

	/**
	 * 
	 */
	public PropertyOwner() {
	  // do nothing
	}

	/** 
	 *  
	 * current.eContainer
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof Property) {
		  Property pro = (Property) object_p;
		  if (pro.eClass().getName().equalsIgnoreCase(InformationPackage.Literals.PROPERTY.getName())) {
		    EObject container = pro.eContainer();
	      if (container != null) {
	        result.add(container);        
	      }  
      }
			
		}
		return result;
	}
}
