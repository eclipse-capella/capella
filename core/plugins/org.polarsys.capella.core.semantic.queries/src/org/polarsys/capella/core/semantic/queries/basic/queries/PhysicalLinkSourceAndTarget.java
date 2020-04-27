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

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query is about PhysicalLink --> SourceComponent and TargetComponent
 *
 */
public class PhysicalLinkSourceAndTarget implements IQuery {

	/**
	 * 
	 */
	public PhysicalLinkSourceAndTarget() {
    // do nothing
	}

	/** 
	 *  
	 * current.flowSource.ownerElement
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof PhysicalLink) {
		  PhysicalLink link = (PhysicalLink) object;
		  EObject source = PhysicalLinkExt.getSourceComponent(link);
		  if (null != source){ 
			result.add(source);
		  }
		  EObject target = PhysicalLinkExt.getTargetComponent(link);
		  if (null != target){ 
			result.add(target);
		  }	
		}
		return result;
	}
}
