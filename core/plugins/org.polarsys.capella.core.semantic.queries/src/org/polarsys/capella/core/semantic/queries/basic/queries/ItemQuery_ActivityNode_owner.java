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

import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 */
public class ItemQuery_ActivityNode_owner implements IQuery {

  /**
	 * 
	 */
  public ItemQuery_ActivityNode_owner() {
    //Does nothing
  }

  /**
   * 
   * (source and target).owner
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    //This query musn't return any data if the object is an operational activity
    if (object instanceof ActivityNode && !(object instanceof OperationalActivity)) {
      ActivityNode an = (ActivityNode) object;
      result.add(an.eContainer());
    }
    return result;
  }
}
