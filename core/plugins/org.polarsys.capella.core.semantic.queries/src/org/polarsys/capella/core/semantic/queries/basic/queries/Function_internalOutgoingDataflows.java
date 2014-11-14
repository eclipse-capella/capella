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
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 */
public class Function_internalOutgoingDataflows implements IQuery {

  public Function_internalOutgoingDataflows() {
    // Does nothing
  }

  /**
   * Gathering recursively all sub components: subFonctions Gathering all exchanges (using FlowPorts.outgoingFlows) Displaying all internal exchanges
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {

    List<Object> result = new ArrayList<Object>();
    // handle operational activity
    if (object_p instanceof OperationalActivity) {
      return new OperationalActivity_OutgoingInternalInteraction().compute(object_p);
    }

    if (object_p instanceof AbstractFunction) {
      AbstractFunction sf = (AbstractFunction) object_p;
      EList<AbstractFunction> subfunctions = sf.getSubFunctions();
      for (AbstractFunction subfunction : subfunctions) {
        EList<OutputPin> fpins = subfunction.getOutputs();
        for (OutputPin fpin : fpins) {
          for (ActivityEdge edge : fpin.getOutgoing()) {
            ActivityNode target = edge.getTarget();
            if (target != null) {
              EObject eContainer = target.eContainer();
              if (eContainer != null) {
                if ((!subfunctions.contains(eContainer)) && (eContainer != sf)) {
                  result.add(edge);
                }   
              }
            }
            
          }
        }
      }
    }
    return result;
  }
}
