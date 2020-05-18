/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.handlers.traceability;

import org.polarsys.capella.core.projection.common.constants.ITransitionConstants;
import org.polarsys.capella.core.projection.common.context.IContext;

/**
 */
public class TraceabilityHandlerHelper {

  public static ITraceabilityHandler getInstance(IContext context) {
    ITraceabilityHandler handler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_HANDLER);
    if (handler == null) {
      handler = new TraceabilityByLinksHandler();
      handler.init(context);
      context.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);
    }
    return handler;
  }

}
