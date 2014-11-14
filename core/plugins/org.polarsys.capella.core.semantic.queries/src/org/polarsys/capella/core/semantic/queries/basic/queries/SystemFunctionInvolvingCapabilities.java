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

import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return involving capabilities of current system function
 * 
 * 
 */
public class SystemFunctionInvolvingCapabilities implements IQuery {

  /**
	 * 
	 */
  public SystemFunctionInvolvingCapabilities() {
    // Does nothing
  }

  /**
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>(0);
    if (object_p instanceof SystemFunction ) {
      AbstractFunction sf = (AbstractFunction) object_p;
      EList<Involvement> involvments = sf.getInvolvingInvolvements();
      for (Involvement involvement : involvments) {
        if (involvement instanceof AbstractFunctionAbstractCapabilityInvolvement) {
          InvolverElement involver = involvement.getInvolver();
          if (involver instanceof Capability) {
            result.add(involver);
          }
        }
      }
    }
    
    return result;
  }
}
