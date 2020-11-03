/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 */
public class Function_internalIncomingDataflows implements IQuery {

  /**
	 * 
	 */
  public Function_internalIncomingDataflows() {
    //does nothing
  }

  /**
   * Gathering recursively all sub components: current.subFonctions Gathering all dataflows (using current.ownedPartitions.(select FlowPort).incomingFlows)
   * Displaying all internal exchanges
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {

    List<Object> result = new ArrayList<Object>();
    if (object instanceof OperationalActivity) {
      //Handles the case of the operational activities
      result = new OperationalActivity_IncomingInternalInteractions().compute(object);
      return result;
    }

    if (object instanceof AbstractFunction) {
      AbstractFunction sf = (AbstractFunction) object;
      EList<AbstractFunction> subfunctions = sf.getSubFunctions();
      for (AbstractFunction subfunction : subfunctions) {
        EList<InputPin> fpins = subfunction.getInputs();
        for (InputPin fpin : fpins) {
          for (ActivityEdge edge : fpin.getIncoming()) {
            ActivityNode source = edge.getSource();
            if (null == source) {
              continue;
            }
            EObject eContainer = source.eContainer();
            if (null == eContainer) {
              continue;
            }
            if ((!subfunctions.contains(eContainer)) && (eContainer != sf)) {
              result.add(edge);
            }
          }
        }
      }
    }
    return result;
  }
}
