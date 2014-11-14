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

import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return In Coming IState(state, mode, etc...)  of given StateTransition
 * 
 *
 */
public class StateTransitionInComingIState implements IQuery {

  public StateTransitionInComingIState() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof StateTransition) {
      // cast
      StateTransition ele = (StateTransition)object_p;
      // get source value of the stateTransition
      IState source = ele.getSource();
      // check null value
      if (null != source) {
        result.add(source);
      }
    }
    return result;
  }

}
