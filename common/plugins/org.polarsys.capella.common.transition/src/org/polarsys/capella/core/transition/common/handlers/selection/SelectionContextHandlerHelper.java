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

package org.polarsys.capella.core.transition.common.handlers.selection;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SelectionContextHandlerHelper {

  public static ISelectionContextsHandler getHandler(IContext context) {
    ISelectionContextsHandler handler = (ISelectionContextsHandler) context.get(ITransitionConstants.SELECTION_CONTEXTS_HANDLER);
    if (handler == null) {
      handler = new DefaultSelectionContextsHandler();
      handler.init(context);
      context.put(ITransitionConstants.SELECTION_CONTEXTS_HANDLER, handler);
    }
    return handler;
  }

}
