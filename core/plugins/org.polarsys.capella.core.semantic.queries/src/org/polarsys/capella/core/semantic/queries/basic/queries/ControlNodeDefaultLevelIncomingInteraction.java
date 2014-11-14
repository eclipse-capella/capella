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

import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query allows to get the outgoing interactions from a control node of default levels
 * 
 */
public class ControlNodeDefaultLevelIncomingInteraction implements IQuery {

  /**
	 * 
	 */
  public ControlNodeDefaultLevelIncomingInteraction() {
    // Does nothing
  }

  /**
   * current.ownedFlowPorts.outgoingFlows
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    return result;
  }
}
