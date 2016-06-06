/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class SystemComponent_incomingInformationflows implements IQuery {

	/**
	 * 
	 */
	public SystemComponent_incomingInformationflows() {
    // do nothing
	}

	/** 
	 * current.componentPorts.incomingFlows
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
	  List<Object> result = new ArrayList<Object>();
    if (object instanceof SystemComponent) {
      SystemComponent sysComp = (SystemComponent) object;
      // looking for connection
      for (ComponentPort port : ComponentExt.getOwnedComponentPort(sysComp)) {
        EList<ComponentExchange> connections = port.getComponentExchanges();
        for (ComponentExchange connection : connections) {
          Port targetPort = ComponentExchangeExt.getTargetPort(connection);
          if (null != targetPort && port.equals(targetPort)) {
              result.add(connection);              
          }
        }
      }
      
      // looking for physicalLinks 
      if (sysComp instanceof PhysicalComponent) {
        PhysicalComponent comp  = (PhysicalComponent) sysComp;
        Collection<PhysicalLink> allRelatedPhysicalLinks = PhysicalLinkExt.getAllRelatedPhysicalLinks(comp);
        for (PhysicalLink physicalLink : allRelatedPhysicalLinks) {
          EObject target = PhysicalLinkExt.getTargetComponent(physicalLink);
          if (null != target && comp.equals(target)) {
            result.add(physicalLink);
          }
        }
      }
      
      // retrieve all the information flow related to Component typed elements
      EList<TypedElement> typedElements = sysComp.getTypedElements();
      for (TypedElement typedElement : typedElements) {
        if (typedElement instanceof Part) {
          Part part = (Part) typedElement;
          EList<AbstractInformationFlow> informationFlows = part.getInformationFlows();
          for (AbstractInformationFlow abstractInformationFlow : informationFlows) {
            if (abstractInformationFlow instanceof ComponentExchange) {
              ComponentExchange conn = (ComponentExchange) abstractInformationFlow;
              Part targetPart = ComponentExchangeExt.getTargetPart(conn);
              if (null != targetPart && part.equals(targetPart)) {
                result.add(conn);              
              }
            }
          }
          
        }
      }
      
    }
    
   
    
    return result;
	}
}
