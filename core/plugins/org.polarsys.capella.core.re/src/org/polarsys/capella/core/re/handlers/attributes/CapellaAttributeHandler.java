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
package org.polarsys.capella.core.re.handlers.attributes;

import org.polarsys.capella.common.re.handlers.attributes.DefaultAttributeHandler;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapellaAttributeHandler extends DefaultAttributeHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSuffixableElement(Object object_p, IContext context_p) {
    //avoid suffix on Exchanges
    if ((object_p instanceof FunctionalExchange) || (object_p instanceof ComponentExchange) || (object_p instanceof PhysicalLink)) {
      return false;
    }
    return super.isSuffixableElement(object_p, context_p);
  }
}
