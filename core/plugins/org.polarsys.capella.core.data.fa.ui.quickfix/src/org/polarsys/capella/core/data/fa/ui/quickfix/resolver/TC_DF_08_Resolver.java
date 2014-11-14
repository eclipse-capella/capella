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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

/**
 * Delete Component Exchange of kind DELEGATION
 */
public class TC_DF_08_Resolver extends AbstractDeleteCommandResolver {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj_p) {
    if (obj_p instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) obj_p;
      ComponentExchangeKind kind = exchange.getKind();
      if ((null != kind) && kind.equals(ComponentExchangeKind.DELEGATION)) {
        return obj_p;
      }
    }
    return null;
  }
  

}
