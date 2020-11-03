/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;

/**
 * Return outgoing Component Exchanges and Delegation of current Component Port
 *
 */
public abstract class AbstractComponentPortOutgoingComponentExchanges extends AbstractComponentPortComponentExchanges{

	/**
	 * 
	 */
	public AbstractComponentPortOutgoingComponentExchanges() {
    // do nothing
	}
	
	 /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractComponentPortComponentExchanges#getInformationFlows(org.polarsys.capella.core.data.fa.ComponentPort)
   */
  @Override
  public List<AbstractInformationFlow> getInformationFlows(ComponentPort port) {
    if (null != port) {
      return port.getOutgoingInformationFlows();
    }
    return new ArrayList<AbstractInformationFlow>();
  }
}
