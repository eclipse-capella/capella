/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;

/**
 * Return incoming delegation of given  AbstractActor, System, LC or PC 
 * 
 */
public class ComponentIncomingDelegation  extends AbstractComponentFilteredComponentExchange{

  @Override
  protected boolean isValid(ComponentExchange exchange, Object object) {
    return object.equals(ComponentExchangeExt.getTargetComponent(exchange)) && ComponentExchangeExt.isDelegation(exchange);
  }

}
