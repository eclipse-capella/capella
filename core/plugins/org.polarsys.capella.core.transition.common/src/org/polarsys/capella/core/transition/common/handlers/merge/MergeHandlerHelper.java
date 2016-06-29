/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.handlers.merge;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Returns the MergeHandler
 */
public class MergeHandlerHelper {

  public static IMergeHandler getInstance(IContext context) {
    IMergeHandler handler = (IMergeHandler) context.get(ITransitionConstants.MERGE_DIFFERENCES_HANDLER);
    if (handler == null) {
      handler = new DefaultMergeHandler();
      handler.init(context);
      context.put(ITransitionConstants.MERGE_DIFFERENCES_HANDLER, handler);
    }
    return handler;
  }

}
