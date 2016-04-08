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
import java.util.List;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;

/**
 * Return incoming component exchanges of given  AbstractActor, System, LC or PC 
 * 
 */
public class ComponentIncomingComponentExchange extends AbstractComponentFilteredComponentExchange{

  /** 
   * 
   */
  public ComponentIncomingComponentExchange() {
    // do nothing
  }

  /**
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    
    if (isValidComponentForComponentExchanges(object)) {
    // get the filtered component exchange by kinds
    result = ComponentExchangeExt.filteredComponentExchangesBykind(super.compute(object),
        new ComponentExchangeKind[]{ComponentExchangeKind.ASSEMBLY,ComponentExchangeKind.UNSET,ComponentExchangeKind.FLOW});
    }
    
    // return empty list of non null elements
    if(result.isEmpty()) return new ArrayList<Object>(0);
    
    return result;
  }

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractComponentFilteredComponentExchange#getRelatedParts(org.polarsys.capella.core.data.fa.Connection)
   */
  @Override
  public List<Part> getRelatedParts(ComponentExchange connection) {
    List<Part> result = new ArrayList<Part>(0);
    Part part = ComponentExchangeExt.getTargetPart(connection);
    if (null != part) {
      result.add(part);
    }
    
    return result;
  }

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractComponentFilteredComponentExchange#getRelatedPorts(org.polarsys.capella.core.data.fa.Connection)
   */
  @Override
  public List<Port> getRelatedPorts(ComponentExchange connection) {
    List<Port> result = new ArrayList<Port>(0);
    Port port = ComponentExchangeExt.getTargetPort(connection);
    if (null != port) {
      result.add(port);
    }
    
    return result;
  }
}
