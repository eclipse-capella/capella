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
package org.polarsys.capella.common.re.handlers.attributes;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AttributesHandlerHelper {

  public static IAttributeHandler getInstance(IContext context_p) {
    if (!context_p.exists(IReConstants.ATTRIBUTE_HANDLER)) {
      IHandler handler = new DefaultAttributeHandler();
      context_p.put(IReConstants.ATTRIBUTE_HANDLER, handler);
    }
    return (IAttributeHandler) context_p.get(IReConstants.ATTRIBUTE_HANDLER);
  }
}
