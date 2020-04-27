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

package org.polarsys.capella.common.re.handlers.attributes;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AttributesHandlerHelper {

  public static IAttributeHandler getInstance(IContext context) {
    if (!context.exists(IReConstants.ATTRIBUTE_HANDLER)) {
      IHandler handler = new DefaultAttributeHandler();
      context.put(IReConstants.ATTRIBUTE_HANDLER, handler);
    }
    return (IAttributeHandler) context.get(IReConstants.ATTRIBUTE_HANDLER);
  }
}
