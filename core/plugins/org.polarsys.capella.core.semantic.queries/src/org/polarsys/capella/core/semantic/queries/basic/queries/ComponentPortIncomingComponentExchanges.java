/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;

/**
 * Return Component Exchanges of current Component Port
 *
 */
public class ComponentPortIncomingComponentExchanges extends AbstractComponentPortIncomingComponentExchanges{

	/**
	 * 
	 */
	public ComponentPortIncomingComponentExchanges() {
    // do nothing
	}

  /**
   * @see org.polarsys.capella.core.semantic.queries.basic.queries.AbstractComponentPortIncomingComponentExchanges#isValidComponentExchange(org.polarsys.capella.core.data.fa.ComponentExchange)
   */
  @Override
  public boolean isValidComponentExchange(ComponentExchange exchange) {
    if (null != exchange) {
      if (exchange.getKind() != ComponentExchangeKind.DELEGATION)return true;
    }
    return false;
  }
}
