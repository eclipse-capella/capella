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

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.common.helpers.query.IQuery;


/**
 * Allows to get the allocation blocks (OperationalActor, Actor, LA, PA) of an abstract function.
 * 
 * 
 */
public class FunctionAllocatingActor   implements IQuery{

  public FunctionAllocatingActor() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof OperationalActivity) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof OperationalActor) {
          result.add(object);
        }
      }
    }else  if (object_p instanceof SystemFunction) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof Actor) {
          result.add(object);
        }
      }
      
    } else if (object_p instanceof LogicalFunction) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof LogicalActor) {
          result.add(object);
        }
      }
    }else if (object_p instanceof PhysicalFunction ) {
      for (Object object : AbstractFunctionExt.getAllocationBlocks(object_p)) {
        if (object instanceof PhysicalActor) {
          result.add(object);
        }
      }
    }
    
    return result;
  }
  
}
