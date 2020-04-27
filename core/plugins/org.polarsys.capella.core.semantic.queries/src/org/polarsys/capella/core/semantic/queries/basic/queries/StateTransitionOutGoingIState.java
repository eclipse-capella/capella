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

import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return Out Going IState(state, mode, etc...) of given StateTransition
 * 
 *
 */
public class StateTransitionOutGoingIState implements IQuery {

  public StateTransitionOutGoingIState() {
    // does nothing
  }
  
  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof StateTransition) {
      // cast
      StateTransition ele = (StateTransition)object;
      // get target value of the stateTransition
      IState target = ele.getTarget();
      // check null value
      if (null != target) {
        result.add(target);
      }
    }
    return result;
  }

}
