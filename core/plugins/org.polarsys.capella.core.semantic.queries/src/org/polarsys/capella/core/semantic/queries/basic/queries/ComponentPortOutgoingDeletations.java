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

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;

/**
 * Return Deletation of current Component Port
 *
 */
public class ComponentPortOutgoingDeletations extends AbstractComponentPortOutgoingComponentExchanges{

	/**
	 * 
	 */
	public ComponentPortOutgoingDeletations() {
    // do nothing
	}

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractComponentPortIncomingComponentExchanges#isValidComponentExchange(org.polarsys.capella.core.data.fa.ComponentExchange)
   */
  @Override
  public boolean isValidComponentExchange(ComponentExchange exchange) {
    if (null != exchange) {
      if (exchange.getKind() == ComponentExchangeKind.DELEGATION)return true;
    }
    return false;
  }
}
