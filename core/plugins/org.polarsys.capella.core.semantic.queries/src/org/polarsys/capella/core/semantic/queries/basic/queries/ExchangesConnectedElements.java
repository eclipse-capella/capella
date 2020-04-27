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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 * Return all the source and Target of the Exchanges(Connection, FunctionalExchanges)
 *
 */
public class ExchangesConnectedElements implements IQuery {

	/** 
	 * 
	 */
	public ExchangesConnectedElements() {
	  // do nothing
	}

	/**
	 * 
	 * ownedCommunicationEnds.connectedPart(select "Standard Port").ownerElement (select "Physical Component" AND "not current Component") 
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof ComponentExchange) {
		  ComponentExchange exchange = (ComponentExchange) object_p;
		  
		  // Retrieve Components
		  EObject source = getRelated(exchange.getSource());
      EObject target = getRelated(exchange.getTarget());
			
      if (source!=null) result.add(source);
      if (target!=null) result.add(target);
		}else if (object_p instanceof FunctionalExchange) {
		  List<Object> list = new ArrayList<Object>(1);
		  FunctionalExchange exchange = (FunctionalExchange) object_p;
		  // Retrieve direct source of exchange
		  FunctionalExchange_dataflowSource sourceEle = new FunctionalExchange_dataflowSource();
		  List<Object> dataFlowSources = sourceEle.compute(exchange);
		  if (!dataFlowSources.isEmpty()) {
        list.addAll(dataFlowSources);
      }
      // Retrieve direct target of exchange
      FunctionalExchange_dataflowTarget targetEle = new FunctionalExchange_dataflowTarget();
      List<Object> dataFlowTargetes = targetEle.compute(exchange);
      if (!dataFlowTargetes.isEmpty()) {
        list.addAll(dataFlowTargetes);
      }
		  
      // Retrieve Functions
      for (Object object : list) {
        if (object instanceof ActivityNode && !(object instanceof OperationalActivity)) {
          ActivityNode node = (ActivityNode) object;
          EObject container = node.eContainer();
          if (null != container) result.add(container);
        }else if (object instanceof OperationalActivity) {
          result.add(object);
        }
      }

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
    }else if (bound_p instanceof Entity) {
      return bound_p;
    }
    
    return null;
	}
}
