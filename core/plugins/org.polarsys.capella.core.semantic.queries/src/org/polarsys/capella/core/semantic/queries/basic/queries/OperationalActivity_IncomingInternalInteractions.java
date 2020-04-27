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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * * Gets the internal incoming interactions of an <code>OperationalActivity</code>
 * 
 */
public class OperationalActivity_IncomingInternalInteractions implements IQuery {

  public OperationalActivity_IncomingInternalInteractions() {
    // does nothing
  }

  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) object;
      EList<AbstractFunction> subfunctions = oa.getSubFunctions();
      for (AbstractFunction subfunction : subfunctions) {
        for (ActivityEdge edge : subfunction.getIncoming()) {
          if ((!subfunctions.contains(edge.getSource().eContainer())) && (edge.getSource().eContainer() != oa)) {
            result.add(edge);
          }
        }
      }
    }
    return result;
  }
}
