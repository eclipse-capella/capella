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

import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.common.helpers.query.IQuery;


/**
 * Allows to get the allocation blocks (Entity(!OpeartionalActor), System, LC, PC) of an abstract function.
 * 
 * 
 */
public class FunctionAllocatingComponent implements IQuery {

  public FunctionAllocatingComponent() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof OperationalActivity) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof Entity && !(object instanceof OperationalActor)) {
          result.add(object);
        }
      }
    }else  if (object_p instanceof SystemFunction) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof System) {
          result.add(object);
        }
      }
      
    }else if (object_p instanceof LogicalFunction) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof LogicalComponent) {
          result.add(object);
        }
      }
    }else if (object_p instanceof PhysicalFunction) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof PhysicalComponent) {
          result.add(object);
        }
      }
    }
    
    return result;
  }
  
}
