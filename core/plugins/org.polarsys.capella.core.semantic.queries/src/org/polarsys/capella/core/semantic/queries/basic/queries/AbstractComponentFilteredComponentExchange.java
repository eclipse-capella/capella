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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return incoming  or outgoing component exchanges of given  
 * AbstractActor, System, LC or PC 
 * 
 */
public abstract class AbstractComponentFilteredComponentExchange implements IQuery {

  /** 
   * 
   */
  public AbstractComponentFilteredComponentExchange() {
    // do nothing
  }

  /**
   * current.componentPorts.outgoingFlows
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    
    // get component exchanges for (AbstractActor, System, LC, PC)
    if (isValidComponentForComponentExchanges(object_p)) {
      Component comp = (Component) object_p;
      // collect owned component ports 
      for (ComponentPort port : ComponentExt.getOwnedComponentPort(comp)) {
    	// collect component exchanges 
        EList<ComponentExchange> componentExchanges = port.getComponentExchanges();
        for (ComponentExchange connection : componentExchanges) {
          // collect related ports	
          List<Port> relatedPorts = getRelatedPorts(connection);
          for (Port relatedPort : relatedPorts) {
            if (null != relatedPort && port.equals(relatedPort)) {
              result.add(connection);              
            }  
          }
        }
      }
      
      // looking for connection via part
      // handle connection between parts
      EList<TypedElement> typedElements = comp.getTypedElements();
      for (TypedElement typedElement : typedElements) {
        if (typedElement instanceof Part) {
          Part part = (Part) typedElement;
          EList<AbstractInformationFlow> informationFlows = part.getInformationFlows();
          for (AbstractInformationFlow abstractInformationFlow : informationFlows) {
            if (abstractInformationFlow instanceof ComponentExchange) {
              ComponentExchange connection = (ComponentExchange) abstractInformationFlow;
              List<Part> relatedParts = getRelatedParts(connection);
              for (Part relatedPart : relatedParts) {
                if (null != relatedPart && part.equals(relatedPart)) {
                  result.add(connection);              
                }
              }
            }
          }
          
        }
      }
      
    }
    
    return result;
  }

  /**
   * check for valid exchanges that can have component exchanges
   * 
   * @param object_p
   * @return
   */
  public boolean isValidComponentForComponentExchanges(Object object_p) {
    return object_p instanceof AbstractActor ||
        object_p instanceof org.polarsys.capella.core.data.ctx.System ||
        object_p instanceof LogicalComponent ||
        object_p instanceof PhysicalComponent;
  }
  
  /**
   * 
   * should be implemented by sub class
   * @returns list of ports
   */
  abstract public List<Port> getRelatedPorts(ComponentExchange connection_p); 
  
  /**
   * 
   * should be implemented by sub class
   * @returns list of parts
   */
  abstract public List<Part> getRelatedParts(ComponentExchange connection_p);
  
  /**
   * Return owned Partitionable elements of current element
   * @param object_p
   * @return list of 
   */
  public List<Partition> getOwnedPartitionableElements(Object object_p) {
    List<Partition> ownedPartitions =  new ArrayList<Partition>(0);
    if (null !=  object_p && object_p instanceof PartitionableElement) {
      PartitionableElement element = (PartitionableElement) object_p;
      ownedPartitions.addAll(element.getOwnedPartitions());
    } 
    
    return ownedPartitions;
  }
}
