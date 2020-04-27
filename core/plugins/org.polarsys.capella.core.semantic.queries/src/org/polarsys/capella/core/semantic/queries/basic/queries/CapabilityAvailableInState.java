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

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return the states available in current AbstractCapability element
 *
 */
public class CapabilityAvailableInState implements IQuery {

	public CapabilityAvailableInState() {
    // do nothing
	}

	/**
	 * 
	 * current.availableInSates(select "State" not "Mode")
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof AbstractCapability) {
      AbstractCapability element = (AbstractCapability) object;
      EList<State> availableInStates = element.getAvailableInStates();
      for (State abstractStateMode : availableInStates) {
        if (!(abstractStateMode instanceof Mode)) {
          result.add(abstractStateMode);
        }
      }
    }
    return result;
	}
}
