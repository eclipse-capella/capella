/*******************************************************************************
 * Copyright (c) 2022 OBEO.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - 2304 In Semantic Browser, add Involved Activities category for Operational Capability
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * Return involving capabilities of current operational activity
 * 
 * 
 */
public class OperationalActivityInvolvingCapabilities implements IQuery {

  /**
   * 
   */
  public OperationalActivityInvolvingCapabilities() {
    // Does nothing
  }

  /**
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>(0);
    if (object instanceof OperationalActivity) {
      AbstractFunction sf = (AbstractFunction) object;
      EList<Involvement> involvments = sf.getInvolvingInvolvements();
      for (Involvement involvement : involvments) {
        if (involvement instanceof AbstractFunctionAbstractCapabilityInvolvement) {
          InvolverElement involver = involvement.getInvolver();
          if (involver instanceof OperationalCapability) {
            result.add(involver);
          }
        }
      }
    }

    return result;
  }
}
