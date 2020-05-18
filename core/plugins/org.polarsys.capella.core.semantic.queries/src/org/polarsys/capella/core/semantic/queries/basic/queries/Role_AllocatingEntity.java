/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Returns Allocating Entities of current Role
 */
public class Role_AllocatingEntity implements IQuery {

  public Role_AllocatingEntity() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Role) {
      Role e = (Role) object;
      EList<AbstractTrace> traces = e.getIncomingTraces();
      for (AbstractTrace abstractTrace : traces) {
        if (abstractTrace instanceof RoleAllocation) {
          RoleAllocation link = (RoleAllocation) abstractTrace;
          Entity element = link.getEntity();
          if (null != element) {
            result.add(element);
          }
        }
      }
    }
    return result;
  }
}
