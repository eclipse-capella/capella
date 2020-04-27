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

import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Returns Involving Capability of current Entity
 */
public class OperationalEntityInvolgingOperationalCap implements IQuery {

  public OperationalEntityInvolgingOperationalCap() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Entity) {
      Entity e = (Entity) object;
      EList<Involvement> involvingInvolvements = e.getInvolvingInvolvements();
      for (Involvement abstractTrace : involvingInvolvements) {
        if (abstractTrace instanceof EntityOperationalCapabilityInvolvement) {
          EntityOperationalCapabilityInvolvement link = (EntityOperationalCapabilityInvolvement) abstractTrace;
          OperationalCapability capability = link.getCapability();
          if (null != capability) {
            result.add(capability);
          }
        }
      }
    }
    return result;
  }
}
