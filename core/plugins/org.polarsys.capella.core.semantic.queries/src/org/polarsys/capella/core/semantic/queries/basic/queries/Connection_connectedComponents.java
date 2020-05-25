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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Connection_connectedComponents implements IQuery {

	/** 
	 * 
	 */
	public Connection_connectedComponents() {
	  // do nothing
	}

	/**
	 * 
	 * ownedCommunicationEnds.connectedPart(select "Standard Port").ownerElement (select "Physical Component" AND "not current Component") 
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof ComponentExchange) {
		  ComponentExchange comm = (ComponentExchange) object;
		  EObject source = getRelated(comm.getSource());
      EObject target = getRelated(comm.getTarget());
			
      if (source!=null) result.add(source);
      if (target!=null) result.add(target);
		}
		return result;
	}
	
	private EObject getRelated(EObject bound_p) {
    if (bound_p instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd bound = (ComponentExchangeEnd)bound_p;
      if (bound.getPart() != null && bound.getPart() instanceof Part) {
        Part part = (Part)bound.getPart();
        if (part.getType() !=null && part.getType() instanceof Component) {
          return (part.getType());
        }
      }
    } else if (bound_p instanceof ComponentPort) {
      return (bound_p.eContainer());
    }else if (bound_p instanceof Part) {
      Part part = (Part)bound_p;
      if (part.getType() !=null && part.getType() instanceof Component) {
        return (part.getType());
      }
    }
    
    return null;
	}
}
