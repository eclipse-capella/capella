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

import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.query.IQuery;


/**
 * Return outgoing or incoming communication means of current entity
 * 
 *
 */
public abstract class AbsEntityCommunicationMean  implements IQuery {

	public AbsEntityCommunicationMean() {
		// does nothing
	}

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Entity) {
      Entity entity = (Entity) object;
      // collect Information flows
      List<AbstractInformationFlow> flows = getInformationFlows(entity);
      // retrieve communication mean collected information flows and add to result
      for (AbstractInformationFlow flow : flows) {
        if (flow instanceof CommunicationMean) {
          result.add(flow);
        }
      }

    }
    return result;
  }
	
	/**
	 * Get outgoing or incoming informationFlow
	 * @param entity
	 * @return
	 */
	abstract public List<AbstractInformationFlow> getInformationFlows(Entity entity);
}
